package msa.application.service.enumerator;

public enum Api {

	ANAGRAFICA("ricercaAnagrafica");

	private String value;

	private Api(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
