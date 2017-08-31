package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class FullAnagraficaControparteDBO extends FullAnagraficaDBO {

    @Field("compagnia")
    private String compagnia;
    @Field("veicolo")
    private String veioolo;
    @Field("targa")
    private String targa;
    @Field("estera")
    private boolean targaEstera;
    @Field("speciale")
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
