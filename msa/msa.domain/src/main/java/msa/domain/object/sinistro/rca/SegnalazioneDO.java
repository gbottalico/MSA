package msa.domain.object.sinistro.rca;

import msa.domain.object.sinistro.FullAnagraficaDO;
import msa.domain.object.sinistro.LuogoDO;

import java.util.Date;

public class SegnalazioneDO {
    private FullAnagraficaDO denunciante;
    private Integer codMezzo;
    private Date dataDenuncia;
    private Date dataOraSinistro;
    private String oraSinistro;
    private LuogoDO luogoSinistro;
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

    public LuogoDO getLuogoSinistro() {
        return luogoSinistro;
    }

    public void setLuogoSinistro(LuogoDO luogoSinistro) {
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
