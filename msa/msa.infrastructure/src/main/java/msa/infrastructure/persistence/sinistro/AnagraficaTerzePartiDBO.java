package msa.infrastructure.persistence.sinistro;


import org.springframework.data.mongodb.core.mapping.Field;

public class AnagraficaTerzePartiDBO extends FullAnagraficaDBO {
    @Field("note")
    private String note;
    @Field("lesioni")
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
