package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class FullAnagraficaControparteDBO extends FullAnagraficaDBO {

    @Field("compagnia")
    private String compagnia;
    @Field("veicolo")
    private String veicolo;
    @Field("targa")
    private String targa;
    @Field("estera")
    private Boolean targaEstera;
    @Field("speciale")
    private Boolean targaSpeciale;



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
