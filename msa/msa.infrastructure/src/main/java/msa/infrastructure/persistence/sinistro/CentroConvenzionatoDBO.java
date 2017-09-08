package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class CentroConvenzionatoDBO {
    @Field("id")
    private Integer id;
    @Field("denominazione")
    private String denominazione;
    @Field("longitudine")
    private String longitudine;
    @Field("latitudines")
    private String latitudine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }
}
