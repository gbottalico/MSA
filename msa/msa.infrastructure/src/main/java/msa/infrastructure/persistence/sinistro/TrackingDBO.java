package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class TrackingDBO {
    @Field("residenza")
    private LuogoDBO residenza;
    @Field("telefono")
    private String telefono;
    @Field("indirizzo")
    private String indirizzo;
    @Field("cellulare")
    private String cellulare;
    @Field("mail")
    private String mail;

    public LuogoDBO getResidenza() {
        return residenza;
    }

    public void setResidenza(LuogoDBO residenza) {
        this.residenza = residenza;
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
