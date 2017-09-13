package msa.infrastructure.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ruoli")
public class RuoliDBO {
    @Id
    private Integer id;
    @Field("raggruppamento")
    private String raggruppamento;
    @Field("descrizione")
    private String descrizione;
    @Field("Partita Danno - ASSociato")
    private String pdAss;
    @Field("lesioni")
    private Boolean lesioni;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRaggruppamento() {
        return raggruppamento;
    }

    public void setRaggruppamento(String raggruppamento) {
        this.raggruppamento = raggruppamento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getPdAss() {
        return pdAss;
    }

    public void setPdAss(String pdAss) {
        this.pdAss = pdAss;
    }

    public Boolean getLesioni() {
        return lesioni;
    }

    public void setLesioni(Boolean lesioni) {
        this.lesioni = lesioni;
    }
}
