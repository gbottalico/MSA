package msa.infrastructure.base.repository.domain;

import msa.infrastructure.persistence.domain.ProvinciaDBO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProvinceBaseRepository extends MongoRepository<ProvinciaDBO, Integer> {


	@Query("{'codNazione': ?0 , 'desProv': {$regex: '^?1',$options:'i'}, 'finValidita':null}")

	List<ProvinciaDBO> findByDesc(Integer idNazione, String descProvincia);
}
