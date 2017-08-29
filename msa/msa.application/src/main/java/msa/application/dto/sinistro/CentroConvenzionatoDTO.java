package msa.application.dto.sinistro;

public class CentroConvenzionatoDTO extends AbstractDTO{
    private Integer id;
    private String denominazione;
    private String longitudine;
    private String latitudine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    public CentroConvenzionatoDTO(Integer id, String denominazione, String longitudine, String latitudine) {
        this.id = id;
        this.denominazione = denominazione;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
    }

    public CentroConvenzionatoDTO() {
    }
}
