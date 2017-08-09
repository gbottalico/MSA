package msa.application.dto.ricerca;

import msa.application.dto.sinistro.BaseSinistroDTO;

import java.util.Date;

public class InputRicercaDTO extends BaseSinistroDTO{
    private static final long serialVersionUID = 6248082871486818955L;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputRicercaDTO that = (InputRicercaDTO) o;

        if (cognome != null ? !cognome.equals(that.cognome) : that.cognome != null) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (tipoPersona != null ? !tipoPersona.equals(that.tipoPersona) : that.tipoPersona != null) return false;
        if (numeroPolizza != null ? !numeroPolizza.equals(that.numeroPolizza) : that.numeroPolizza != null)
            return false;
        if (numeroSinistro != null ? !numeroSinistro.equals(that.numeroSinistro) : that.numeroSinistro != null)
            return false;
        if (dataEvento != null ? !dataEvento.equals(that.dataEvento) : that.dataEvento != null) return false;
        if (targa != null ? !targa.equals(that.targa) : that.targa != null) return false;
        if (numeroProvvisorio != null ? !numeroProvvisorio.equals(that.numeroProvvisorio) : that.numeroProvvisorio != null)
            return false;
        if (numeroPreapertura != null ? !numeroPreapertura.equals(that.numeroPreapertura) : that.numeroPreapertura != null)
            return false;
        return compagnia != null ? compagnia.equals(that.compagnia) : that.compagnia == null;
    }

    @Override
    public int hashCode() {
        int result = cognome != null ? cognome.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (tipoPersona != null ? tipoPersona.hashCode() : 0);
        result = 31 * result + (numeroPolizza != null ? numeroPolizza.hashCode() : 0);
        result = 31 * result + (numeroSinistro != null ? numeroSinistro.hashCode() : 0);
        result = 31 * result + (dataEvento != null ? dataEvento.hashCode() : 0);
        result = 31 * result + (targa != null ? targa.hashCode() : 0);
        result = 31 * result + (numeroProvvisorio != null ? numeroProvvisorio.hashCode() : 0);
        result = 31 * result + (numeroPreapertura != null ? numeroPreapertura.hashCode() : 0);
        result = 31 * result + (compagnia != null ? compagnia.hashCode() : 0);
        return result;
    }}
