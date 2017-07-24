package msa.application.dto.domain;

import java.util.Date;

public class ProvinciaDTO {
	private String id;

	private Integer codNazione;

	private Integer codProvincia;
	private Date iniValidita;
	private Date finValidita;
	private String descProvincia;
	private String siglaProv;
	private String codFornitore;

	public String getCodFornitore() {
		return codFornitore;
	}

	public void setCodFornitore(String codFornitore) {
		this.codFornitore = codFornitore;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCodNazione() {
		return codNazione;
	}

	public void setCodNazione(Integer codNazione) {
		this.codNazione = codNazione;
	}

	public Integer getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(Integer codProvincia) {
		this.codProvincia = codProvincia;
	}

	public Date getIniValidita() {
		return iniValidita;
	}

	public void setIniValidita(Date iniValidita) {
		this.iniValidita = iniValidita;
	}

	public Date getFinValidita() {
		return finValidita;
	}

	public void setFinValidita(Date finValidita) {
		this.finValidita = finValidita;
	}

	public String getDescProvincia() {
		return descProvincia;
	}

	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}

	public String getSiglaProv() {
		return siglaProv;
	}

	public void setSiglaProv(String siglaProv) {
		this.siglaProv = siglaProv;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProvinciaDTO that = (ProvinciaDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (codNazione != null ? !codNazione.equals(that.codNazione) : that.codNazione != null) return false;
        if (codProvincia != null ? !codProvincia.equals(that.codProvincia) : that.codProvincia != null) return false;
        if (iniValidita != null ? !iniValidita.equals(that.iniValidita) : that.iniValidita != null) return false;
        if (finValidita != null ? !finValidita.equals(that.finValidita) : that.finValidita != null) return false;
        if (descProvincia != null ? !descProvincia.equals(that.descProvincia) : that.descProvincia != null)
            return false;
        if (siglaProv != null ? !siglaProv.equals(that.siglaProv) : that.siglaProv != null) return false;
        return codFornitore != null ? codFornitore.equals(that.codFornitore) : that.codFornitore == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (codNazione != null ? codNazione.hashCode() : 0);
        result = 31 * result + (codProvincia != null ? codProvincia.hashCode() : 0);
        result = 31 * result + (iniValidita != null ? iniValidita.hashCode() : 0);
        result = 31 * result + (finValidita != null ? finValidita.hashCode() : 0);
        result = 31 * result + (descProvincia != null ? descProvincia.hashCode() : 0);
        result = 31 * result + (siglaProv != null ? siglaProv.hashCode() : 0);
        result = 31 * result + (codFornitore != null ? codFornitore.hashCode() : 0);
        return result;
    }
}
