package msa.application.dto.domain;

import java.util.Date;


public class NazioneDTO {

    private String codNazione;
    private Date inizioValidita;
    private Date fineValidita;
    private String descrizione;
    private String sigla;
    private String codFornitore;

    public String getCodFornitore() {
        return codFornitore;
    }

    public void setCodFornitore(String codFornitore) {
        this.codFornitore = codFornitore;
    }

    public String getCodNazione() {
        return codNazione;
    }

    public void setCodNazione(String codNazione) {
        this.codNazione = codNazione;
    }

    public Date getInizioValidita() {
        return inizioValidita;
    }

    public void setInizioValidita(Date inizioValidita) {
        this.inizioValidita = inizioValidita;
    }

    public Date getFineValidita() {
        return fineValidita;
    }

    public void setFineValidita(Date fineValidita) {
        this.fineValidita = fineValidita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
