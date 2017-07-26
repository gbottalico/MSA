package msa.application.dto.sinistro.anagrafica;

public class FullAnagraficaControparteDTO extends FullAnagraficaDTO {
private String tipoPersona;
private String compagnia;
private String veioolo;
private String targa;
private boolean targaEstera;
private boolean targaSpeciale;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FullAnagraficaControparteDTO that = (FullAnagraficaControparteDTO) o;

        if (targaEstera != that.targaEstera) return false;
        if (targaSpeciale != that.targaSpeciale) return false;
        if (tipoPersona != null ? !tipoPersona.equals(that.tipoPersona) : that.tipoPersona != null) return false;
        if (compagnia != null ? !compagnia.equals(that.compagnia) : that.compagnia != null) return false;
        if (veioolo != null ? !veioolo.equals(that.veioolo) : that.veioolo != null) return false;
        return targa != null ? targa.equals(that.targa) : that.targa == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tipoPersona != null ? tipoPersona.hashCode() : 0);
        result = 31 * result + (compagnia != null ? compagnia.hashCode() : 0);
        result = 31 * result + (veioolo != null ? veioolo.hashCode() : 0);
        result = 31 * result + (targa != null ? targa.hashCode() : 0);
        result = 31 * result + (targaEstera ? 1 : 0);
        result = 31 * result + (targaSpeciale ? 1 : 0);
        return result;
    }

    public String getTipoPersona() {

        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getCompagnia() {
        return compagnia;
    }

    public void setCompagnia(String compagnia) {
        this.compagnia = compagnia;
    }

    public String getVeioolo() {
        return veioolo;
    }

    public void setVeioolo(String veioolo) {
        this.veioolo = veioolo;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public boolean isTargaEstera() {
        return targaEstera;
    }

    public void setTargaEstera(boolean targaEstera) {
        this.targaEstera = targaEstera;
    }

    public boolean isTargaSpeciale() {
        return targaSpeciale;
    }

    public void setTargaSpeciale(boolean targaSpeciale) {
        this.targaSpeciale = targaSpeciale;
    }
}
