package msa.application.dto.sinistro.anagrafica;

import java.util.Date;

public class PatenteDTO {

    private String patente;
    private Date scadenza;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatenteDTO that = (PatenteDTO) o;

        if (patente != null ? !patente.equals(that.patente) : that.patente != null) return false;
        return scadenza != null ? scadenza.equals(that.scadenza) : that.scadenza == null;
    }

    @Override
    public int hashCode() {
        int result = patente != null ? patente.hashCode() : 0;
        result = 31 * result + (scadenza != null ? scadenza.hashCode() : 0);
        return result;
    }

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
