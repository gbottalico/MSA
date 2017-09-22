package msa.application.dto.sinistro;

import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;

public class SinistroInfortuniConducenteDTO extends SinistroNoRcaDTO {
    private static final long serialVersionUID = 3026178539055839655L;
    private String descrizioneDanni;
    private String osservazioniInfortunato;
    private Boolean conducenteDiverso;
    private Boolean lesioniConducente;
    private FullAnagraficaDTO conducente;

    public String getDescrizioneDanni() {
        return descrizioneDanni;
    }

    public void setDescrizioneDanni(String descrizioneDanni) {
        this.descrizioneDanni = descrizioneDanni;
    }

    public String getOsservazioniInfortunato() {
        return osservazioniInfortunato;
    }

    public void setOsservazioniInfortunato(String osservazioniInfortunato) {
        this.osservazioniInfortunato = osservazioniInfortunato;
    }

    public Boolean getConducenteDiverso() {
        return conducenteDiverso;
    }

    public void setConducenteDiverso(Boolean conducenteDiverso) {
        this.conducenteDiverso = conducenteDiverso;
    }

    public FullAnagraficaDTO getConducente() {
        return conducente;
    }

    public void setConducente(FullAnagraficaDTO conducente) {
        this.conducente = conducente;
    }

	public Boolean getLesioniConducente() {
		return lesioniConducente;
	}

	public void setLesioniConducente(Boolean lesioniConducente) {
		this.lesioniConducente = lesioniConducente;
	}
    
}
