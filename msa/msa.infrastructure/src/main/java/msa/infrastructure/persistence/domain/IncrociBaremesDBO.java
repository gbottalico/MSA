package msa.infrastructure.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by simon.calabrese on 10/08/2017.
 */
@Document(collection = "incrociBaremes")
public class IncrociBaremesDBO {
    @Id
    private Integer id;
    @Field(value = "Cod_baremes_cliente")
    private Integer codBaremesCliente;
    @Field(value = "Cod_baremes_controparte")
    private Integer codBaremesControparte;
    @Field(value = "Cod_responsabilita")
    private String codResponsabilita;
    @Field("Per_resp_cliente")
    private Integer percRespCliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodBaremesCliente() {
        return codBaremesCliente;
    }

    public void setCodBaremesCliente(Integer codBaremesCliente) {
        this.codBaremesCliente = codBaremesCliente;
    }

    public Integer getCodBaremesControparte() {
        return codBaremesControparte;
    }

    public void setCodBaremesControparte(Integer codBaremesControparte) {
        this.codBaremesControparte = codBaremesControparte;
    }

    public String getCodResponsabilita() {
        return codResponsabilita;
    }

    public void setCodResponsabilita(String codResponsabilita) {
        this.codResponsabilita = codResponsabilita;
    }

    public Integer getPercRespCliente() {
        return percRespCliente;
    }

    public void setPercRespCliente(Integer percRespCliente) {
        this.percRespCliente = percRespCliente;
    }
}
