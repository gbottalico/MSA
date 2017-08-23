package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SegnalazioneDBO {
    @Field("denunciante")
    private FullAnagraficaDBO denunciante;
    @Field("codMezzo")
    private Integer codMezzo;

    @Field("dataDenuncia")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dataDenuncia;

    @Field("dataOraSinistro")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dataOraSinistro;
    @Field("oraSinistro")
    private String oraSinistro;
    @Field("codNazione")
    private Integer codNazione;
    @Field("codProvincia")
    private Integer codProvincia;
    @Field("codComune")
    private Integer codComune;
    @Field("cap")
    private Integer cap;
    @Field("tipoStrada")
    private Integer tipoStrada;
    @Field("denominazioneStrada")
    private String denominazioneStrada;
    @Field("civicoStrada")
    private String civicoStrada;
    @Field("garanziaSelected")
    private String garanziaSelected;

    public FullAnagraficaDBO getDenunciante() {
        return denunciante;
    }

    public void setDenunciante(FullAnagraficaDBO denunciante) {
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
