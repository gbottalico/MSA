package msa.infrastructure.repository;

import msa.domain.object.ricerca.FullPolizzaDO;
import msa.domain.object.sinistro.UserLoggedDO;
import msa.infrastructure.base.repository.domain.BaseRepository;
import msa.infrastructure.persistence.ricerca.FullPolizzaDBO;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by simon.calabrese on 08/09/2017.
 */
@Repository
public class PolizzeRepository extends BaseRepository {

    public void savePolizzeMsa(List<FullPolizzaDO> fullPolizzaDOS) {
        insert(fullPolizzaDOS, FullPolizzaDBO.class);
    }

    public void deletePolizzeForUser(UserLoggedDO userLogged) {
        Query query = getCriteriaQueryBuilder().addCriteria(Criteria.where("user.idUser").is(userLogged.getIdUser()));
        findAndDelete(query, FullPolizzaDBO.class);
    }
}
