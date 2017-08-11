package msa.domain.object.sinistro;

import java.util.List;

public class SinistroDO extends AbstractDO {
    private static final long serialVersionUID = 1665918958997727249L;
    private String id;
    private String numeroPolizza;
    private Integer compagnia;
    private String targa;
    private SegnalazioneDO segnalazione;
    private EventoRcaDO eventoRca;
    private ConstatazioneAmichevoleDO constatazioneAmichevole;
    private CaiDO cai;
    private DannoRcaDO dannoRca;
    private List<AnagraficaTerzePartiDO> anagraficaTerzeParti;
    private FullAnagraficaDO contraente;
    private PeritoDO perito;
    private Integer numSinistroProvv;

    public Integer getNumSinistroProvv() {
        return numSinistroProvv;
    }

    public void setNumSinistroProvv(Integer numSinistroProvv) {
        this.numSinistroProvv = numSinistroProvv;
    }

    public FullAnagraficaDO getContraente() {
        return contraente;
    }

    public void setContraente(FullAnagraficaDO contraente) {
        this.contraente = contraente;
    }

    public List<AnagraficaTerzePartiDO> getAnagraficaTerzeParti() {
        return anagraficaTerzeParti;
    }

    public void setAnagraficaTerzeParti(List<AnagraficaTerzePartiDO> anagraficaTerzeParti) {
        this.anagraficaTerzeParti = anagraficaTerzeParti;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public EventoRcaDO getEventoRca() {
        return eventoRca;
    }

    public void setEventoRca(EventoRcaDO eventoRca) {
        this.eventoRca = eventoRca;
    }

    public ConstatazioneAmichevoleDO getConstatazioneAmichevole() {
        return constatazioneAmichevole;
    }

    public void setConstatazioneAmichevole(ConstatazioneAmichevoleDO constatazioneAmichevole) {
        this.constatazioneAmichevole = constatazioneAmichevole;
    }

    public CaiDO getCai() {
        return cai;
    }

    public void setCai(CaiDO cai) {
        this.cai = cai;
    }

    public DannoRcaDO getDannoRca() {
        return dannoRca;
    }

    public void setDannoRca(DannoRcaDO dannoRca) {
        this.dannoRca = dannoRca;
    }

    public PeritoDO getPerito() {
        return perito;
    }

    public void setPerito(PeritoDO perito) {
        this.perito = perito;
    }
}
