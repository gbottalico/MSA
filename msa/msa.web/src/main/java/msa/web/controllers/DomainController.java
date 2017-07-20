package msa.web.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.config.enumerator.Esito;
import msa.application.dto.domain.NazioneDTO;
import msa.application.service.domain.DomainService;
import msa.web.api.BaseController;

@RestController
@RequestMapping(value = "/api/dominio")
public class DomainController extends BaseController {
	@Autowired
	DomainService domainService;

	@ApiOperation(value = "Metodo che recupera l'elenco delle nazioni che matchano il nome passato")
	@RequestMapping(value = "/nazione/{name}", method = RequestMethod.GET)
	public BaseDTO<List<NazioneDTO>> getNazioni(@PathVariable("name") String name)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {

		BaseDTO<List<NazioneDTO>> result = new BaseDTO<List<NazioneDTO>>();
		result.setResult(domainService.getElencoNazioni(name));
		result.setEsito(Esito.OK);
		return result;
	}
	
	
}
