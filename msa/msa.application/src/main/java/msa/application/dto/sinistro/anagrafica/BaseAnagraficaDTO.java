package msa.application.dto.sinistro.anagrafica;

import java.io.Serializable;

public class BaseAnagraficaDTO implements Serializable{


    private static final long serialVersionUID = -3128726274922115214L;

    private String nome;
    private String cognome;
    private String codRuolo;

    private String cf;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseAnagraficaDTO that = (BaseAnagraficaDTO) o;

        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (cognome != null ? !cognome.equals(that.cognome) : that.cognome != null) return false;
        if (codRuolo != null ? !codRuolo.equals(that.codRuolo) : that.codRuolo != null) return false;

        return cf != null ? cf.equals(that.cf) : that.cf == null;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (codRuolo != null ? codRuolo.hashCode() : 0);

        result = 31 * result + (cf != null ? cf.hashCode() : 0);
        return result;
    }

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



    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }
}
