package msa.application.dto.domain;

import java.util.Date;

public class ProvinciaDTO {
	private String id;

	private Integer codNazione;

	private Integer codProvincia;
	private Date iniValidita;
	private Date finValidita;
	private String descProvincia;
	private String siglaProv;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCodNazione() {
		return codNazione;
	}

	public void setCodNazione(Integer codNazione) {
		this.codNazione = codNazione;
	}

	public Integer getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(Integer codProvincia) {
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
