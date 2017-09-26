package msa.application.service.sinistri.tipoSinistro;

import msa.domain.Converter.FunctionUtils;
import msa.domain.object.dominio.TipoVeicoloDO;
import msa.domain.object.enums.TipiSinisto;
import msa.domain.object.sinistro.BaseSinistroDO;
import msa.domain.object.sinistro.FullAnagraficaControparteDO;
import msa.domain.object.sinistro.SinistroRcaDO;
import msa.domain.object.sinistro.rca.AnagraficaDanniDO;
import msa.domain.object.sinistro.rca.IncrociBaremesDO;
import msa.infrastructure.costanti.MsaCostanti;
import msa.infrastructure.repository.DomainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

abstract class CalcoloTipoSinistroFunctions<T extends BaseSinistroDO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalcoloTipoSinistroFunctions.class);
    @Autowired
    private DomainRepository domainRepository;

    private final Predicate<FullAnagraficaControparteDO> hasLesioni = FullAnagraficaControparteDO::getLesioni;

    private final Function<FullAnagraficaControparteDO, Boolean> isConvenzioneCTT = anagrafica -> {
        final Boolean flagGestioneCTTVeicolo = domainRepository.getElencoTipoVeicoli()
                .parallelStream()
                .filter(e -> e.getId().equals(FunctionUtils.numberConverter(anagrafica.getVeicolo(), Integer::valueOf)))
                .findFirst()
                .map(TipoVeicoloDO::getGestioneCtt)
                .orElse(Boolean.FALSE);
        final Boolean flagCard = Optional.ofNullable(anagrafica.getFlagCard()).orElseGet(() -> Boolean.FALSE);
        final Boolean targaEstera = Optional.ofNullable(!anagrafica.getTargaEstera()).orElseGet(() -> Boolean.FALSE);
        return flagCard && flagGestioneCTTVeicolo && targaEstera;
    };

    private final Function<SinistroRcaDO, Boolean> isInItalia = sinis -> Integer.compare(
            FunctionUtils.numberConverter(sinis.getSegnalazione().getLuogoSinistro().getCodNazione(),Integer::valueOf),
            MsaCostanti.COD_NAZIONE_ITALIA) == 0;

    private final Function<List<FullAnagraficaControparteDO>, Boolean> nostroTrasportatoCliente = (anags) -> {
        final Predicate<FullAnagraficaControparteDO> isTrasportato = e -> e.getCodRuolo().equals(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString());
        final Predicate<FullAnagraficaControparteDO> concat = hasLesioni.and(isTrasportato);

        return anags.stream().anyMatch(concat);
    };

    private final Function<List<FullAnagraficaControparteDO>, Boolean> nostroTrasportatoControparte = (anags) -> {
        final Predicate<FullAnagraficaControparteDO> isTrasportato = e -> e.getCodRuolo().equals(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CONTROPARTE.toString());
        final Predicate<FullAnagraficaControparteDO> concat = hasLesioni.and(isTrasportato);
        return anags.stream().anyMatch(concat);
    };

    private final Function<SinistroRcaDO, Integer> getPercCID = sinis -> {
        final IncrociBaremesDO incrocio = domainRepository.getColpaByBaremes(sinis.getConstatazioneAmichevole().getBaremesCliente(), sinis.getConstatazioneAmichevole().getBaremesControparte());
        return incrocio.getPercRespCliente();
    };


    private final Function<FullAnagraficaControparteDO, Boolean> isConvenzioneCARD = anagrafica -> {
        final Boolean flagGestioneCTTVeicolo = domainRepository.getElencoTipoVeicoli()
                .parallelStream()
                .filter(e -> e.getId().equals(FunctionUtils.numberConverter(anagrafica.getVeicolo(), Integer::valueOf)))
                .findFirst()
                .map(TipoVeicoloDO::getGestioneCid)
                .orElse(Boolean.FALSE);
        final Boolean flagCard = Optional.ofNullable(anagrafica.getFlagCard()).orElseGet(() -> Boolean.FALSE);
        final Boolean targaEstera = Optional.ofNullable(!anagrafica.getTargaEstera()).orElseGet(() -> Boolean.FALSE);
        return flagCard && flagGestioneCTTVeicolo && targaEstera;
    };

    protected Function<SinistroRcaDO, TipiSinisto> GET_BY_CID_PRESENTE_FULL= sinis -> {
        final Integer cid = getPercCID.apply(sinis);
        if (cid == 0) {
            if (nostroTrasportatoCliente.apply(sinis.getDannoRca().getTerzeParti())) {
                if (isConvenzioneCTT.apply(sinis.getDannoRca().getAnagraficaDanniCliente().getAnagrafica())/*manca parte di controparte */) {
                    return TipiSinisto.CID_CTT_GESTIONARIO;
                } else {
                    return TipiSinisto.RCA;
                }
            } else {
                return TipiSinisto.CID_GESTIONARIO;
            }
        } else if (FunctionUtils.genericBetween(0, 100, cid, Boolean.FALSE)) {
            if (nostroTrasportatoCliente.apply(sinis.getDannoRca().getTerzeParti())
                    && nostroTrasportatoControparte.apply(sinis.getDannoRca().getTerzeParti())) {
                if (isConvenzioneCTT.apply(sinis.getDannoRca().getAnagraficaDanniCliente().getAnagrafica())/*manca parte di controparte */) {
                    return TipiSinisto.CID_CTT_CONCORSUALE;
                } else {
                    return TipiSinisto.RCA;
                }
            } else {
                return TipiSinisto.CID_CONCORSUALE;
            }
        } else if (cid == 100) {
            if (nostroTrasportatoControparte.apply(sinis.getDannoRca().getTerzeParti())) {
                if (isConvenzioneCTT.apply(sinis.getDannoRca().getAnagraficaDanniCliente().getAnagrafica())/*manca parte di controparte */) {
                    return TipiSinisto.CID_CTT_DEBITORIO;
                } else {
                    return TipiSinisto.RCA;
                }
            } else {
                return TipiSinisto.CID_DEBITORIO;
            }
        } else {
            return TipiSinisto.RCA;
        }
    };

    protected Function<SinistroRcaDO, TipiSinisto> GET_BY_CID_AND_PRESENTE= sinis -> {
        final Integer cid = getPercCID.apply(sinis);
        LOGGER.info("getByCidAndPresente con esito " + cid);
        if (cid == 0 && nostroTrasportatoCliente.apply(sinis.getDannoRca().getTerzeParti())) {
            return TipiSinisto.CTT_PURO_GESTIONARIO;
        } else if (FunctionUtils.genericBetween(0, 100, cid, Boolean.FALSE) && nostroTrasportatoCliente.apply(sinis.getDannoRca().getTerzeParti())
                && (nostroTrasportatoCliente.apply(sinis.getDannoRca().getTerzeParti())
                || nostroTrasportatoControparte.apply(sinis.getDannoRca().getTerzeParti()))) {
            return TipiSinisto.CTT_PURO_CONCORSUALE;
        } else if (cid == 100 && nostroTrasportatoControparte.apply(sinis.getDannoRca().getTerzeParti())) {
            return TipiSinisto.CTT_PURO_DEBITORIO;
        } else {
            return TipiSinisto.RCA;
        }
    };

    protected Function<SinistroRcaDO, TipiSinisto> IS_IN_ITALIA_AND_CARD_CONTRAENTE_CONTROPARTE = sinis -> {
        final Boolean isCard = isInItalia.apply(sinis)
                && isConvenzioneCARD.apply(sinis.getDannoRca().getAnagraficaDanniCliente().getAnagrafica())
                && sinis.getDannoRca().getAnagraficaDanniControparte()
                .stream()
                .map(AnagraficaDanniDO::getAnagrafica)
                .map(isConvenzioneCARD)
                .anyMatch(e -> Boolean.TRUE);

        if(isCard) {
            return GET_BY_CID_PRESENTE_FULL.apply(sinis);
        } else {
            return GET_BY_CID_AND_PRESENTE.apply(sinis);
        }
    };

    protected Function<SinistroRcaDO,TipiSinisto> IS_IN_ITALIA_AND_CTT_CONTRAENTE_CONTROPARTE = sinis -> {
        final Boolean result = isInItalia.apply(sinis)
                && isConvenzioneCTT.apply(sinis.getDannoRca().getAnagraficaDanniCliente().getAnagrafica())
                && sinis.getDannoRca().getAnagraficaDanniControparte()
                .stream()
                .map(AnagraficaDanniDO::getAnagrafica)
                .map(isConvenzioneCTT)
                .anyMatch(e -> Boolean.TRUE);

        if(result) {
            return GET_BY_CID_AND_PRESENTE.apply(sinis);
        } else {
            return TipiSinisto.RCA;
        }
    };

    protected Function<SinistroRcaDO,TipiSinisto> NUM_VEICOLI_OR_COLLISIONE = sinis -> {
        final Boolean evaluate = sinis.getEventoRca().getNumVeicoli() > 2 || sinis.getEventoRca().getCollisione() == Boolean.FALSE;
        if(evaluate) {
            return IS_IN_ITALIA_AND_CTT_CONTRAENTE_CONTROPARTE.apply(sinis);
        } else {
            return IS_IN_ITALIA_AND_CARD_CONTRAENTE_CONTROPARTE.apply(sinis);
        }
    };

    public Function<BaseSinistroDO, Boolean> isRca = sinistro -> sinistro.getSegnalazione().getGaranziaSelected().equals(MsaCostanti.COD_GARANZIA_RCA);
}