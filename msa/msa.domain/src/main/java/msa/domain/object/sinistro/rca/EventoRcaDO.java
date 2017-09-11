package msa.domain.object.sinistro.rca;

import java.util.Date;

public class EventoRcaDO {
    private Boolean collisione;
    private Integer numVeicoli;
    private Boolean interventoAutorita;
    private Integer codAutorita;
    private String comandoAutorita;
    private Date dataDenuncia;

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
