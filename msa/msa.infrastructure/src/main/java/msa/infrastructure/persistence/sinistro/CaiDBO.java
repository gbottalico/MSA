package msa.infrastructure.persistence.sinistro;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

public class CaiDBO {
    @Field("baremesCliente")
    private Map<Integer, Boolean> baremesCliente;
    @Field("baremesControparte")
    private Map<Integer, Boolean> baremesControparte;
    @Field("noteCliente")
    private String noteCliente;
    @Field("noteControparte")
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
