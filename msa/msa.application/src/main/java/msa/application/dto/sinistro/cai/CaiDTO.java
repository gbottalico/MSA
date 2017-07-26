package msa.application.dto.sinistro.cai;

import java.io.Serializable;
import java.util.Map;

public class CaiDTO implements Serializable {
    private static final long serialVersionUID = -2575869510337533595L;
    private Map<Integer, Boolean> baremesCliente;
    private Map<Integer, Boolean> baremesControparte;
    private String noteCliente;
    private String noteControparte;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CaiDTO caiDTO = (CaiDTO) o;

        if (baremesCliente != null ? !baremesCliente.equals(caiDTO.baremesCliente) : caiDTO.baremesCliente != null)
            return false;
        if (baremesControparte != null ? !baremesControparte.equals(caiDTO.baremesControparte) : caiDTO.baremesControparte != null)
            return false;
        if (noteCliente != null ? !noteCliente.equals(caiDTO.noteCliente) : caiDTO.noteCliente != null) return false;
        return noteControparte != null ? noteControparte.equals(caiDTO.noteControparte) : caiDTO.noteControparte == null;
    }

    @Override
    public int hashCode() {
        int result = baremesCliente != null ? baremesCliente.hashCode() : 0;
        result = 31 * result + (baremesControparte != null ? baremesControparte.hashCode() : 0);
        result = 31 * result + (noteCliente != null ? noteCliente.hashCode() : 0);
        result = 31 * result + (noteControparte != null ? noteControparte.hashCode() : 0);
        return result;
    }
}
