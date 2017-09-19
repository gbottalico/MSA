package msa.application.dto.sinistro;

public class LuogoDTO {
    private String codNazione;
    private String codProvincia;
    private String codComune;
    private String descrizioneNazione;
    private String descrizioneProvincia;
    private String descrizioneComune;
    private String cap;
    
    public String getCodNazione() {
        return codNazione;
    }

    public void setCodNazione(String codNazione) {
        this.codNazione = codNazione;
    }

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getCodComune() {
        return codComune;
    }

    public void setCodComune(String codComune) {
        this.codComune = codComune;
    }

    public String getDescrizioneNazione() {
        return descrizioneNazione;
    }

    public void setDescrizioneNazione(String descrizioneNazione) {
        this.descrizioneNazione = descrizioneNazione;
    }

    public String getDescrizioneProvincia() {
        return descrizioneProvincia;
    }

    public void setDescrizioneProvincia(String descrizioneProvincia) {
        this.descrizioneProvincia = descrizioneProvincia;
    }

    public String getDescrizioneComune() {
        return descrizioneComune;
    }

    public void setDescrizioneComune(String descrizioneComune) {
        this.descrizioneComune = descrizioneComune;
    }

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

}
