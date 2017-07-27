package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

public class FullAnagraficaDBO extends BaseAnagraficaDBO{
    @Field("patente")
    private PatenteDBO patente;
    @Field("tracking")
    private TrackingDBO tracking;

    public PatenteDBO getPatente() {
        return patente;
    }

    public void setPatente(PatenteDBO patente) {
        this.patente = patente;
    }

    public TrackingDBO getTracking() {
        return tracking;
    }

    public void setTracking(TrackingDBO tracking) {
        this.tracking = tracking;
    }
}
