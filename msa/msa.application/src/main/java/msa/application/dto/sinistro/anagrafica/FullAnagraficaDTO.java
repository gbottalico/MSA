package msa.application.dto.sinistro.anagrafica;

public class FullAnagraficaDTO extends BaseAnagraficaDTO {

    private PatenteDTO patente;
    private TrackingDTO tracking;

    public PatenteDTO getPatente() {
        return patente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FullAnagraficaDTO that = (FullAnagraficaDTO) o;

        if (patente != null ? !patente.equals(that.patente) : that.patente != null) return false;
        return tracking != null ? tracking.equals(that.tracking) : that.tracking == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (patente != null ? patente.hashCode() : 0);
        result = 31 * result + (tracking != null ? tracking.hashCode() : 0);
        return result;
    }

    public void setPatente(PatenteDTO patente) {
        this.patente = patente;
    }

    public TrackingDTO getTracking() {
        return tracking;
    }

    public void setTracking(TrackingDTO tracking) {
        this.tracking = tracking;
    }
}
