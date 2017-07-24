package msa.web.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.config.enumerator.Esito;
import msa.application.dto.domain.ComuneDTO;
import msa.application.dto.domain.NazioneDTO;
import msa.application.dto.domain.ProvinciaDTO;
import msa.application.service.domain.DomainService;
import msa.web.api.BaseController;

@RestController
@RequestMapping(value = "/api/dominio")
public class DomainController extends BaseController {
	@Autowired
	DomainService domainService;

	/**
	 * Ottiene la lista delle nazioni il cui nome inizia con la stringa passata nel
	 * path
	 * 
	 * @param name
	 *            la stinga da cercare
	 * @return un BaseDTO contente le informazioni richieste
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	@ApiOperation(value = "Metodo che recupera l'elenco delle nazioni che matchano il nome passato come parametro")
	@RequestMapping(value = "/nazione/{name}", method = RequestMethod.GET)
	public BaseDTO<List<NazioneDTO>> getNazioni(@PathVariable("name") String name)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {

		BaseDTO<List<NazioneDTO>> result = new BaseDTO<List<NazioneDTO>>();
		result.setResult(domainService.getElencoNazioni(name));
		result.setEsito(Esito.OK);
		return result;
	}

	/**
	 * Ottiene la lista delle province che iniziano con la stringa passata e che
	 * appartengono alla nazione di cui viene passato l'id come parametro
	 * 
	 * @param id
	 *            l'id della nazione di cui cercare le province, descProvincia la
	 *            stringa da cercare
	 * @return un BaseDTO contenente le informazioni richieste
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */

	@ApiOperation(value = "Metodo che recupera l'elenco delle province in base all'ID della nazione passato come parametro e la descrizione")
	@RequestMapping(value = "/provincia/{nazione}/{descProvincia}", method = RequestMethod.GET)
	public BaseDTO<List<ProvinciaDTO>> getProvinceByCodNazione(@PathVariable("nazione") Integer idNazione,
			@PathVariable("descProvincia") String descProvincia)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		BaseDTO<List<ProvinciaDTO>> result = new BaseDTO<List<ProvinciaDTO>>();
		result.setResult(domainService.getElencoProvince(idNazione, descProvincia));
		result.setEsito(Esito.OK);
		return result;
	}

	/**
	 * Ottiene la lista dei comuni che appartengono ad una data nazione e provincia e il cui nome inizia con la stringa passata
	 * @param idNazione l'id della nazione 
	 * @param idProvincia l'id della provincia 
	 * @param desComune la stringa da cercare
	 * @return un BaseDTO contenente le informazioni richieste
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@ApiOperation(value = "Metodo che recupera l'elenco dei comuni in base all'id della nazione, id della provincia e la descrizione")
	@RequestMapping(value = "/comune/{nazione}/{provincia}/{desComune}", method = RequestMethod.GET)
	public BaseDTO<List<ComuneDTO>> getElencoComuni(@PathVariable("nazione") Integer idNazione,
			@PathVariable("provincia") Integer idProvincia, @PathVariable("desComune") String desComune)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		BaseDTO<List<ComuneDTO>> result = new BaseDTO<List<ComuneDTO>>();
		result.setResult(domainService.getElencoComuni(idNazione, idProvincia, desComune));
		result.setEsito(Esito.OK);
		return result;

	}

}
