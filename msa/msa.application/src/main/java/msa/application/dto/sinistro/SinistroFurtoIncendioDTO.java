package msa.application.dto.sinistro;

public class SinistroFurtoIncendioDTO extends BaseSinistroDTO{
    private static final long serialVersionUID = -6115145650287622064L;
    private Boolean sviluppoFiamme;
    private Boolean responsabilita;
    private String descrizioneDanni;
    private String osservazioniCliente;
    private Boolean interventoAutorita;

    public Boolean getInterventoAutorita() {
        return interventoAutorita;
    }

    public void setInterventoAutorita(Boolean interventoAutorita) {
        this.interventoAutorita = interventoAutorita;
    }

    public Boolean getSviluppoFiamme() {
        return sviluppoFiamme;
    }

    public void setSviluppoFiamme(Boolean sviluppoFiamme) {
        this.sviluppoFiamme = sviluppoFiamme;
    }

    public Boolean getResponsabilita() {
        return responsabilita;
    }

    public void setResponsabilita(Boolean responsabilita) {
        this.responsabilita = responsabilita;
    }

    public String getDescrizioneDanni() {
        return descrizioneDanni;
    }

    public void setDescrizioneDanni(String descrizioneDanni) {
        this.descrizioneDanni = descrizioneDanni;
    }

    public String getOsservazioniCliente() {
        return osservazioniCliente;
    }

    public void setOsservazioniCliente(String osservazioniCliente) {
        this.osservazioniCliente = osservazioniCliente;
    }

}
