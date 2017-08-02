package msa.application.dto.sinistro.eventoRca;

import msa.application.dto.sinistro.BaseSinistroDTO;

import java.util.Date;

public class EventoRcaDTO extends BaseSinistroDTO {
    private static final long serialVersionUID = 1892216877924480979L;
    private Boolean collisione;
    private Integer numVeicoli;
    private Boolean interventoAutorita;
    private Integer codAutorita;
    private String comandoAutorita;
    private Date dataDenuncia;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventoRcaDTO that = (EventoRcaDTO) o;

        if (collisione != null ? !collisione.equals(that.collisione) : that.collisione != null) return false;
        if (numVeicoli != null ? !numVeicoli.equals(that.numVeicoli) : that.numVeicoli != null) return false;
        if (interventoAutorita != null ? !interventoAutorita.equals(that.interventoAutorita) : that.interventoAutorita != null)
            return false;
        if (codAutorita != null ? !codAutorita.equals(that.codAutorita) : that.codAutorita != null) return false;
        if (comandoAutorita != null ? !comandoAutorita.equals(that.comandoAutorita) : that.comandoAutorita != null)
            return false;
        return dataDenuncia != null ? dataDenuncia.equals(that.dataDenuncia) : that.dataDenuncia == null;
    }

    @Override
    public int hashCode() {
        int result = collisione != null ? collisione.hashCode() : 0;
        result = 31 * result + (numVeicoli != null ? numVeicoli.hashCode() : 0);
        result = 31 * result + (interventoAutorita != null ? interventoAutorita.hashCode() : 0);
        result = 31 * result + (codAutorita != null ? codAutorita.hashCode() : 0);
        result = 31 * result + (comandoAutorita != null ? comandoAutorita.hashCode() : 0);
        result = 31 * result + (dataDenuncia != null ? dataDenuncia.hashCode() : 0);
        return result;
    }

    public Boolean getCollisione() {

        return collisione;
    }

    public void setCollisione(Boolean collisione) {
        this.collisione = collisione;
    }

    public Integer getNumVeicoli() {
        return numVeicoli;
    }

    public void setNumVeicoli(Integer numVeicoli) {
        this.numVeicoli = numVeicoli;
    }

    public Boolean getInterventoAutorita() {
        return interventoAutorita;
    }

    public void setInterventoAutorita(Boolean interventoAutorita) {
        this.interventoAutorita = interventoAutorita;
    }

    public Integer getCodAutorita() {
        return codAutorita;
    }

    public void setCodAutorita(Integer codAutorita) {
        this.codAutorita = codAutorita;
    }

    public String getComandoAutorita() {
        return comandoAutorita;
    }

    public void setComandoAutorita(String comandoAutorita) {
        this.comandoAutorita = comandoAutorita;
    }

    public Date getDataDenuncia() {
        return dataDenuncia;
    }

    public void setDataDenuncia(Date dataDenuncia) {
        this.dataDenuncia = dataDenuncia;
    }
}
