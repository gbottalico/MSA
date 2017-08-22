package msa.application.service.sinistri;

import msa.application.dto.sinistro.AbstractDTO;
import msa.application.dto.sinistro.PeritoDTO;
import msa.application.dto.sinistro.anagrafica.AnagraficaTerzePartiDTO;
import msa.application.dto.sinistro.rca.cai.CaiDTO;
import msa.application.dto.sinistro.rca.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.rca.dannoRca.AnagraficaDanniDTO;
import msa.application.dto.sinistro.rca.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.rca.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.domain.object.dominio.BaremesDO;
import msa.domain.object.sinistro.*;
import msa.domain.object.sinistro.rca.*;
import msa.infrastructure.repository.DomainRepository;
import msa.infrastructure.repository.SinistriRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
public class BaseSinistroService extends BaseService {

    @Autowired
    protected SinistriRepository sinistriRepository;

    @Autowired
    private DomainRepository domainRepository;

    private List<SinistroFunction> coupleSinistroFunctions;

    public BaseSinistroService() {
        coupleSinistroFunctions = new ArrayList<>();
        coupleSinistroFunctions.add(new SinistroFunction<>(SegnalazioneDTO.class, SEGNALAZIONE));
        coupleSinistroFunctions.add(new SinistroFunction<>(EventoRcaDTO.class, EVENTORCA));
        coupleSinistroFunctions.add(new SinistroFunction<>(DannoRcaDTO.class, DANNORCA_CONDUCENTE));
        coupleSinistroFunctions.add(new SinistroFunction<>(AnagraficaDanniDTO.class, DANNORCA_CONTROPARTE));
        coupleSinistroFunctions.add(new SinistroFunction<>(ConstatazioneAmichevoleDTO.class, CONSTATAZIONE_AMICHEVOLE));
        coupleSinistroFunctions.add(new SinistroFunction<>(AnagraficaTerzePartiDTO.class, TERZE_PARTI));
        coupleSinistroFunctions.add(new SinistroFunction<>(CaiDTO.class, CAI));
        coupleSinistroFunctions.add(new SinistroFunction<>(PeritoDTO.class,PERITO));
    }

    protected <T extends AbstractDTO> SinistroDO getSinistroDOByDTOAndFunction(T dto, Integer numProvv, MsaBiFunction<T, Integer, SinistroDO> andThen) throws InternalMsaException {
        return andThen.apply(dto, numProvv);
    }

    protected <T extends AbstractDTO> SinistroDO getSinistroDOByDTO(List<T> dto, Integer numProvv, BinaryOperator<SinistroDO> binaryOperator) throws InternalMsaException {
        return dto.stream()
                .collect(Collectors.toMap(e -> e, e -> numProvv))
                .entrySet()
                .stream()
                .map(e -> {
                    try {
                        return getSinistroDOByDTO(e.getKey(), e.getValue());
                    } catch (InternalMsaException e1) {
                        return null;
                    }
                }).reduce(binaryOperator).orElse(null);
    }

    protected <T extends AbstractDTO> SinistroDO getSinistroDOByDTO(T dto, Integer numProvv) throws InternalMsaException {
        final MsaBiFunction<T, Integer, SinistroDO> msaBiFunction = this.coupleSinistroFunctions.stream()
                .filter(e -> e.getClazz().equals(dto.getClass()))
                .reduce(null,
                        (a, b) -> b.getBiFunction(),
                        (a, b) -> b);
        return msaBiFunction.apply(dto, numProvv);
    }

    public class SinistroFunction<T extends AbstractDTO> {
        private Class<T> clazz;
        private MsaBiFunction<T, Integer, SinistroDO> biFunction;

        public SinistroFunction(Class<T> clazz, MsaBiFunction<T, Integer, SinistroDO> biFunction) {
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


    protected final MsaFunction<Integer, SinistroDO> GETSINISTRO =
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
    private final MsaBiFunction<DannoRcaDTO, Integer, SinistroDO> DANNORCA_CONDUCENTE =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                    sinistroByNumProvv.setDannoRca(converter.convertObject(o, DannoRcaDO.class));

                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

    private final MsaBiFunction<AnagraficaDanniDTO, Integer, SinistroDO> DANNORCA_CONTROPARTE =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                    sinistroByNumProvv.getDannoRca().setAnagraficaDanniControparte(converter.convertObject(o, AnagraficaDanniDO.class));

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
    private final MsaBiFunction<AnagraficaTerzePartiDTO, Integer, SinistroDO> TERZE_PARTI =
            (O, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                    AnagraficaTerzePartiDO anagraficaTerzePartiDO = converter.convertObject(O, AnagraficaTerzePartiDO.class);
                    sinistroByNumProvv.getDannoRca().setTerzeParti(Collections.singletonList(anagraficaTerzePartiDO));
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    protected final MsaBiFunction<AnagraficaTerzePartiDTO, Integer, SinistroDO> LEGALE =
            (O, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                    AnagraficaTerzePartiDO anagraficaTerzePartiDO = converter.convertObject(O, AnagraficaTerzePartiDO.class);
                    sinistroByNumProvv.getDannoRca().getTerzeParti().add(anagraficaTerzePartiDO);
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaFunction<SinistroDO,SinistroDO> ADD_DES_TO_CAI = cai -> {
        try {
            cai.getCai().getBaremesCliente().setDescrizione(domainRepository.getDesbaremesById(cai.getCai().getBaremesCliente().getId()).getDescrizione());
            final BaremesDO controparte = cai.getCai().getBaremesControparte();
            if(controparte != null) {
                controparte.setDescrizione(domainRepository.getDesbaremesById(cai.getCai().getBaremesControparte().getId()).getDescrizione());
            }
            return cai;
        } catch (Exception e) {
            throw new InternalMsaException();
        }
    };
    private final MsaBiFunction<CaiDTO, Integer, SinistroDO> CAI =
            (O, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
                    CaiDO caiDO = converter.convertObject(O, CaiDO.class);
                    sinistroByNumProvv.setCai(caiDO);
                    return ADD_DES_TO_CAI.apply(sinistroByNumProvv);
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

    private final MsaBiFunction<PeritoDTO,Integer,SinistroDO> PERITO =
            (perito,numSinistro) -> {
                try{
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro);
                    PeritoDO peritoDO = converter.convertObject(perito,PeritoDO.class);
                    sinistroByNumProvv.setPerito(peritoDO);
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

}
