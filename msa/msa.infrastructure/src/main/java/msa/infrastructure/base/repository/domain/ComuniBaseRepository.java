package msa.infrastructure.base.repository.domain;

import msa.infrastructure.persistence.domain.ComuneDBO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ComuniBaseRepository extends MongoRepository<ComuneDBO, Integer> {
	
	@Query("{'codNazione': ?0 , 'codProvincia': ?1,'descrizione':{$regex: '^?2', $options: 'i'}, 'finValidita':null}")
	List<ComuneDBO> findByDesc(String codNazione, String provincia, String name);
}
