package msa.application.dto.domain;

import java.util.Arrays;

public class CasaRegoleDTO {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CasaRegoleDTO that = (CasaRegoleDTO) o;

        if (aperturaSenzaCopertura != that.aperturaSenzaCopertura) return false;
        if (sita != that.sita) return false;
        if (sic != that.sic) return false;
        if (carrozzeriaConvenzionata != that.carrozzeriaConvenzionata) return false;
        if (incaricoPerito != that.incaricoPerito) return false;
        if (aperturaSxFuoriCopertura != that.aperturaSxFuoriCopertura) return false;
        if (idCompagnia != null ? !idCompagnia.equals(that.idCompagnia) : that.idCompagnia != null) return false;
        if (descrizioneCompagnia != null ? !descrizioneCompagnia.equals(that.descrizioneCompagnia) : that.descrizioneCompagnia != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(campiObbligatoriRicerca, that.campiObbligatoriRicerca)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(garanzieDefaultSenzaCopertura, that.garanzieDefaultSenzaCopertura);
    }

    @Override
    public int hashCode() {
        int result = idCompagnia != null ? idCompagnia.hashCode() : 0;
        result = 31 * result + (descrizioneCompagnia != null ? descrizioneCompagnia.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(campiObbligatoriRicerca);
        result = 31 * result + (aperturaSenzaCopertura ? 1 : 0);
        result = 31 * result + Arrays.hashCode(garanzieDefaultSenzaCopertura);
        result = 31 * result + (sita ? 1 : 0);
        result = 31 * result + (sic ? 1 : 0);
        result = 31 * result + (carrozzeriaConvenzionata ? 1 : 0);
        result = 31 * result + (incaricoPerito ? 1 : 0);
        result = 31 * result + (aperturaSxFuoriCopertura ? 1 : 0);
        return result;
    }
}
