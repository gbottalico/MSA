package msa.infrastructure.repository;

import msa.domain.object.dispatcher.DispatcherDO;
import msa.infrastructure.base.repository.domain.BaseRepository;
import msa.infrastructure.persistence.dispatcher.AlberInterfacceDBO;
import msa.infrastructure.persistence.dispatcher.FogliaDBO;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
@Repository
public class DispatcherRepository extends BaseRepository {
    public DispatcherDO getNextInterface(final DispatcherDO path) {
        final Criteria criteria = Criteria
                .where(getMongoNameByAttributeName("garanzia", AlberInterfacceDBO.class))
                .is(String.valueOf(path.getGaranziaSelected()));
        final Query criteriaQuery = getCriteriaQueryBuilder().addCriteria(criteria);
        FogliaDBO fogliaDBO = mongoTemplate.findOne(criteriaQuery, AlberInterfacceDBO.class)
                .getNextTree().stream()
                .filter(foglia -> (foglia.getThisView().equals(path.getThisView()))
                        && foglia.getParameter().equals(path.getParam()))
                .findFirst()
                .orElse(null);
        path.setNextView(fogliaDBO.getNextView());
        return path;
    }
}
