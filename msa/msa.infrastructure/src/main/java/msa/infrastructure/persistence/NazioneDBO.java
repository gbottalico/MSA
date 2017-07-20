package msa.infrastructure.persistence;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "nazioni")
public class NazioneDBO {

	@Id
	private String id;

	@Field("SiglaNazione")
	private String sigla;
	@Field("DescrizioneNazione")
	private String descrizioneNazione;
	@Field("CodIso")
	private String codIso;
	@Field("DataInserimento")
	private Date dataInserimento;
	@Field("DataVariazione")
	private Date dataVariazione;
	@Field("CodFornitore")
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescrizioneNazione() {
		return descrizioneNazione;
	}

	public void setDescrizioneNazione(String descrizioneNazione) {
		this.descrizioneNazione = descrizioneNazione;
	}

	public String getCodIso() {
		return codIso;
	}

	public void setCodIso(String codIso) {
		this.codIso = codIso;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataVariazione() {
		return dataVariazione;
	}

	public void setDataVariazione(Date dataVariazione) {
		this.dataVariazione = dataVariazione;
	}

}
