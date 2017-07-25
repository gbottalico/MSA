package msa.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "tipoVeicoli")
public class TipoVeicoloDBO {
    @Id
    private Integer id;
    @Field("descVeicolo")
    private String descVeicolo;
    @Field("convVeicolo")
    private Character convVeicolo;
    @Field("codISVAP")
    private Integer codIsvap;
    @Field("codAnia")
    private Character codAnia;
    @Field("gestioneCid")
    private boolean gestioneCid;
    @Field("gestioneCtt")
    private boolean gestioneCtt;
    @Field("targaObbligatoria")
    private boolean targaObbligatori;
    @Field("codTipologiaVeicoloForfaitCard")
    private Character codTipologiaVeicoloForfaitCard;
    private String codFornitore;

    public String getCodFornitore() {
        return codFornitore;
    }

    public void setCodFornitore(String codFornitore) {
        this.codFornitore = codFornitore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescVeicolo() {
        return descVeicolo;
    }

    public void setDescVeicolo(String descVeicolo) {
        this.descVeicolo = descVeicolo;
    }

    public Character getConvVeicolo() {
        return convVeicolo;
    }

    public void setConvVeicolo(Character convVeicolo) {
        this.convVeicolo = convVeicolo;
    }

    public Integer getCodIsvap() {
        return codIsvap;
    }

    public void setCodIsvap(Integer codIsvap) {
        this.codIsvap = codIsvap;
    }

    public Character getCodAnia() {
        return codAnia;
    }

    public void setCodAnia(Character codAnia) {
        this.codAnia = codAnia;
    }

    public boolean isGestioneCid() {
        return gestioneCid;
    }

    public void setGestioneCid(boolean gestioneCid) {
        this.gestioneCid = gestioneCid;
    }

    public boolean isGestioneCtt() {
        return gestioneCtt;
    }

    public void setGestioneCtt(boolean gestioneCtt) {
        this.gestioneCtt = gestioneCtt;
    }

    public boolean isTargaObbligatori() {
        return targaObbligatori;
    }

    public void setTargaObbligatori(boolean targaObbligatori) {
        this.targaObbligatori = targaObbligatori;
    }

    public Character getCodTipologiaVeicoloForfaitCard() {
        return codTipologiaVeicoloForfaitCard;
    }

    public void setCodTipologiaVeicoloForfaitCard(Character codTipologiaVeicoloForfaitCard) {
        this.codTipologiaVeicoloForfaitCard = codTipologiaVeicoloForfaitCard;
    }
}
