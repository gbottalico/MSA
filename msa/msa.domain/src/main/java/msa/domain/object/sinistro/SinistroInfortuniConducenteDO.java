package msa.domain.object.sinistro;

public class SinistroInfortuniConducenteDO extends BaseSinistroDO{
    private Boolean conducenteDiversoContraente;
    private String descrizioneDanni;
    private String osservazioniInfortunato;
    private FullAnagraficaDO anagraficaInfortunato;
    private Boolean interventoAutorita;

    public Boolean getInterventoAutorita() {
        return interventoAutorita;
    }

    public void setInterventoAutorita(Boolean interventoAutorita) {
        this.interventoAutorita = interventoAutorita;
    }

    public Boolean getConducenteDiversoContraente() {
        return conducenteDiversoContraente;
    }

    public void setConducenteDiversoContraente(Boolean conducenteDiversoContraente) {
        this.conducenteDiversoContraente = conducenteDiversoContraente;
    }

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

    public FullAnagraficaDO getAnagraficaInfortunato() {
        return anagraficaInfortunato;
    }

    public void setAnagraficaInfortunato(FullAnagraficaDO anagraficaInfortunato) {
        this.anagraficaInfortunato = anagraficaInfortunato;
    }
}
