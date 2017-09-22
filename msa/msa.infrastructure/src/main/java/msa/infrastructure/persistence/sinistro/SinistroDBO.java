package msa.infrastructure.persistence.sinistro;

import msa.domain.object.enums.TipiSinisto;
import msa.infrastructure.persistence.AbstractDBO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
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

    @Field("flagIsCard")
    private Boolean flagSinistroCard;
    @Field("terzeParti")
    private List<AnagraficaTerzePartiDBO> anagraficaTerzeParti;
    @Field("legali")
    private List<AnagraficaTerzePartiDBO> legali;

    @Field("contraente")
    private FullAnagraficaControparteDBO contraente;
    @Field("proprietario")
    private FullAnagraficaControparteDBO proprietario;

    @Field("perito")
    private PeritoDBO perito;

    @Field("centroConvenzionato")
    private CentroConvenzionatoDBO centroConvenzionato;
    @Field("numSinistroProvv")
    private Integer numSinistroProvv;
    //INIZIO FURTO INCENDIO
    @Field("sviluppoFiamme")
    private Boolean sviluppoFiamme;
    @Field("responsabilita")
    private Boolean responsabilita;


    @Field("interventoAutorita")
    private Boolean interventoAutorita;
    @Field("codAutorita")
    private Integer codAutorita;
    @Field("comandoAutorita")
    private String comandoAutorita;
    @Field("dataDenunciaAutorita")
    private Date dataDenuncia;


    //FINE FURTO INCENDIO
    //IN COMUNE FRA FURTOINCENDIO E KASKO
    @Field("osservazioniCliente")
    private String osservazioniCliente;
    //INIZIO KASKO
    @Field("danniKasko")
    private DanniDBO danniKasko;
    @Field("conducenteDiverso")
    private Boolean conducenteDiverso;
    @Field("conducente")
    private FullAnagraficaDBO conducente;
    //FINE KASKO
    //INIZIO CRISTALLI
    @Field("desCristalloRotto")
    private String desCristalloRotto;
    @Field("codRotturaCristalli")
    private String codRotturaCristalli;
    @Field("flagRiparazione")
    private Boolean flagRiparazione;
    @Field("flagFattura")
    private Boolean flagFattura;
    //FINE CRISTALLI
    //INFORTUNI CONDUCENTE
    //IN COMUNE FRA RCA ED INFORTUNI CONDUCENTE
    @Field("descrizioneDanni")
    private String descrizioneDanni;
    @Field("osservazioniInfortunato")
    private String osservazioniInfortunato;

    @Field("tipoSinistro")
    private TipiSinisto tipoSinisto;

    public Boolean getFlagSinistroCard() {
        return flagSinistroCard;
    }

    public void setFlagSinistroCard(Boolean flagSinistroCard) {
        this.flagSinistroCard = flagSinistroCard;
    }


    public String getOsservazioniInfortunato() {
        return osservazioniInfortunato;
    }

    public void setOsservazioniInfortunato(String osservazioniInfortunato) {
        this.osservazioniInfortunato = osservazioniInfortunato;
    }

    public FullAnagraficaControparteDBO getProprietario() {
        return proprietario;
    }

    public void setProprietario(FullAnagraficaControparteDBO proprietario) {
        this.proprietario = proprietario;
    }

    public FullAnagraficaControparteDBO getContraente() {
        return contraente;
    }

    public void setContraente(FullAnagraficaControparteDBO contraente) {
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

    public DanniDBO getDanniKasko() {
        return danniKasko;
    }

    public void setDanniKasko(DanniDBO danniKasko) {
        this.danniKasko = danniKasko;
    }

    public String getDesCristalloRotto() {
        return desCristalloRotto;
    }

    public void setDesCristalloRotto(String desCristalloRotto) {
        this.desCristalloRotto = desCristalloRotto;
    }

    public String getCodRotturaCristalli() {
        return codRotturaCristalli;
    }

    public void setCodRotturaCristalli(String codRotturaCristalli) {
        this.codRotturaCristalli = codRotturaCristalli;
    }

    public Boolean getFlagRiparazione() {
        return flagRiparazione;
    }

    public void setFlagRiparazione(Boolean flagRiparazione) {
        this.flagRiparazione = flagRiparazione;
    }

    public Boolean getFlagFattura() {
        return flagFattura;
    }

    public void setFlagFattura(Boolean flagFattura) {
        this.flagFattura = flagFattura;
    }

    public CentroConvenzionatoDBO getCentroConvenzionato() {
        return centroConvenzionato;
    }

    public void setCentroConvenzionato(CentroConvenzionatoDBO centroConvenzionato) {
        this.centroConvenzionato = centroConvenzionato;
    }

    public List<AnagraficaTerzePartiDBO> getLegali() {
        return legali;
    }

    public void setLegali(List<AnagraficaTerzePartiDBO> legali) {
        this.legali = legali;
    }

    public Integer getCodAutorita() {
        return codAutorita;
    }

    public void setCodAutorita(Integer codAutorita) {
        this.codAutorita = codAutorita;
    }

    public String getComandoAutorita() {
        return comandoAutorita;
    }

    public void setComandoAutorita(String comandoAutorita) {
        this.comandoAutorita = comandoAutorita;
    }

    public Date getDataDenuncia() {
        return dataDenuncia;
    }

    public void setDataDenuncia(Date dataDenuncia) {
        this.dataDenuncia = dataDenuncia;
    }

    public TipiSinisto getTipoSinisto() {
        return tipoSinisto;
    }

    public void setTipoSinisto(TipiSinisto tipoSinisto) {
        this.tipoSinisto = tipoSinisto;
    }

    public Boolean getConducenteDiverso() {
        return conducenteDiverso;
    }

    public void setConducenteDiverso(Boolean conducenteDiverso) {
        this.conducenteDiverso = conducenteDiverso;
    }

    public FullAnagraficaDBO getConducente() {
        return conducente;
    }

    public void setConducente(FullAnagraficaDBO conducente) {
        this.conducente = conducente;
    }
}
