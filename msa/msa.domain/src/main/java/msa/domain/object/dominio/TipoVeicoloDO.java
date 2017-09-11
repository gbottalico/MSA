package msa.domain.object.dominio;

public class TipoVeicoloDO {


    private Integer id;

    private String descVeicolo;

    private Character convVeicolo;

    private Integer codIsvap;

    private Character codAnia;

    private Boolean gestioneCid;

    private Boolean gestioneCtt;

    private Boolean targaObbligatori;

    private Character codTipologiaVeicoloForfaitCard;
    private String codFornitore;

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

    public Boolean getGestioneCid() {
        return gestioneCid;
    }

    public void setGestioneCid(Boolean gestioneCid) {
        this.gestioneCid = gestioneCid;
    }

    public Boolean getGestioneCtt() {
        return gestioneCtt;
    }

    public void setGestioneCtt(Boolean gestioneCtt) {
        this.gestioneCtt = gestioneCtt;
    }

    public Boolean getTargaObbligatori() {
        return targaObbligatori;
    }

    public void setTargaObbligatori(Boolean targaObbligatori) {
        this.targaObbligatori = targaObbligatori;
    }

    public Character getCodTipologiaVeicoloForfaitCard() {
        return codTipologiaVeicoloForfaitCard;
    }

    public void setCodTipologiaVeicoloForfaitCard(Character codTipologiaVeicoloForfaitCard) {
        this.codTipologiaVeicoloForfaitCard = codTipologiaVeicoloForfaitCard;
    }

    public String getCodFornitore() {
        return codFornitore;
    }

    public void setCodFornitore(String codFornitore) {
        this.codFornitore = codFornitore;
    }
}
