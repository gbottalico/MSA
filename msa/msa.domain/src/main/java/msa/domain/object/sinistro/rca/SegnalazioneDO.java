package msa.domain.object.sinistro.rca;

import msa.domain.object.sinistro.FullAnagraficaDO;

import java.util.Date;

public class SegnalazioneDO {
    private FullAnagraficaDO denunciante;
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

    public FullAnagraficaDO getDenunciante() {
        return denunciante;
    }

    public void setDenunciante(FullAnagraficaDO denunciante) {
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



    public String getGaranziaSelected() {
        return garanziaSelected;
    }

    public void setGaranziaSelected(String garanziaSelected) {
        this.garanziaSelected = garanziaSelected;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
