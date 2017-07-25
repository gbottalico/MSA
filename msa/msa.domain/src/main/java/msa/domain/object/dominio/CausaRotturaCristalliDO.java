package msa.domain.object.dominio;

import java.util.Date;

public class CausaRotturaCristalliDO {


    private Integer codRottura;

    private String descrizioneRottura;

    private Date dataInserimento;

    private Date dataVariazione;

    private String codFornitore;

    public String getCodFornitore() {
        return codFornitore;
    }

    public void setCodFornitore(String codFornitore) {
        this.codFornitore = codFornitore;
    }

    public Integer getCodRottura() {
        return codRottura;
    }

    public void setCodRottura(Integer codRottura) {
        this.codRottura = codRottura;
    }

    public String getDescrizioneRottura() {
        return descrizioneRottura;
    }

    public void setDescrizioneRottura(String descrizioneRottura) {
        this.descrizioneRottura = descrizioneRottura;
    }

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public Date getDataVariazione() {
        return dataVariazione;
    }

    public void setDataVariazione(Date dataVariazione) {
        this.dataVariazione = dataVariazione;
    }
}
