package msa.application.dto.ricerca;

import msa.application.dto.sinistro.SinistroDTO;

import java.io.Serializable;

public class OutputRicercaDTO implements Serializable {
    private static final long serialVersionUID = 4248903662200448918L;
    private List<PolizzeDTO> polizze;
    private List<SinistroDTO> sinistriProvvisori;

    public List<PolizzeDTO> getPolizze() {
        return polizze;
    }

    public void setPolizze(List<PolizzeDTO> polizze) {
        this.polizze = polizze;
    }

    public List<SinistroDTO> getSinistriProvvisori() {
        return sinistriProvvisori;
    }

    public void setSinistriProvvisori(List<SinistroDTO> sinistriProvvisori) {
        this.sinistriProvvisori = sinistriProvvisori;
    }
}
