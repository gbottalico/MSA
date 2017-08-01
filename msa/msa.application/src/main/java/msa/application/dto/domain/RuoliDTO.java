package msa.application.dto.domain;

import java.io.Serializable;

public class RuoliDTO implements Serializable {
    private static final long serialVersionUID = 650299083224454039L;
    private Integer id;
private String descrizioneRuolo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuoliDTO ruoliDTO = (RuoliDTO) o;

        if (id != null ? !id.equals(ruoliDTO.id) : ruoliDTO.id != null) return false;
        return descrizioneRuolo != null ? descrizioneRuolo.equals(ruoliDTO.descrizioneRuolo) : ruoliDTO.descrizioneRuolo == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descrizioneRuolo != null ? descrizioneRuolo.hashCode() : 0);
        return result;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizioneRuolo() {
        return descrizioneRuolo;
    }

    public void setDescrizioneRuolo(String descrizioneRuolo) {
        this.descrizioneRuolo = descrizioneRuolo;
    }
}
