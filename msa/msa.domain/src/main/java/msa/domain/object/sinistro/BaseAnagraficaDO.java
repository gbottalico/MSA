package msa.domain.object.sinistro;

import java.util.Date;

public class BaseAnagraficaDO {
    private String nome;
    private String cognome;
    private String codRuolo;
    private Integer codComuneNascita;
    private String descComuneNascita;
    private Date dataNascita;

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    private String cf;

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

