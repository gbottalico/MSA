package msa.application.dto.sinistro.anagrafica;

public class AnagraficaTerzePartiDTO extends FullAnagraficaDTO {
    private static final long serialVersionUID = -5033305975768471583L;
    private String note;
    private Boolean isAnagrafica;
    private String descrizione;
    public String getNote() {
        return note;
    }

    public Boolean getAnagrafica() {
        return isAnagrafica;
    }

    public void setAnagrafica(Boolean anagrafica) {
        isAnagrafica = anagrafica;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
