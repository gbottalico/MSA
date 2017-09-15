package msa.domain.object.sinistro;

public class AnagraficaTerzePartiDO extends FullAnagraficaControparteDO {
    private String note;
    private Boolean lesioni;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getLesioni() {
        return lesioni;
    }

    public void setLesioni(Boolean lesioni) {
        this.lesioni = lesioni;
    }
}
