package msa.infrastructure.base.repository.domain;

import msa.infrastructure.persistence.domain.MezzoComunicazioneDBO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MezziComunicazioneBaseRepository extends MongoRepository<MezzoComunicazioneDBO, Integer> {
    List<MezzoComunicazioneDBO> findAll();

}
