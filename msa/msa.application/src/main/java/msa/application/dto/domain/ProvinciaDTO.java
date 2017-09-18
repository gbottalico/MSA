package msa.application.dto.domain;

import java.util.Date;

public class ProvinciaDTO {
	private String codNazione;
	private String codProvincia;
	private Date iniValidita;
	private Date finValidita;
	private String descProvincia;
	private String siglaProv;
	private String codFornitore;

	public String getCodFornitore() {
		return codFornitore;
	}

	public void setCodFornitore(String codFornitore) {
		this.codFornitore = codFornitore;
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

	public Date getIniValidita() {
		return iniValidita;
	}

	public void setIniValidita(Date iniValidita) {
		this.iniValidita = iniValidita;
	}

	public Date getFinValidita() {
		return finValidita;
	}

	public void setFinValidita(Date finValidita) {
		this.finValidita = finValidita;
	}

	public String getDescProvincia() {
		return descProvincia;
	}

	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}

	public String getSiglaProv() {
		return siglaProv;
	}

	public void setSiglaProv(String siglaProv) {
		this.siglaProv = siglaProv;
	}

}
