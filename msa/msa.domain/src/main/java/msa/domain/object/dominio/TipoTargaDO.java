package msa.domain.object.dominio;

public class TipoTargaDO {
    private Integer id;
    private String descrizione;
    private Character codAnia;
    private Integer codNewAge;
    private String codFornitore;

    public String getCodFornitore() {
        return codFornitore;
    }

    public void setCodFornitore(String codFornitore) {
        this.codFornitore = codFornitore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Character getCodAnia() {
        return codAnia;
    }

    public void setCodAnia(Character codAnia) {
        this.codAnia = codAnia;
    }

    public Integer getCodNewAge() {
        return codNewAge;
    }

    public void setCodNewAge(Integer codNewAge) {
        this.codNewAge = codNewAge;
    }
}
