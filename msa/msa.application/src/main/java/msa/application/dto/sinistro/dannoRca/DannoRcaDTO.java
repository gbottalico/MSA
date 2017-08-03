package msa.application.dto.sinistro.dannoRca;

import msa.application.dto.sinistro.BaseSinistroDTO;
import msa.application.dto.sinistro.anagrafica.FullAnagraficaControparteDTO;
import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;

import java.io.Serializable;
import java.util.List;

public class DannoRcaDTO  extends BaseSinistroDTO{
    private static final long serialVersionUID = 2999366958849613093L;
    private Boolean lesioniConducente;
    private AnagraficaDanniDTO anagraficaDanniCliente;
    private AnagraficaDanniDTO anagraficaDanniControparte;
    private List<AnagraficaDanniDTO> altreControparti;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getLesioniConducente() {
        return lesioniConducente;
    }

    public void setLesioniConducente(Boolean lesioniConducente) {
        this.lesioniConducente = lesioniConducente;
    }

    public AnagraficaDanniDTO getAnagraficaDanniCliente() {
        return anagraficaDanniCliente;
    }

    public void setAnagraficaDanniCliente(AnagraficaDanniDTO anagraficaDanniCliente) {
        this.anagraficaDanniCliente = anagraficaDanniCliente;
    }

    public AnagraficaDanniDTO getAnagraficaDanniControparte() {
        return anagraficaDanniControparte;
    }

    public void setAnagraficaDanniControparte(AnagraficaDanniDTO anagraficaDanniControparte) {
        this.anagraficaDanniControparte = anagraficaDanniControparte;
    }

    public List<AnagraficaDanniDTO> getAltreControparti() {
        return altreControparti;
    }

    public void setAltreControparti(List<AnagraficaDanniDTO> altreControparti) {
        this.altreControparti = altreControparti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DannoRcaDTO that = (DannoRcaDTO) o;

        if (lesioniConducente != null ? !lesioniConducente.equals(that.lesioniConducente) : that.lesioniConducente != null)
            return false;
        if (anagraficaDanniCliente != null ? !anagraficaDanniCliente.equals(that.anagraficaDanniCliente) : that.anagraficaDanniCliente != null)
            return false;
        if (anagraficaDanniControparte != null ? !anagraficaDanniControparte.equals(that.anagraficaDanniControparte) : that.anagraficaDanniControparte != null)
            return false;
        return altreControparti != null ? altreControparti.equals(that.altreControparti) : that.altreControparti == null;
    }


}

