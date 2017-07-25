package msa.infrastructure.base.repository;

import msa.infrastructure.persistence.CompagniaDBO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CompagnieBaseRepository extends MongoRepository<CompagniaDBO,Integer>{
   @Query("{ 'DescrizioneCompagnia' : { $regex: ?0, $options: 'i'}}")
    List<CompagniaDBO> findByDescrizioneIgnoreCase(String desc);
}
