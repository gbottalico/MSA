package msa.infrastructure.persistence.sinistro;

import msa.infrastructure.persistence.AbstractDBO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "sinistri")
public class SinistroDBO extends AbstractDBO {
    @Id
    private String id;
    @Field("numeroPolizza")
    private String numeroPolizza;
    @Field("compagnia")
    private Integer compagnia;
    @Field("segnalazione")
    private SegnalazioneDBO segnalazione;
    @Field("eventoRCA")
    private EventoRcaDBO eventoRCA;
    @Field("constatazioneAmichevole")
    private ConstatazioneAmichevoleDBO constatazioneAmichevole;
    @Field("cai")
    private CaiDBO cai;
    @Field("dannoRCA")
    private DannoRcaDBO dannoRca;
    @Field("terzeParti")
    private List<AnagraficaTerzePartiDBO> anagraficaTerzeParti;

    @Field("contraente")
    private FullAnagraficaDBO contraente;
    @Field("perito")
    private PeritoDBO perito;

    @Field("numSinistroProvv")
    private Integer numSinistroProvv;
    //INIZIO FURTO INCENDIO
    @Field("sviluppoFiamme")
    private Boolean sviluppoFiamme;
    @Field("responsabilita")
    private Boolean responsabilita;
    @Field("descrizioneDanni")
    private String descrizioneDanni;
    @Field("osservazioniCliente")
    private String osservazioniCliente;
    @Field("interventoAutorita")
    private Boolean interventoAutorita;

    //FINE FURTO INCENDIO
    public FullAnagraficaDBO getContraente() {
        return contraente;
    }

    public void setContraente(FullAnagraficaDBO contraente) {
        this.contraente = contraente;
    }

    public List<AnagraficaTerzePartiDBO> getAnagraficaTerzeParti() {
        return anagraficaTerzeParti;
    }

    public void setAnagraficaTerzeParti(List<AnagraficaTerzePartiDBO> anagraficaTerzeParti) {
        this.anagraficaTerzeParti = anagraficaTerzeParti;
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

    public DannoRcaDBO getDannoRca() {
        return dannoRca;
    }

    public void setDannoRca(DannoRcaDBO dannoRca) {
        this.dannoRca = dannoRca;
    }

    public PeritoDBO getPerito() {
        return perito;
    }

    public void setPerito(PeritoDBO perito) {
        this.perito = perito;
    }

    public Integer getNumSinistroProvv() {
        return numSinistroProvv;
    }

    public void setNumSinistroProvv(Integer numSinistroProvv) {
        this.numSinistroProvv = numSinistroProvv;
    }

    public Boolean getSviluppoFiamme() {
        return sviluppoFiamme;
    }

    public void setSviluppoFiamme(Boolean sviluppoFiamme) {
        this.sviluppoFiamme = sviluppoFiamme;
    }

    public Boolean getResponsabilita() {
        return responsabilita;
    }

    public void setResponsabilita(Boolean responsabilita) {
        this.responsabilita = responsabilita;
    }

    public String getDescrizioneDanni() {
        return descrizioneDanni;
    }

    public void setDescrizioneDanni(String descrizioneDanni) {
        this.descrizioneDanni = descrizioneDanni;
    }

    public String getOsservazioniCliente() {
        return osservazioniCliente;
    }

    public void setOsservazioniCliente(String osservazioniCliente) {
        this.osservazioniCliente = osservazioniCliente;
    }

    public Boolean getInterventoAutorita() {
        return interventoAutorita;
    }

    public void setInterventoAutorita(Boolean interventoAutorita) {
        this.interventoAutorita = interventoAutorita;
    }
}
