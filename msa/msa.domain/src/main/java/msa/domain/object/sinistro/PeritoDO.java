package msa.domain.object.sinistro;

/**
 * Created by simon.calabrese on 11/08/2017.
 */
public class PeritoDO {
    private LuogoDO luogoPerizia;
    private String denominazione;
    private String cfPartitaIva;
    private String telefono;
    private String targaDaPerizare;
    private String notePerizia;

    public LuogoDO getLuogoPerizia() {
        return luogoPerizia;
    }

    public void setLuogoPerizia(LuogoDO luogoPerizia) {
        this.luogoPerizia = luogoPerizia;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getCfPartitaIva() {
        return cfPartitaIva;
    }

    public void setCfPartitaIva(String cfPartitaIva) {
        this.cfPartitaIva = cfPartitaIva;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTargaDaPerizare() {
        return targaDaPerizare;
    }

    public void setTargaDaPerizare(String targaDaPerizare) {
        this.targaDaPerizare = targaDaPerizare;
    }

    public String getNotePerizia() {
        return notePerizia;
    }

    public void setNotePerizia(String notePerizia) {
        this.notePerizia = notePerizia;
    }
}
