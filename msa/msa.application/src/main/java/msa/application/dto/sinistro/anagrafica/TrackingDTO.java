package msa.application.dto.sinistro.anagrafica;

public class TrackingDTO {
    private String nazione;

    private String descNazione;

    private String provincia;

    private String descProvincia;
    private String comune;

    private String descComune;
    private Integer cap;
    private String telefono;
    private String cellulare;
    private String mail;
    private String indirizzo;

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDescNazione() {
        return descNazione;
    }

    public void setDescNazione(String descNazione) {
        this.descNazione = descNazione;
    }

    public String getDescProvincia() {
        return descProvincia;
    }

    public void setDescProvincia(String descProvincia) {
        this.descProvincia = descProvincia;
    }

    public String getDescComune() {
        return descComune;
    }

    public void setDescComune(String descComune) {
        this.descComune = descComune;
    }
}
