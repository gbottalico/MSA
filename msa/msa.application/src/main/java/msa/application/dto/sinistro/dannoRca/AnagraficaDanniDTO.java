package msa.application.dto.sinistro.dannoRca;

import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;
import msa.infrastructure.persistence.sinistro.DanniDBO;
import msa.infrastructure.persistence.sinistro.FullAnagraficaDBO;

public class AnagraficaDanniDTO {
    private FullAnagraficaDTO anagrafica;
    private DanniDTO danni;

    public FullAnagraficaDTO getAnagrafica() {
        return anagrafica;
    }

    public void setAnagrafica(FullAnagraficaDTO anagrafica) {
        this.anagrafica = anagrafica;
    }

    public DanniDTO getDanni() {
        return danni;
    }

    public void setDanni(DanniDTO danni) {
        this.danni = danni;
    }
}
