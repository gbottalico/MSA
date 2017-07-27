package msa.domain.object.sinistro;

import java.util.Map;

public class CaiDO {
    private Map<Integer, Boolean> baremesCliente;
    private Map<Integer, Boolean> baremesControparte;
    private String noteCliente;
    private String noteControparte;

    public Map<Integer, Boolean> getBaremesCliente() {
        return baremesCliente;
    }

    public void setBaremesCliente(Map<Integer, Boolean> baremesCliente) {
        this.baremesCliente = baremesCliente;
    }

    public Map<Integer, Boolean> getBaremesControparte() {
        return baremesControparte;
    }

    public void setBaremesControparte(Map<Integer, Boolean> baremesControparte) {
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
}
