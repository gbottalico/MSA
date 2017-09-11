package msa.infrastructure.persistence.ricerca;

import msa.infrastructure.persistence.AbstractDBO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "polizze")
public class BasePolizzaDBO extends AbstractDBO{
    @Id
    private String numeroPolizza;
    @Field("targa")
    private String targa;

    @Field("nominativoContraente")
    private String nominativoContraente;
    @Field("stato")
    private String stato;
    @Field("dataVariazione")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

    private Date dataVariazione;


    @Field("dataAttivazione")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

    private Date dataAttivazione;

    @Field("dataScadenza")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

    private Date dataScadenza;


    public String getNumeroPolizza() {
        return numeroPolizza;
    }

    public void setNumeroPolizza(String numeroPolizza) {
        this.numeroPolizza = numeroPolizza;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getNominativoContraente() {
        return nominativoContraente;
    }

    public void setNominativoContraente(String nominativoContraente) {
        this.nominativoContraente = nominativoContraente;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Date getDataVariazione() {
        return dataVariazione;
    }

    public void setDataVariazione(Date dataVariazione) {
        this.dataVariazione = dataVariazione;
    }

    public Date getDataAttivazione() {
        return dataAttivazione;
    }

    public void setDataAttivazione(Date dataAttivazione) {
        this.dataAttivazione = dataAttivazione;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

}
