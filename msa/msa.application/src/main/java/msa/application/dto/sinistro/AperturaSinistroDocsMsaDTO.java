package msa.application.dto.sinistro;

import java.util.List;

/**
 * Created by simon.calabrese on 26/09/2017.
 */
public class AperturaSinistroDocsMsaDTO extends BaseSinistroDTO {
    private List<String> idDocsMsa;

    public List<String> getIdDocsMsa() {
        return idDocsMsa;
    }

    public void setIdDocsMsa(List<String> idDocsMsa) {
        this.idDocsMsa = idDocsMsa;
    }
}
