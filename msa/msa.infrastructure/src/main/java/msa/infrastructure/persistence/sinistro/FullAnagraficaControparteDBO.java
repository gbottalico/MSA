package msa.infrastructure.persistence.sinistro;

import msa.domain.object.enums.TipoGestione;
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

    @Field("flagCard")
    private Boolean flagCard;
    @Field("tipologiaGestione")
    private TipoGestione tipoGestione;


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
}
