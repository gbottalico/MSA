package msa.infrastructure.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comuni")
public class ComuneDBO {
	@Id
	private String id;
	@Field("codNazione")
	private String codNazione;
	@Field("codProvincia")
	private String codProvincia;
	@Field("codComune")
	private String codComune;
	@Field("finValidita")
	private Date fineValidita;
	@Field("descrizione")
	private String descrizione;
	@Field("cap")
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

	public String getCodFornitore() {
		return codFornitore;
	}

	public void setCodFornitore(String codFornitore) {
		this.codFornitore = codFornitore;
	}
}
