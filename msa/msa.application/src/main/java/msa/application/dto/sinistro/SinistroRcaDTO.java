package msa.application.dto.sinistro;

import msa.application.dto.sinistro.rca.cai.CaiDTO;
import msa.application.dto.sinistro.rca.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.rca.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.rca.eventoRca.EventoRcaDTO;

public class SinistroRcaDTO extends BaseSinistroDTO {
    private static final long serialVersionUID = 1665918958997727249L;


    private EventoRcaDTO eventoRca;
    private ConstatazioneAmichevoleDTO constatazioneAmichevole;
    private CaiDTO cai;
    private DannoRcaDTO dannoRca;

    public EventoRcaDTO getEventoRca() {
        return eventoRca;
    }

    public void setEventoRca(EventoRcaDTO eventoRca) {
        this.eventoRca = eventoRca;
    }

    public ConstatazioneAmichevoleDTO getConstatazioneAmichevole() {
        return constatazioneAmichevole;
    }

    public void setConstatazioneAmichevole(ConstatazioneAmichevoleDTO constatazioneAmichevole) {
        this.constatazioneAmichevole = constatazioneAmichevole;
    }

    public CaiDTO getCai() {
        return cai;
    }

    public void setCai(CaiDTO cai) {
        this.cai = cai;
    }

    public DannoRcaDTO getDannoRca() {
        return dannoRca;
    }

    public void setDannoRca(DannoRcaDTO dannoRca) {
        this.dannoRca = dannoRca;
    }
}
