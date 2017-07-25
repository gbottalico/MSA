package msa.infrastructure.base.repository;

import msa.infrastructure.persistence.TipoVeicoloDBO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TipoVeicoloBaseRepository extends MongoRepository<TipoVeicoloDBO,Integer> {
    
    List<TipoVeicoloDBO> findAll();
}
