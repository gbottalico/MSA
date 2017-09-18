package msa.infrastructure.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "province")
public class ProvinciaDBO {
    @Id
    private String codProvincia;
    @Field("codNazione")
    private String codNazione;
    @Field("iniValidita")
    private Date iniValidita;
    @Field("finValidita")
    private Date finValidita;
    @Field("desProv")
    private String descProvincia;
    @Field("siglaProv")
    private String siglaProv;
    private String codFornitore;

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getCodNazione() {
        return codNazione;
    }

    public void setCodNazione(String codNazione) {
        this.codNazione = codNazione;
    }

    public Date getIniValidita() {
        return iniValidita;
    }

    public void setIniValidita(Date iniValidita) {
        this.iniValidita = iniValidita;
    }

    public Date getFinValidita() {
        return finValidita;
    }

    public void setFinValidita(Date finValidita) {
        this.finValidita = finValidita;
    }

    public String getDescProvincia() {
        return descProvincia;
    }

    public void setDescProvincia(String descProvincia) {
        this.descProvincia = descProvincia;
    }

    public String getSiglaProv() {
        return siglaProv;
    }

    public void setSiglaProv(String siglaProv) {
        this.siglaProv = siglaProv;
    }

    public String getCodFornitore() {
        return codFornitore;
    }

    public void setCodFornitore(String codFornitore) {
        this.codFornitore = codFornitore;
    }
}
