package msa.application.service.domain;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import msa.application.dto.domain.NazioneDTO;
import msa.application.service.base.BaseService;
import msa.domain.object.dominio.NazioneDO;
import msa.infrastructure.repository.DomainRepository;

@Service
public class DomainService extends BaseService {
	@Autowired 
 	DomainRepository domainRepository;
	
	public List<NazioneDTO> getElencoNazioni(String nome) throws IllegalAccessException, InvocationTargetException, InstantiationException{
		
		List<NazioneDO> result =  domainRepository.getListaNazioni(nome);
		List<NazioneDTO> listaDto = converter.convertList(result, NazioneDTO.class);
		return listaDto;
	}

}
