package msa.infrastructure.base.repository.domain;

import msa.infrastructure.persistence.domain.TipoVeicoloDBO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TipoVeicoloBaseRepository extends MongoRepository<TipoVeicoloDBO,Integer> {
    
    List<TipoVeicoloDBO> findAll();
}
