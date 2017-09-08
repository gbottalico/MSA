package msa.domain.object.ricerca;

import java.math.BigDecimal;
import java.util.Date;

public class GaranziaDO {
    private String codice;
    private String descrizione;
    private BigDecimal sommaAssicurata;
    private BigDecimal franchigia;
    private BigDecimal scoperto;
    private BigDecimal massimalePersone;
    private BigDecimal massimaleCose;
    private BigDecimal massimaleVeicolo;
    private Date dataCoperturaGaranzia;
    private Date dataScadenzaGaranzia;
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
