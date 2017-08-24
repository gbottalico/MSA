package msa.infrastructure.repository;

import msa.domain.object.dispatcher.DispatcherDO;
import msa.domain.object.dispatcher.NavigazioneViewDO;
import msa.infrastructure.base.repository.domain.BaseRepository;
import msa.infrastructure.persistence.dispatcher.AlberoInterfacceDBO;
import msa.infrastructure.persistence.dispatcher.FogliaDBO;
import msa.infrastructure.persistence.dispatcher.NavigazioneViewDBO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
@Repository
public class DispatcherRepository extends BaseRepository {

    public Optional<NavigazioneViewDO> getAllViewBySinistro(final Integer numSinistro) {
        final List<NavigazioneViewDBO> result = findAll(NavigazioneViewDBO.class, Pair.of("numSinistro", numSinistro));
        if(CollectionUtils.isNotEmpty(result)){
            return Optional.of(converter.convertObject(result.stream()
                    .reduce((a, b) -> b)
                    .orElse(null), NavigazioneViewDO.class));
        } else {
            return Optional.empty();
        }
    }


    public Optional<String> getNextInterface(final DispatcherDO path) {
        final Criteria criteria = Criteria
                .where(getMongoNameByAttributeName("garanzia", AlberoInterfacceDBO.class))
                .is(String.valueOf(path.getGaranziaSelected()));
        final Query criteriaQuery = getCriteriaQueryBuilder().addCriteria(criteria);
        final Optional<FogliaDBO> fogliaDBO = mongoTemplate.findOne(criteriaQuery, AlberoInterfacceDBO.class)
                .getNextTree().stream()
                .filter(foglia -> (foglia.getThisView().equals(path.getLastView())))
                .filter(foglia -> path.getParamCod() == null || foglia.getParameter().equals(path.getParamCod()))
                .findFirst();
        return fogliaDBO.map(FogliaDBO::getNextView);
    }

    public void persistInViewNavigated(final NavigazioneViewDO navigazione) {
        update(navigazione, NavigazioneViewDBO.class);
    }
}
