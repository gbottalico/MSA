package msa.domain.object.sinistro;

import msa.domain.object.sinistro.rca.SegnalazioneDO;

import java.util.List;

/**
 * Created by simon.calabrese on 21/08/2017.
 */
public class BaseSinistroDO extends AbstractDO  {
    private String id;
    private FullAnagraficaControparteDO contraente;
    private String numeroPolizza;
    private Integer compagnia;
    private String targa;
    private SegnalazioneDO segnalazione;
    private List<AnagraficaTerzePartiDO> legali;
    private PeritoDO perito;
    private Integer numSinistroProvv;
    private CentroConvenzionatoDO centroConvenzionato;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FullAnagraficaControparteDO getContraente() {
        return contraente;
    }

    public void setContraente(FullAnagraficaControparteDO contraente) {
        this.contraente = contraente;
    }

    public String getNumeroPolizza() {
        return numeroPolizza;
    }

    public void setNumeroPolizza(String numeroPolizza) {
        this.numeroPolizza = numeroPolizza;
    }

    public Integer getCompagnia() {
        return compagnia;
    }

    public void setCompagnia(Integer compagnia) {
        this.compagnia = compagnia;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public SegnalazioneDO getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(SegnalazioneDO segnalazione) {
        this.segnalazione = segnalazione;
    }

    public List<AnagraficaTerzePartiDO> getLegali() {
        return legali;
    }

    public void setLegali(List<AnagraficaTerzePartiDO> legali) {
        this.legali = legali;
    }

    public PeritoDO getPerito() {
        return perito;
    }

    public void setPerito(PeritoDO perito) {
        this.perito = perito;
    }

    public Integer getNumSinistroProvv() {
        return numSinistroProvv;
    }

    public void setNumSinistroProvv(Integer numSinistroProvv) {
        this.numSinistroProvv = numSinistroProvv;
    }

    public CentroConvenzionatoDO getCentroConvenzionato() {
        return centroConvenzionato;
    }

    public void setCentroConvenzionato(CentroConvenzionatoDO centroConvenzionato) {
        this.centroConvenzionato = centroConvenzionato;
    }
}
