package msa.domain.object.sinistro;

public class AnagraficaTerzePartiDO extends FullAnagraficaControparteDO {
    private String note;
    private Boolean lesioni;
    private FullAnagraficaControparteDO associato;

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

    public FullAnagraficaControparteDO getAssociato() {
        return associato;
    }

    public void setAssociato(FullAnagraficaControparteDO associato) {
        this.associato = associato;
    }
}
