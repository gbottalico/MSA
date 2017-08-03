package msa.application.dto.sinistro.constatazioneAmichevole;

import msa.application.dto.sinistro.BaseSinistroDTO;

public class ConstatazioneAmichevoleDTO extends BaseSinistroDTO{
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
