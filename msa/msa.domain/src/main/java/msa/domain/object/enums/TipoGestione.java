package msa.domain.object.enums;

import java.util.Arrays;
import java.util.List;

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
}
