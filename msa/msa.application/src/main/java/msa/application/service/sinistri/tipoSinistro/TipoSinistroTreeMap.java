package msa.application.service.sinistri.tipoSinistro;

import msa.application.exceptions.InternalMsaException;
import msa.domain.object.sinistro.BaseSinistroDO;
import msa.domain.object.enums.TipiSinisto;
import org.springframework.stereotype.Component;


/**
 * Created by simon.calabrese on 11/09/2017.
 */
@Component
public class TipoSinistroTreeMap<T extends BaseSinistroDO> extends CalcoloTipoSinistroFunctions {

    public TipiSinisto calcolaTipoSinistro(final T sinistro) throws InternalMsaException {
       if(isRca.apply(sinistro).equals(Boolean.TRUE)) {
           return (TipiSinisto) NUM_VEICOLI_OR_COLLISIONE.apply(sinistro);
       } else {
           return null;
       }
    }


}
