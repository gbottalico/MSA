package msa.infrastructure.persistence.sinistro;


import org.springframework.data.mongodb.core.mapping.Field;

public class AnagraficaTerzePartiDBO extends FullAnagraficaControparteDBO {
    @Field("note")
    private String note;
    @Field("lesioni")
    private Boolean lesioni;
    @Field("associato")
    private FullAnagraficaControparteDBO associato;


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

    public FullAnagraficaControparteDBO getAssociato() {
        return associato;
    }

    public void setAssociato(FullAnagraficaControparteDBO associato) {
        this.associato = associato;
    }
}
