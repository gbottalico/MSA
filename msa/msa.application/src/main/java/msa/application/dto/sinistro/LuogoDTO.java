package msa.application.dto.sinistro;

public class LuogoDTO {
    private String codNazione;
    private String codProvincia;
    private String codComune;
    private String descrizioneNazione;
    private String descrizioneProvincia;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LuogoDTO luogoDTO = (LuogoDTO) o;

        if (codNazione != null ? !codNazione.equals(luogoDTO.codNazione) : luogoDTO.codNazione != null) return false;
        if (codProvincia != null ? !codProvincia.equals(luogoDTO.codProvincia) : luogoDTO.codProvincia != null)
            return false;
        if (codComune != null ? !codComune.equals(luogoDTO.codComune) : luogoDTO.codComune != null) return false;
        if (descrizioneNazione != null ? !descrizioneNazione.equals(luogoDTO.descrizioneNazione) : luogoDTO.descrizioneNazione != null)
            return false;
        if (descrizioneProvincia != null ? !descrizioneProvincia.equals(luogoDTO.descrizioneProvincia) : luogoDTO.descrizioneProvincia != null)
            return false;
        return descrizioneComune != null ? descrizioneComune.equals(luogoDTO.descrizioneComune) : luogoDTO.descrizioneComune == null;
    }

    @Override
    public int hashCode() {
        int result = codNazione != null ? codNazione.hashCode() : 0;
        result = 31 * result + (codProvincia != null ? codProvincia.hashCode() : 0);
        result = 31 * result + (codComune != null ? codComune.hashCode() : 0);
        result = 31 * result + (descrizioneNazione != null ? descrizioneNazione.hashCode() : 0);
        result = 31 * result + (descrizioneProvincia != null ? descrizioneProvincia.hashCode() : 0);
        result = 31 * result + (descrizioneComune != null ? descrizioneComune.hashCode() : 0);
        return result;
    }
}
