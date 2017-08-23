package msa.application.dto.sinistro.anagrafica;

public class TrackingDTO {
    private String nazione;
    private String provincia;
    private String comune;
    private Integer cap;
    private String telefono;
    private String cellulare;
    private String mail;
    private String tipoStrada;
    private String denominazioneStrada;
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
