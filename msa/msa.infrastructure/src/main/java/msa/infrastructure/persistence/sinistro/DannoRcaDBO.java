package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class DannoRcaDBO {
    @Field("lesioniConducente")
    private Boolean lesioniConducente;
    @Field("anagraficaConducente")
    private FullAnagraficaDBO anagraficaConducente;
    @Field("anagraficaControparte")
    private FullAnagraficaControparteDBO anagraficaControparte;
    @Field("danniCliente")
    private DanniDBO danniCliente;
    @Field("danniControparte")
    private DanniDBO danniControparte;

    public Boolean getLesioniConducente() {
        return lesioniConducente;
    }

    public void setLesioniConducente(Boolean lesioniConducente) {
        this.lesioniConducente = lesioniConducente;
    }

    public FullAnagraficaDBO getAnagraficaConducente() {
        return anagraficaConducente;
    }

    public void setAnagraficaConducente(FullAnagraficaDBO anagraficaConducente) {
        this.anagraficaConducente = anagraficaConducente;
    }

    public FullAnagraficaControparteDBO getAnagraficaControparte() {
        return anagraficaControparte;
    }

    public void setAnagraficaControparte(FullAnagraficaControparteDBO anagraficaControparte) {
        this.anagraficaControparte = anagraficaControparte;
    }

    public DanniDBO getDanniCliente() {
        return danniCliente;
    }

    public void setDanniCliente(DanniDBO danniCliente) {
        this.danniCliente = danniCliente;
    }

    public DanniDBO getDanniControparte() {
        return danniControparte;
    }

    public void setDanniControparte(DanniDBO danniControparte) {
        this.danniControparte = danniControparte;
    }
}
