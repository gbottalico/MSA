package msa.domain.object.sinistro;

public class FullAnagraficaDO extends BaseAnagraficaDO {

    private PatenteDO patente;
    private TrackingDO tracking;

    public PatenteDO getPatente() {
        return patente;
    }

    public void setPatente(PatenteDO patente) {
        this.patente = patente;
    }

    public TrackingDO getTracking() {
        return tracking;
    }

    public void setTracking(TrackingDO tracking) {
        this.tracking = tracking;
    }
}
