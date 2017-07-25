package msa.infrastructure.base.repository;

import msa.infrastructure.persistence.CasaRegoleDBO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CasaRegoleBaseRepository extends MongoRepository<CasaRegoleDBO,String>{

}
