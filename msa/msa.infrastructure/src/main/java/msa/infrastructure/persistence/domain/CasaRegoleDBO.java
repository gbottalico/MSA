package msa.infrastructure.persistence.domain;

import msa.infrastructure.persistence.AbstractDBO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "casaRegole")
public class CasaRegoleDBO extends AbstractDBO {
    //TODO da implementare
    @Id
    private String idCompagnia;
    @Field("descrizioneCompagnia")
    private String descrizioneCompagnia;

    @Field("campiObbligatoriRicerca")
    private List<CampiObbligatoriDBO> campiObbligatoriRicerca;
    @Field("aperturaSenzaCopertura")
    private Boolean aperturaSenzaCopertura;
    @Field("garanzieDefaultSenzaCopertura")
    private List<String> garanzieDefaultSenzaCopertura;
    @Field("sita")
    private Boolean sita;
    @Field("sic")
    private Boolean sic;
    @Field("carrozzeriaConvenzionata")
    private Boolean carrozzeriaConvenzionata;
    @Field("incaricoPerito")
    private Boolean incaricoPerito;

    public String getIdCompagnia() {
        return idCompagnia;
    }

    public void setIdCompagnia(String idCompagnia) {
        this.idCompagnia = idCompagnia;
    }

    public String getDescrizioneCompagnia() {
        return descrizioneCompagnia;
    }

    public void setDescrizioneCompagnia(String descrizioneCompagnia) {
        this.descrizioneCompagnia = descrizioneCompagnia;
    }

    public List<CampiObbligatoriDBO> getCampiObbligatoriRicerca() {
        return campiObbligatoriRicerca;
    }

    public void setCampiObbligatoriRicerca(List<CampiObbligatoriDBO> campiObbligatoriRicerca) {
        this.campiObbligatoriRicerca = campiObbligatoriRicerca;
    }

    public Boolean getAperturaSenzaCopertura() {
        return aperturaSenzaCopertura;
    }

    public void setAperturaSenzaCopertura(Boolean aperturaSenzaCopertura) {
        this.aperturaSenzaCopertura = aperturaSenzaCopertura;
    }

    public List<String> getGaranzieDefaultSenzaCopertura() {
        return garanzieDefaultSenzaCopertura;
    }

    public void setGaranzieDefaultSenzaCopertura(List<String> garanzieDefaultSenzaCopertura) {
        this.garanzieDefaultSenzaCopertura = garanzieDefaultSenzaCopertura;
    }

    public Boolean getSita() {
        return sita;
    }

    public void setSita(Boolean sita) {
        this.sita = sita;
    }

    public Boolean getSic() {
        return sic;
    }

    public void setSic(Boolean sic) {
        this.sic = sic;
    }

    public Boolean getCarrozzeriaConvenzionata() {
        return carrozzeriaConvenzionata;
    }

    public void setCarrozzeriaConvenzionata(Boolean carrozzeriaConvenzionata) {
        this.carrozzeriaConvenzionata = carrozzeriaConvenzionata;
    }

    public Boolean getIncaricoPerito() {
        return incaricoPerito;
    }

    public void setIncaricoPerito(Boolean incaricoPerito) {
        this.incaricoPerito = incaricoPerito;
    }
}
