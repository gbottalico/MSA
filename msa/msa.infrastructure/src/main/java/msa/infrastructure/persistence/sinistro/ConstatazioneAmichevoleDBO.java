package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class ConstatazioneAmichevoleDBO extends CaiDBO{
    @Field("caCompilata")
    private Boolean caCompilata;
    @Field("caCompilataControparte")
    private Boolean caCompilataControparte;

    public Boolean getCaCompilata() {
        return caCompilata;
    }

    public void setCaCompilata(Boolean caCompilata) {
        this.caCompilata = caCompilata;
    }

    public Boolean getCaCompilataControparte() {
        return caCompilataControparte;
    }

    public void setCaCompilataControparte(Boolean caCompilataControparte) {
        this.caCompilataControparte = caCompilataControparte;
    }
}
