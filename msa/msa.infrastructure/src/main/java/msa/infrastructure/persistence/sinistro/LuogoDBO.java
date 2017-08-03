package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class LuogoDBO {
    @Field("codNazione")
    private String codNazione;
    @Field("codProvincia")
    private String codProvincia;
    @Field("codComune")
    private String codComune;
    @Field("descrizioneNazione")
    private String descrizioneNazione;
    @Field("descrizioneProvincia")
    private String descrizioneProvincia;
    @Field("descrizioneComune")
    private String descrizioneComune;

    public String getCodNazione() {
        return codNazione;
    }

    public void setCodNazione(String codNazione) {
        this.codNazione = codNazione;
    }

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getCodComune() {
        return codComune;
    }

    public void setCodComune(String codComune) {
        this.codComune = codComune;
    }

    public String getDescrizioneNazione() {
        return descrizioneNazione;
    }

    public void setDescrizioneNazione(String descrizioneNazione) {
        this.descrizioneNazione = descrizioneNazione;
    }

    public String getDescrizioneProvincia() {
        return descrizioneProvincia;
    }

    public void setDescrizioneProvincia(String descrizioneProvincia) {
        this.descrizioneProvincia = descrizioneProvincia;
    }

    public String getDescrizioneComune() {
        return descrizioneComune;
    }

    public void setDescrizioneComune(String descrizioneComune) {
        this.descrizioneComune = descrizioneComune;
    }
}
