package msa.infrastructure.repository;

import msa.infrastructure.base.repository.domain.BaseRepository;
import msa.infrastructure.base.repository.sinistri.SinistriBaseRepository;
import msa.infrastructure.persistence.sinistro.SinistroDBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SinistriRepository extends BaseRepository {
    @Autowired
    SinistriBaseRepository sinistriRepository;

    public List<SinistroDBO> getElencoSinistriProvvisori() {
        return null;
    }

}
