package msa.infrastructure.persistence.documenti;

import msa.infrastructure.persistence.AbstractDBO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by simon.calabrese on 11/08/2017.
 */
@Document(collection = "documenti")
public class DocumentoDBO extends AbstractDBO {

    @Id
    private Integer idDocumento;
    @Field(value = "numSinistro")
    private Integer numSinistro;
    @Field(value = "path")
    private String path;
    @Field(value = "codTipoDocumento")
    private Integer codTipoDocumento;
    @Field(value = "dataRicezione")
    private Date dataRicezione;

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
}
