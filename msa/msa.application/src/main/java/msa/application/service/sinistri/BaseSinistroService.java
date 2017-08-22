package msa.application.service.sinistri;

import msa.application.dto.sinistro.AbstractDTO;
import msa.application.dto.sinistro.KaskoDTO;
import msa.application.dto.sinistro.PeritoDTO;
import msa.application.dto.sinistro.SinistroFurtoIncendioDTO;
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
import java.util.List;

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
        coupleSinistroFunctions.add(new SinistroFunction<>(PeritoDTO.class, PERITO));
        coupleSinistroFunctions.add(new SinistroFunction<>(SinistroFurtoIncendioDTO.class, SINISTRO_FURTO_INCENDIO));
        coupleSinistroFunctions.add(new SinistroFunction<>(KaskoDTO.class, KASKO));

    }

    protected <T extends AbstractDTO, E extends BaseSinistroDO> BaseSinistroDO getSinistroDOByDTOAndFunction(T dto, Integer numProvv, MsaBiFunction<T, Integer, E> andThen) throws InternalMsaException {
        return andThen.apply(dto, numProvv);
    }

    protected <T extends AbstractDTO, K extends BaseSinistroDO> K getSinistroDOByDTO(T dto, Integer numProvv) throws InternalMsaException {
        final MsaBiFunction<T, Integer, K> msaBiFunction = this.coupleSinistroFunctions.stream()
                .filter(e -> e.getClazz().equals(dto.getClass()))
                .reduce(null,
                        (a, b) -> b.getBiFunction(),
                        (a, b) -> b);
        return msaBiFunction.apply(dto, numProvv);
    }

    public class SinistroFunction<T extends AbstractDTO, K> {
        private Class<T> clazz;
        private MsaBiFunction<T, Integer, K> biFunction;
        private Class<K> doClass;

        public SinistroFunction(Class<T> clazz, MsaBiFunction<T, Integer, K> biFunction) {
            this.clazz = clazz;
            this.biFunction = biFunction;
        }

        public Class<T> getClazz() {
            return clazz;
        }

        public MsaBiFunction<T, Integer, K> getBiFunction() {
            return biFunction;
        }
    }


    private final MsaBiFunction<SegnalazioneDTO, Integer, BaseSinistroDO> SEGNALAZIONE =
            (o, numSinistroProvv) -> {
                try {
                    final BaseSinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, BaseSinistroDO.class);
                    sinistroByNumProvv.setSegnalazione(converter.convertObject(o, SegnalazioneDO.class));
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<EventoRcaDTO, Integer, SinistroDO> EVENTORCA =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroDO.class);
                    sinistroByNumProvv.setEventoRca(converter.convertObject(o, EventoRcaDO.class));
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<DannoRcaDTO, Integer, SinistroDO> DANNORCA_CONDUCENTE =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroDO.class);
                    sinistroByNumProvv.setDannoRca(converter.convertObject(o, DannoRcaDO.class));

                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

    private final MsaBiFunction<AnagraficaDanniDTO, Integer, SinistroDO> DANNORCA_CONTROPARTE =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroDO.class);
                    sinistroByNumProvv.getDannoRca().setAnagraficaDanniControparte(converter.convertObject(o, AnagraficaDanniDO.class));

                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<ConstatazioneAmichevoleDTO, Integer, SinistroDO> CONSTATAZIONE_AMICHEVOLE =
            (constatazioneAmichevoleDTO, numSinistroProvv) -> {
                try {
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroDO.class);
                    sinistroByNumProvv.setConstatazioneAmichevole(converter.convertObject(constatazioneAmichevoleDTO, ConstatazioneAmichevoleDO.class));
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<AnagraficaTerzePartiDTO, Integer, BaseSinistroDO> TERZE_PARTI =
            (O, numSinistroProvv) -> {
                try {
                    final BaseSinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, BaseSinistroDO.class);
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    protected final MsaBiFunction<AnagraficaTerzePartiDTO, Integer, BaseSinistroDO> LEGALE =
            (O, numSinistroProvv) -> {
                try {
                    final BaseSinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, BaseSinistroDO.class);
                    AnagraficaTerzePartiDO anagraficaTerzePartiDO = converter.convertObject(O, AnagraficaTerzePartiDO.class);
                    sinistroByNumProvv.getAnagraficaTerzeParti().add(anagraficaTerzePartiDO);
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaFunction<SinistroDO, SinistroDO> ADD_DES_TO_CAI = cai -> {
        try {
            cai.getCai().getBaremesCliente().setDescrizione(domainRepository.getDesbaremesById(cai.getCai().getBaremesCliente().getId()).getDescrizione());
            final BaremesDO controparte = cai.getCai().getBaremesControparte();
            if (controparte != null) {
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
                    final SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroDO.class);
                    CaiDO caiDO = converter.convertObject(O, CaiDO.class);
                    sinistroByNumProvv.setCai(caiDO);
                    return ADD_DES_TO_CAI.apply(sinistroByNumProvv);
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

    private final MsaBiFunction<PeritoDTO, Integer, BaseSinistroDO> PERITO =
            (perito, numSinistro) -> {
                try {
                    final BaseSinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro, BaseSinistroDO.class);
                    PeritoDO peritoDO = converter.convertObject(perito, PeritoDO.class);
                    sinistroByNumProvv.setPerito(peritoDO);
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<SinistroFurtoIncendioDTO, Integer, SinistroFurtoIncendioDO> SINISTRO_FURTO_INCENDIO =
            (o, numSinistro) -> {
                try {
                    SinistroFurtoIncendioDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro, SinistroFurtoIncendioDO.class);
                    sinistroByNumProvv.setDescrizioneDanni(o.getDescrizioneDanni());
                    sinistroByNumProvv.setOsservazioniCliente(o.getOsservazioniCliente());
                    sinistroByNumProvv.setResponsabilita(o.getResponsabilita());
                    sinistroByNumProvv.setSviluppoFiamme(o.getSviluppoFiamme());
                    sinistroByNumProvv.setInterventoAutorita(o.getInterventoAutorita());
                    return sinistroByNumProvv;

                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<KaskoDTO, Integer, KaskoDO> KASKO =
            (o, numSinistro) -> {
                try {

                    KaskoDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro, KaskoDO.class);
                    sinistroByNumProvv.setDanniKasko(converter.convertObject(o.getDanniKasko(),DanniDO.class));
                    sinistroByNumProvv.setOsservazioniCliente(o.getOsservazioniCliente());
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

}
