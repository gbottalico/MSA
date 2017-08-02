package msa.application.dto.sinistro.dannoRca;

import msa.application.dto.sinistro.anagrafica.FullAnagraficaControparteDTO;
import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;

import java.io.Serializable;

public class DannoRcaDTO  implements Serializable{
    private static final long serialVersionUID = 2999366958849613093L;
    private Boolean lesioniConducente;
    private FullAnagraficaDTO anagraficaConducente;
    private FullAnagraficaControparteDTO anagraficaControparte;
    private DanniDTO danniCliente;
    private DanniDTO danniControparte;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getLesioniConducente() {
        return lesioniConducente;
    }

    public void setLesioniConducente(Boolean lesioniConducente) {
        this.lesioniConducente = lesioniConducente;
    }

    public FullAnagraficaDTO getAnagraficaConducente() {
        return anagraficaConducente;
    }

    public void setAnagraficaConducente(FullAnagraficaDTO anagraficaConducente) {
        this.anagraficaConducente = anagraficaConducente;
    }

    public FullAnagraficaControparteDTO getAnagraficaControparte() {
        return anagraficaControparte;
    }

    public void setAnagraficaControparte(FullAnagraficaControparteDTO anagraficaControparte) {
        this.anagraficaControparte = anagraficaControparte;
    }

    public DanniDTO getDanniCliente() {
        return danniCliente;
    }

    public void setDanniCliente(DanniDTO danniCliente) {
        this.danniCliente = danniCliente;
    }

    public DanniDTO getDanniControparte() {
        return danniControparte;
    }

    public void setDanniControparte(DanniDTO danniControparte) {
        this.danniControparte = danniControparte;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DannoRcaDTO that = (DannoRcaDTO) o;

        if (lesioniConducente != null ? !lesioniConducente.equals(that.lesioniConducente) : that.lesioniConducente != null)
            return false;
        if (anagraficaConducente != null ? !anagraficaConducente.equals(that.anagraficaConducente) : that.anagraficaConducente != null)
            return false;
        if (anagraficaControparte != null ? !anagraficaControparte.equals(that.anagraficaControparte) : that.anagraficaControparte != null)
            return false;
        if (danniCliente != null ? !danniCliente.equals(that.danniCliente) : that.danniCliente != null) return false;
        return danniControparte != null ? danniControparte.equals(that.danniControparte) : that.danniControparte == null;
    }


}

