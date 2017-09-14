package msa.domain.object.sinistro;

import msa.domain.object.sinistro.rca.CaiDO;
import msa.domain.object.sinistro.rca.ConstatazioneAmichevoleDO;
import msa.domain.object.sinistro.rca.DannoRcaDO;
import msa.domain.object.sinistro.rca.EventoRcaDO;

public class SinistroRcaDO extends BaseSinistroDO {
    private static final long serialVersionUID = 1665918958997727249L;
    private EventoRcaDO eventoRca;
    private ConstatazioneAmichevoleDO constatazioneAmichevole;
    private CaiDO cai;
    private DannoRcaDO dannoRca;
    private Boolean flagSinistroCard;

    public EventoRcaDO getEventoRca() {
        return eventoRca;
    }

    public void setEventoRca(EventoRcaDO eventoRca) {
        this.eventoRca = eventoRca;
    }

    public ConstatazioneAmichevoleDO getConstatazioneAmichevole() {
        return constatazioneAmichevole;
    }

    public void setConstatazioneAmichevole(ConstatazioneAmichevoleDO constatazioneAmichevole) {
        this.constatazioneAmichevole = constatazioneAmichevole;
    }

    public CaiDO getCai() {
        return cai;
    }

    public void setCai(CaiDO cai) {
        this.cai = cai;
    }

    public DannoRcaDO getDannoRca() {
        return dannoRca;
    }

    public void setDannoRca(DannoRcaDO dannoRca) {
        this.dannoRca = dannoRca;
    }

    public Boolean getFlagSinistroCard() {
        return flagSinistroCard;
    }

    public void setFlagSinistroCard(Boolean flagSinistroCard) {
        this.flagSinistroCard = flagSinistroCard;
    }
}
