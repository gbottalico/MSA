package msa.application.dto.sinistro.dannoRca;

import msa.application.dto.sinistro.AbstractDTO;
import msa.application.dto.sinistro.anagrafica.AnagraficaTerzePartiDTO;

import java.util.List;

public class DannoRcaDTO  extends AbstractDTO {
    private static final long serialVersionUID = 2999366958849613093L;
    private Boolean lesioniConducente;
    private AnagraficaDanniDTO anagraficaDanniCliente;
    private AnagraficaDanniDTO anagraficaDanniControparte;
    private List<AnagraficaTerzePartiDTO> terzeParti;


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

    public List<AnagraficaTerzePartiDTO> getTerzeParti() {
        return terzeParti;
    }

    public void setTerzeParti(List<AnagraficaTerzePartiDTO> terzeParti) {
        this.terzeParti = terzeParti;
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
        return terzeParti != null ? terzeParti.equals(that.terzeParti) : that.terzeParti == null;
    }

    @Override
    public int hashCode() {
        int result = lesioniConducente != null ? lesioniConducente.hashCode() : 0;
        result = 31 * result + (anagraficaDanniCliente != null ? anagraficaDanniCliente.hashCode() : 0);
        result = 31 * result + (anagraficaDanniControparte != null ? anagraficaDanniControparte.hashCode() : 0);
        result = 31 * result + (terzeParti != null ? terzeParti.hashCode() : 0);
        return result;
    }
}

