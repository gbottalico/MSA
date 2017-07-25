package msa.domain.object.dominio;


import java.util.Date;

public class CompagniaDO {

    private String id;
    private String descrizione;
    private Character convenzioneCid;
    private Character estera;
    private Date dataInCard;
    private Date dataOutCard;
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

    public Date getDataInCard() {
        return dataInCard;
    }

    public void setDataInCard(Date dataInCard) {
        this.dataInCard = dataInCard;
    }

    public Date getDataOutCard() {
        return dataOutCard;
    }

    public void setDataOutCard(Date dataOutCard) {
        this.dataOutCard = dataOutCard;
    }

    public Integer getLiquidazioneCoatta() {
        return liquidazioneCoatta;
    }

    public void setLiquidazioneCoatta(Integer liquidazioneCoatta) {
        this.liquidazioneCoatta = liquidazioneCoatta;
    }
}
