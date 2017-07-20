package msa.infrastructure.base.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import msa.infrastructure.persistence.NazioneDBO;

public interface NazioniBaseRepository extends MongoRepository<NazioneDBO, Integer> {

	@Query("{DescrizioneNazione: { $regex: ?0 }}")
	List<NazioneDBO> findLikeNome (String name);
	
	
	
}
