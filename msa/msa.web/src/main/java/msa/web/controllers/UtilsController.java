package msa.web.controllers;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.dto.sinistro.anagrafica.BaseAnagraficaDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.util.UtilsService;
import msa.web.api.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private UtilsService utilsService;

    @ApiOperation(value = "Metodo che calcola il cf")
    @RequestMapping(value = "/calcolaCf", method = RequestMethod.POST)
    public BaseDTO<String> getCodiceFiscale(@RequestBody BaseAnagraficaDTO anagrafica)throws InternalMsaException {
        return new BaseDTO<>(utilsService.calcolaCodiceFiscale(anagrafica));
    }
}
