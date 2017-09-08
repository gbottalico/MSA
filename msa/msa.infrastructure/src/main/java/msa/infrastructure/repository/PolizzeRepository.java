package msa.infrastructure.repository;

import msa.domain.object.ricerca.FullPolizzaDO;
import msa.infrastructure.base.repository.domain.BaseRepository;
import msa.infrastructure.persistence.ricerca.FullPolizzaDBO;
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
}
