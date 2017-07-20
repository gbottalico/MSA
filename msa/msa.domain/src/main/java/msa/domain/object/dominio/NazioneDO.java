package msa.domain.object.dominio;

import java.util.Date;

public class NazioneDO {

	private String id;
	private String sigla;
	private String descrizioneNazione;
	private String codIso;
	private Date dataInserimento;
	private Date dataVariazione;
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
