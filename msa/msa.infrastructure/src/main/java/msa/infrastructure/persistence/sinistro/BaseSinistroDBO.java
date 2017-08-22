package msa.infrastructure.persistence.sinistro;

import msa.infrastructure.persistence.AbstractDBO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "sinistri")

public class BaseSinistroDBO extends AbstractDBO{

    @Id
    private String id;
    @Field("numeroPolizza")
    private String numeroPolizza;
    @Field("compagnia")
    private Integer compagnia;
    @Field("segnalazione")
    private SegnalazioneDBO segnalazione;
    @Field("terzeParti")
    private List<AnagraficaTerzePartiDBO> anagraficaTerzeParti;

    @Field("contraente")
    private FullAnagraficaDBO contraente;
    @Field("perito")
    private PeritoDBO perito;

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

    public SegnalazioneDBO getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(SegnalazioneDBO segnalazione) {
        this.segnalazione = segnalazione;
    }

    public List<AnagraficaTerzePartiDBO> getAnagraficaTerzeParti() {
        return anagraficaTerzeParti;
    }

    public void setAnagraficaTerzeParti(List<AnagraficaTerzePartiDBO> anagraficaTerzeParti) {
        this.anagraficaTerzeParti = anagraficaTerzeParti;
    }

    public FullAnagraficaDBO getContraente() {
        return contraente;
    }

    public void setContraente(FullAnagraficaDBO contraente) {
        this.contraente = contraente;
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
}
