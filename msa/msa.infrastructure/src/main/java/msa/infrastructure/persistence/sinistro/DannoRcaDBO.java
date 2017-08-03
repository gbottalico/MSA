package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class DannoRcaDBO {
    @Field("lesioniConducente")
    private Boolean lesioniConducente;
    @Field("anagraficaDanniCliente")
    private AnagraficaDanniDBO anagraficaDanniCliente;
    @Field("anagraficaDanniControparte")
    private AnagraficaDanniDBO anagraficaDanniControparte;
    @Field("terzeParti")
    private List<AnagraficaTerzePartiDBO> terzeParti;


    public Boolean getLesioniConducente() {
        return lesioniConducente;
    }

    public void setLesioniConducente(Boolean lesioniConducente) {
        this.lesioniConducente = lesioniConducente;
    }

    public AnagraficaDanniDBO getAnagraficaDanniCliente() {
        return anagraficaDanniCliente;
    }

    public void setAnagraficaDanniCliente(AnagraficaDanniDBO anagraficaDanniCliente) {
        this.anagraficaDanniCliente = anagraficaDanniCliente;
    }

    public AnagraficaDanniDBO getAnagraficaDanniControparte() {
        return anagraficaDanniControparte;
    }

    public void setAnagraficaDanniControparte(AnagraficaDanniDBO anagraficaDanniControparte) {
        this.anagraficaDanniControparte = anagraficaDanniControparte;
    }

    public List<AnagraficaTerzePartiDBO> getTerzeParti() {
        return terzeParti;
    }

    public void setTerzeParti(List<AnagraficaTerzePartiDBO> terzeParti) {
        this.terzeParti = terzeParti;
    }
}
