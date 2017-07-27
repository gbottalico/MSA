package msa.infrastructure.base.repository.sinistri;

import com.mongodb.Mongo;
import msa.infrastructure.persistence.sinistro.SinistroDBO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SinistriBaseRepository  extends MongoRepository<SinistroDBO, String>{
    List<SinistroDBO> findAll();

}
