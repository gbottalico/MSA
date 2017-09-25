package msa.application.service.sinistri.tipoSinistro;

import msa.domain.object.enums.TipiSinisto;
import msa.domain.object.enums.TipoConvensioneCard;
import msa.domain.object.enums.TipoGestione;
import msa.domain.object.sinistro.FullAnagraficaControparteDO;
import msa.infrastructure.costanti.MsaCostanti;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static msa.domain.object.enums.TipiSinisto.*;

/**
 * Created by simon.calabrese on 15/09/2017.
 */
public class TipoGestioneTreeMap {

    private static Map<TipiSinisto, List<Pair<String, Pair<TipoGestione, TipoConvensioneCard>>>> tabellaCalcoloTipoGestione = new TreeMap<>();

    static {
        final List<Pair<String, Pair<TipoGestione, TipoConvensioneCard>>> CTT_GESTIONARIO_ROW =
                Arrays.asList(
                        Pair.of(MsaCostanti.COD_RUOLO_CONTRAENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONDUCENTE_NO_PROPR.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CTT)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONTROPARTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)));
        final List<Pair<String, Pair<TipoGestione, TipoConvensioneCard>>> CTT_DEBITORIO_ROW =
                Arrays.asList(
                        Pair.of(MsaCostanti.COD_RUOLO_CONTRAENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONDUCENTE_NO_PROPR.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONTROPARTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.CD, TipoConvensioneCard.CTT)));
        final List<Pair<String, Pair<TipoGestione, TipoConvensioneCard>>> CTT_CONCORSUALE_ROW =
                Arrays.asList(
                        Pair.of(MsaCostanti.COD_RUOLO_CONTRAENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONDUCENTE_NO_PROPR.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CTT)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONTROPARTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.CD, TipoConvensioneCard.CTT)));
        final List<Pair<String, Pair<TipoGestione, TipoConvensioneCard>>> CID_GESTIONARIO_ROW =
                Arrays.asList(
                        Pair.of(MsaCostanti.COD_RUOLO_CONTRAENTE.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONDUCENTE_NO_PROPR.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONTROPARTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)));
        final List<Pair<String, Pair<TipoGestione, TipoConvensioneCard>>> CID_CONCORSUALE_ROW =
                Arrays.asList(
                        Pair.of(MsaCostanti.COD_RUOLO_CONTRAENTE.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONDUCENTE_NO_PROPR.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONTROPARTE.toString(), Pair.of(TipoGestione.CD, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)));
        final List<Pair<String, Pair<TipoGestione, TipoConvensioneCard>>> CID_DEBITORIO_ROW =
                Arrays.asList(
                        Pair.of(MsaCostanti.COD_RUOLO_CONTRAENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONDUCENTE_NO_PROPR.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONTROPARTE.toString(), Pair.of(TipoGestione.CD, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)));
        final List<Pair<String, Pair<TipoGestione, TipoConvensioneCard>>> CID_CTT_GESTIONARIO_ROW =
                Arrays.asList(
                        Pair.of(MsaCostanti.COD_RUOLO_CONTRAENTE.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONDUCENTE_NO_PROPR.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CTT)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONTROPARTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)));
        final List<Pair<String, Pair<TipoGestione, TipoConvensioneCard>>> CID_CTT_CONCORSUALE_ROW =
                Arrays.asList(
                        Pair.of(MsaCostanti.COD_RUOLO_CONTRAENTE.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONDUCENTE_NO_PROPR.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.CG, TipoConvensioneCard.CTT)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONTROPARTE.toString(), Pair.of(TipoGestione.CD, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.CD, TipoConvensioneCard.CTT)));
        final List<Pair<String, Pair<TipoGestione, TipoConvensioneCard>>> CID_CTT_DEBITORIO_ROW =
                Arrays.asList(
                        Pair.of(MsaCostanti.COD_RUOLO_CONTRAENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONDUCENTE_NO_PROPR.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.NC, TipoConvensioneCard.NC)),
                        Pair.of(MsaCostanti.COD_RUOLO_CONTROPARTE.toString(), Pair.of(TipoGestione.CD, TipoConvensioneCard.CID)),
                        Pair.of(MsaCostanti.COD_RUOLO_TERZO_TRASPORTATO_CLIENTE.toString(), Pair.of(TipoGestione.CD, TipoConvensioneCard.CID)));

        tabellaCalcoloTipoGestione.put(CTT_PURO_GESTIONARIO, CTT_GESTIONARIO_ROW);
        tabellaCalcoloTipoGestione.put(CTT_PURO_CONCORSUALE, CTT_CONCORSUALE_ROW);
        tabellaCalcoloTipoGestione.put(CTT_PURO_DEBITORIO, CTT_DEBITORIO_ROW);
        tabellaCalcoloTipoGestione.put(CID_GESTIONARIO, CID_GESTIONARIO_ROW);
        tabellaCalcoloTipoGestione.put(CID_CTT_GESTIONARIO, CID_CTT_GESTIONARIO_ROW);
        tabellaCalcoloTipoGestione.put(CID_CONCORSUALE, CID_CONCORSUALE_ROW);
        tabellaCalcoloTipoGestione.put(CID_CTT_CONCORSUALE, CID_CTT_CONCORSUALE_ROW);
        tabellaCalcoloTipoGestione.put(CID_DEBITORIO, CID_DEBITORIO_ROW);
        tabellaCalcoloTipoGestione.put(CID_CTT_DEBITORIO, CID_CTT_DEBITORIO_ROW);
    }

    public FullAnagraficaControparteDO calcolaTipoGestione(final TipiSinisto tipoSinisto, FullAnagraficaControparteDO anag) {
        final Pair<TipoGestione, TipoConvensioneCard> result = tabellaCalcoloTipoGestione.get(tipoSinisto)
                .parallelStream()
                .filter(e -> e.getKey().equals(anag.getCodRuolo()))
                .map(Pair::getValue)
                .findFirst().orElseGet(() -> Pair.of(TipoGestione.NC, TipoConvensioneCard.NC));

        anag.setTipoGestione(result.getKey());
        anag.setTipoConvensioneCard(result.getValue());
        return anag;
    }
}
