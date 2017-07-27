package msa.infrastructure.repository;

import msa.infrastructure.base.repository.BaseRepository;
import msa.infrastructure.persistence.errori.ErroriDBO;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
@Repository
public class ErroriRepository extends BaseRepository {

    public ErroriDBO findByCodErrore(final String codErrore) {
        Query query = getCriteriaQueryBuilder().addCriteria(Criteria.where("codErrore").is(codErrore));
        return mongoTemplate.findOne(query,ErroriDBO.class);
    }

    public List<ErroriDBO> findAll() {
        Query query = getCriteriaQueryBuilder();
        return mongoTemplate.findAll(ErroriDBO.class);
    }
}
