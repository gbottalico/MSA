package msa.application.service.sinistri.tipoSinistro;

import msa.domain.object.enums.TipiSinisto;
import msa.domain.object.enums.TipoGestione;
import msa.domain.object.sinistro.FullAnagraficaControparteDO;

import java.util.function.Function;

/**
 * Created by simon.calabrese on 15/09/2017.
 */
public class TipoGestioneTreeMap<T extends FullAnagraficaControparteDO> {
    /**
     * @param tipoSinisto
     * @param anag
     * @return pair of codRuolo
     */
    public<T extends FullAnagraficaControparteDO> T calcolaTipoGestione(final TipiSinisto tipoSinisto, T anag) {
        return null;
    }

    private final Function<T,TipoGestione> calcolaTipoGestioneFunc = null;


}
