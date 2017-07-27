
package msa.infrastructure.base.repository.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import msa.infrastructure.persistence.domain.NazioneDBO;

public interface NazioniBaseRepository extends MongoRepository<NazioneDBO, Integer> {

	@Query("{'descrizione': { $regex: '^?0' ,$options:'i' }}")

	List<NazioneDBO> findLikeNome(String name);
	
	
	
}
