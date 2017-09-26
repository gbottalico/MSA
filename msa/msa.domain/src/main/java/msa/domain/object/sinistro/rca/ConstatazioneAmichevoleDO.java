package msa.domain.object.sinistro.rca;

public class ConstatazioneAmichevoleDO extends CaiDO{
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
