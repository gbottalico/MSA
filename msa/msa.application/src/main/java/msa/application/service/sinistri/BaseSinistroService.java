package msa.application.service.sinistri;

import msa.application.dto.sinistro.BaseSinistroDTO;
import msa.application.dto.sinistro.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.domain.object.sinistro.EventoRcaDO;
import msa.domain.object.sinistro.SegnalazioneDO;
import msa.domain.object.sinistro.SinistroDO;
import msa.infrastructure.repository.SinistriRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
public class BaseSinistroService extends BaseService {

    @Autowired
    protected SinistriRepository sinistriRepository;

    private List<Function> coupleFunctions;

    public BaseSinistroService() {
        coupleFunctions = new ArrayList<>();
        coupleFunctions.add(new Function<>(SegnalazioneDTO.class, SEGNALAZIONE));
        coupleFunctions.add(new Function<>(EventoRcaDTO.class, EVENTORCA));
    }

    protected <T extends BaseSinistroDTO> SinistroDO getSinistroDOByDTO(T dto, Integer numProvv) throws InternalMsaException {
        final MsaBiFunction<T,Integer,SinistroDO> msaBiFunction = this.coupleFunctions.stream()
                .filter(e -> e.getClazz().equals(dto.getClass()))
                .reduce(null,
                        (a,b) -> b.getBiFunction(),
                        (a,b) -> b);
        return msaBiFunction.apply(dto,numProvv);
    }

    public class Function<T extends BaseSinistroDTO> {
        private Class<T> clazz;
        private MsaBiFunction<T, Integer, SinistroDO> biFunction;

        public Function(Class<T> clazz, MsaBiFunction<T, Integer, SinistroDO> biFunction) {
            this.clazz = clazz;
            this.biFunction = biFunction;
        }

        public Class<T> getClazz() {
            return clazz;
        }

        public MsaBiFunction<T, Integer, SinistroDO> getBiFunction() {
            return biFunction;
        }
    }


    private final MsaBiFunction<SegnalazioneDTO, Integer, SinistroDO> SEGNALAZIONE =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                    sinistroByNumProvv.setSegnalazione(converter.convertObject(o, SegnalazioneDO.class));
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<EventoRcaDTO, Integer, SinistroDO> EVENTORCA =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                    sinistroByNumProvv.setEventoRca(converter.convertObject(o, EventoRcaDO.class));
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

}
