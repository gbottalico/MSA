package msa.infrastructure.base.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import msa.infrastructure.persistence.ComuneDBO;

public interface ComuniBaseRepository extends MongoRepository<ComuneDBO, Integer> {
	
	@Query("{'codNazione': ?0 , 'codProvincia': ?1,'descrizione':{$regex: '^?2'}, 'finValidita':null}")
	List<ComuneDBO> findByDesc(Integer codNazione, Integer provincia, String name);
}
