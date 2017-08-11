package msa.application.service.documenti;

import msa.application.commons.Constants;
import msa.application.config.BaseDTO;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.documenti.DocumentoDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.domain.object.documenti.DocumentoDO;
import msa.infrastructure.repository.DocumentiRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by simon.calabrese on 11/08/2017.
 */
@Service
public class DocumentiService extends BaseService {

    @Autowired
    private DocumentiRepository documentiRepository;

    public BaseDTO uploadDocumento(MultipartFile file, Integer numSinistro, Integer codTipoDoc, String userHeader) throws InternalMsaException {
        final String path = saveFileOnDirectory(file, numSinistro);
        try {
            DocumentoDTO documentoDTO = new DocumentoDTO();
            documentoDTO.setCodTipoDocumento(codTipoDoc);
            documentoDTO.setDataRicezione(new Date());
            documentoDTO.setIdDocumento(documentiRepository.getNextIdDoc());
            documentoDTO.setNumSinistro(numSinistro);
            documentoDTO.setPath(path);
            documentoDTO.parseUserLogged(userHeader);
            if (documentiRepository.insertDocumento(converter.convertObject(documentoDTO, DocumentoDO.class))) {
                return new BaseDTO<>();
            } else {
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA007"));
            }
        } catch (InternalMsaException e) {
            rollbackDocumento(path);
            throw e;
        } catch (Exception e) {
            rollbackDocumento(path);
            throw new InternalMsaException(e,getErrorMessagesByCodErrore(MessageType.ERROR, "MSA007"));
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
            Path path = Paths.get(pathAsString);
            Files.delete(path);
            if (!Files.newDirectoryStream(path.getParent()).iterator().hasNext()) {
                Files.delete(path.getParent());
            }
        } catch (Exception e) {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA007"));
        }

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
            if (!Files.exists(Paths.get(properties.getPathDocumenti() + "\\" + numSinistro + "\\"))) {
                Files.createDirectory(Paths.get(properties.getPathDocumenti() + "\\" + numSinistro + "\\"));
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
        return dateToConcat.map(DATE_TO_PATH).orElse(null);

    }
}
