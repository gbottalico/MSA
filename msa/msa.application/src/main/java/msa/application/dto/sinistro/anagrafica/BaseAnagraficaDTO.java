package msa.application.dto.sinistro.anagrafica;

import java.io.Serializable;
import java.util.Date;

public class BaseAnagraficaDTO implements Serializable{


    private static final long serialVersionUID = -3128726274922115214L;

    private String nome;
    private String cognome;
    private String codRuolo;

    private String cf;
    private Integer codComuneNascita;
    private String descComuneNascita;
    private Date dataNascita;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseAnagraficaDTO that = (BaseAnagraficaDTO) o;

        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (cognome != null ? !cognome.equals(that.cognome) : that.cognome != null) return false;
        if (codRuolo != null ? !codRuolo.equals(that.codRuolo) : that.codRuolo != null) return false;
        if (cf != null ? !cf.equals(that.cf) : that.cf != null) return false;
        if (codComuneNascita != null ? !codComuneNascita.equals(that.codComuneNascita) : that.codComuneNascita != null)
            return false;
        if (descComuneNascita != null ? !descComuneNascita.equals(that.descComuneNascita) : that.descComuneNascita != null)
            return false;
        return dataNascita != null ? dataNascita.equals(that.dataNascita) : that.dataNascita == null;
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (codRuolo != null ? codRuolo.hashCode() : 0);
        result = 31 * result + (cf != null ? cf.hashCode() : 0);
        result = 31 * result + (codComuneNascita != null ? codComuneNascita.hashCode() : 0);
        result = 31 * result + (descComuneNascita != null ? descComuneNascita.hashCode() : 0);
        result = 31 * result + (dataNascita != null ? dataNascita.hashCode() : 0);
        return result;
    }

    public Date getDataNascita() {

        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Integer getCodComuneNascita() {
        return codComuneNascita;
    }

    public void setCodComuneNascita(Integer codComuneNascita) {
        this.codComuneNascita = codComuneNascita;
    }

    public String getDescComuneNascita() {
        return descComuneNascita;
    }

    public void setDescComuneNascita(String descComuneNascita) {
        this.descComuneNascita = descComuneNascita;
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
