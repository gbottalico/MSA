package msa.domain.object.ricerca;

import msa.domain.object.sinistro.AbstractDO;

import java.util.Date;

public class BasePolizzaDO extends AbstractDO {

    private String numeroPolizza;
    private String targa;
    private String nominativoContraente;
    private String stato;
    private Date dataVariazione;
    private Date dataAttivazione;
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
