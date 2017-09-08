package msa.infrastructure.persistence.ricerca;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FullPolizzaDBO extends BasePolizzaDBO{
    @Field("dateSospensione")
    private List<Map<Date, Date>> dateSospensione; // la mappa è formata da -> valore, a-> valore
    @Field("telaio")
    private String telaio;
    @Field("immatricolazione")
    private Date immatricolazione;
    @Field("marca")
    private String marca;
    @Field("modello")
    private String modello;
    @Field("copertura")
    private Boolean copertura;
    @Field("id")
    private Integer id;
    @Field("frazionamento")
    private String frazionamento;
    @Field("dataAcquisto")
    private Date dataAcquisto;
    @Field("dataCopertura")
    private Date dataCopertura;
    @Field("classeVeicolo")
    private String classeVeicolo;
    @Field("allestimento")
    private String allestimento;
    @Field("hp")

    private Integer hp;
    @Field("ql")

    private Integer ql;
    @Field("cc")

    private Integer cc;
    @Field("valoreAssicurato")

    private BigDecimal valoreAssicurato;
    @Field("tipoGuida")

    private String tipoGuida;
    @Field("progVeicolo")

    private String progVeicolo; // da verificare
    @Field("codFiscContraente")

    private String codFiscContraente;
    @Field("cognomeContraente")
    private String cognomeContraente;
    @Field("indirizzoContraente")
    private String indirizzoContraente;
    @Field("capContraente")
    private String capContraente;
    @Field("cittaContraente")
    private String cittaContraente;
    @Field("provinciaContraente")
    private String provinciaContraente;
    @Field("codFiscProprietario")
    private String codFiscProprietario;
    @Field("cognomeProprietario")
    private String cognomeProprietario;
    @Field("indirizzoProprietario")
    private String indirizzoProprietario;
    @Field("capProprietario")

    private String capProprietario;
    @Field("cittaProprietario")
    private String cittaProprietario;
    @Field("provinciaProprietario")
    private String provinciaProprietario;
    @Field("codPacchetto")
    private String codPacchetto;
    @Field("codPacchetto2")
    private String codPacchetto2;
    @Field("cellulare")
    private String cellulare;
    @Field("email")
    private String email;
    @Field("brokerAgenzia")
    private String brokerAgenzia;     // DA verificare perchè nel JSON sono NULL
    @Field("collabor")
    private String collabor;    // DA verificare perchè nel JSON sono NULL
    @Field("brokerAnagrafica")
    private String brokerAnagrafica;     // DA verificare perchè nel JSON sono NULL
    @Field("brokerIndirizzo")
    private String brokerIndirizzo;
    @Field("brokerCap")
    private String brokerCap;
    @Field("brokerCitta")
    private String brokerCitta;
    @Field("brokerProvincia")
    private String brokerProvincia;
    @Field("utilizzatore")
    private String utilizzatore;    // DA verificare perchè nel JSON sono NULL
    @Field("listaGaranzie")
    private List<GaranziaDBO> listaGaranzie;

    public String getTelaio() {
        return telaio;
    }

    public void setTelaio(String telaio) {
        this.telaio = telaio;
    }

    public Date getImmatricolazione() {
        return immatricolazione;
    }

    public void setImmatricolazione(Date immatricolazione) {
        this.immatricolazione = immatricolazione;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public Boolean getCopertura() {
        return copertura;
    }

    public void setCopertura(Boolean copertura) {
        this.copertura = copertura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrazionamento() {
        return frazionamento;
    }

    public void setFrazionamento(String frazionamento) {
        this.frazionamento = frazionamento;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public Date getDataCopertura() {
        return dataCopertura;
    }

    public void setDataCopertura(Date dataCopertura) {
        this.dataCopertura = dataCopertura;
    }

    public String getClasseVeicolo() {
        return classeVeicolo;
    }

    public void setClasseVeicolo(String classeVeicolo) {
        this.classeVeicolo = classeVeicolo;
    }

    public String getAllestimento() {
        return allestimento;
    }

    public void setAllestimento(String allestimento) {
        this.allestimento = allestimento;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getQl() {
        return ql;
    }

    public void setQl(Integer ql) {
        this.ql = ql;
    }

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public BigDecimal getValoreAssicurato() {
        return valoreAssicurato;
    }

    public void setValoreAssicurato(BigDecimal valoreAssicurato) {
        this.valoreAssicurato = valoreAssicurato;
    }

    public String getTipoGuida() {
        return tipoGuida;
    }

    public void setTipoGuida(String tipoGuida) {
        this.tipoGuida = tipoGuida;
    }

    public String getProgVeicolo() {
        return progVeicolo;
    }

    public void setProgVeicolo(String progVeicolo) {
        this.progVeicolo = progVeicolo;
    }

    public String getCodFiscContraente() {
        return codFiscContraente;
    }

    public void setCodFiscContraente(String codFiscContraente) {
        this.codFiscContraente = codFiscContraente;
    }

    public String getCognomeContraente() {
        return cognomeContraente;
    }

    public void setCognomeContraente(String cognomeContraente) {
        this.cognomeContraente = cognomeContraente;
    }

    public String getIndirizzoContraente() {
        return indirizzoContraente;
    }

    public void setIndirizzoContraente(String indirizzoContraente) {
        this.indirizzoContraente = indirizzoContraente;
    }

    public String getCapContraente() {
        return capContraente;
    }

    public void setCapContraente(String capContraente) {
        this.capContraente = capContraente;
    }

    public String getCittaContraente() {
        return cittaContraente;
    }

    public void setCittaContraente(String cittaContraente) {
        this.cittaContraente = cittaContraente;
    }

    public String getProvinciaContraente() {
        return provinciaContraente;
    }

    public void setProvinciaContraente(String provinciaContraente) {
        this.provinciaContraente = provinciaContraente;
    }

    public String getCodFiscProprietario() {
        return codFiscProprietario;
    }

    public void setCodFiscProprietario(String codFiscProprietario) {
        this.codFiscProprietario = codFiscProprietario;
    }

    public String getCognomeProprietario() {
        return cognomeProprietario;
    }

    public void setCognomeProprietario(String cognomeProprietario) {
        this.cognomeProprietario = cognomeProprietario;
    }

    public String getIndirizzoProprietario() {
        return indirizzoProprietario;
    }

    public void setIndirizzoProprietario(String indirizzoProprietario) {
        this.indirizzoProprietario = indirizzoProprietario;
    }

    public String getCapProprietario() {
        return capProprietario;
    }

    public void setCapProprietario(String capProprietario) {
        this.capProprietario = capProprietario;
    }

    public String getCittaProprietario() {
        return cittaProprietario;
    }

    public void setCittaProprietario(String cittaProprietario) {
        this.cittaProprietario = cittaProprietario;
    }

    public String getProvinciaProprietario() {
        return provinciaProprietario;
    }

    public void setProvinciaProprietario(String provinciaProprietario) {
        this.provinciaProprietario = provinciaProprietario;
    }

    public String getCodPacchetto() {
        return codPacchetto;
    }

    public void setCodPacchetto(String codPacchetto) {
        this.codPacchetto = codPacchetto;
    }

    public String getCodPacchetto2() {
        return codPacchetto2;
    }

    public void setCodPacchetto2(String codPacchetto2) {
        this.codPacchetto2 = codPacchetto2;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrokerAgenzia() {
        return brokerAgenzia;
    }

    public void setBrokerAgenzia(String brokerAgenzia) {
        this.brokerAgenzia = brokerAgenzia;
    }

    public String getCollabor() {
        return collabor;
    }

    public void setCollabor(String collabor) {
        this.collabor = collabor;
    }

    public String getBrokerAnagrafica() {
        return brokerAnagrafica;
    }

    public void setBrokerAnagrafica(String brokerAnagrafica) {
        this.brokerAnagrafica = brokerAnagrafica;
    }

    public String getBrokerIndirizzo() {
        return brokerIndirizzo;
    }

    public void setBrokerIndirizzo(String brokerIndirizzo) {
        this.brokerIndirizzo = brokerIndirizzo;
    }

    public String getBrokerCap() {
        return brokerCap;
    }

    public void setBrokerCap(String brokerCap) {
        this.brokerCap = brokerCap;
    }

    public String getBrokerCitta() {
        return brokerCitta;
    }

    public void setBrokerCitta(String brokerCitta) {
        this.brokerCitta = brokerCitta;
    }

    public String getBrokerProvincia() {
        return brokerProvincia;
    }

    public void setBrokerProvincia(String brokerProvincia) {
        this.brokerProvincia = brokerProvincia;
    }

    public String getUtilizzatore() {
        return utilizzatore;
    }

    public void setUtilizzatore(String utilizzatore) {
        this.utilizzatore = utilizzatore;
    }



    public List<Map<Date, Date>> getDateSospensione() {
        return dateSospensione;
    }

    public void setDateSospensione(List<Map<Date, Date>> dateSospensione) {
        this.dateSospensione = dateSospensione;
    }

    public List<GaranziaDBO> getListaGaranzie() {
        return listaGaranzie;
    }

    public void setListaGaranzie(List<GaranziaDBO> listaGaranzie) {
        this.listaGaranzie = listaGaranzie;
    }
}
