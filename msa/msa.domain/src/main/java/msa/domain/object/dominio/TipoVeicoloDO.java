package msa.domain.object.dominio;

public class TipoVeicoloDO {


    private Integer id;

    private String descVeicolo;

    private Character convVeicolo;

    private Integer codIsvap;

    private Character codAnia;

    private boolean gestioneCid;

    private boolean gestioneCtt;

    private boolean targaObbligatori;

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
