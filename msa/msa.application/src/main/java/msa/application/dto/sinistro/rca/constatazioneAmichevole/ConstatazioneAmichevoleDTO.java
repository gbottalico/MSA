package msa.application.dto.sinistro.rca.constatazioneAmichevole;

import msa.application.dto.sinistro.rca.cai.CaiDTO;

public class ConstatazioneAmichevoleDTO extends CaiDTO {
    private static final long serialVersionUID = 8823486816446117120L;
    private Boolean caCompilata;
    private Boolean caCompilataControparte;

    public Boolean getCaCompilata() {
        return caCompilata;
    }

    public void setCaCompilata(Boolean caCompilata) {
        this.caCompilata = caCompilata;
    }

    public Boolean getCaCompilataControparte() {
        return caCompilataControparte;
    }

    public void setCaCompilataControparte(Boolean caCompilataControparte) {
        this.caCompilataControparte = caCompilataControparte;
    }
}
