package msa.application.dto.domain;

public class TipoTargaDTO {
private Integer id;
private String descrizione;
private Character codAnia;
private Integer codNewAge;
private String codFornitore;

    public String getCodFornitore() {
        return codFornitore;
    }

    public void setCodFornitore(String codFornitore) {
        this.codFornitore = codFornitore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Character getCodAnia() {
        return codAnia;
    }

    public void setCodAnia(Character codAnia) {
        this.codAnia = codAnia;
    }

    public Integer getCodNewAge() {
        return codNewAge;
    }

    public void setCodNewAge(Integer codNewAge) {
        this.codNewAge = codNewAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoTargaDTO that = (TipoTargaDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (descrizione != null ? !descrizione.equals(that.descrizione) : that.descrizione != null) return false;
        if (codAnia != null ? !codAnia.equals(that.codAnia) : that.codAnia != null) return false;
        return codNewAge != null ? codNewAge.equals(that.codNewAge) : that.codNewAge == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        result = 31 * result + (codAnia != null ? codAnia.hashCode() : 0);
        result = 31 * result + (codNewAge != null ? codNewAge.hashCode() : 0);
        return result;
    }
}
