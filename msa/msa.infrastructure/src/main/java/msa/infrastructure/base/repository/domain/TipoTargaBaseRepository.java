package msa.infrastructure.base.repository;

import msa.infrastructure.persistence.domain.TipoTargaDBO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TipoTargaBaseRepository extends MongoRepository<TipoTargaDBO, Integer> {

    List<TipoTargaDBO> findAll();
}
