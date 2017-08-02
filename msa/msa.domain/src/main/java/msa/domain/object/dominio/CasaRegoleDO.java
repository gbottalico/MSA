package msa.domain.object.dominio;

import java.util.List;

public class CasaRegoleDO {
    private String idCompagnia;
    private String descrizioneCompagnia;
    private List<CampiObbligatoriDO> campiObbligatoriRicerca;
    private Boolean aperturaSenzaCopertura;
    private List<Integer> garanzieDefaultSenzaCopertura;
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

    public List<CampiObbligatoriDO> getCampiObbligatoriRicerca() {
        return campiObbligatoriRicerca;
    }

    public void setCampiObbligatoriRicerca(List<CampiObbligatoriDO> campiObbligatoriRicerca) {
        this.campiObbligatoriRicerca = campiObbligatoriRicerca;
    }

    public Boolean getAperturaSenzaCopertura() {
        return aperturaSenzaCopertura;
    }

    public void setAperturaSenzaCopertura(Boolean aperturaSenzaCopertura) {
        this.aperturaSenzaCopertura = aperturaSenzaCopertura;
    }

    public List<Integer> getGaranzieDefaultSenzaCopertura() {
        return garanzieDefaultSenzaCopertura;
    }

    public void setGaranzieDefaultSenzaCopertura(List<Integer> garanzieDefaultSenzaCopertura) {
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
