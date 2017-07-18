package msa.infrastructure.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import msa.infrastructure.base.repository.NazioniBaseRepository;
import msa.infrastructure.persistence.NazioniDBO;

@Repository
public class DomainRepository {

	@Autowired 
	NazioniBaseRepository nazioniRepository;
	
	public List<NazioniDBO> getListaNazioni(){
		return nazioniRepository.findLikeByNome("sp");
	}
	
	
	
}
