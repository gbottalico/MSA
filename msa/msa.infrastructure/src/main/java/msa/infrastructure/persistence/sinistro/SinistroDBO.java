package msa.infrastructure.persistence.sinistro;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "sinistri")
public class SinistroDBO {
    @Id
    private String id;
    @Field("numeroPolizza")
    private String numeroPolizza;
    @Field("compagnia")
    private Integer compagnia;
    @Field("nominativoContraente")
    private String nominativoContraente;
    @Field("segnalazione")
    private SegnalazioneDBO segnalazione;
    @Field("eventoRCA")
    private EventoRcaDBO eventoRCA;
    @Field("constatazioneAmichevole")
    private ConstatazioneAmichevoleDBO constatazioneAmichevole;
    @Field("cai")
    private CaiDBO cai;
    @Field("dannoRCA")
    private DanniRcaDBO dannoRca;
    @Field("numSinistroProvv")
    private Integer numSinistroProvv;

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

    public String getNominativoContraente() {
        return nominativoContraente;
    }

    public void setNominativoContraente(String nominativoContraente) {
        this.nominativoContraente = nominativoContraente;
    }

    public SegnalazioneDBO getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(SegnalazioneDBO segnalazione) {
        this.segnalazione = segnalazione;
    }

    public EventoRcaDBO getEventoRCA() {
        return eventoRCA;
    }

    public void setEventoRCA(EventoRcaDBO eventoRCA) {
        this.eventoRCA = eventoRCA;
    }

    public ConstatazioneAmichevoleDBO getConstatazioneAmichevole() {
        return constatazioneAmichevole;
    }

    public void setConstatazioneAmichevole(ConstatazioneAmichevoleDBO constatazioneAmichevole) {
        this.constatazioneAmichevole = constatazioneAmichevole;
    }

    public CaiDBO getCai() {
        return cai;
    }

    public void setCai(CaiDBO cai) {
        this.cai = cai;
    }

    public DanniRcaDBO getDannoRca() {
        return dannoRca;
    }

    public void setDannoRca(DanniRcaDBO dannoRca) {
        this.dannoRca = dannoRca;
    }

    public Integer getNumSinistroProvv() {
        return numSinistroProvv;
    }

    public void setNumSinistroProvv(Integer numSinistroProvv) {
        this.numSinistroProvv = numSinistroProvv;
    }
}
