package msa.infrastructure.persistence.ricerca;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;

@Document
public class GaranziaDBO {
    @Field("codice")
    private String codice;
    @Field("descrizione")
    private String descrizione;

    @Field("sommaAssicurata")
    private BigDecimal sommaAssicurata;

    @Field("franchigia")
    private BigDecimal franchigia;
    @Field("scoperto")
    private BigDecimal scoperto;
    @Field("massimalePersone")
    private BigDecimal massimalePersone;

    @Field("massimaleCose")
    private BigDecimal massimaleCose;

    @Field("massimaleVeicolo")
    private BigDecimal massimaleVeicolo;

    @Field("dataCoperturaGaranzia")
    private Date dataCoperturaGaranzia;

    @Field("dataScadenzaGaranzia")
    private Date dataScadenzaGaranzia;

    @Field("isRcaConFranchigia")
    private Boolean isRcaConFranchigia;

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getSommaAssicurata() {
        return sommaAssicurata;
    }

    public void setSommaAssicurata(BigDecimal sommaAssicurata) {
        this.sommaAssicurata = sommaAssicurata;
    }

    public BigDecimal getFranchigia() {
        return franchigia;
    }

    public void setFranchigia(BigDecimal franchigia) {
        this.franchigia = franchigia;
    }

    public BigDecimal getScoperto() {
        return scoperto;
    }

    public void setScoperto(BigDecimal scoperto) {
        this.scoperto = scoperto;
    }

    public BigDecimal getMassimalePersone() {
        return massimalePersone;
    }

    public void setMassimalePersone(BigDecimal massimalePersone) {
        this.massimalePersone = massimalePersone;
    }

    public BigDecimal getMassimaleCose() {
        return massimaleCose;
    }

    public void setMassimaleCose(BigDecimal massimaleCose) {
        this.massimaleCose = massimaleCose;
    }

    public BigDecimal getMassimaleVeicolo() {
        return massimaleVeicolo;
    }

    public void setMassimaleVeicolo(BigDecimal massimaleVeicolo) {
        this.massimaleVeicolo = massimaleVeicolo;
    }

    public Date getDataCoperturaGaranzia() {
        return dataCoperturaGaranzia;
    }

    public void setDataCoperturaGaranzia(Date dataCoperturaGaranzia) {
        this.dataCoperturaGaranzia = dataCoperturaGaranzia;
    }

    public Date getDataScadenzaGaranzia() {
        return dataScadenzaGaranzia;
    }

    public void setDataScadenzaGaranzia(Date dataScadenzaGaranzia) {
        this.dataScadenzaGaranzia = dataScadenzaGaranzia;
    }

    public Boolean getRcaConFranchigia() {
        return isRcaConFranchigia;
    }

    public void setRcaConFranchigia(Boolean rcaConFranchigia) {
        isRcaConFranchigia = rcaConFranchigia;
    }
}
