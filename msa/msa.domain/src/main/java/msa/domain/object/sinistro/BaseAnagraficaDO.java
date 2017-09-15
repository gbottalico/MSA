package msa.domain.object.sinistro;

import java.io.Serializable;
import java.util.Date;

public class BaseAnagraficaDO implements Serializable{
    private String nome;
    private String cognome;
    private String codRuolo;
    private LuogoDO luogoNascita;
    private Date dataNascita;
    private String cf;
    private String tipoPersona;
    private String ragioneSociale;
    private Character sesso;


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

    public LuogoDO getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(LuogoDO luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public Character getSesso() {
        return sesso;
    }

    public void setSesso(Character sesso) {
        this.sesso = sesso;
    }
}

