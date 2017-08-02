package msa.application.service.sinistri;

import msa.application.dto.sinistro.BaseSinistroDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.service.base.BaseService;
import msa.domain.object.sinistro.SegnalazioneDO;
import msa.domain.object.sinistro.SinistroDO;
import msa.infrastructure.repository.SinistriRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
public class BaseSinistroService extends BaseService {

    @Autowired
    protected SinistriRepository sinistriRepository;

    private final Map<Integer, BiFunction<Object, Integer, SinistroDO>> map = new HashMap<>();

    public BaseSinistroService() {
        initMap();
    }

    protected <T extends BaseSinistroDTO> SinistroDO getSinistroDOByDTO(Integer key, T dto, Integer numProvv) {
        return this.map.get(key).apply(dto, numProvv);
    }


    private void initMap() {
        map.put(0, SEGNALAZIONE);
    }


    private final BiFunction<Object, Integer, SinistroDO> SEGNALAZIONE =
            (((Object o, Integer numSinistroProvv) -> {
                final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                sinistroByNumProvv.setSegnalazione(converter.convertObject(o, SegnalazioneDO.class));
                sinistroByNumProvv.setNumSinistroProvv(numSinistroProvv);
                return sinistroByNumProvv;
            }));

}
