package msa.application.dto.documenti;

import msa.application.dto.sinistro.AbstractDTO;

import java.util.Date;

/**
 * Created by simon.calabrese on 11/08/2017.
 */
public class DocumentoDTO extends AbstractDTO {
    private static final long serialVersionUID = -337856609749977112L;
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
