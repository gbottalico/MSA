package msa.domain.object.sinistro;

import java.util.Date;

/**
 * Created by simon.calabrese on 31/07/2017.
 */
public class InputRicercaDO {
    private String cognome;
    private String nome;
    private String tipoPersona;
    private String numeroPolizza;
    private String numeroSinistro;
    private Date dataEvento;
    private String targa;
    private String numeroProvvisorio;
    private String numeroPreapertura;
    private Integer compagnia;


    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getNumeroPolizza() {
        return numeroPolizza;
    }

    public void setNumeroPolizza(String numeroPolizza) {
        this.numeroPolizza = numeroPolizza;
    }

    public String getNumeroSinistro() {
        return numeroSinistro;
    }

    public void setNumeroSinistro(String numeroSinistro) {
        this.numeroSinistro = numeroSinistro;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getNumeroProvvisorio() {
        return numeroProvvisorio;
    }

    public void setNumeroProvvisorio(String numeroProvvisorio) {
        this.numeroProvvisorio = numeroProvvisorio;
    }

    public String getNumeroPreapertura() {
        return numeroPreapertura;
    }

    public void setNumeroPreapertura(String numeroPreapertura) {
        this.numeroPreapertura = numeroPreapertura;
    }

    public Integer getCompagnia() {
        return compagnia;
    }

    public void setCompagnia(Integer compagnia) {
        this.compagnia = compagnia;
    }
}
