package msa.infrastructure.base.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import msa.infrastructure.persistence.ProvinciaDBO;

public interface ProvinceBaseRepository extends MongoRepository<ProvinciaDBO, Integer> {


	@Query("{'codNazione': ?0 , 'desProv': {$regex: '^?1'}, 'finValidita':null}")

	List<ProvinciaDBO> findByDesc(Integer idNazione, String descProvincia);
}
