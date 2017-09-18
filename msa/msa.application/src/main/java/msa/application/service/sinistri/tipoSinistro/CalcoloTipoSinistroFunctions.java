package msa.application.service.sinistri.tipoSinistro;

import msa.domain.Converter.FunctionUtils;
import msa.domain.object.dominio.TipoVeicoloDO;
import msa.domain.object.enums.TipiSinisto;
import msa.domain.object.sinistro.*;
import msa.domain.object.sinistro.rca.AnagraficaDanniDO;
import msa.domain.object.sinistro.rca.IncrociBaremesDO;
import msa.infrastructure.costanti.MsaCostanti;
import msa.infrastructure.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

abstract class CalcoloTipoSinistroFunctions<T extends BaseSinistroDO> {

    @Autowired
    private DomainRepository domainRepository;


    protected final Function<SinistroRcaDO, Boolean> isInItalia = sinis -> Integer.compare(sinis.getSegnalazione().getCodNazione(), MsaCostanti.COD_NAZIONE_ITALIA) == 0;

    protected final Function<SinistroRcaDO, Integer> getPercCID = sinis -> {
        final IncrociBaremesDO incrocio = domainRepository.getColpaByBaremes(sinis.getCai().getBaremesCliente(), sinis.getCai().getBaremesControparte());
        return incrocio.getPercRespCliente();
    };
    protected final Predicate<AnagraficaTerzePartiDO> hasLesioni = AnagraficaTerzePartiDO::getLesioni;

    protected final Function<List<AnagraficaTerzePartiDO>, Boolean> nostroTrasportatoCliente = (anags) -> {
        final Predicate<AnagraficaTerzePartiDO> isTrasportato = e -> e.getCodRuolo().equals(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE);
        final Predicate<AnagraficaTerzePartiDO> concat = hasLesioni.and(isTrasportato);

        return anags.stream().anyMatch(concat);
    };

    protected final Function<List<AnagraficaTerzePartiDO>, Boolean> nostroTrasportatoControparte = (anags) -> {
        final Predicate<AnagraficaTerzePartiDO> isTrasportato = e -> e.getCodRuolo().equals(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CONTROPARTE);
        final Predicate<AnagraficaTerzePartiDO> concat = hasLesioni.and(isTrasportato);
        return anags.stream().anyMatch(concat);
    };


    protected final Function<FullAnagraficaControparteDO, Boolean> isConvenzioneCARD = anagrafica -> {
        final Boolean flagGestioneCTTVeicolo = domainRepository.getElencoTipoVeicoli()
                .parallelStream()
                .filter(e -> e.getId().equals(FunctionUtils.numberConverter(anagrafica.getVeicolo(), Integer::valueOf)))
                .findFirst()
                .map(TipoVeicoloDO::getGestioneCid)
                .orElse(Boolean.FALSE);
        final Boolean flagCard = Optional.ofNullable(anagrafica.getFlagCard()).orElseGet(() -> Boolean.FALSE);
        final Boolean targaEstera = Optional.ofNullable(!anagrafica.getTargaEstera()).orElseGet(() -> Boolean.FALSE);
        return flagCard && flagGestioneCTTVeicolo && !targaEstera;
    };

    protected final Function<FullAnagraficaControparteDO, Boolean> isConvenzioneCTT = anagrafica -> {
        final Boolean flagGestioneCTTVeicolo = domainRepository.getElencoTipoVeicoli()
                .parallelStream()
                .filter(e -> e.getId().equals(FunctionUtils.numberConverter(anagrafica.getVeicolo(), Integer::valueOf)))
                .findFirst()
                .map(TipoVeicoloDO::getGestioneCtt)
                .orElse(Boolean.FALSE);
        final Boolean flagCard = Optional.ofNullable(anagrafica.getFlagCard()).orElseGet(() -> Boolean.FALSE);
        final Boolean targaEstera = Optional.ofNullable(!anagrafica.getTargaEstera()).orElseGet(() -> Boolean.FALSE);
        return flagCard && flagGestioneCTTVeicolo && !targaEstera;
    };


    protected Function<SinistroRcaDO, Integer> isIntaliaAndCardClienteAndControparte = sinis -> {
        final Boolean isCard = isInItalia.apply(sinis)
                && isConvenzioneCARD.apply(sinis.getDannoRca().getAnagraficaDanniCliente().getAnagrafica())
                && sinis.getDannoRca().getAnagraficaDanniControparte()
                .stream()
                .map(AnagraficaDanniDO::getAnagrafica)
                .map(isConvenzioneCARD)
                .anyMatch(e -> Boolean.TRUE);
        return isCard ? 1 : 0;
    };

