package msa.application.dto.sinistro.anagrafica;

public class TrackingDTO {
    private String nazione;
    private String provincia;
    private String comune;
    private Integer cap;
    private String telefono;
    private String cellulare;
    private String mail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackingDTO that = (TrackingDTO) o;

        if (nazione != null ? !nazione.equals(that.nazione) : that.nazione != null) return false;
        if (provincia != null ? !provincia.equals(that.provincia) : that.provincia != null) return false;
        if (comune != null ? !comune.equals(that.comune) : that.comune != null) return false;
        if (cap != null ? !cap.equals(that.cap) : that.cap != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (cellulare != null ? !cellulare.equals(that.cellulare) : that.cellulare != null) return false;
        return mail != null ? mail.equals(that.mail) : that.mail == null;
    }

    @Override
    public int hashCode() {
        int result = nazione != null ? nazione.hashCode() : 0;
        result = 31 * result + (provincia != null ? provincia.hashCode() : 0);
        result = 31 * result + (comune != null ? comune.hashCode() : 0);
        result = 31 * result + (cap != null ? cap.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (cellulare != null ? cellulare.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
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
}
