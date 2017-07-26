package msa.application.dto.domain.baremes;

import java.io.Serializable;

public class BaremesDTO implements Serializable {
    private static final long serialVersionUID = -3179946555427038803L;
    private Integer id;
    private String descrizione;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaremesDTO that = (BaremesDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return descrizione != null ? descrizione.equals(that.descrizione) : that.descrizione == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        return result;
    }

    public String getDescrizione() {

        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
