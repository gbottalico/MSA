package msa.domain.object.sinistro;

public class TrackingDO {
    private LuogoDO residenza;
    private String telefono;
    private String cellulare;
    private String mail;
    private String indirizzo;

    public LuogoDO getResidenza() {
        return residenza;
    }

    public void setResidenza(LuogoDO residenza) {
        this.residenza = residenza;
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
}
