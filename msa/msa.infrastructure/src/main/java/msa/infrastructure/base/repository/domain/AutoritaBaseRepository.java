package msa.infrastructure.base.repository.domain;

import msa.infrastructure.persistence.domain.AutoritaDBO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AutoritaBaseRepository extends MongoRepository<AutoritaDBO, Integer> {
    List<AutoritaDBO> findAll();
}
