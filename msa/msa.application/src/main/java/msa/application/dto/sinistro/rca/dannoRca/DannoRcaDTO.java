package msa.application.dto.sinistro.rca.dannoRca;

import msa.application.dto.sinistro.AbstractDTO;
import msa.application.dto.sinistro.anagrafica.AnagraficaTerzePartiDTO;

import java.util.List;

public class DannoRcaDTO  extends AbstractDTO {
    private static final long serialVersionUID = 2999366958849613093L;
    private Boolean lesioniConducente;
    private Boolean conducenteDiverso;
    private AnagraficaDanniDTO anagraficaDanniCliente;
    private List<AnagraficaDanniDTO> anagraficaDanniControparte;
    private List<AnagraficaTerzePartiDTO> terzeParti;


    public Boolean getLesioniConducente() {
        return lesioniConducente;
    }

    public void setLesioniConducente(Boolean lesioniConducente) {
        this.lesioniConducente = lesioniConducente;
    }

    public Boolean getConducenteDiverso() {
        return conducenteDiverso;
    }

    public void setConducenteDiverso(Boolean conducenteDiverso) {
        this.conducenteDiverso = conducenteDiverso;
    }

    public AnagraficaDanniDTO getAnagraficaDanniCliente() {
        return anagraficaDanniCliente;
    }

    public void setAnagraficaDanniCliente(AnagraficaDanniDTO anagraficaDanniCliente) {
        this.anagraficaDanniCliente = anagraficaDanniCliente;
    }

    public List<AnagraficaDanniDTO> getAnagraficaDanniControparte() {
        return anagraficaDanniControparte;
    }

    public void setAnagraficaDanniControparte(List<AnagraficaDanniDTO> anagraficaDanniControparte) {
        this.anagraficaDanniControparte = anagraficaDanniControparte;
    }



    public List<AnagraficaTerzePartiDTO> getTerzeParti() {
        return terzeParti;
    }

    public void setTerzeParti(List<AnagraficaTerzePartiDTO> terzeParti) {
        this.terzeParti = terzeParti;
    }


}

