package msa.application.dto.domain;

/**
 * Created by simon.calabrese on 09/08/2017.
 */
public class CodiciCatastaliPojo {
    private String codCatastale;
    private String nomeComune;
    private String provincia;

    public CodiciCatastaliPojo() {
    }

    public CodiciCatastaliPojo(String codCatastale, String nomeComune, String provincia) {
        this.codCatastale = codCatastale;
        this.nomeComune = nomeComune;
        this.provincia = provincia;
    }

    public String getCodCatastale() {
        return codCatastale;
    }

    public void setCodCatastale(String codCatastale) {
        this.codCatastale = codCatastale;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
