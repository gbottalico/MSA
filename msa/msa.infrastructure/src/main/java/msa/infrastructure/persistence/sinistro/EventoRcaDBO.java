package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class EventoRcaDBO {
    @Field("collisione")
    private Boolean collisione;
    @Field("numVeicoli")
    private Integer numVeicoli;
    @Field("interventoAutorita")
    private Boolean interventoAutorita;
    @Field("codAutorita")
    private Integer codAutorita;
    @Field("comandoAutorita")
    private String comandoAutorita;
    @Field("dataDenuncia")
    private Date dataDenuncia;

    @Field("flagCard")
    private Boolean flagCard;

    public Boolean getCollisione() {
        return collisione;
    }

    public void setCollisione(Boolean collisione) {
        this.collisione = collisione;
    }

    public Integer getNumVeicoli() {
        return numVeicoli;
    }

    public void setNumVeicoli(Integer numVeicoli) {
        this.numVeicoli = numVeicoli;
    }

    public Boolean getInterventoAutorita() {
        return interventoAutorita;
    }

    public void setInterventoAutorita(Boolean interventoAutorita) {
        this.interventoAutorita = interventoAutorita;
    }

    public Integer getCodAutorita() {
        return codAutorita;
    }

    public void setCodAutorita(Integer codAutorita) {
        this.codAutorita = codAutorita;
    }

    public String getComandoAutorita() {
        return comandoAutorita;
    }

    public void setComandoAutorita(String comandoAutorita) {
        this.comandoAutorita = comandoAutorita;
    }

    public Date getDataDenuncia() {
        return dataDenuncia;
    }

    public void setDataDenuncia(Date dataDenuncia) {
        this.dataDenuncia = dataDenuncia;
    }

    public Boolean getFlagCard() {
        return flagCard;
    }

    public void setFlagCard(Boolean flagCard) {
        this.flagCard = flagCard;
    }
}
