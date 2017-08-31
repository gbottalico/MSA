package msa.domain.object.sinistro.rca;

import msa.domain.object.sinistro.AnagraficaTerzePartiDO;

import java.util.List;

public class DannoRcaDO {
    private Boolean lesioniConducente;
    private AnagraficaDanniDO anagraficaDanniCliente;
    private AnagraficaDanniDO anagraficaDanniControparte;
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

    public AnagraficaDanniDO getAnagraficaDanniControparte() {
        return anagraficaDanniControparte;
    }

    public void setAnagraficaDanniControparte(AnagraficaDanniDO anagraficaDanniControparte) {
        this.anagraficaDanniControparte = anagraficaDanniControparte;
    }

    public List<AnagraficaTerzePartiDO> getTerzeParti() {
        return terzeParti;
    }

    public void setTerzeParti(List<AnagraficaTerzePartiDO> terzeParti) {
        this.terzeParti = terzeParti;
    }
}
