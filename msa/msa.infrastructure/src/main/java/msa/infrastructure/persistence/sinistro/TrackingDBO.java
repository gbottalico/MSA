package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class TrackingDBO {
    @Field("nazione")
    private String nazione;
    @Field("descNazione")
    private String descNazione;
    @Field("provincia")
    private String provincia;
    @Field("descProvincia")
    private String descProvincia;
    @Field("comune")
    private String comune;
    @Field("descComune")
    private String descComune;
    @Field("cap")
    private Integer cap;
    @Field("telefono")
    private String telefono;
    @Field("indirizzo")
    private String indirizzo;
    @Field("cellulare")
    private String cellulare;
    @Field("mail")
    private String mail;


    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getDescNazione() {
        return descNazione;
    }

    public void setDescNazione(String descNazione) {
        this.descNazione = descNazione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDescProvincia() {
        return descProvincia;
    }

    public void setDescProvincia(String descProvincia) {
        this.descProvincia = descProvincia;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getDescComune() {
        return descComune;
    }

    public void setDescComune(String descComune) {
        this.descComune = descComune;
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
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
}
