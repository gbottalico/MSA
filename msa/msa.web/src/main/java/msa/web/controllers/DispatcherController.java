package msa.web.controllers;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.dto.dispatcher.DispatcherDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.interfaceDispatcher.DispatcherService;
import msa.web.api.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
@RestController
@RequestMapping(value = "/api/dispatcher")
public class DispatcherController extends BaseController {

    @Autowired
    private DispatcherService dispatcherService;

    @ApiOperation(value = "Metodo che recupera l' interfaccia successiva dato un path")
    @RequestMapping(value = "/nextPath", method = RequestMethod.POST)
    public BaseDTO<Map<Integer, String>> getNextInterface(@RequestBody final DispatcherDTO basePath) throws InternalMsaException {
        return dispatcherService.getNextInterfaceAndPersist(basePath);
    }

    @ApiOperation("Metodo che recupera tutte le interfacce giù navigate per un sinistro")
    @RequestMapping(value = "/getPath",method = RequestMethod.GET)
    public BaseDTO<Map<Integer,String>> getAllInterface(@RequestParam(value = "numSinistro") final Integer numSinistroProvv) throws InternalMsaException {
        return dispatcherService.getAllInterface(numSinistroProvv);
    }

    @ApiOperation("Metodo che recupera la percentuale di avanzamento")
    @RequestMapping(value = "/getPercentuale",method = RequestMethod.POST)
    public BaseDTO<Integer> getPercentualeAvanzamento(@RequestBody final DispatcherDTO dispatcherDTO) throws InternalMsaException {
        return new BaseDTO<>(dispatcherService.getPercentualeAvanzamento(dispatcherDTO));
    }
}
