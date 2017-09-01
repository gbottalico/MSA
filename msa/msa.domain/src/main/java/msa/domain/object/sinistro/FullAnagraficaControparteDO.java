package msa.domain.object.sinistro;

public class FullAnagraficaControparteDO extends FullAnagraficaDO {
    private String compagnia;
    private String veicolo;
    private String targa;
    private boolean targaEstera;
    private boolean targaSpeciale;



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

    public boolean isTargaEstera() {
        return targaEstera;
    }

    public void setTargaEstera(boolean targaEstera) {
        this.targaEstera = targaEstera;
    }

    public boolean isTargaSpeciale() {
        return targaSpeciale;
    }

    public void setTargaSpeciale(boolean targaSpeciale) {
        this.targaSpeciale = targaSpeciale;
    }
}
