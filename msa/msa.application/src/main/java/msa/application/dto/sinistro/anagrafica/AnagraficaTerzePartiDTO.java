package msa.application.dto.sinistro.anagrafica;

public class AnagraficaTerzePartiDTO extends FullAnagraficaDTO {
    private static final long serialVersionUID = -5033305975768471583L;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AnagraficaTerzePartiDTO that = (AnagraficaTerzePartiDTO) o;

        return note != null ? note.equals(that.note) : that.note == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }
}
