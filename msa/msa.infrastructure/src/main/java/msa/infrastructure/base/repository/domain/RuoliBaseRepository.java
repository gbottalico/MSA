package msa.infrastructure.base.repository.domain;

import msa.infrastructure.persistence.domain.RuoliDBO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RuoliBaseRepository extends MongoRepository<RuoliDBO, Integer>{
List<RuoliDBO> findAll();
}
