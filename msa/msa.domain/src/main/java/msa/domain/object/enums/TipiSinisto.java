package msa.domain.object.enums;

import java.util.Arrays;

/**
 * Created by simon.calabrese on 11/09/2017.
 */
public enum TipiSinisto {
    RCA("RCA"),
    CTT_PURO_GESTIONARIO("CTT_PURO_GESTIONARIO"),
    CTT_PURO_CONCORSUALE("CTT_PURO_CONCORSUALE"),
    CTT_PURO_DEBITORIO("CTT_PURO_DEBITORIO"),
    CID_GESTIONARIO("CID_GESTIONARIO"),
    CID_CTT_GESTIONARIO("CID_CTT_GESTIONARIO"),
    CID_CONCORSUALE("CID_CONCORSUALE"),
    CID_CTT_CONCORSUALE("CID_CTT_CONCORSUALE"),
    CID_DEBITORIO("CID_DEBITORIO"),
    CID_CTT_DEBITORIO("CID_CTT_DEBITORIO"),
    INCENDIO("incendio"),
    FURTO_TOTALE("furto_totale"),
    FURTO_PARZIALE("furto_parziale"),
    KASKO("kasko"),
    CRISTALLI("cristalli"),
    INF_CONDUCENTE("inf_conducente")
    ;

    private String des;

    TipiSinisto(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public static TipiSinisto getByDes(final String des) {
        return Arrays.stream(TipiSinisto.values())
                .filter(e -> e.getDes().equals(des))
                .findFirst().orElse(null);
    }
}
