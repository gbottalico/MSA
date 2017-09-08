package msa.domain.object.ricerca;

import java.util.Date;

public class BasePolizzaDO {

    private String numeroPolizza;
    private String targa;
    private String nominativoContraente;
    private String stato;
    private Date variazione;
    private Date attivazione;
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
