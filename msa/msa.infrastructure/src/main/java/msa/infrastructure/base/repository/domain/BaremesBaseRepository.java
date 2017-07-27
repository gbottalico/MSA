package msa.infrastructure.base.repository;

import msa.infrastructure.persistence.domain.BaremesDBO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BaremesBaseRepository extends MongoRepository<BaremesDBO,Integer>{

List<BaremesDBO> findAll();

}
