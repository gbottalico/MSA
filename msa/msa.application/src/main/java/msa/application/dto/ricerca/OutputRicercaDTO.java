package msa.application.dto.ricerca;

import msa.application.dto.sinistro.SinistroRcaDTO;

import java.io.Serializable;
import java.util.List;

public class OutputRicercaDTO implements Serializable {
    private static final long serialVersionUID = 4248903662200448918L;
    private List<PolizzeDTO> polizze;
    private List<SinistroRcaDTO> sinistriProvvisori;

    public List<PolizzeDTO> getPolizze() {
        return polizze;
    }

    public void setPolizze(List<PolizzeDTO> polizze) {
        this.polizze = polizze;
    }

    public List<SinistroRcaDTO> getSinistriProvvisori() {
        return sinistriProvvisori;
    }

    public void setSinistriProvvisori(List<SinistroRcaDTO> sinistriProvvisori) {
        this.sinistriProvvisori = sinistriProvvisori;
    }
}
