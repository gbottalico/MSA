package msa.application.dto.sinistro.segnalazione;

import msa.application.dto.sinistro.AbstractDTO;
import msa.application.dto.sinistro.LuogoDTO;
import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;

import java.util.Date;

public class SegnalazioneDTO extends AbstractDTO {
    private static final long serialVersionUID = -4385676517188502365L;
    private FullAnagraficaDTO denunciante;
    private Integer codMezzo;
    private Date dataDenuncia;
    private Date dataOraSinistro;
    private String oraSinistro;
    private LuogoDTO luogoSinistro;
    private String indirizzo;
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

    public LuogoDTO getLuogoSinistro() {
        return luogoSinistro;
    }

    public void setLuogoSinistro(LuogoDTO luogoSinistro) {
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
