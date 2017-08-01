package msa.application.dto.ricerca;

import msa.application.dto.domain.CompagniaDTO;
import java.util.List;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
public class PolizzeDTO {
    private String numeroPolizza;
    //compagnia già presente a FE poichè fa la ricerca
    private String targa;
    private String nominativoContraente;

    private List<SinistroPolizzaDTO> sinistri;

    public String getNumeroPolizza() {
        return numeroPolizza;
    }

    public void setNumeroPolizza(String numeroPolizza) {
        this.numeroPolizza = numeroPolizza;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getNominativoContraente() {
        return nominativoContraente;
    }

    public void setNominativoContraente(String nominativoContraente) {
        this.nominativoContraente = nominativoContraente;
    }

    public List<SinistroPolizzaDTO> getSinistri() {
        return sinistri;
    }

    public void setSinistri(List<SinistroPolizzaDTO> sinistri) {
        this.sinistri = sinistri;
    }
}
