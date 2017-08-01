package msa.infrastructure.persistence.domain;

import org.springframework.data.mongodb.core.mapping.Field;

public class CampiObbligatoriDBO {
    @Field("idFE")
    private String idFE;
    @Field("required")
    private Boolean required;

    public String getIdFE() {
        return idFE;
    }

    public void setIdFE(String idFE) {
        this.idFE = idFE;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}
