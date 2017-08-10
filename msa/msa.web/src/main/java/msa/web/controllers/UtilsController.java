package msa.web.controllers;

import io.swagger.annotations.ApiOperation;
import msa.application.dto.sinistro.anagrafica.BaseAnagraficaDTO;
import msa.application.exceptions.InternalMsaException;
import msa.web.api.BaseController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by simon.calabrese on 10/08/2017.
 */
@RestController
@RequestMapping(value = "/api/utils")
public class UtilsController extends BaseController{


    @ApiOperation(value = "Metodo che recupera l'elenco delle nazioni che matchano il nome passato come parametro")
    @RequestMapping(value = "/nazione/{name}", method = RequestMethod.POST)
    public void getCodiceFiscale(@RequestBody BaseAnagraficaDTO anagrafica)throws InternalMsaException {

    }
}
