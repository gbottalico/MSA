package msa.application.dto.sinistro.segnalazione;

import msa.application.dto.sinistro.AbstractDTO;
import msa.application.dto.sinistro.anagrafica.BaseAnagraficaDTO;

import java.util.Date;

public class SegnalazioneDTO extends AbstractDTO {
    private static final long serialVersionUID = -4385676517188502365L;
    private BaseAnagraficaDTO denunciante;
    private Integer codMezzo;
    private Date dataDenuncia;
    private Date dataOraSinistro;
    private String oraSinistro;
    private Integer codNazione;
    private Integer codProvincia;
    private Integer codComune;
    private Integer cap;
    private String indirizzo;
    private String garanziaSelected;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BaseAnagraficaDTO getDenunciante() {
        return denunciante;
    }

    public void setDenunciante(BaseAnagraficaDTO denunciante) {
        this.denunciante = denunciante;
    }

    public Integer getCodMezzo() {
        return codMezzo;
    }

    public void setCodMezzo(Integer codMezzo) {
        this.codMezzo = codMezzo;
    }

    public Date getDataDenuncia() {
        return dataDenuncia;
    }

    public void setDataDenuncia(Date dataDenuncia) {
        this.dataDenuncia = dataDenuncia;
    }

    public Date getDataOraSinistro() {
        return dataOraSinistro;
    }

    public void setDataOraSinistro(Date dataOraSinistro) {
        this.dataOraSinistro = dataOraSinistro;
    }

    public Integer getCodNazione() {
        return codNazione;
    }

    public void setCodNazione(Integer codNazione) {
        this.codNazione = codNazione;
    }

    public Integer getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(Integer codProvincia) {
        this.codProvincia = codProvincia;
    }

    public Integer getCodComune() {
        return codComune;
    }

    public void setCodComune(Integer codComune) {
        this.codComune = codComune;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getGaranziaSelected() {
        return garanziaSelected;
    }

    public void setGaranziaSelected(String garanziaSelected) {
        this.garanziaSelected = garanziaSelected;

    }

    public String getOraSinistro() {
        return oraSinistro;
    }

    public void setOraSinistro(String oraSinistro) {
        this.oraSinistro = oraSinistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SegnalazioneDTO that = (SegnalazioneDTO) o;

        if (denunciante != null ? !denunciante.equals(that.denunciante) : that.denunciante != null) return false;
        if (codMezzo != null ? !codMezzo.equals(that.codMezzo) : that.codMezzo != null) return false;
        if (dataDenuncia != null ? !dataDenuncia.equals(that.dataDenuncia) : that.dataDenuncia != null) return false;
        if (dataOraSinistro != null ? !dataOraSinistro.equals(that.dataOraSinistro) : that.dataOraSinistro != null)
            return false;
        if (oraSinistro != null ? !oraSinistro.equals(that.oraSinistro) : that.oraSinistro != null) return false;
        if (codNazione != null ? !codNazione.equals(that.codNazione) : that.codNazione != null) return false;
        if (codProvincia != null ? !codProvincia.equals(that.codProvincia) : that.codProvincia != null) return false;
        if (codComune != null ? !codComune.equals(that.codComune) : that.codComune != null) return false;
        if (cap != null ? !cap.equals(that.cap) : that.cap != null) return false;
        if (indirizzo != null ? !indirizzo.equals(that.indirizzo) : that.indirizzo != null) return false;
        return garanziaSelected != null ? garanziaSelected.equals(that.garanziaSelected) : that.garanziaSelected == null;
    }

    @Override
    public int hashCode() {
        int result = denunciante != null ? denunciante.hashCode() : 0;
        result = 31 * result + (codMezzo != null ? codMezzo.hashCode() : 0);
        result = 31 * result + (dataDenuncia != null ? dataDenuncia.hashCode() : 0);
        result = 31 * result + (dataOraSinistro != null ? dataOraSinistro.hashCode() : 0);
        result = 31 * result + (oraSinistro != null ? oraSinistro.hashCode() : 0);
        result = 31 * result + (codNazione != null ? codNazione.hashCode() : 0);
        result = 31 * result + (codProvincia != null ? codProvincia.hashCode() : 0);
        result = 31 * result + (codComune != null ? codComune.hashCode() : 0);
        result = 31 * result + (cap != null ? cap.hashCode() : 0);
        result = 31 * result + (indirizzo != null ? indirizzo.hashCode() : 0);
        result = 31 * result + (garanziaSelected != null ? garanziaSelected.hashCode() : 0);
        return result;
    }
}
