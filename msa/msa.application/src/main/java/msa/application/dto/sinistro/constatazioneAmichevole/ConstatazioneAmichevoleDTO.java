package msa.application.dto.sinistro.constatazioneAmichevole;

import msa.application.dto.sinistro.BaseSinistroDTO;

public class ConstatazioneAmichevoleDTO extends BaseSinistroDTO{
    private static final long serialVersionUID = 8823486816446117120L;
    private Boolean isCaCompilata;
    private Boolean isCaCompilataControparte;

    public Boolean getCaCompilata() {
        return isCaCompilata;
    }

    public void setCaCompilata(Boolean caCompilata) {
        isCaCompilata = caCompilata;
    }

    public Boolean getCaCompilataControparte() {
        return isCaCompilataControparte;
    }

    public void setCaCompilataControparte(Boolean caCompilataControparte) {
        isCaCompilataControparte = caCompilataControparte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstatazioneAmichevoleDTO that = (ConstatazioneAmichevoleDTO) o;

        if (isCaCompilata != null ? !isCaCompilata.equals(that.isCaCompilata) : that.isCaCompilata != null)
            return false;
        return isCaCompilataControparte != null ? isCaCompilataControparte.equals(that.isCaCompilataControparte) : that.isCaCompilataControparte == null;
    }

    @Override
    public int hashCode() {
        int result = isCaCompilata != null ? isCaCompilata.hashCode() : 0;
        result = 31 * result + (isCaCompilataControparte != null ? isCaCompilataControparte.hashCode() : 0);
        return result;
    }
}
