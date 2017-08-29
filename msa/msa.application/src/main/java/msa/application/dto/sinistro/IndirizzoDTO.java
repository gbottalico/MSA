package msa.application.dto.sinistro;

public class IndirizzoDTO extends LuogoDTO {
    private String codParticella;
    private String desVia;
    private String numCivico;

    public String getCodParticella() {
        return codParticella;
    }

    public void setCodParticella(String codParticella) {
        this.codParticella = codParticella;
    }

    public String getDesVia() {
        return desVia;
    }

    public void setDesVia(String desVia) {
        this.desVia = desVia;
    }

    public String getNumCivico() {
        return numCivico;
    }

    public void setNumCivico(String numCivico) {
        this.numCivico = numCivico;
    }
}
