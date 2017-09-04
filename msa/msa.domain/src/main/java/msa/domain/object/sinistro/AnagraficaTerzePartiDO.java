package msa.domain.object.sinistro;

public class AnagraficaTerzePartiDO extends FullAnagraficaDO{
    private String note;
    private String descrizione;
    private Boolean isAnagrafica;

    public Boolean getAnagrafica() {
        return isAnagrafica;
    }

    public void setAnagrafica(Boolean anagrafica) {
        isAnagrafica = anagrafica;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
