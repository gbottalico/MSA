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
import msa.domain.Converter.FunctionUtils;
import msa.domain.Converter.MsaConverter;
import msa.domain.object.dominio.BaremesDO;
import msa.domain.object.dominio.TipoVeicoloDO;
import msa.domain.object.sinistro.*;
import msa.domain.object.sinistro.rca.*;
import msa.infrastructure.costanti.MsaCostanti;
import msa.infrastructure.repository.DomainRepository;
import msa.infrastructure.repository.SinistriRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        classiGaranzieMap.put("rca", SinistroRcaDTO.class);
        classiGaranzieMap.put("incendio", SinistroFurtoIncendioDTO.class);
        classiGaranzieMap.put("furto_totale", SinistroFurtoIncendioDTO.class);
        classiGaranzieMap.put("furto_parziale", SinistroFurtoIncendioDTO.class);
        classiGaranzieMap.put("kasko", SinistroKaskoDTO.class);
        classiGaranzieMap.put("cristalli", SinistroCristalliDTO.class);
        classiGaranzieMap.put("inf_conducente", SinistroInfortuniConducenteDTO.class);

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
/*
        coupleSinistroFunctions.add(new SinistroFunction<>(PeritoDTO.class, PERITO));
*/
        coupleSinistroFunctions.add(new SinistroFunction<>(SinistroFurtoIncendioDTO.class, SINISTRO_FURTO_INCENDIO));
        coupleSinistroFunctions.add(new SinistroFunction<>(SinistroKaskoDTO.class, KASKO));
        coupleSinistroFunctions.add(new SinistroFunction<>(SinistroCristalliDTO.class, CRISTALLI));
        coupleSinistroFunctions.add(new SinistroFunction<>(SinistroInfortuniConducenteDTO.class, INFORTUNI_CONDUCENTE));
        //coupleSinistroFunctions.add(new SinistroFunction<>(CentroConvenzionatoDTO.class, CENTRO_CONVENZIONATO));

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
                    if (sinistroByNumProvv.getDannoRca() == null) {
                        sinistroByNumProvv.setDannoRca(converter.convertObject(o, DannoRcaDO.class));
                    } else {
                        final DannoRcaDO dannoRcaDO = converter.convertObject(o, DannoRcaDO.class);
                        sinistroByNumProvv.getDannoRca().setLesioniConducente(dannoRcaDO.getLesioniConducente());
                        sinistroByNumProvv.getDannoRca().setConducenteDiverso(dannoRcaDO.getConducenteDiverso());
                        sinistroByNumProvv.getDannoRca().setAnagraficaDanniCliente(dannoRcaDO.getAnagraficaDanniCliente());
                        sinistroByNumProvv.getDannoRca().setLesioniConducente(dannoRcaDO.getLesioniConducente());
                    }
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };

    private final MsaBiFunction<AnagraficaDanniDTO, Integer, SinistroRcaDO> DANNORCA_CONTROPARTE =
            (o, numSinistroProvv) -> {
                try {
                    final SinistroRcaDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroRcaDO.class);
                    //sinistroByNumProvv.getDannoRca().setAnagraficaDanniControparte(converter.convertObject(o, AnagraficaDanniDO.class));

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
    private final MsaBiFunction<AnagraficaTerzePartiDTO, Integer, SinistroRcaDO> TERZE_PARTI =
            (O, numSinistroProvv) -> {
                try {
                    final SinistroRcaDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv, SinistroRcaDO.class);
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

    private final MsaBiFunction<SinistroFurtoIncendioDTO, Integer, SinistroFurtoIncendioDO> SINISTRO_FURTO_INCENDIO =
            (o, numSinistro) -> {
                try {
                    SinistroFurtoIncendioDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro, SinistroFurtoIncendioDO.class);
                    sinistroByNumProvv.setDescrizioneDanni(o.getDescrizioneDanni());
                    sinistroByNumProvv.setOsservazioniCliente(o.getOsservazioniCliente());
                    sinistroByNumProvv.setResponsabilita(o.getResponsabilita());
                    sinistroByNumProvv.setSviluppoFiamme(o.getSviluppoFiamme());
                    sinistroByNumProvv.setInterventoAutorita(o.getInterventoAutorita());
                    sinistroByNumProvv.setCodAutorita(o.getCodAutorita());
                    sinistroByNumProvv.setComandoAutorita(o.getComandoAutorita());
                    sinistroByNumProvv.setDataDenuncia(o.getDataDenuncia());
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
                    sinistroByNumProvv.setDescrizioneDanni(o.getDescrizioneDanni());
                    sinistroByNumProvv.setOsservazioniCliente(o.getOsservazioniCliente());
                    sinistroByNumProvv.setLesioniConducente(o.getLesioniConducente());
                    sinistroByNumProvv.setInterventoAutorita(o.getInterventoAutorita());
                    sinistroByNumProvv.setCodAutorita(o.getCodAutorita());
                    sinistroByNumProvv.setComandoAutorita(o.getComandoAutorita());
                    sinistroByNumProvv.setDataDenuncia(o.getDataDenuncia());
                    if(!o.getConducenteDiverso()) {
                        sinistroByNumProvv.setConducente(sinistroByNumProvv.getContraente());
                    } else {
                        sinistroByNumProvv.setConducente(converter.convertObject(o.getConducente(),FullAnagraficaDO.class));
                    }
                    sinistroByNumProvv.setConducenteDiverso(o.getConducenteDiverso());
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
                    sinistroByNumProvv.setCodAutorita(o.getCodAutorita());
                    sinistroByNumProvv.setComandoAutorita(o.getComandoAutorita());
                    sinistroByNumProvv.setDataDenuncia(o.getDataDenuncia()); 

                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }
            };
    private final MsaBiFunction<SinistroInfortuniConducenteDTO, Integer, BaseSinistroDO> INFORTUNI_CONDUCENTE =
            (o, numSinistro) -> {
                try {

                    SinistroInfortuniConducenteDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro, SinistroInfortuniConducenteDO.class);
                    sinistroByNumProvv.setConducenteDiverso(o.getConducenteDiverso());
                    sinistroByNumProvv.setDescrizioneDanni(o.getDescrizioneDanni());
                    sinistroByNumProvv.setOsservazioniInfortunato(o.getOsservazioniInfortunato());
                    sinistroByNumProvv.setInterventoAutorita(o.getInterventoAutorita());
                    sinistroByNumProvv.setCodAutorita(o.getCodAutorita());
                    sinistroByNumProvv.setComandoAutorita(o.getComandoAutorita());
                    sinistroByNumProvv.setDataDenuncia(o.getDataDenuncia());
                    if(!o.getConducenteDiverso()) {
                        sinistroByNumProvv.setConducente(sinistroByNumProvv.getContraente());
                    } else {
                        sinistroByNumProvv.setConducente(converter.convertObject(o.getConducente(),FullAnagraficaDO.class));
                    }
                    return sinistroByNumProvv;
                } catch (Exception e) {
                    throw new InternalMsaException();
                }

            };


    protected<T extends BaseSinistroDO> Stream<FullAnagraficaControparteDO> getStreamPd(Integer numSinistroProvv) throws Exception {
        T sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
        if(sinistroByNumProvv.getSegnalazione().getGaranziaSelected().equals(MsaCostanti.COD_GARANZIA_RCA)) {
            final Map<SinistroRcaDO, List<Function<SinistroRcaDO, Stream<FullAnagraficaControparteDO>>>> mapRca = new HashMap<>();
            final List<Function<SinistroRcaDO, Stream<FullAnagraficaControparteDO>>> functionsRca = new ArrayList<>();
            functionsRca.add(e -> {
                final Boolean sameproprietario = e.getProprietario() == null ? Boolean.TRUE : e.getContraente().getCf().equalsIgnoreCase(e.getProprietario().getCf());
                if (sameproprietario) {
                    return Stream.of(e.getContraente());
                } else {
                    return Stream.of(e.getContraente(), e.getProprietario());
                }
            });
            final SinistroRcaDO sinistroRcaDO = (SinistroRcaDO) sinistroByNumProvv;
            if (sinistroRcaDO.getDannoRca().getConducenteDiverso()) {
                functionsRca.add(e -> Stream.of(e.getDannoRca().getAnagraficaDanniCliente().getAnagrafica()));
            }
            functionsRca.add(e -> Optional.ofNullable(e.getDannoRca()
                    .getAnagraficaDanniControparte())
                    .map(elem -> elem.stream().map(AnagraficaDanniDO::getAnagrafica))
                    .orElse(Stream.empty()));
            functionsRca.add(e -> Optional.ofNullable(e.getDannoRca()
                    .getTerzeParti())
                    .map(terzaParte -> terzaParte.stream()
                            .filter(elem -> {
                                final Optional<RuoliDO> first = domainRepository.getElencoRuoli().stream().filter(elem2 -> elem2.getId().toString().equals(elem.getCodRuolo())).findFirst();
                                final String pdAss = first.map(RuoliDO::getPdAss).orElse(null);
                                return pdAss.equals(MsaCostanti.COD_PARTITA_DANNO);
                            }).map(row -> converter.convertObject(row, FullAnagraficaControparteDO.class)))
                    .orElse(Stream.empty()));
            mapRca.put(sinistroRcaDO, functionsRca);
            final FunctionUtils.StreamBuilder<FullAnagraficaControparteDO> streamBuilder = new FunctionUtils.StreamBuilder<>();
            streamBuilder.of(mapRca);
            return streamBuilder.getStream();
        } else {
            final Map<T, List<Function<T, Stream<FullAnagraficaControparteDO>>>> map = new HashMap<>();
            final List<Function<T, Stream<FullAnagraficaControparteDO>>> functions = new ArrayList<>();
            functions.add(e -> {
                final Boolean sameproprietario = e.getContraente().getCf().equalsIgnoreCase(e.getProprietario().getCf());
                if (sameproprietario) {
                    return Stream.of(e.getContraente());
                } else {
                    return Stream.of(e.getContraente(), e.getProprietario());
                }
            });
            map.put(sinistroByNumProvv,functions);
            final FunctionUtils.StreamBuilder<FullAnagraficaControparteDO> streamBuilder = new FunctionUtils.StreamBuilder<>();
            streamBuilder.of(map);
            return streamBuilder.getStream();
        }
    }
}
