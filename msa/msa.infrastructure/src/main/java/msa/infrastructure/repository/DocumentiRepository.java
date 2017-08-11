package msa.infrastructure.repository;

import msa.domain.object.documenti.DocumentoDO;
import msa.infrastructure.base.repository.domain.BaseRepository;
import msa.infrastructure.persistence.documenti.DocumentoDBO;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

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

    public List<DocumentoDO> getListaDocumenti(Integer numSinistro) {
        return getListaDocumenti(Pair.of("numSinistro",numSinistro));
    }

    public List<DocumentoDO> getListaDocumenti(Pair<String,Object> param) {
        return converter.convertList(findAll(DocumentoDBO.class, param),DocumentoDO.class);
    }

    public DocumentoDO find(Integer idDoc) {
        return converter.convertObject(mongoTemplate.findById(idDoc,DocumentoDBO.class),DocumentoDO.class);
    }
}
