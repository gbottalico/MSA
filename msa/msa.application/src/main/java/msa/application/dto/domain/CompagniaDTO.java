package msa.application.dto.domain;


import java.time.LocalDate;
import java.util.Date;

public class CompagniaDTO {

    private String id;
    private String descrizione;
    private Character convenzioneCid;
    private Character estera;
    private LocalDate dataInCard;
    private LocalDate dataOutCard;
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

    public LocalDate getDataInCard() {
        return dataInCard;
    }

    public void setDataInCard(LocalDate dataInCard) {
        this.dataInCard = dataInCard;
    }

    public LocalDate getDataOutCard() {
        return dataOutCard;
    }

    public void setDataOutCard(LocalDate dataOutCard) {
        this.dataOutCard = dataOutCard;
    }

    public Integer getLiquidazioneCoatta() {
        return liquidazioneCoatta;
    }

    public void setLiquidazioneCoatta(Integer liquidazioneCoatta) {
        this.liquidazioneCoatta = liquidazioneCoatta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompagniaDTO that = (CompagniaDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (descrizione != null ? !descrizione.equals(that.descrizione) : that.descrizione != null) return false;
        if (convenzioneCid != null ? !convenzioneCid.equals(that.convenzioneCid) : that.convenzioneCid != null)
            return false;
        if (estera != null ? !estera.equals(that.estera) : that.estera != null) return false;
        if (dataInCard != null ? !dataInCard.equals(that.dataInCard) : that.dataInCard != null) return false;
        if (dataOutCard != null ? !dataOutCard.equals(that.dataOutCard) : that.dataOutCard != null) return false;
        return liquidazioneCoatta != null ? liquidazioneCoatta.equals(that.liquidazioneCoatta) : that.liquidazioneCoatta == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        result = 31 * result + (convenzioneCid != null ? convenzioneCid.hashCode() : 0);
        result = 31 * result + (estera != null ? estera.hashCode() : 0);
        result = 31 * result + (dataInCard != null ? dataInCard.hashCode() : 0);
        result = 31 * result + (dataOutCard != null ? dataOutCard.hashCode() : 0);
        result = 31 * result + (liquidazioneCoatta != null ? liquidazioneCoatta.hashCode() : 0);
        return result;
    }
}
