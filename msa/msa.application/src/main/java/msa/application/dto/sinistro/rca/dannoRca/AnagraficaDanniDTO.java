package msa.application.dto.sinistro.rca.dannoRca;

import msa.application.dto.sinistro.AbstractDTO;
import msa.application.dto.sinistro.anagrafica.FullAnagraficaControparteDTO;
import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;

public class AnagraficaDanniDTO extends AbstractDTO {
    private static final long serialVersionUID = 4626830090849854292L;
    private FullAnagraficaControparteDTO anagrafica;
    private DanniDTO danni;

    public FullAnagraficaControparteDTO getAnagrafica() {
        return anagrafica;
    }

    public void setAnagrafica(FullAnagraficaControparteDTO anagrafica) {
        this.anagrafica = anagrafica;
    }

    public DanniDTO getDanni() {
        return danni;
    }

    public void setDanni(DanniDTO danni) {
        this.danni = danni;
    }
}
