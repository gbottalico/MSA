package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class TrackingDBO {
    @Field("nazione")
    private String nazione;
    @Field("provincia")
    private String provincia;
    @Field("comune")
    private String comune;
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

    @Field("tipoStrada")
    private String tipoStrada;
    @Field("denominazioneStrada")
    private String denominazioneStrada;
    @Field("civicoStrada")
    private String civicoStrada;

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

    public String getTipoStrada() {
        return tipoStrada;
    }

    public void setTipoStrada(String tipoStrada) {
        this.tipoStrada = tipoStrada;
    }

    public String getDenominazioneStrada() {
        return denominazioneStrada;
    }

    public void setDenominazioneStrada(String denominazioneStrada) {
        this.denominazioneStrada = denominazioneStrada;
    }

    public String getCivicoStrada() {
        return civicoStrada;
    }

    public void setCivicoStrada(String civicoStrada) {
        this.civicoStrada = civicoStrada;
    }
}
