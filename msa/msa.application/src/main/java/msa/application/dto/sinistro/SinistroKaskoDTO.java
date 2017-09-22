package msa.application.dto.sinistro;

import msa.application.dto.sinistro.anagrafica.FullAnagraficaControparteDTO;
import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;
import msa.application.dto.sinistro.rca.dannoRca.DanniDTO;

public class SinistroKaskoDTO extends  SinistroNoRcaDTO {
    private DanniDTO danniKasko;
    private String descrizioneDanni;
    private String osservazioniCliente;
    private Boolean conducenteDiverso;
    private Boolean lesioniConducente;
    private FullAnagraficaDTO conducente;

    public DanniDTO getDanniKasko() {
        return danniKasko;
    }

    public void setDanniKasko(DanniDTO danniKasko) {
        this.danniKasko = danniKasko;
    }

    public String getOsservazioniCliente() {
        return osservazioniCliente;
    }

    public void setOsservazioniCliente(String osservazioniCliente) {
        this.osservazioniCliente = osservazioniCliente;
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

	public String getDescrizioneDanni() {
		return descrizioneDanni;
	}

	public void setDescrizioneDanni(String descrizioneDanni) {
		this.descrizioneDanni = descrizioneDanni;
	}
    
	
	
}
