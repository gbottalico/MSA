package msa.infrastructure.persistence.sinistro;

import com.mongodb.DBObject;
import msa.infrastructure.persistence.AbstractDBO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class SinistroDBO extends BaseSinistroDBO{

    @Field("eventoRCA")
    private EventoRcaDBO eventoRCA;
    @Field("constatazioneAmichevole")
    private ConstatazioneAmichevoleDBO constatazioneAmichevole;
    @Field("cai")
    private CaiDBO cai;
    @Field("dannoRCA")
    private DannoRcaDBO dannoRca;

    public EventoRcaDBO getEventoRCA() {
        return eventoRCA;
    }

    public void setEventoRCA(EventoRcaDBO eventoRCA) {
        this.eventoRCA = eventoRCA;
    }

    public ConstatazioneAmichevoleDBO getConstatazioneAmichevole() {
        return constatazioneAmichevole;
    }

    public void setConstatazioneAmichevole(ConstatazioneAmichevoleDBO constatazioneAmichevole) {
        this.constatazioneAmichevole = constatazioneAmichevole;
    }

    public CaiDBO getCai() {
        return cai;
    }

    public void setCai(CaiDBO cai) {
        this.cai = cai;
    }

    public DannoRcaDBO getDannoRca() {
        return dannoRca;
    }

    public void setDannoRca(DannoRcaDBO dannoRca) {
        this.dannoRca = dannoRca;
    }
}
