package msa.domain.object.sinistro;

public class SinistroInfortuniConducenteDO extends SinistroNoRcaDO{
    private String descrizioneDanni;
    private String osservazioniInfortunato;
    private Boolean conducenteDiverso;
    private FullAnagraficaDO conducente;

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

    public FullAnagraficaDO getConducente() {
        return conducente;
    }

    public void setConducente(FullAnagraficaDO conducente) {
        this.conducente = conducente;
    }
}