    protected Function<SinistroRcaDO, Integer> getByCidAndPresente = sinis -> {
        final Integer cid = getPercCID.apply(sinis);
        if (cid == 0 && nostroTrasportatoCliente.apply(sinis.getDannoRca().getTerzeParti())) {
            return TipiSinisto.CTT_PURO_GESTIONARIO.ordinal();
        } else if (FunctionUtils.genericBetween(0, 100, cid, Boolean.FALSE) && nostroTrasportatoCliente.apply(sinis.getDannoRca().getTerzeParti())
                && (nostroTrasportatoCliente.apply(sinis.getDannoRca().getTerzeParti())
                || nostroTrasportatoControparte.apply(sinis.getDannoRca().getTerzeParti()))) {
            return TipiSinisto.CTT_PURO_CONCORSUALE.ordinal();
        } else if (cid == 100 && nostroTrasportatoControparte.apply(sinis.getDannoRca().getTerzeParti())) {
            return TipiSinisto.CTT_PURO_DEBITORIO.ordinal();
        } else {
            return TipiSinisto.RCA.ordinal();
        }
    };

    protected Function<SinistroRcaDO, Integer> getGetPercCIDFull = sinis -> {
        final Integer cid = getPercCID.apply(sinis);
        if (cid == 0) {
            if (nostroTrasportatoCliente.apply(sinis.getDannoRca().getTerzeParti())) {
                if (isConvenzioneCTT.apply(sinis.getDannoRca().getAnagraficaDanniCliente().getAnagrafica())/*manca parte di controparte */) {
                    return TipiSinisto.CID_CTT_GESTIONARIO.ordinal();
                } else {
                    return TipiSinisto.RCA.ordinal();
                }
            } else {
                return TipiSinisto.CID_GESTIONARIO.ordinal();
            }
        } else if (FunctionUtils.genericBetween(0, 100, cid, Boolean.FALSE)) {
            if (nostroTrasportatoCliente.apply(sinis.getDannoRca().getTerzeParti())
                    && nostroTrasportatoControparte.apply(sinis.getDannoRca().getTerzeParti())) {
                if (isConvenzioneCTT.apply(sinis.getDannoRca().getAnagraficaDanniCliente().getAnagrafica())/*manca parte di controparte */) {
                    return TipiSinisto.CID_CTT_CONCORSUALE.ordinal();
                } else {
                    return TipiSinisto.RCA.ordinal();
                }
            } else {
                return TipiSinisto.CID_CONCORSUALE.ordinal();
            }
        } else if (cid == 100) {
            if (nostroTrasportatoControparte.apply(sinis.getDannoRca().getTerzeParti())) {
                if (isConvenzioneCTT.apply(sinis.getDannoRca().getAnagraficaDanniCliente().getAnagrafica())/*manca parte di controparte */) {
                    return TipiSinisto.CID_CTT_DEBITORIO.ordinal();
                } else {
                    return TipiSinisto.RCA.ordinal();
                }
            } else {
                return TipiSinisto.CID_DEBITORIO.ordinal();
            }
        } else {
            return TipiSinisto.RCA.ordinal();
        }
    };

    protected Function<SinistroRcaDO, Integer> isIntaliaAndCTTClienteAndControparte = sinis -> {
        final Boolean isCtt = isInItalia.apply(sinis)
                && isConvenzioneCTT.apply(sinis.getDannoRca().getAnagraficaDanniCliente().getAnagrafica())
                && sinis.getDannoRca().getAnagraficaDanniControparte()
                .stream()
                .map(AnagraficaDanniDO::getAnagrafica)
                .map(isConvenzioneCTT)
                .anyMatch(e -> Boolean.TRUE);
        return isCtt ? 1 : 0;
    };

    protected final Function<SinistroRcaDO, Integer> numVeicoliEqualsDue = rca -> {
        final Boolean numVeicoli = Integer.compare(rca.getEventoRca().getNumVeicoli(), 2) == 0;
        return numVeicoli ? 1 : 0;
    };

    protected Function<BaseSinistroDO, Boolean> isRca = sinistro -> sinistro.getSegnalazione().getGaranziaSelected().equals(MsaCostanti.COD_GARANZIA_RCA);

    protected Function<Integer, TipiSinisto> finalStepRca = key -> Arrays.stream(TipiSinisto.values())
            .filter(e -> e.ordinal() == key)
            .findFirst()
            .orElse(null);
    protected Function<String, TipiSinisto> finalStepOthers = key -> Arrays.stream(TipiSinisto.values())
            .filter(e -> e.getDes().equalsIgnoreCase(key))
            .findFirst()
            .orElse(null);

    protected Function<SinistroRcaDO, Integer> fromSinistroToRca = e -> TipiSinisto.RCA.ordinal();

    protected Function<BaseSinistroDO, String> getGaranzia = e -> e.getSegnalazione().getGaranziaSelected();

    protected Function<T,TipiSinisto> getTipoSinistro = BaseSinistroDO::getTipoSinisto;
}