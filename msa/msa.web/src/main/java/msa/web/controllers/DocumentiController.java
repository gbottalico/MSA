package msa.web.controllers;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.dto.documenti.DocumentoDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.documenti.DocumentiService;
import msa.web.api.BaseController;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * Created by simon.calabrese on 11/08/2017.
 */
@RestController
@RequestMapping(value = "/api/documenti")
public class DocumentiController extends BaseController {

    @Autowired
    private DocumentiService documentiService;

    @ApiOperation(value = "Metodo che effettua il caricamento di un file")
    @RequestMapping(value = "/{numeroSinistro}/{codTipoDoc}/upload", method = RequestMethod.POST)
    public BaseDTO uploadDocumenti(@RequestParam("file") final MultipartFile file,
                                   @PathVariable("numeroSinistro") final Integer numSinistro,
                                   @PathVariable("codTipoDoc") final Integer codTipoDoc,
                                   @RequestHeader(value = "user") final String userHeader) throws InternalMsaException {
        return documentiService.uploadDocumento(file, numSinistro, codTipoDoc, userHeader);
    }

    @ApiOperation(value = "Metodo che restituisce tutti i documenti caricati per quel sinistro")
    @RequestMapping(value = "/{numeroSinistro}/lista", method = RequestMethod.GET)
    public BaseDTO<List<DocumentoDTO>> getListaDocumenti(@PathVariable("numeroSinistro") final Integer numSinistro) throws InternalMsaException {
        return documentiService.getListaDocumenti(numSinistro);
    }

    @ApiOperation(value = "Metodo che restituisce tutti i documenti caricati per quel sinistro")
    @RequestMapping(value = "/{idDoc}/get", method = RequestMethod.GET)
    public ResponseEntity<Resource> stampaDocumento(@PathVariable(value = "idDoc") final Integer idDoc) throws InternalMsaException {

        return documentiService.getDocumento(idDoc);
    }


}
