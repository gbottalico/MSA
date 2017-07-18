package msa.application.service.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import msa.application.service.base.BaseService;
import msa.infrastructure.persistence.NazioniDBO;
import msa.infrastructure.repository.DomainRepository;

@Service
public class DomainService extends BaseService {
	@Autowired 
 	DomainRepository domainRepository;
	
	public List<NazioniDBO> getElencoNazioni(){
		return domainRepository.getListaNazioni();
	}

}
