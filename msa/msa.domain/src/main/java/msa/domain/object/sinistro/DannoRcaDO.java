package msa.domain.object.sinistro;

import java.util.List;

public class DannoRcaDO {
    private Boolean lesioniConducente;
    private AnagraficaDanniDO anagraficaDanniCliente;
    private AnagraficaDanniDO anagraficaDanniControparte;
    private List<AnagraficaDanniDO> altreControparti;

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

    public List<AnagraficaDanniDO> getAltreControparti() {
        return altreControparti;
    }

    public void setAltreControparti(List<AnagraficaDanniDO> altreControparti) {
        this.altreControparti = altreControparti;
    }
}
