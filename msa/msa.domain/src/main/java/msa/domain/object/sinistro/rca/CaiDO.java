package msa.domain.object.sinistro.rca;

import msa.domain.object.dominio.BaremesDO;

public class CaiDO {
    private BaremesDO baremesCliente;
    private BaremesDO baremesControparte;
    private String noteCliente;
    private String noteControparte;
    private String colpa;

    public BaremesDO getBaremesCliente() {
        return baremesCliente;
    }

    public void setBaremesCliente(BaremesDO baremesCliente) {
        this.baremesCliente = baremesCliente;
    }

    public BaremesDO getBaremesControparte() {
        return baremesControparte;
    }

    public void setBaremesControparte(BaremesDO baremesControparte) {
        this.baremesControparte = baremesControparte;
    }

    public String getNoteCliente() {
        return noteCliente;
    }

    public void setNoteCliente(String noteCliente) {
        this.noteCliente = noteCliente;
    }

    public String getNoteControparte() {
        return noteControparte;
    }

    public void setNoteControparte(String noteControparte) {
        this.noteControparte = noteControparte;
    }

    public String getColpa() {
        return colpa;
    }

    public void setColpa(String colpa) {
        this.colpa = colpa;
    }
}
