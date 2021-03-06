package msa.application.dto.ricerca;

import msa.application.dto.sinistro.BaseSinistroDTO;

import java.io.Serializable;
import java.util.List;

public class OutputRicercaDTO implements Serializable {
    private static final long serialVersionUID = 4248903662200448918L;
    private List<FullPolizzaDTO> polizze;
    private List<? extends BaseSinistroDTO> sinistriProvvisori;

    public List<FullPolizzaDTO> getPolizze() {
        return polizze;
    }

    public void setPolizze(List<FullPolizzaDTO> polizze) {
        this.polizze = polizze;
    }

    public List<? extends BaseSinistroDTO> getSinistriProvvisori() {
        return sinistriProvvisori;
    }

    public void setSinistriProvvisori(List<? extends BaseSinistroDTO> sinistriProvvisori) {
        this.sinistriProvvisori = sinistriProvvisori;
    }
}
