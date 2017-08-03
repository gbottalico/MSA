package msa.application.service.sinistri;

import msa.application.dto.sinistro.BaseSinistroDTO;
import msa.application.dto.sinistro.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.domain.object.sinistro.*;
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
        coupleFunctions.add(new Function<>(DannoRcaDTO.class, DANNORCA));

        coupleFunctions.add(new Function<>(ConstatazioneAmichevoleDTO.class, CONSTATAZIONE_AMICHEVOLE));
    }

    protected <T extends BaseSinistroDTO> SinistroDO getSinistroDOByDTO(T dto, Integer numProvv, MsaBiFunction<T, SinistroDO, SinistroDO> andThen) throws InternalMsaException {
        return andThen.apply(dto,GETSINISTRO.apply(numProvv));
    }


    protected <T extends BaseSinistroDTO> SinistroDO getSinistroDOByDTO(T dto, Integer numProvv) throws InternalMsaException {
        final MsaBiFunction<T, Integer, SinistroDO> msaBiFunction = this.coupleFunctions.stream()
                .filter(e -> e.getClazz().equals(dto.getClass()))
                .reduce(null,
                        (a, b) -> b.getBiFunction(),
                        (a, b) -> b);
        return msaBiFunction.apply(dto, numProvv);
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


    private final MsaFunction<Integer, SinistroDO> GETSINISTRO =
            numSinistroProvv -> {
                try {
                    return sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

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
    private final MsaBiFunction<DannoRcaDTO, Integer, SinistroDO> DANNORCA =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                    sinistroByNumProvv.setDannoRca(converter.convertObject(o, DannoRcaDO.class));
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<ConstatazioneAmichevoleDTO, Integer, SinistroDO> CONSTATAZIONE_AMICHEVOLE =
            (constatazioneAmichevoleDTO, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                    sinistroByNumProvv.setConstatazioneAmichevole(converter.convertObject(constatazioneAmichevoleDTO, ConstatazioneAmichevoleDO.class));
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

}
