package msa.application.dto.sinistro.anagrafica;

public class FullAnagraficaControparteDTO extends FullAnagraficaDTO {
    private String compagnia;
    private String veicolo; //codice tipo veicolo
    private String targa;
    private Boolean targaEstera;
    private Boolean targaSpeciale;


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
}
