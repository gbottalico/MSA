package msa.application.service.documenti;

import msa.application.commons.Constants;
import msa.application.config.BaseDTO;
import msa.application.config.enumerator.ContentTypePairs;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.documenti.DocumentoDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.domain.object.documenti.DocumentoDO;
import msa.infrastructure.repository.DocumentiRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by simon.calabrese on 11/08/2017.
 */
@Service
public class DocumentiService extends BaseService {

    @Autowired
    private DocumentiRepository documentiRepository;

    /**
     * Metodo per il caricamento su cartella remota e su DB Mongo del file passato in input.
     *
     * @param file
     * @param numSinistro
     * @param codTipoDoc
     * @param userHeader
     * @param dataRicezione
     * @return
     * @throws InternalMsaException
     */
    public BaseDTO<DocumentoDTO> uploadDocumento(MultipartFile file, Integer numSinistro, Integer codTipoDoc, String userHeader, Optional<Date> dataRicezione) throws InternalMsaException {
        final String path = saveFileOnDirectory(file, numSinistro);
        try {
            DocumentoDTO documentoDTO = new DocumentoDTO();
            documentoDTO.setCodTipoDocumento(codTipoDoc);
            documentoDTO.setDataRicezione(dataRicezione.orElseGet(Date::new));
            documentoDTO.setIdDocumento(documentiRepository.getNextIdDoc());
            documentoDTO.setNumSinistro(numSinistro);
            documentoDTO.setPath(path);
            documentoDTO.parseUserLogged(userHeader);
            if (documentiRepository.insertDocumento(converter.convertObject(documentoDTO, DocumentoDO.class))) {
                return new BaseDTO<>(documentoDTO);
            } else {
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA007"));
            }
        } catch (InternalMsaException e) {
            rollbackDocumento(path);
            throw e;
        } catch (Exception e) {
            rollbackDocumento(path);
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA007"));
        }
    }

    /**
     * Procedura di rollback salvataggio documento.
     * Se la cartella contenente il file ha solo quello allora eliminiamo anche la cartella
     *
     * @param pathAsString
     * @throws InternalMsaException
     */
    private void rollbackDocumento(final String pathAsString) throws InternalMsaException {
        try {
            if (!pathAsString.equalsIgnoreCase(properties.getPathDocumenti())) {
                DocumentiService.deleteByPath(pathAsString);
                rollbackDocumento(splitAndParent(pathAsString));
            }
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA007"));
        }

    }

    private String splitAndParent(final String pathAsString) {
        final String[] split = pathAsString.split("\\\\");
        return Arrays.stream(split).limit((long) (split.length - 1)).collect(Collectors.joining("\\"));
    }

    private static void deleteByPath(final String pathAsString) throws IOException {
        Path path = Paths.get(pathAsString);
        Files.delete(path);
    }

    private void rollbackDocumento(final String pathAsString, final Boolean withDirectorty) throws InternalMsaException {
        try {
            if (withDirectorty) {
                rollbackDocumento(pathAsString);
            } else {
                DocumentiService.deleteByPath(pathAsString);
            }
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA007"));
        }
    }

    public BaseDTO deleteDocFromFileSystem(final Integer idDoc) throws InternalMsaException {
        DocumentoDO documentoDO = documentiRepository.find(idDoc);
        deleteDocFromFileSystem(documentoDO, Boolean.TRUE);
        return new BaseDTO<>();
    }

    private void deleteDocFromFileSystem(final DocumentoDO documentoDO, final Boolean withDirectorty) throws InternalMsaException {
        if (documentiRepository.deleteDoc(documentoDO)) {
            rollbackDocumento(documentoDO.getPath(), withDirectorty);
        }
    }

    public Boolean deleteDocByNumProvv(final Integer numSinistroProvv) throws InternalMsaException {
        final List<DocumentoDO> documenti = documentiRepository.deleteDocByNumSinistro(numSinistroProvv);
        final DocumentoDO documentoDO = documenti.stream().findFirst().orElse(null);
        for (DocumentoDO d : documenti) {
            deleteDocFromFileSystem(d, Boolean.FALSE);
        }
        deleteDocFromFileSystem(converter.enrichObject(documentoDO, (DocumentoDO e) -> {
            e.setPath(splitAndParent(e.getPath()));
            return e;
        }), Boolean.TRUE);
        return Boolean.TRUE;
    }

    /**
     * Metodo di scrittura del file sulla directory in properties
     * Se la directory non esiste viene create e poi viene creato il file con la seguente sintassi:
     * nomeFileCaricato + data e ora + estensione del file
     * vedi {@code getPathWithExtensionFile}
     *
     * @param file
     * @param numSinistro
     * @return
     * @throws InternalMsaException
     */
    private String saveFileOnDirectory(final MultipartFile file, final Integer numSinistro) throws InternalMsaException {
        try {
            if (!Files.exists(Paths.get(properties.getPathDocumenti())))
                Files.createDirectories(Paths.get(properties.getPathDocumenti()));

            if (!Files.exists(Paths.get(properties.getPathDocumenti() + "\\" + numSinistro + "\\"))) {
                Files.createDirectories(Paths.get(properties.getPathDocumenti() + "\\" + numSinistro + "\\"));
            }
            final String pathAsString = properties.getPathDocumenti()
                    + "\\" + numSinistro
                    + "\\" + getPathWithExtensionFile(file.getOriginalFilename(),
                    Optional.of(new Date()));
            final Path pathFile = Paths.get(pathAsString);
            Files.write(pathFile, file.getBytes());
            return pathAsString;
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA006"));
        }
    }

    /**
     * Metodo per la creazione del path del file con la sintassi:
     * nomeFileCaricato + data e ora + estensione del file
     *
     * @param originalFileName
     * @param dateToConcat
     * @return
     */
    private String getPathWithExtensionFile(final String originalFileName, final Optional<Date> dateToConcat) {
        final String extension = FilenameUtils.getExtension(originalFileName);
        final Function<String, String> dateInStringPlusEstension = dateAsString -> dateAsString + "." + FilenameUtils.getExtension(originalFileName);
        final Function<String, String> completePath = dateAndExtension -> originalFileName.substring(0, originalFileName.indexOf(extension) - 1) + dateAndExtension;
        final Function<Date, String> DATE_TO_PATH = Constants.DATE_TO_STRING_DOT.andThen(dateInStringPlusEstension).andThen(completePath);
        return dateToConcat.map(DATE_TO_PATH).orElse(null).replace(" ", "_");

    }

    public BaseDTO<List<DocumentoDTO>> getListaDocumenti(final Integer numSinistro) {
        return new BaseDTO<>(converter.convertList(documentiRepository.getListaDocumenti(numSinistro), DocumentoDTO.class));
    }

    public ResponseEntity getDocumento(final Integer idDoc) throws InternalMsaException {
        DocumentoDO documentoDO = documentiRepository.find(idDoc);
        String contentType = ContentTypePairs.getContentTypeByExtension(FilenameUtils.getExtension(documentoDO.getPath()));
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(new InputStreamResource(new FileInputStream(new File(documentoDO.getPath()))));
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA008"));
        }
    }

    public void persistDocsMsa(final List<String> idDocsMsa, final Integer numSinistroProvv) {
        idDocsMsa.parallelStream().map(id -> {
            DocumentoDO documentoDO = new DocumentoDO();
            documentoDO.setIdDocumentoMsa(id);
            documentoDO.setNumSinistro(numSinistroProvv);
            return documentoDO;
        }).forEach(e -> documentiRepository.persistDocsMsa(e));
    }
}
