package msa.application.dto.domain;

import java.util.Date;
import java.util.List;

public class ComuneDTO {
	private String id;
	private String codNazione;
	private String codProvincia;
	private String codComune;
	private Date fineValidita;
	private String descrizione;
	private String codFornitore;
	private List<String> cap;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodNazione() {
		return codNazione;
	}
	public void setCodNazione(String codNazione) {
		this.codNazione = codNazione;
	}
	public String getCodProvincia() {
		return codProvincia;
	}
	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}
	public String getCodComune() {
		return codComune;
	}
	public void setCodComune(String codComune) {
		this.codComune = codComune;
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
	public List<String> getCap() {
		return cap;
	}
	public void setCap(List<String> cap) {
		this.cap = cap;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComuneDTO comuneDTO = (ComuneDTO) o;

        if (id != null ? !id.equals(comuneDTO.id) : comuneDTO.id != null) return false;
        if (codNazione != null ? !codNazione.equals(comuneDTO.codNazione) : comuneDTO.codNazione != null) return false;
        if (codProvincia != null ? !codProvincia.equals(comuneDTO.codProvincia) : comuneDTO.codProvincia != null)
            return false;
        if (codComune != null ? !codComune.equals(comuneDTO.codComune) : comuneDTO.codComune != null) return false;
        if (fineValidita != null ? !fineValidita.equals(comuneDTO.fineValidita) : comuneDTO.fineValidita != null)
            return false;
        if (descrizione != null ? !descrizione.equals(comuneDTO.descrizione) : comuneDTO.descrizione != null)
            return false;
        if (codFornitore != null ? !codFornitore.equals(comuneDTO.codFornitore) : comuneDTO.codFornitore != null)
            return false;
        return cap != null ? cap.equals(comuneDTO.cap) : comuneDTO.cap == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (codNazione != null ? codNazione.hashCode() : 0);
        result = 31 * result + (codProvincia != null ? codProvincia.hashCode() : 0);
        result = 31 * result + (codComune != null ? codComune.hashCode() : 0);
        result = 31 * result + (fineValidita != null ? fineValidita.hashCode() : 0);
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        result = 31 * result + (codFornitore != null ? codFornitore.hashCode() : 0);
        result = 31 * result + (cap != null ? cap.hashCode() : 0);
        return result;
    }

    public String getCodFornitore() {
		return codFornitore;
	}

	public void setCodFornitore(String codFornitore) {
		this.codFornitore = codFornitore;
	}
}
