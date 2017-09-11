package msa.application.dto.domain;

import java.io.Serializable;

public class RuoliDTO implements Serializable {
    private static final long serialVersionUID = 650299083224454039L;
    private Integer id;
    private String descrizioneRuolo;
    private Boolean lesioni;

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

    public Boolean getLesioni() {
        return lesioni;
    }

    public void setLesioni(Boolean lesioni) {
        this.lesioni = lesioni;
    }
}
