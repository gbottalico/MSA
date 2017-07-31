package msa.infrastructure.persistence.sinistro;

import msa.domain.object.sinistro.FullAnagraficaDO;

public class AnagraficaTerzePartiDBO extends FullAnagraficaDO {
    private String note;

    public String getNote() {

        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
