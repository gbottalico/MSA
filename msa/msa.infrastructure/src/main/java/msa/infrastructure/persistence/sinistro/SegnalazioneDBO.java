package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class SegnalazioneDBO {
    @Field("denunciante")
    private BaseAnagraficaDBO denunciante;
    @Field("codMezzo")
    private Integer codMezzo;
    @Field("dataDenuncia")
    private Date dataDenuncia;
    @Field("dataOraSinistro")
    private Date dataOraSinistro;
    @Field("codNazione")
    private Integer codNazione;
    @Field("codProvincia")
    private Integer codProvincia;
    @Field("codComune")
    private Integer codComune;
    @Field("cap")
    private Integer cap;
    @Field("indirizzo")
    private String indirizzo;
    @Field("garanziaSelected")
    private String garanziaSelected;


    public BaseAnagraficaDBO getDenunciante() {
        return denunciante;
    }

    public void setDenunciante(BaseAnagraficaDBO denunciante) {
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
}
