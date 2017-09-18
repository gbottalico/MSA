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
    @Field("luogoSinistro")
    private LuogoDBO luogoSinistro;
    @Field("indirizzo")
    private String indirizzo;
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

    public LuogoDBO getLuogoSinistro() {
        return luogoSinistro;
    }

    public void setLuogoSinistro(LuogoDBO luogoSinistro) {
        this.luogoSinistro = luogoSinistro;
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
