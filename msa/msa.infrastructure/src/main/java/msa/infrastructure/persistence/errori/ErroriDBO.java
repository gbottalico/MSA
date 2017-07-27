package msa.infrastructure.persistence.errori;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
@Document(collection = "errori")
public class ErroriDBO {
    @Id
    private Integer id;
    @Field(value = "codErrore")
    private String codErrore;
    @Field(value = "testo")
    private String testo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodErrore() {
        return codErrore;
    }

    public void setCodErrore(String codErrore) {
        this.codErrore = codErrore;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
