package msa.domain.object.sinistro;

import msa.domain.object.enums.TipoConvensioneCard;
import msa.domain.object.enums.TipoGestione;

public class FullAnagraficaControparteDO extends FullAnagraficaDO {
    private String compagnia;
    private String desCompagnia;
    private String veicolo;
    private String targa;
    private Boolean targaEstera;
    private Boolean targaSpeciale;
    private Boolean flagCard;
    private String note;
    private Boolean lesioni;
    private FullAnagraficaControparteDO associato;
    private TipoGestione tipoGestione;
    private TipoConvensioneCard tipoConvensioneCard;


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

    public Boolean getTargaEstera() {
        return targaEstera;
    }

    public void setTargaEstera(Boolean targaEstera) {
        this.targaEstera = targaEstera;
    }

    public Boolean getTargaSpeciale() {
        return targaSpeciale;
    }

    public void setTargaSpeciale(Boolean targaSpeciale) {
        this.targaSpeciale = targaSpeciale;
    }

    public Boolean getFlagCard() {
        return flagCard;
    }

    public void setFlagCard(Boolean flagCard) {
        this.flagCard = flagCard;
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

    public FullAnagraficaControparteDO getAssociato() {
        return associato;
    }

    public void setAssociato(FullAnagraficaControparteDO associato) {
        this.associato = associato;
    }

    public TipoConvensioneCard getTipoConvensioneCard() {
        return tipoConvensioneCard;
    }

    public void setTipoConvensioneCard(TipoConvensioneCard tipoConvensioneCard) {
        this.tipoConvensioneCard = tipoConvensioneCard;
    }

	public String getDesCompagnia() {
		return desCompagnia;
	}

	public void setDesCompagnia(String desCompagnia) {
		this.desCompagnia = desCompagnia;
	}
    
    
}
