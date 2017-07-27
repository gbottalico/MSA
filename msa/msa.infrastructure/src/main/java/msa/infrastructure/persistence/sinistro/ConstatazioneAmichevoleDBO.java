package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class ConstatazioneAmichevoleDBO {
    @Field("isCaCompilata")
    private Boolean isCaCompilata;
    @Field("isCaCompilataControparte")
    private Boolean isCaCompilataControparte;

    public Boolean getCaCompilata() {
        return isCaCompilata;
    }

    public void setCaCompilata(Boolean caCompilata) {
        isCaCompilata = caCompilata;
    }

    public Boolean getCaCompilataControparte() {
        return isCaCompilataControparte;
    }

    public void setCaCompilataControparte(Boolean caCompilataControparte) {
        isCaCompilataControparte = caCompilataControparte;
    }
}
