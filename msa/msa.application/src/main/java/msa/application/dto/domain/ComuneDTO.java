package msa.application.dto.domain;

import java.util.Date;
import java.util.List;

public class ComuneDTO {
	private String id;
	private String codLocalita;
	private String codCatasto;
	private String codIstat;
	private String codComune;
	private Date fineValidita;
	private String descrizione;
	private List<String> cap;
	private String codProvincia;

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodLocalita() {
		return codLocalita;
	}

	public void setCodLocalita(String codLocalita) {
		this.codLocalita = codLocalita;
	}

	public String getCodCatasto() {
		return codCatasto;
	}

	public void setCodCatasto(String codCatasto) {
		this.codCatasto = codCatasto;
	}

	public String getCodIstat() {
		return codIstat;
	}

	public void setCodIstat(String codIstat) {
		this.codIstat = codIstat;
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
