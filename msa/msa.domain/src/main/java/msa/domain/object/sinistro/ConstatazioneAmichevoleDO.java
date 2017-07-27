package msa.domain.object.sinistro;

public class ConstatazioneAmichevoleDO {
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
}
