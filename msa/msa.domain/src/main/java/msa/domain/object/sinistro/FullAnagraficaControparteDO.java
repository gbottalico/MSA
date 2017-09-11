package msa.domain.object.sinistro;

public class FullAnagraficaControparteDO extends FullAnagraficaDO {
    private String compagnia;
    private String veicolo;
    private String targa;
    private Boolean targaEstera;
    private Boolean targaSpeciale;
    private Boolean flagCard;


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
}
