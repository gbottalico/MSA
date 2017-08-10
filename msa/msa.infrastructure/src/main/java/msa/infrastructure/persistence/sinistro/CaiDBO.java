package msa.infrastructure.persistence.sinistro;

import msa.infrastructure.persistence.domain.BaremesDBO;
import org.springframework.data.mongodb.core.mapping.Field;

public class CaiDBO {
    @Field("baremesCliente")
    private BaremesDBO baremesCliente;
    @Field("baremesControparte")
    private BaremesDBO baremesControparte;
    @Field("noteCliente")
    private String noteCliente;
    @Field("noteControparte")
    private String noteControparte;
    @Field("colpa")
    private String colpa;

    public BaremesDBO getBaremesCliente() {
        return baremesCliente;
    }

    public void setBaremesCliente(BaremesDBO baremesCliente) {
        this.baremesCliente = baremesCliente;
    }

    public BaremesDBO getBaremesControparte() {
        return baremesControparte;
    }

    public void setBaremesControparte(BaremesDBO baremesControparte) {
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
