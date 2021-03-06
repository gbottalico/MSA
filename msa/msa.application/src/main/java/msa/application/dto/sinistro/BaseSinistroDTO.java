package msa.application.dto.sinistro;

import msa.application.dto.sinistro.anagrafica.FullAnagraficaControparteDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.domain.object.enums.TipiSinisto;

import java.util.List;

/**
 * Created by simon.calabrese on 21/08/2017.
 */
public class BaseSinistroDTO extends AbstractDTO {
    private static final long serialVersionUID = -5338122661424264125L;

    private String id;
    private FullAnagraficaControparteDTO contraente;
    private FullAnagraficaControparteDTO proprietario;
    private String numeroPolizza;
    private String compagnia;
    private String targa;
    private SegnalazioneDTO segnalazione;
    private List<FullAnagraficaControparteDTO> legali;
    private PeritoDTO perito;
    private Integer numSinistroProvv;
    private CentroConvenzionatoDTO centroConvenzionato;
    private TipiSinisto tipoSinisto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FullAnagraficaControparteDTO getContraente() {
        return contraente;
    }

    public void setContraente(FullAnagraficaControparteDTO contraente) {
        this.contraente = contraente;
    }

    public FullAnagraficaControparteDTO getProprietario() {
        return proprietario;
    }

    public void setProprietario(FullAnagraficaControparteDTO proprietario) {
        this.proprietario = proprietario;
    }

    public String getNumeroPolizza() {
        return numeroPolizza;
    }

    public void setNumeroPolizza(String numeroPolizza) {
        this.numeroPolizza = numeroPolizza;
    }

    public String getCompagnia() {
        return compagnia;
    }

    public void setCompagnia(String compagnia) {
        this.compagnia = compagnia;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public SegnalazioneDTO getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(SegnalazioneDTO segnalazione) {
        this.segnalazione = segnalazione;
    }

    public List<FullAnagraficaControparteDTO> getLegali() {
        return legali;
    }

    public void setLegali(List<FullAnagraficaControparteDTO> legali) {
        this.legali = legali;
    }

    public PeritoDTO getPerito() {
        return perito;
    }

    public void setPerito(PeritoDTO perito) {
        this.perito = perito;
    }

    public Integer getNumSinistroProvv() {
        return numSinistroProvv;
    }

    public void setNumSinistroProvv(Integer numSinistroProvv) {
        this.numSinistroProvv = numSinistroProvv;
    }

    public CentroConvenzionatoDTO getCentroConvenzionato() {
        return centroConvenzionato;
    }

    public void setCentroConvenzionato(CentroConvenzionatoDTO centroConvenzionato) {
        this.centroConvenzionato = centroConvenzionato;
    }

    public TipiSinisto getTipoSinisto() {
        return tipoSinisto;
    }

    public void setTipoSinisto(TipiSinisto tipoSinisto) {
        this.tipoSinisto = tipoSinisto;
    }
}
