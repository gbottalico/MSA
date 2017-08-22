
package msa.infrastructure.base.repository.domain;

import msa.infrastructure.persistence.domain.NazioneDBO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NazioniBaseRepository extends MongoRepository<NazioneDBO, Integer> {

	@Query("{'descrizione': { $regex: '^?0' ,$options:'i' }}")

	List<NazioneDBO> findLikeNome(String name);
	
	
	
}
