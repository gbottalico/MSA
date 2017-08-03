package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class BaseAnagraficaDBO {

    @Field("nome")
    private String nome;
    @Field("cognome")
    private String cognome;
    @Field("codRuolo")
    private String codRuolo;
    @Field("cf")
    private String cf;
    @Field("dataNascita")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dataNascita;
    @Field("luogoNascita")
    private LuogoDBO luogoNascita;

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

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public LuogoDBO getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(LuogoDBO luogoNascita) {
        this.luogoNascita = luogoNascita;
    }
}
