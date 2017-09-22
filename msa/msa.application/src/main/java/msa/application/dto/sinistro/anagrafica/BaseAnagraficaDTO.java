package msa.application.dto.sinistro.anagrafica;

import msa.application.config.enumerator.TipoPersona;
import msa.application.dto.sinistro.AbstractDTO;
import msa.application.dto.sinistro.LuogoDTO;
import msa.domain.object.enums.TipoGestione;

import java.util.Date;

public class BaseAnagraficaDTO extends AbstractDTO {


    private static final long serialVersionUID = -3128726274922115214L;

    private String nome;
    private String cognome;
    private String codRuolo;
    private Character sesso;
    private String cf;
    private TipoPersona tipoPersona;
    private String ragioneSociale;
    private LuogoDTO luogoNascita;
    private Date dataNascita;
    private TipoGestione tipologiaGestione;

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

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public TipoGestione getTipologiaGestione() {
        return tipologiaGestione;
    }

    public void setTipologiaGestione(TipoGestione tipologiaGestione) {
        this.tipologiaGestione = tipologiaGestione;
    }
}
