package msa.application.service.sinistri;

import msa.application.config.enumerator.MessageType;
import msa.application.dto.sinistro.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
public class BaseSinistroService extends BaseService {

    @Autowired
    protected SinistriRepository sinistriRepository;

    @Autowired
    private DomainRepository domainRepository;

    private List<SinistroFunction> coupleSinistroFunctions;

    private static Map<String, Class> classiGaranzieMap = new HashMap<>();

    static {
        classiGaranzieMap.put("0", SinistroRcaDTO.class);
        classiGaranzieMap.put("1", SinistroFurtoIncendioDTO.class);
        classiGaranzieMap.put("2", SinistroFurtoIncendioDTO.class);
        classiGaranzieMap.put("3", SinistroFurtoIncendioDTO.class);
        classiGaranzieMap.put("4", SinistroKaskoDTO.class);
        classiGaranzieMap.put("1", SinistroCristalliDTO.class);
    }

    protected <T extends BaseSinistroDTO> Class<T> getClassByGaranzia(final String garanziaSelected) {
        return classiGaranzieMap.get(garanziaSelected);
    }

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
        coupleSinistroFunctions.add(new SinistroFunction<>(SinistroKaskoDTO.class, KASKO));
        coupleSinistroFunctions.add(new SinistroFunction<>(SinistroCristalliDTO.class, CRISTALLI));
        coupleSinistroFunctions.add(new SinistroFunction<>(SinistroInfortuniConducenteDTO.class, INFORTUNI_CONDUCENTE));
        coupleSinistroFunctions.add(new SinistroFunction<>(CentroConvenzionatoDTO.class, CENTRO_CONVENZIONATO));

    }

    protected <T extends AbstractDTO, E extends BaseSinistroDO> BaseSinistroDO getSinistroDOByDTOAndFunction(T dto, Integer numProvv, MsaBiFunction<T, Integer, E> andThen) throws InternalMsaException {
        return andThen.apply(dto, numProvv);
    }

    protected <T extends AbstractDTO, K extends BaseSinistroDO> K getSinistroDOByDTO(T dto, Integer numProvv) throws InternalMsaException {
        try {
            final MsaBiFunction<T, Integer, K> msaBiFunction = this.coupleSinistroFunctions.stream()
                    .filter(e -> e.getClazz().equals(dto.getClass()))
                    .reduce(null,
                            (a, b) -> b.getBiFunction(),
                            (a, b) -> b);
            return msaBiFunction.apply(dto, numProvv);
        } catch (InternalMsaException e) {
            throw new InternalMsaException(e.getExceptionThrowed(), getErrorMessagesByCodErrore(MessageType.ERROR, "MSA012"));
        }
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

    protected final MsaFunction<Integer, BaseSinistroDO> GET_SINISTRO =
            numSinistro -> {
                try {
                    return converter.convertObject(sinistriRepository.getSinistroByNumProvv(numSinistro), BaseSinistroDO.class);
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };


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
    private final MsaBiFunction<EventoRcaDTO, Integer, SinistroRcaDO> EVENTORCA =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroRcaDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroRcaDO.class);
                    if (Integer.compare(o.getNumVeicoli(), 2) < 0
                            && sinistroByNumProvv.getDannoRca() != null
                            && sinistroByNumProvv.getDannoRca().getAnagraficaDanniControparte() != null) {
                        sinistroByNumProvv.getDannoRca().setAnagraficaDanniControparte(null);
                    }
                    sinistroByNumProvv.setEventoRca(converter.convertObject(o, EventoRcaDO.class));
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<DannoRcaDTO, Integer, SinistroRcaDO> DANNORCA_CONDUCENTE =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroRcaDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroRcaDO.class);
                    sinistroByNumProvv.setDannoRca(converter.convertObject(o, DannoRcaDO.class));

                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

    private final MsaBiFunction<AnagraficaDanniDTO, Integer, SinistroRcaDO> DANNORCA_CONTROPARTE =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroRcaDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroRcaDO.class);
                    sinistroByNumProvv.getDannoRca().setAnagraficaDanniControparte(converter.convertObject(o, AnagraficaDanniDO.class));

                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<ConstatazioneAmichevoleDTO, Integer, SinistroRcaDO> CONSTATAZIONE_AMICHEVOLE =
            (constatazioneAmichevoleDTO, numSinistroProvv) -> {
                try {
                    final SinistroRcaDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroRcaDO.class);
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
    private final MsaFunction<SinistroRcaDO, SinistroRcaDO> ADD_DES_TO_CAI = cai -> {
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
    private final MsaBiFunction<CaiDTO, Integer, SinistroRcaDO> CAI =
            (O, numSinistroProvv) -> {
                try {
                    final SinistroRcaDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroRcaDO.class);
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
    private final MsaBiFunction<SinistroKaskoDTO, Integer, SinistroKaskoDO> KASKO =
            (o, numSinistro) -> {
                try {

                    SinistroKaskoDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro, SinistroKaskoDO.class);
                    sinistroByNumProvv.setDanniKasko(converter.convertObject(o.getDanniKasko(), DanniDO.class));
                    sinistroByNumProvv.setOsservazioniCliente(o.getOsservazioniCliente());
                    sinistroByNumProvv.setInterventoAutorita(o.getInterventoAutorita());

                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<SinistroCristalliDTO, Integer, SinistroCristalliDO> CRISTALLI =
            (o, numSinistro) -> {
                try {

                    SinistroCristalliDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro, SinistroCristalliDO.class);
                    sinistroByNumProvv.setDesCristalloRotto(o.getDesCristalloRotto());
                    sinistroByNumProvv.setCodRotturaCristalli(o.getCodRotturaCristalli());
                    sinistroByNumProvv.setFlagFattura(o.getFlagFattura());
                    sinistroByNumProvv.setFlagRiparazione(o.getFlagRiparazione());
                    sinistroByNumProvv.setInterventoAutorita(o.getInterventoAutorita());

                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<SinistroInfortuniConducenteDTO, Integer, BaseSinistroDO> INFORTUNI_CONDUCENTE =
            (o, numSinistro) -> {
                try {

                    SinistroInfortuniConducenteDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro, SinistroInfortuniConducenteDO.class);
                    sinistroByNumProvv.setConducenteDiversoContraente(o.getConducenteDiversoContraente());
                    sinistroByNumProvv.setDescrizioneDanni(o.getDescrizioneDanni());
                    sinistroByNumProvv.setOsservazioniInfortunato(o.getOsservazioniInfortunato());
                    sinistroByNumProvv.setInterventoAutorita(o.getInterventoAutorita());
                    if (o.getAnagraficaInfortunato() != null) {
                        sinistroByNumProvv.setAnagraficaInfortunato(converter.convertObject(o.getAnagraficaInfortunato(), FullAnagraficaDO.class));
                    }
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }

            };
    private final MsaBiFunction<CentroConvenzionatoDTO, Integer, BaseSinistroDO> CENTRO_CONVENZIONATO =
            (o, numSinistro) -> {
                try {
                    final BaseSinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro, BaseSinistroDO.class);
                    sinistroByNumProvv.setCentroConvenzionato(converter.convertObject(o, CentroConvenzionatoDO.class));
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
}
