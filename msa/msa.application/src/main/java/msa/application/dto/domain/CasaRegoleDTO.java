package msa.application.dto.domain;

import java.util.List;

public class CasaRegoleDTO {
    private String idCompagnia;
    private String descrizioneCompagnia;
    private List<CampiObbligatoriDTO> campiObbligatoriRicerca;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CasaRegoleDTO that = (CasaRegoleDTO) o;

        if (idCompagnia != null ? !idCompagnia.equals(that.idCompagnia) : that.idCompagnia != null) return false;
        if (descrizioneCompagnia != null ? !descrizioneCompagnia.equals(that.descrizioneCompagnia) : that.descrizioneCompagnia != null)
            return false;
        if (campiObbligatoriRicerca != null ? !campiObbligatoriRicerca.equals(that.campiObbligatoriRicerca) : that.campiObbligatoriRicerca != null)
            return false;
        if (aperturaSenzaCopertura != null ? !aperturaSenzaCopertura.equals(that.aperturaSenzaCopertura) : that.aperturaSenzaCopertura != null)
            return false;
        if (garanzieDefaultSenzaCopertura != null ? !garanzieDefaultSenzaCopertura.equals(that.garanzieDefaultSenzaCopertura) : that.garanzieDefaultSenzaCopertura != null)
            return false;
        if (sita != null ? !sita.equals(that.sita) : that.sita != null) return false;
        if (sic != null ? !sic.equals(that.sic) : that.sic != null) return false;
        if (carrozzeriaConvenzionata != null ? !carrozzeriaConvenzionata.equals(that.carrozzeriaConvenzionata) : that.carrozzeriaConvenzionata != null)
            return false;
        return incaricoPerito != null ? incaricoPerito.equals(that.incaricoPerito) : that.incaricoPerito == null;
    }

    @Override
    public int hashCode() {
        int result = idCompagnia != null ? idCompagnia.hashCode() : 0;
        result = 31 * result + (descrizioneCompagnia != null ? descrizioneCompagnia.hashCode() : 0);
        result = 31 * result + (campiObbligatoriRicerca != null ? campiObbligatoriRicerca.hashCode() : 0);
        result = 31 * result + (aperturaSenzaCopertura != null ? aperturaSenzaCopertura.hashCode() : 0);
        result = 31 * result + (garanzieDefaultSenzaCopertura != null ? garanzieDefaultSenzaCopertura.hashCode() : 0);
        result = 31 * result + (sita != null ? sita.hashCode() : 0);
        result = 31 * result + (sic != null ? sic.hashCode() : 0);
        result = 31 * result + (carrozzeriaConvenzionata != null ? carrozzeriaConvenzionata.hashCode() : 0);
        result = 31 * result + (incaricoPerito != null ? incaricoPerito.hashCode() : 0);
        return result;
    }
}
