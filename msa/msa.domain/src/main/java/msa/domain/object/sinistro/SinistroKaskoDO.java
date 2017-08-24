package msa.domain.object.sinistro;

import msa.domain.object.sinistro.rca.DanniDO;

public class SinistroKaskoDO extends BaseSinistroDO{
    private DanniDO danniKasko;
    private String osservazioniCliente;
    private Boolean interventoAutorita;

    public DanniDO getDanniKasko() {
        return danniKasko;
    }

    public void setDanniKasko(DanniDO danniKasko) {
        this.danniKasko = danniKasko;
    }

    public String getOsservazioniCliente() {
        return osservazioniCliente;
    }

    public void setOsservazioniCliente(String osservazioniCliente) {
        this.osservazioniCliente = osservazioniCliente;
    }

    public Boolean getInterventoAutorita() {
        return interventoAutorita;
    }

    public void setInterventoAutorita(Boolean interventoAutorita) {
        this.interventoAutorita = interventoAutorita;
    }
}
