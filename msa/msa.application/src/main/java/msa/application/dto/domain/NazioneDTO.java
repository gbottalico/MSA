package msa.application.dto.domain;

import java.util.Date;


public class NazioneDTO {

	private String id;

	private Date inizioValidita;
	private Date fineValidita;
	private String descrizione;
	private String sigla;
	private String codFornitore;

	public String getCodFornitore() {
		return codFornitore;
	}

	public void setCodFornitore(String codFornitore) {
		this.codFornitore = codFornitore;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getInizioValidita() {
		return inizioValidita;
	}

	public void setInizioValidita(Date inizioValidita) {
		this.inizioValidita = inizioValidita;
	}

	public Date getFineValidita() {
		return fineValidita;
	}

	public void setFineValidita(Date fineValidita) {
		this.fineValidita = fineValidita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NazioneDTO that = (NazioneDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (inizioValidita != null ? !inizioValidita.equals(that.inizioValidita) : that.inizioValidita != null)
            return false;
        if (fineValidita != null ? !fineValidita.equals(that.fineValidita) : that.fineValidita != null) return false;
        if (descrizione != null ? !descrizione.equals(that.descrizione) : that.descrizione != null) return false;
        if (sigla != null ? !sigla.equals(that.sigla) : that.sigla != null) return false;
        return codFornitore != null ? codFornitore.equals(that.codFornitore) : that.codFornitore == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (inizioValidita != null ? inizioValidita.hashCode() : 0);
        result = 31 * result + (fineValidita != null ? fineValidita.hashCode() : 0);
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        result = 31 * result + (sigla != null ? sigla.hashCode() : 0);
        result = 31 * result + (codFornitore != null ? codFornitore.hashCode() : 0);
        return result;
    }
}
