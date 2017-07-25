package msa.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "tipoTarga")
public class TipoTargaDBO {
    @Id
    private Integer id;
    @Field("descrizione")
    private String descrizione;
    @Field("codAnia")
    private Character codAnia;
    @Field("conNewAge")
    private Integer codNewAge;
    private String codFornitore;

    public String getCodFornitore() {
        return codFornitore;
    }

    public void setCodFornitore(String codFornitore) {
        this.codFornitore = codFornitore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Character getCodAnia() {
        return codAnia;
    }

    public void setCodAnia(Character codAnia) {
        this.codAnia = codAnia;
    }

    public Integer getCodNewAge() {
        return codNewAge;
    }

    public void setCodNewAge(Integer codNewAge) {
        this.codNewAge = codNewAge;
    }
}
