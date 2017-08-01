package msa.application.dto.sinistro;

import msa.application.dto.sinistro.anagrafica.FullAnagraficaDTO;
import msa.application.dto.sinistro.cai.CaiDTO;
import msa.application.dto.sinistro.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.infrastructure.persistence.sinistro.AnagraficaTerzePartiDBO;

import java.io.Serializable;
import java.util.List;

public class SinistroDTO implements Serializable {
    private static final long serialVersionUID = 1665918958997727249L;
    private String id;
    private FullAnagraficaDTO contraente;
    private String numeroPolizza;
    private String compagnia;
    private String targa;
    private String nominativoCliente;
    private SegnalazioneDTO segnalazione;
    private EventoRcaDTO eventoRca;
    private ConstatazioneAmichevoleDTO constatazioneAmichevole;
    private CaiDTO cai;
    private DannoRcaDTO dannoRca;
    private List<AnagraficaTerzePartiDBO> anagraficaTerzeParti;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SinistroDTO that = (SinistroDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (contraente != null ? !contraente.equals(that.contraente) : that.contraente != null) return false;
        if (numeroPolizza != null ? !numeroPolizza.equals(that.numeroPolizza) : that.numeroPolizza != null)
            return false;
        if (compagnia != null ? !compagnia.equals(that.compagnia) : that.compagnia != null) return false;
        if (targa != null ? !targa.equals(that.targa) : that.targa != null) return false;
        if (nominativoCliente != null ? !nominativoCliente.equals(that.nominativoCliente) : that.nominativoCliente != null)
            return false;
        if (segnalazione != null ? !segnalazione.equals(that.segnalazione) : that.segnalazione != null) return false;
        if (eventoRca != null ? !eventoRca.equals(that.eventoRca) : that.eventoRca != null) return false;
        if (constatazioneAmichevole != null ? !constatazioneAmichevole.equals(that.constatazioneAmichevole) : that.constatazioneAmichevole != null)
            return false;
        if (cai != null ? !cai.equals(that.cai) : that.cai != null) return false;
        if (dannoRca != null ? !dannoRca.equals(that.dannoRca) : that.dannoRca != null) return false;
        return anagraficaTerzeParti != null ? anagraficaTerzeParti.equals(that.anagraficaTerzeParti) : that.anagraficaTerzeParti == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (contraente != null ? contraente.hashCode() : 0);
        result = 31 * result + (numeroPolizza != null ? numeroPolizza.hashCode() : 0);
        result = 31 * result + (compagnia != null ? compagnia.hashCode() : 0);
        result = 31 * result + (targa != null ? targa.hashCode() : 0);
        result = 31 * result + (nominativoCliente != null ? nominativoCliente.hashCode() : 0);
        result = 31 * result + (segnalazione != null ? segnalazione.hashCode() : 0);
        result = 31 * result + (eventoRca != null ? eventoRca.hashCode() : 0);
        result = 31 * result + (constatazioneAmichevole != null ? constatazioneAmichevole.hashCode() : 0);
        result = 31 * result + (cai != null ? cai.hashCode() : 0);
        result = 31 * result + (dannoRca != null ? dannoRca.hashCode() : 0);
        result = 31 * result + (anagraficaTerzeParti != null ? anagraficaTerzeParti.hashCode() : 0);
        return result;
    }

    public FullAnagraficaDTO getContraente() {

        return contraente;
    }

    public void setContraente(FullAnagraficaDTO contraente) {
        this.contraente = contraente;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroPolizza() {
        return numeroPolizza;
    }

    public void setNumeroPolizza(String numeroPolizza) {
        this.numeroPolizza = numeroPolizza;
    }

    public String getCompagnia() {
        return compagnia;
    }

    public void setCompagnia(String compagnia) {
        this.compagnia = compagnia;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getNominativoCliente() {
        return nominativoCliente;
    }

    public void setNominativoCliente(String nominativoCliente) {
        this.nominativoCliente = nominativoCliente;
    }

    public SegnalazioneDTO getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(SegnalazioneDTO segnalazione) {
        this.segnalazione = segnalazione;
    }

    public EventoRcaDTO getEventoRca() {
        return eventoRca;
    }

    public void setEventoRca(EventoRcaDTO eventoRca) {
        this.eventoRca = eventoRca;
    }

    public ConstatazioneAmichevoleDTO getConstatazioneAmichevole() {
        return constatazioneAmichevole;
    }

    public void setConstatazioneAmichevole(ConstatazioneAmichevoleDTO constatazioneAmichevole) {
        this.constatazioneAmichevole = constatazioneAmichevole;
    }

    public CaiDTO getCai() {
        return cai;
    }

    public void setCai(CaiDTO cai) {
        this.cai = cai;
    }

    public DannoRcaDTO getDannoRca() {
        return dannoRca;
    }

    public void setDannoRca(DannoRcaDTO dannoRca) {
        this.dannoRca = dannoRca;
    }

    public List<AnagraficaTerzePartiDBO> getAnagraficaTerzeParti() {
        return anagraficaTerzeParti;
    }

    public void setAnagraficaTerzeParti(List<AnagraficaTerzePartiDBO> anagraficaTerzeParti) {
        this.anagraficaTerzeParti = anagraficaTerzeParti;
    }

}
