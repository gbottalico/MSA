package msa.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "compagnie")
public class CompagniaDBO {
    @Id
    private String id;
    @Field("DescrizioneCompagnia")
    private String descrizione;
    @Field("ConvenzioneCid")
    private Character convenzioneCid;
    @Field("Estera")
    private Character estera;
    @Field("Data_in_Card")
    private String dataInCard;
    @Field("Data_OUT_Card")
    private String dataOutCard;
    @Field("FlagLiquidazioneCoatta")
    private Integer liquidazioneCoatta;
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

    public Character getConvenzioneCid() {
        return convenzioneCid;
    }

    public void setConvenzioneCid(Character convenzioneCid) {
        this.convenzioneCid = convenzioneCid;
    }

    public Character getEstera() {
        return estera;
    }

    public void setEstera(Character estera) {
        this.estera = estera;
    }

    public String getDataInCard() {
        return dataInCard;
    }

    public void setDataInCard(String dataInCard) {
        this.dataInCard = dataInCard;
    }

    public String getDataOutCard() {
        return dataOutCard;
    }

    public void setDataOutCard(String dataOutCard) {
        this.dataOutCard = dataOutCard;
    }

    public Integer getLiquidazioneCoatta() {
        return liquidazioneCoatta;
    }

    public void setLiquidazioneCoatta(Integer liquidazioneCoatta) {
        this.liquidazioneCoatta = liquidazioneCoatta;
    }
}
