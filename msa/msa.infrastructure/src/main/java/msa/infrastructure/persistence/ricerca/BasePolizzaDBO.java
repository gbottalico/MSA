package msa.infrastructure.persistence.ricerca;

import msa.infrastructure.persistence.AbstractDBO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    @Field("variazione")
    private Date variazione;
    @Field("attivazione")
    private Date attivazione;
    @Field("scadenza")
    private Date scadenza;


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

    public Date getVariazione() {
        return variazione;
    }

    public void setVariazione(Date variazione) {
        this.variazione = variazione;
    }

    public Date getAttivazione() {
        return attivazione;
    }

    public void setAttivazione(Date attivazione) {
        this.attivazione = attivazione;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

}
