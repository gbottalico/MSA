package msa.application.service.sinistri.tipoSinistro;

import msa.application.exceptions.InternalMsaException;
import msa.domain.object.enums.TipoGestione;
import msa.domain.object.sinistro.BaseSinistroDO;

import java.util.Optional;

/**
 * Created by simon.calabrese on 15/09/2017.
 */
public class TipoGestioneTreeMap<T extends BaseSinistroDO> extends CalcoloTipoSinistroFunctions {
    public TipoGestione calcolaTipoGestione(T sinistro) throws Throwable {
        if (Optional.ofNullable(sinistro)
                .map(isRca)
                .orElseThrow(InternalMsaException::new).equals(Boolean.TRUE)) {
            //getTipoSinistro;
        } else {
            return null;
        }

        return null;
    }
}
