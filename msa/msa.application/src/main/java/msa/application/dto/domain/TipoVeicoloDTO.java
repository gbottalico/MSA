package msa.application.dto.domain;




public class TipoVeicoloDTO {


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoVeicoloDTO that = (TipoVeicoloDTO) o;

        if (gestioneCid != that.gestioneCid) return false;
        if (gestioneCtt != that.gestioneCtt) return false;
        if (targaObbligatori != that.targaObbligatori) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (descVeicolo != null ? !descVeicolo.equals(that.descVeicolo) : that.descVeicolo != null) return false;
        if (convVeicolo != null ? !convVeicolo.equals(that.convVeicolo) : that.convVeicolo != null) return false;
        if (codIsvap != null ? !codIsvap.equals(that.codIsvap) : that.codIsvap != null) return false;
        if (codAnia != null ? !codAnia.equals(that.codAnia) : that.codAnia != null) return false;
        return codTipologiaVeicoloForfaitCard != null ? codTipologiaVeicoloForfaitCard.equals(that.codTipologiaVeicoloForfaitCard) : that.codTipologiaVeicoloForfaitCard == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descVeicolo != null ? descVeicolo.hashCode() : 0);
        result = 31 * result + (convVeicolo != null ? convVeicolo.hashCode() : 0);
        result = 31 * result + (codIsvap != null ? codIsvap.hashCode() : 0);
        result = 31 * result + (codAnia != null ? codAnia.hashCode() : 0);
        result = 31 * result + (gestioneCid ? 1 : 0);
        result = 31 * result + (gestioneCtt ? 1 : 0);
        result = 31 * result + (targaObbligatori ? 1 : 0);
        result = 31 * result + (codTipologiaVeicoloForfaitCard != null ? codTipologiaVeicoloForfaitCard.hashCode() : 0);
        return result;
    }
}
