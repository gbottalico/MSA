package msa.domain.object.sinistro;

public class SinistroFurtoIncendioDO {
    private Boolean sviluppoFiamme;
    private Boolean responsabilita;
    private String descrizioneDanni;
    private String osservazioniCliente;

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
