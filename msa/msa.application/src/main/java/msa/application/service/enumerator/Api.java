package msa.application.service.enumerator;

public enum Api {

	CODICE_FISCALE("codiceFiscale"),
	ID_CITTA_CODICE_FISCALE("idCittaCodiceFiscale");

	private String value;

	private Api(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
