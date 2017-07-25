package msa.application.dto.domain;

import java.util.Date;

public class CausaRotturaCristalliDTO {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CausaRotturaCristalliDTO that = (CausaRotturaCristalliDTO) o;

        if (codRottura != null ? !codRottura.equals(that.codRottura) : that.codRottura != null) return false;
        if (descrizioneRottura != null ? !descrizioneRottura.equals(that.descrizioneRottura) : that.descrizioneRottura != null)
            return false;
        if (dataInserimento != null ? !dataInserimento.equals(that.dataInserimento) : that.dataInserimento != null)
            return false;
        return dataVariazione != null ? dataVariazione.equals(that.dataVariazione) : that.dataVariazione == null;
    }

    @Override
    public int hashCode() {
        int result = codRottura != null ? codRottura.hashCode() : 0;
        result = 31 * result + (descrizioneRottura != null ? descrizioneRottura.hashCode() : 0);
        result = 31 * result + (dataInserimento != null ? dataInserimento.hashCode() : 0);
        result = 31 * result + (dataVariazione != null ? dataVariazione.hashCode() : 0);
        return result;
    }
}
