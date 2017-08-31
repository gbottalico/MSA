package msa.application.config.enumerator;

public enum TipoPersona {
    PG("PG"),PF("PF");
    private String cod;

    TipoPersona(String cod) {
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }
}
