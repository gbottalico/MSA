package msa.application.dto.domain;

import java.util.Date;

public class MezzoComunicazioneDTO {
    private Integer id;
    private String descrizione;
    private Date dataInserimento;
    private Date dataVariazione;
    private String codFornitore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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

    public String getCodFornitore() {
        return codFornitore;
    }

    public void setCodFornitore(String codFornitore) {
        this.codFornitore = codFornitore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MezzoComunicazioneDTO that = (MezzoComunicazioneDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (descrizione != null ? !descrizione.equals(that.descrizione) : that.descrizione != null) return false;
        if (dataInserimento != null ? !dataInserimento.equals(that.dataInserimento) : that.dataInserimento != null)
            return false;
        if (dataVariazione != null ? !dataVariazione.equals(that.dataVariazione) : that.dataVariazione != null)
            return false;
        return codFornitore != null ? codFornitore.equals(that.codFornitore) : that.codFornitore == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        result = 31 * result + (dataInserimento != null ? dataInserimento.hashCode() : 0);
        result = 31 * result + (dataVariazione != null ? dataVariazione.hashCode() : 0);
        result = 31 * result + (codFornitore != null ? codFornitore.hashCode() : 0);
        return result;
    }
}
