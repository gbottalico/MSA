package msa.domain.object.dominio;

import java.util.List;

public class CasaRegoleDO {
    private String idCompagnia;
    private String descrizioneCompagnia;
    private List<CampiObbligatoriDO> campiObbligatoriRicerca;
    private boolean aperturaSenzaCopertura;
    private List<Integer> garanzieDefaultSenzaCopertura;
    private boolean sita;
    private boolean sic;
    private boolean carrozzeriaConvenzionata;
    private boolean incaricoPerito;

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

    public boolean isAperturaSenzaCopertura() {
        return aperturaSenzaCopertura;
    }

    public void setAperturaSenzaCopertura(boolean aperturaSenzaCopertura) {
        this.aperturaSenzaCopertura = aperturaSenzaCopertura;
    }

    public List<Integer> getGaranzieDefaultSenzaCopertura() {
        return garanzieDefaultSenzaCopertura;
    }

    public void setGaranzieDefaultSenzaCopertura(List<Integer> garanzieDefaultSenzaCopertura) {
        this.garanzieDefaultSenzaCopertura = garanzieDefaultSenzaCopertura;
    }

    public boolean isSita() {
        return sita;
    }

    public void setSita(boolean sita) {
        this.sita = sita;
    }

    public boolean isSic() {
        return sic;
    }

    public void setSic(boolean sic) {
        this.sic = sic;
    }

    public boolean isCarrozzeriaConvenzionata() {
        return carrozzeriaConvenzionata;
    }

    public void setCarrozzeriaConvenzionata(boolean carrozzeriaConvenzionata) {
        this.carrozzeriaConvenzionata = carrozzeriaConvenzionata;
    }

    public boolean isIncaricoPerito() {
        return incaricoPerito;
    }

    public void setIncaricoPerito(boolean incaricoPerito) {
        this.incaricoPerito = incaricoPerito;
    }
}
