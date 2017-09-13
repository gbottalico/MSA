package msa.application.dto.domain;

import java.util.List;

public class CasaRegoleDTO {
    private String idCompagnia;
    private String descrizioneCompagnia;
    private List<CampiObbligatoriDTO> campiObbligatoriRicerca;
    private Boolean aperturaSenzaCopertura;
    private List<String> garanzieDefaultSenzaCopertura;
    private Boolean sita;
    private Boolean sic;
    private Boolean carrozzeriaConvenzionata;
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

    public List<CampiObbligatoriDTO> getCampiObbligatoriRicerca() {
        return campiObbligatoriRicerca;
    }

    public void setCampiObbligatoriRicerca(List<CampiObbligatoriDTO> campiObbligatoriRicerca) {
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
