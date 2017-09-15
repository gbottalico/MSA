package msa.domain.object.sinistro;

import msa.domain.object.sinistro.rca.DanniDO;

public class SinistroKaskoDO extends SinistroNoRcaDO{
    private DanniDO danniKasko;
    private String osservazioniCliente;

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

}
