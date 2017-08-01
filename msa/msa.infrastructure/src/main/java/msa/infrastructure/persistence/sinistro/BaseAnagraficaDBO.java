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
    @Field("desComuneNascita")
    private String descComuneNascita;
    @Field("codComuneNascita")
    private String codComuneNascita;
    @Field("dataNascita")
    private Date dataNascita;

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getDescComuneNascita() {
        return descComuneNascita;
    }

    public void setDescComuneNascita(String descComuneNascita) {
        this.descComuneNascita = descComuneNascita;
    }

    public String getCodComuneNascita() {
        return codComuneNascita;
    }

    public void setCodComuneNascita(String codComuneNascita) {
        this.codComuneNascita = codComuneNascita;
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
