package msa.infrastructure.base.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import msa.infrastructure.persistence.NazioniDBO;

public interface NazioniBaseRepository extends MongoRepository<NazioniDBO, Integer> {

	@Query("{nome: { $regex: ?0 }}")
	List<NazioniDBO> findLikeByNome (String name);
	
}
