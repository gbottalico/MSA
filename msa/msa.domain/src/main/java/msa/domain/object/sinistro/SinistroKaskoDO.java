package msa.domain.object.sinistro;

import msa.domain.object.sinistro.rca.DanniDO;

public class SinistroKaskoDO extends SinistroNoRcaDO{
    private DanniDO danniKasko;
    private String descrizioneDanni;
    private String osservazioniCliente;
    private Boolean conducenteDiverso;
    private Boolean lesioniConducente;
    private FullAnagraficaDO conducente;

    public DanniDO getDanniKasko() {
        return danniKasko;
    }

    public void setDanniKasko(DanniDO danniKasko) {
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

    public FullAnagraficaDO getConducente() {
        return conducente;
    }

    public void setConducente(FullAnagraficaDO conducente) {
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
