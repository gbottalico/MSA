package msa.web.controllers;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.documenti.DocumentiService;
import msa.web.api.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return documentiService.uploadDocumento(file,numSinistro,codTipoDoc,userHeader);
    }


}
