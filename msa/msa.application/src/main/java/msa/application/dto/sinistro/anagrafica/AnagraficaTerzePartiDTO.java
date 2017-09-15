package msa.application.dto.sinistro.anagrafica;

public class AnagraficaTerzePartiDTO extends FullAnagraficaControparteDTO {
    private static final long serialVersionUID = -5033305975768471583L;
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
