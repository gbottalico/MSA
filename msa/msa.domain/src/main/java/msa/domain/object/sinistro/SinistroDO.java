package msa.domain.object.sinistro;

public class SinistroDO {
    private static final long serialVersionUID = 1665918958997727249L;
    private String id;
    private String numeroPolizza;
    private String compagnia;
    private String targa;
    private String nominativoCliente;
    private SegnalazioneDO segnalazione;
    private EventoRcaDO eventoRca;
    private ConstatazioneAmichevoleDO constatazioneAmichevole;
    private CaiDO cai;
    private DannoRcaDO dannoRca;

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

    public String getNominativoCliente() {
        return nominativoCliente;
    }

    public void setNominativoCliente(String nominativoCliente) {
        this.nominativoCliente = nominativoCliente;
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
}
