package msa.application.dto.sinistro.segnalazione;

import msa.application.dto.sinistro.AbstractDTO;
import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;

import java.util.Date;

public class SegnalazioneDTO extends AbstractDTO {
    private static final long serialVersionUID = -4385676517188502365L;
    private FullAnagraficaDTO denunciante;
    private Integer codMezzo;
    private Date dataDenuncia;
    private Date dataOraSinistro;
    private String oraSinistro;
    private Integer codNazione;
    private Integer codProvincia;
    private Integer codComune;
    private Integer cap;
    private Integer tipoStrada;
    private String denominazioneStrada;
    private String civicoStrada;
    private String garanziaSelected;

    public FullAnagraficaDTO getDenunciante() {
        return denunciante;
    }

    public void setDenunciante(FullAnagraficaDTO denunciante) {
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

    public String getOraSinistro() {
        return oraSinistro;
    }

    public void setOraSinistro(String oraSinistro) {
        this.oraSinistro = oraSinistro;
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

    public Integer getTipoStrada() {
        return tipoStrada;
    }

    public void setTipoStrada(Integer tipoStrada) {
        this.tipoStrada = tipoStrada;
    }

    public String getDenominazioneStrada() {
        return denominazioneStrada;
    }

    public void setDenominazioneStrada(String denominazioneStrada) {
        this.denominazioneStrada = denominazioneStrada;
    }

    public String getCivicoStrada() {
        return civicoStrada;
    }

    public void setCivicoStrada(String civicoStrada) {
        this.civicoStrada = civicoStrada;
    }

    public String getGaranziaSelected() {
        return garanziaSelected;
    }

    public void setGaranziaSelected(String garanziaSelected) {
        this.garanziaSelected = garanziaSelected;
    }
}
