package msa.domain.object.sinistro;

public class CentroConvenzionatoDO {
    private Integer id;
    private String denominazione;
    private String longitudine;
    private String latitudine;
    private PeritoDO perito;

    public PeritoDO getPerito() {
        return perito;
    }

    public void setPerito(PeritoDO perito) {
        this.perito = perito;
    }

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
}
