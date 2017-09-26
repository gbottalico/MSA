package msa.domain.object.documenti;

import msa.domain.object.sinistro.AbstractDO;

import java.util.Date;

/**
 * Created by simon.calabrese on 11/08/2017.
 */
public class DocumentoDO extends AbstractDO {
    private Integer numSinistro;
    private Integer idDocumento;
    private String path;
    private Integer codTipoDocumento;
    private Date dataRicezione;
    private String idDocumentoMsa;

    public Integer getNumSinistro() {
        return numSinistro;
    }

    public void setNumSinistro(Integer numSinistro) {
        this.numSinistro = numSinistro;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getCodTipoDocumento() {
        return codTipoDocumento;
    }

    public void setCodTipoDocumento(Integer codTipoDocumento) {
        this.codTipoDocumento = codTipoDocumento;
    }

    public Date getDataRicezione() {
        return dataRicezione;
    }

    public void setDataRicezione(Date dataRicezione) {
        this.dataRicezione = dataRicezione;
    }

    public String getIdDocumentoMsa() {
        return idDocumentoMsa;
    }

    public void setIdDocumentoMsa(String idDocumentoMsa) {
        this.idDocumentoMsa = idDocumentoMsa;
    }
}
