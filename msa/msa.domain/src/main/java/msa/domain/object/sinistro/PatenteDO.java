package msa.domain.object.sinistro;

import java.util.Date;

public class PatenteDO {
    private String patente;
    private Date scadenza;

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }
}
