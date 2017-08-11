package msa.infrastructure.repository;

import msa.domain.object.documenti.DocumentoDO;
import msa.infrastructure.base.repository.domain.BaseRepository;
import msa.infrastructure.persistence.documenti.DocumentoDBO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Comparator;

/**
 * Created by simon.calabrese on 11/08/2017.
 */
@Repository
public class DocumentiRepository extends BaseRepository {


    public Boolean insertDocumento(final DocumentoDO documentoDO) {
        try {
            insert(documentoDO, DocumentoDBO.class);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public Integer getNextIdDoc() {
        return getMaxElem(DocumentoDBO.class,
                Comparator.comparing(DocumentoDBO::getIdDocumento).reversed())
                .map((documentoDBO) -> documentoDBO.getIdDocumento() + 1).orElse(0);
    }
}
