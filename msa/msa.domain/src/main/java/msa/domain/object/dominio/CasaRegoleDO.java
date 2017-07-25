package msa.domain.object.dominio;

public class CasaRegoleDO {
    private String idCompagnia;
    private String descrizioneCompagnia;
    private String[] campiObbligatoriRicerca;
    private boolean aperturaSenzaCopertura;
    private String[] garanzieDefaultSenzaCopertura;
    private boolean sita;
    private boolean sic;
    private boolean carrozzeriaConvenzionata;
    private boolean incaricoPerito;
    private boolean aperturaSxFuoriCopertura;

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

    public String[] getCampiObbligatoriRicerca() {
        return campiObbligatoriRicerca;
    }

    public void setCampiObbligatoriRicerca(String[] campiObbligatoriRicerca) {
        this.campiObbligatoriRicerca = campiObbligatoriRicerca;
    }

    public boolean isAperturaSenzaCopertura() {
        return aperturaSenzaCopertura;
    }

    public void setAperturaSenzaCopertura(boolean aperturaSenzaCopertura) {
        this.aperturaSenzaCopertura = aperturaSenzaCopertura;
    }

    public String[] getGaranzieDefaultSenzaCopertura() {
        return garanzieDefaultSenzaCopertura;
    }

    public void setGaranzieDefaultSenzaCopertura(String[] garanzieDefaultSenzaCopertura) {
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

    public boolean isAperturaSxFuoriCopertura() {
        return aperturaSxFuoriCopertura;
    }

    public void setAperturaSxFuoriCopertura(boolean aperturaSxFuoriCopertura) {
        this.aperturaSxFuoriCopertura = aperturaSxFuoriCopertura;
    }
}
