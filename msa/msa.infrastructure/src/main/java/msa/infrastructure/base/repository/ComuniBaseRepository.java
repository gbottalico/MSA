package msa.infrastructure.base.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import msa.infrastructure.persistence.ComuneDBO;

public interface ComuniBaseRepository extends MongoRepository<ComuneDBO, Integer> {
	//TODO cambiare la query
	@Query("{'_id': { $regex: '^?1'  }}")
	List<ComuneDBO> findByDesc(Integer codNazione, String provincia, String name);
}
