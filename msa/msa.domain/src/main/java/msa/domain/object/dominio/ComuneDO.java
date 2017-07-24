package msa.domain.object.dominio;

import java.util.Date;
import java.util.List;



public class ComuneDO {
	private String id;
	private String codNazione;
	private String codProvincia;
	private String codComune;
	private Date fineValidita;
	private String descrizione;
	private List<String> cap;
	private String codFornitore;
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


}
