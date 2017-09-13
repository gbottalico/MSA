package msa.domain.object.sinistro.rca;

import msa.domain.object.sinistro.AnagraficaTerzePartiDO;

import java.util.List;

public class DannoRcaDO {
    private Boolean lesioniConducente;
    private Boolean conducenteDiverso;
    private AnagraficaDanniDO anagraficaDanniCliente;
    private List<AnagraficaDanniDO> anagraficaDanniControparte;
    private List<AnagraficaTerzePartiDO> terzeParti;

    public Boolean getLesioniConducente() {
        return lesioniConducente;
    }

    public void setLesioniConducente(Boolean lesioniConducente) {
        this.lesioniConducente = lesioniConducente;
    }

    public AnagraficaDanniDO getAnagraficaDanniCliente() {
        return anagraficaDanniCliente;
    }

    public void setAnagraficaDanniCliente(AnagraficaDanniDO anagraficaDanniCliente) {
        this.anagraficaDanniCliente = anagraficaDanniCliente;
    }

    public List<AnagraficaDanniDO> getAnagraficaDanniControparte() {
        return anagraficaDanniControparte;
    }

    public void setAnagraficaDanniControparte(List<AnagraficaDanniDO> anagraficaDanniControparte) {
        this.anagraficaDanniControparte = anagraficaDanniControparte;
    }

    public List<AnagraficaTerzePartiDO> getTerzeParti() {
        return terzeParti;
    }

    public void setTerzeParti(List<AnagraficaTerzePartiDO> terzeParti) {
        this.terzeParti = terzeParti;
    }

    public Boolean getConducenteDiverso() {
        return conducenteDiverso;
    }

    public void setConducenteDiverso(Boolean conducenteDiverso) {
        this.conducenteDiverso = conducenteDiverso;
    }
}
