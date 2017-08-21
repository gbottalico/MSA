package msa.domain.object.sinistro.rca;

import msa.domain.object.sinistro.FullAnagraficaDO;

public class AnagraficaDanniDO {
    private FullAnagraficaDO anagrafica;
    private DanniDO danni;

    public FullAnagraficaDO getAnagrafica() {
        return anagrafica;
    }

    public void setAnagrafica(FullAnagraficaDO anagrafica) {
        this.anagrafica = anagrafica;
    }

    public DanniDO getDanni() {
        return danni;
    }

    public void setDanni(DanniDO danni) {
        this.danni = danni;
    }
}
