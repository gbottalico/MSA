package msa.application.dto.domain;

import java.io.Serializable;

public class RuoliDTO implements Serializable {
    private static final long serialVersionUID = 650299083224454039L;
    private Integer id;
    private String raggruppamento;
    private String descrizione;
    private String pdAss;
    private Boolean lesioni;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRaggruppamento() {
        return raggruppamento;
    }

    public void setRaggruppamento(String raggruppamento) {
        this.raggruppamento = raggruppamento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getPdAss() {
        return pdAss;
    }

    public void setPdAss(String pdAss) {
        this.pdAss = pdAss;
    }

    public Boolean getLesioni() {
        return lesioni;
    }

    public void setLesioni(Boolean lesioni) {
        this.lesioni = lesioni;
    }
}
