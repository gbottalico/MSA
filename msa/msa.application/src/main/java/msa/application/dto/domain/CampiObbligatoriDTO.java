package msa.application.dto.domain;

public class CampiObbligatoriDTO {
    private Integer idFE;
    private  Boolean required;

    public Integer getIdFE() {
        return idFE;
    }

    public void setIdFE(Integer idFE) {
        this.idFE = idFE;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}
