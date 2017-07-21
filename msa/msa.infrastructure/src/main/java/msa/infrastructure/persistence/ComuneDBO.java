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
	@Field("codLoca")
	private String codLocalita;
	@Field("codCata")
	private String codCatasto;
	@Field("codIstat")
	private String codIstat;
	@Field("codComu")
	private String codComune;
	@Field("finValidita")
	private Date fineValidita;
	@Field("descrizione")
	private String descrizione;
	@Field("cap")
	private List<String> cap;

	@Field("codProv")

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
