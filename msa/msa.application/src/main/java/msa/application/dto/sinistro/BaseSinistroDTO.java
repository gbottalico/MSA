package msa.application.dto.sinistro;

import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.infrastructure.persistence.sinistro.AnagraficaTerzePartiDBO;

import java.util.List;

/**
 * Created by simon.calabrese on 21/08/2017.
 */
public class BaseSinistroDTO extends AbstractDTO {
    private static final long serialVersionUID = -5338122661424264125L;

    private String id;
    private FullAnagraficaDTO contraente;
    private String numeroPolizza;
    private String compagnia;
    private String targa;
    private SegnalazioneDTO segnalazione;
    private List<AnagraficaTerzePartiDBO> anagraficaTerzeParti; // in rca sono tutte le terze parti più legale. In tutti gli altri casi è solo il legale
    private PeritoDTO perito;
    private Integer numSinistroProvv;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FullAnagraficaDTO getContraente() {
        return contraente;
    }

    public void setContraente(FullAnagraficaDTO contraente) {
        this.contraente = contraente;
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

    public List<AnagraficaTerzePartiDBO> getAnagraficaTerzeParti() {
        return anagraficaTerzeParti;
    }

    public void setAnagraficaTerzeParti(List<AnagraficaTerzePartiDBO> anagraficaTerzeParti) {
        this.anagraficaTerzeParti = anagraficaTerzeParti;
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
}
