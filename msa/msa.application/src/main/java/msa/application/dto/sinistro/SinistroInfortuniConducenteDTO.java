package msa.application.dto.sinistro;

import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;

public class SinistroInfortuniConducenteDTO extends SinistroNoRcaDTO {
    private static final long serialVersionUID = 3026178539055839655L;
    private Boolean conducenteDiversoContraente;
    private String descrizioneDanni;
    private String osservazioniInfortunato;
    private FullAnagraficaDTO anagraficaInfortunato;

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

    public FullAnagraficaDTO getAnagraficaInfortunato() {
        return anagraficaInfortunato;
    }

    public void setAnagraficaInfortunato(FullAnagraficaDTO anagraficaInfortunato) {
        this.anagraficaInfortunato = anagraficaInfortunato;
    }
}
