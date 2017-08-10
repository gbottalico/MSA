package msa.application.dto.sinistro.anagrafica;

import msa.application.dto.sinistro.BaseSinistroDTO;
import msa.application.dto.sinistro.LuogoDTO;

import java.io.Serializable;
import java.util.Date;

public class BaseAnagraficaDTO extends BaseSinistroDTO {


    private static final long serialVersionUID = -3128726274922115214L;

    private String nome;
    private String cognome;
    private String codRuolo;
    private Character sesso;
    private String cf;
    private LuogoDTO luogoNascita;
    private Date dataNascita;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodRuolo() {
        return codRuolo;
    }

    public void setCodRuolo(String codRuolo) {
        this.codRuolo = codRuolo;
    }

    public Character getSesso() {
        return sesso;
    }

    public void setSesso(Character sesso) {
        this.sesso = sesso;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public LuogoDTO getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(LuogoDTO luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseAnagraficaDTO that = (BaseAnagraficaDTO) o;

        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (cognome != null ? !cognome.equals(that.cognome) : that.cognome != null) return false;
        if (codRuolo != null ? !codRuolo.equals(that.codRuolo) : that.codRuolo != null) return false;
        if (cf != null ? !cf.equals(that.cf) : that.cf != null) return false;
        if (luogoNascita != null ? !luogoNascita.equals(that.luogoNascita) : that.luogoNascita != null) return false;
        return dataNascita != null ? dataNascita.equals(that.dataNascita) : that.dataNascita == null;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (codRuolo != null ? codRuolo.hashCode() : 0);
        result = 31 * result + (cf != null ? cf.hashCode() : 0);
        result = 31 * result + (luogoNascita != null ? luogoNascita.hashCode() : 0);
        result = 31 * result + (dataNascita != null ? dataNascita.hashCode() : 0);
        return result;
    }
}
