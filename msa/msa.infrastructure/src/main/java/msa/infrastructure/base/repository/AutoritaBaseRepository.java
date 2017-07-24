package msa.infrastructure.base.repository;

import msa.infrastructure.persistence.AutoritaDBO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AutoritaBaseRepository extends MongoRepository<AutoritaDBO, Integer> {
    List<AutoritaDBO> findAll();
}
