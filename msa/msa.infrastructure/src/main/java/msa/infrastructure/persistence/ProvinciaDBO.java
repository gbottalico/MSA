package msa.infrastructure.persistence;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "province")
public class ProvinciaDBO {
	@Id
	private String id;
	@Field("codNazione")
	private int codNazione;
	@Field("codProvincia")
	private int codProvincia;
	@Field("iniValidita")
	private Date iniValidita;
	@Field("finValidita")
	private Date finValidita;
	@Field("desProv")
	private String descProvincia;
	@Field("siglaProv")
	private String siglaProv;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCodNazione() {
		return codNazione;
	}

	public void setCodNazione(int codNazione) {
		this.codNazione = codNazione;
	}

	public int getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(int codProvincia) {
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
