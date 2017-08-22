package msa.application.dto.sinistro.rca.cai;

import msa.application.dto.domain.baremes.BaremesDTO;
import msa.application.dto.sinistro.AbstractDTO;

public class CaiDTO extends AbstractDTO {
    private static final long serialVersionUID = -2575869510337533595L;
    private BaremesDTO baremesCliente;
    private BaremesDTO baremesControparte;
    private String noteCliente;
    private String noteControparte;
    private String colpa;

    public BaremesDTO getBaremesCliente() {
        return baremesCliente;
    }

    public void setBaremesCliente(BaremesDTO baremesCliente) {
        this.baremesCliente = baremesCliente;
    }

    public BaremesDTO getBaremesControparte() {
        return baremesControparte;
    }

    public void setBaremesControparte(BaremesDTO baremesControparte) {
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
