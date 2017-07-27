package msa.infrastructure.base.repository.domain;

import msa.infrastructure.persistence.domain.CausaRotturaCristalliDBO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CauseRotturaCristalliBaseRepository extends MongoRepository<CausaRotturaCristalliDBO, Integer> {
    List<CausaRotturaCristalliDBO> findAll();
}
