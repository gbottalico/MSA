package msa.application.service.domain;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import msa.application.dto.domain.ComuneDTO;
import msa.application.dto.domain.NazioneDTO;
import msa.application.dto.domain.ProvinciaDTO;
import msa.application.service.base.BaseService;
import msa.domain.object.dominio.ComuneDO;
import msa.domain.object.dominio.NazioneDO;
import msa.domain.object.dominio.ProvinciaDO;
import msa.infrastructure.repository.DomainRepository;

@Service
public class DomainService extends BaseService {
	@Autowired
	DomainRepository domainRepository;

	/**
	 * Utilizza il DomainRepository per ottenere la lista delle nazioni che iniziano
	 * per una data stringa
	 * 
	 * @param nome
	 *            la stringa da cercare
	 * @return una lista di NazioneDTO
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public List<NazioneDTO> getElencoNazioni(String nome)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {

		List<NazioneDO> result = domainRepository.getListaNazioni(nome);
		List<NazioneDTO> listaDto = converter.convertList(result, NazioneDTO.class);
		return listaDto;
	}

	/**
	 * Utilizza il DomainRepository per ottenere la lista delle province che
	 * appartengono ad una data nazione ed il cui nome inizia con la stringa data
	 * 
	 * @param idNazione
	 *            l'id della nazione
	 * @param descProvincia
	 *            la stringa da cercare
	 * @return una lista di oggetti ProvinciaDTO
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<ProvinciaDTO> getElencoProvince(Integer idNazione, String descProvincia)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		List<ProvinciaDO> result = domainRepository.getElencoProvince(idNazione, descProvincia);
		List<ProvinciaDTO> listaDto = converter.convertList(result, ProvinciaDTO.class);
		return listaDto;
	}
	/**
	 * Utilizza il DomainRepository per ottenere la lista dei comuni che appartengono ad una data nazione e provincia ed il cui nome inizia con la stringa data
	 * @param idNazione id della nazione
	 * @param idProvincia id della provincia 
	 * @param desc la stringa da cercare
	 * @return una lista di oggetti ComuneDTO
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<ComuneDTO> getElencoComuni(Integer idNazione, Integer idProvincia, String desc)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		List<ComuneDO> result = domainRepository.getElencoComuni(idNazione, idProvincia, desc);
		List<ComuneDTO> listaDto = converter.convertList(result, ComuneDTO.class);
		return listaDto;
	}
}
