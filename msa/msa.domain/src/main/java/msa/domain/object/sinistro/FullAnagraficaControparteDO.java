package msa.domain.object.sinistro;

public class FullAnagraficaControparteDO extends FullAnagraficaDO {
    private String compagnia;
    private String veioolo;
    private String targa;
    private boolean targaEstera;
    private boolean targaSpeciale;



    public String getCompagnia() {
        return compagnia;
    }

    public void setCompagnia(String compagnia) {
        this.compagnia = compagnia;
    }

    public String getVeioolo() {
        return veioolo;
    }

    public void setVeioolo(String veioolo) {
        this.veioolo = veioolo;
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
