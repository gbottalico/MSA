package msa.domain.object.sinistro;

/**
 * Created by simon.calabrese on 23/08/2017.
 */
public class SinistroCristalliDO extends BaseSinistroDO {
    private String desCristalloRotto;
    private String codRotturaCristalli;
    private Boolean flagRiparazione;
    private Boolean flagFattura;
    private Boolean interventoAutorita;

    public String getDesCristalloRotto() {
        return desCristalloRotto;
    }

    public void setDesCristalloRotto(String desCristalloRotto) {
        this.desCristalloRotto = desCristalloRotto;
    }

    public String getCodRotturaCristalli() {
        return codRotturaCristalli;
    }

    public void setCodRotturaCristalli(String codRotturaCristalli) {
        this.codRotturaCristalli = codRotturaCristalli;
    }

    public Boolean getFlagRiparazione() {
        return flagRiparazione;
    }

    public void setFlagRiparazione(Boolean flagRiparazione) {
        this.flagRiparazione = flagRiparazione;
    }

    public Boolean getFlagFattura() {
        return flagFattura;
    }

    public void setFlagFattura(Boolean flagFattura) {
        this.flagFattura = flagFattura;
    }

    public Boolean getInterventoAutorita() {
        return interventoAutorita;
    }

    public void setInterventoAutorita(Boolean interventoAutorita) {
        this.interventoAutorita = interventoAutorita;
    }
}
