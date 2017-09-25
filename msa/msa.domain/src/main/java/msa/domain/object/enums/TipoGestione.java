package msa.domain.object.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static msa.domain.object.enums.TipiSinisto.*;

/**
 * Created by simon.calabrese on 15/09/2017.
 */
public enum TipoGestione {
    NC("NO_CARD",null),
    CG("CARD_GESTIONARIO", Arrays.asList(TipoConvensioneCard.CID,TipoConvensioneCard.CTT)),
    CD("CARD_DEBITORE", Arrays.asList(TipoConvensioneCard.CID,TipoConvensioneCard.CTT));

    private String des;
    private List<TipoConvensioneCard> tipiConvensioni;


    TipoGestione(String des, List<TipoConvensioneCard> tipiConvensioni) {
        this.des = des;
        this.tipiConvensioni = tipiConvensioni;
    }

    public String getDes() {
        return des;
    }

    public List<TipoConvensioneCard> getTipiConvensioni() {
        return tipiConvensioni;
    }
    public static final List<TipiSinisto> ACCEPTED = Stream.of(CTT_PURO_GESTIONARIO,
            CTT_PURO_CONCORSUALE,
            CTT_PURO_DEBITORIO,
            CID_GESTIONARIO,
            CID_CTT_GESTIONARIO,
            CID_CONCORSUALE,
            CID_CTT_CONCORSUALE,
            CID_DEBITORIO,
            CID_CTT_DEBITORIO).collect(Collectors.toList());
}
