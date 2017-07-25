package msa.application.dto.domain;

import java.util.Date;

public class AutoritaDTO {

    private String id;
    private String descrizione;
    private Date dataInserimento;
    private Date dataVariazione;
    private String codFornitore;

    public String getCodFornitore() {
        return codFornitore;
    }

    public void setCodFornitore(String codFornitore) {
        this.codFornitore = codFornitore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoritaDTO that = (AutoritaDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (descrizione != null ? !descrizione.equals(that.descrizione) : that.descrizione != null) return false;
        if (dataInserimento != null ? !dataInserimento.equals(that.dataInserimento) : that.dataInserimento != null)
            return false;
        return dataVariazione != null ? dataVariazione.equals(that.dataVariazione) : that.dataVariazione == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        result = 31 * result + (dataInserimento != null ? dataInserimento.hashCode() : 0);
        result = 31 * result + (dataVariazione != null ? dataVariazione.hashCode() : 0);
        return result;
    }
}
