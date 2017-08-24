package msa.application.dto.sinistro;

import msa.application.dto.sinistro.rca.dannoRca.DanniDTO;

public class SinistroKaskoDTO extends  BaseSinistroDTO{
    private DanniDTO danniKasko;
    private String osservazioniCliente;
    private Boolean interventoAutorita;

    public DanniDTO getDanniKasko() {
        return danniKasko;
    }

    public void setDanniKasko(DanniDTO danniKasko) {
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
