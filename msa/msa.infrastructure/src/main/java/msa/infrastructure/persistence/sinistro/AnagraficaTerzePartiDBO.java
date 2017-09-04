package msa.infrastructure.persistence.sinistro;

import msa.domain.object.sinistro.FullAnagraficaDO;

public class AnagraficaTerzePartiDBO extends FullAnagraficaDO {
    private String note;
    private String descrizione;
    private Boolean isAnagrafica;

    public Boolean getAnagrafica() {
        return isAnagrafica;
    }

    public void setAnagrafica(Boolean anagrafica) {
        isAnagrafica = anagrafica;
    }

    public String getNote() {

        return note;
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
