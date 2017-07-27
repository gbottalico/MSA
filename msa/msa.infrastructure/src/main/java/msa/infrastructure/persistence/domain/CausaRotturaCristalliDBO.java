package msa.infrastructure.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "causaRotturaCristalli")
public class CausaRotturaCristalliDBO {

    @Id
    private Integer codRottura;
    @Field("DescrizioneCausaRottura")
    private String descrizioneRottura;
    @Field("DataInserimento")
    private Date dataInserimento;
    @Field("DataVariazione")
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
