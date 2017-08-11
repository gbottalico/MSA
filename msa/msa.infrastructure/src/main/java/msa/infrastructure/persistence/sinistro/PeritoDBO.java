package msa.infrastructure.persistence.sinistro;

import msa.infrastructure.persistence.AbstractDBO;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by simon.calabrese on 11/08/2017.
 */
public class PeritoDBO extends AbstractDBO {
    @Field("luogoPerizia")
    private LuogoDBO luogoPerizia;
    @Field("denominazione")
    private String denominazione;
    @Field("cfPartitaIva")
    private String cfPartitaIva;
    @Field("telefono")
    private String telefono;
    @Field("targaDaPerizare")
    private String targaDaPerizare;
    @Field("notePerizia")
    private String notePerizia;

    public LuogoDBO getLuogoPerizia() {
        return luogoPerizia;
    }

    public void setLuogoPerizia(LuogoDBO luogoPerizia) {
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
