package msa.application.dto.sinistro.anagrafica;

import msa.domain.object.enums.TipoConvensioneCard;
import msa.domain.object.enums.TipoGestione;

public class FullAnagraficaControparteDTO extends FullAnagraficaDTO {
    private String compagnia;
    private String veicolo; //codice tipo veicolo
    private String targa;
    private Boolean targaEstera;
    private Boolean targaSpeciale;
    private TipoGestione tipoGestione;
    private TipoConvensioneCard tipoConvensioneCard;
    private String note;
    private Boolean lesioni;
    private FullAnagraficaControparteDTO associato;


    public Boolean getTargaEstera() {
        return targaEstera;
    }

    public Boolean getTargaSpeciale() {
        return targaSpeciale;
    }

    public String getCompagnia() {
        return compagnia;
    }

    public void setCompagnia(String compagnia) {
        this.compagnia = compagnia;
    }

    public String getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(String veicolo) {
        this.veicolo = veicolo;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public Boolean isTargaEstera() {
        return targaEstera;
    }

    public void setTargaEstera(Boolean targaEstera) {
        this.targaEstera = targaEstera;
    }

    public Boolean isTargaSpeciale() {
        return targaSpeciale;
    }

    public void setTargaSpeciale(Boolean targaSpeciale) {
        this.targaSpeciale = targaSpeciale;
    }

    public TipoGestione getTipoGestione() {
        return tipoGestione;
    }

    public void setTipoGestione(TipoGestione tipoGestione) {
        this.tipoGestione = tipoGestione;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getLesioni() {
        return lesioni;
    }

    public void setLesioni(Boolean lesioni) {
        this.lesioni = lesioni;
    }

    public FullAnagraficaControparteDTO getAssociato() {
        return associato;
    }

    public void setAssociato(FullAnagraficaControparteDTO associato) {
        this.associato = associato;
    }

    public TipoConvensioneCard getTipoConvensioneCard() {
        return tipoConvensioneCard;
    }

    public void setTipoConvensioneCard(TipoConvensioneCard tipoConvensioneCard) {
        this.tipoConvensioneCard = tipoConvensioneCard;
    }
}
