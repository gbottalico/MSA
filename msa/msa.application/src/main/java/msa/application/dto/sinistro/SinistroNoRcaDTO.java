package msa.application.dto.sinistro;

import java.util.Date;

/**
 * Created by simon.calabrese on 15/09/2017.
 */
public abstract class SinistroNoRcaDTO extends BaseSinistroDTO {
    private Boolean interventoAutorita;
    private Integer codAutorita;
    private String comandoAutorita;
    private Date dataDenuncia;

    public Boolean getInterventoAutorita() {
        return interventoAutorita;
    }

    public void setInterventoAutorita(Boolean interventoAutorita) {
        this.interventoAutorita = interventoAutorita;
    }

    public Integer getCodAutorita() {
        return codAutorita;
    }

    public void setCodAutorita(Integer codAutorita) {
        this.codAutorita = codAutorita;
    }

    public String getComandoAutorita() {
        return comandoAutorita;
    }

    public void setComandoAutorita(String comandoAutorita) {
        this.comandoAutorita = comandoAutorita;
    }

    public Date getDataDenuncia() {
        return dataDenuncia;
    }

    public void setDataDenuncia(Date dataDenuncia) {
        this.dataDenuncia = dataDenuncia;
    }
}
