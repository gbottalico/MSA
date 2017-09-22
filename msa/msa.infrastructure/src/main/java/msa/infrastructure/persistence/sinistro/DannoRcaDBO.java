package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class DannoRcaDBO {
    @Field("lesioniConducente")
    private Boolean lesioniConducente;
    @Field("conducenteDiverso")
    private Boolean conducenteDiverso;
    @Field("anagraficaDanniConducente")
    private AnagraficaDanniDBO anagraficaDanniCliente;
    @Field("anagraficaDanniControparte")
    private List<AnagraficaDanniDBO> anagraficaDanniControparte;
    @Field("terzeParti")
    private List<FullAnagraficaControparteDBO> terzeParti;


    public Boolean getLesioniConducente() {
        return lesioniConducente;
    }

    public void setLesioniConducente(Boolean lesioniConducente) {
        this.lesioniConducente = lesioniConducente;
    }

    public Boolean getConducenteDiverso() {
        return conducenteDiverso;
    }

    public void setConducenteDiverso(Boolean conducenteDiverso) {
        this.conducenteDiverso = conducenteDiverso;
    }

    public AnagraficaDanniDBO getAnagraficaDanniCliente() {
        return anagraficaDanniCliente;
    }

    public void setAnagraficaDanniCliente(AnagraficaDanniDBO anagraficaDanniCliente) {
        this.anagraficaDanniCliente = anagraficaDanniCliente;
    }

    public List<AnagraficaDanniDBO> getAnagraficaDanniControparte() {
        return anagraficaDanniControparte;
    }

    public void setAnagraficaDanniControparte(List<AnagraficaDanniDBO> anagraficaDanniControparte) {
        this.anagraficaDanniControparte = anagraficaDanniControparte;
    }

    public List<FullAnagraficaControparteDBO> getTerzeParti() {
        return terzeParti;
    }

    public void setTerzeParti(List<FullAnagraficaControparteDBO> terzeParti) {
        this.terzeParti = terzeParti;
    }
}
