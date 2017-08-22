package msa.web.controllers;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.dto.dispatcher.DispatcherDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.interfaceDispatcher.DispatcherService;
import msa.web.api.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseDTO<DispatcherDTO> getNextInterface(@RequestBody final DispatcherDTO basePath) throws InternalMsaException {
        return dispatcherService.getNextInterface(basePath);
    }
}
