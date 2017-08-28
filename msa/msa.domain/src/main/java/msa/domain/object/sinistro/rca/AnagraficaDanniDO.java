package msa.domain.object.sinistro.rca;

import msa.domain.object.sinistro.FullAnagraficaControparteDO;

public class AnagraficaDanniDO {
    private FullAnagraficaControparteDO anagrafica;
    private DanniDO danni;

    public FullAnagraficaControparteDO getAnagrafica() {
        return anagrafica;
    }

    public void setAnagrafica(FullAnagraficaControparteDO anagrafica) {
        this.anagrafica = anagrafica;
    }

    public DanniDO getDanni() {
        return danni;
    }

    public void setDanni(DanniDO danni) {
        this.danni = danni;
    }
}
