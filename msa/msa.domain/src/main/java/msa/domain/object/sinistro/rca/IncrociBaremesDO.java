package msa.domain.object.sinistro.rca;

/**
 * Created by simon.calabrese on 10/08/2017.
 */
public class IncrociBaremesDO {
    private Integer id;
    private Integer codBaremesCliente;
    private Integer codBaremesControparte;
    private String codResponsabilita;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodBaremesCliente() {
        return codBaremesCliente;
    }

    public void setCodBaremesCliente(Integer codBaremesCliente) {
        this.codBaremesCliente = codBaremesCliente;
    }

    public Integer getCodBaremesControparte() {
        return codBaremesControparte;
    }

    public void setCodBaremesControparte(Integer codBaremesControparte) {
        this.codBaremesControparte = codBaremesControparte;
    }

    public String getCodResponsabilita() {
        return codResponsabilita;
    }

    public void setCodResponsabilita(String codResponsabilita) {
        this.codResponsabilita = codResponsabilita;
    }
}
