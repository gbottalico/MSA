package msa.infrastructure.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ruoli")
public class RuoliDBO {
     @Id
    private Integer id;
     @Field("desRuolo")
    private String descrizioneRuolo;
     @Field("lesioni")
     private Boolean lesioni;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizioneRuolo() {
        return descrizioneRuolo;
    }

    public void setDescrizioneRuolo(String descrizioneRuolo) {
        this.descrizioneRuolo = descrizioneRuolo;
    }

    public Boolean getLesioni() {
        return lesioni;
    }

    public void setLesioni(Boolean lesioni) {
        this.lesioni = lesioni;
    }
}
