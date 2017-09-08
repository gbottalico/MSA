package msa.application.dto.ricerca;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FullPolizzaDTO extends BasePolizzaDTO {
    private List<Map<Date, Date>> dateSospensione; // la mappa è formata da -> valore, a-> valore

    private String telaio;
    private Date immatricolazione;
    private String marca;
    private String modello;
    private Boolean copertura;
    private Integer id;
    private String frazionamento;
    private Date dataAcquisto;
    private Date dataCopertura;
    private String classeVeicolo;
    private String allestimento;
    private Integer hp;
    private Integer ql;
    private Integer cc;
    private BigDecimal valoreAssicurato;
    private String tipoGuida;
    private String progVeicolo; // da verificare
    private String codFiscContraente;
    private String cognomeContraente;
    private String indirizzoContraente;
    private String capContraente;
    private String cittaContraente;
    private String provinciaContraente;
    private String codFiscProprietario;
    private String cognomeProprietario;
    private String indirizzoProprietario;
    private String capProprietario;
    private String cittaProprietario;
    private String provinciaProprietario;
    private String codPacchetto;
    private String codPacchetto2;
    private String cellulare;
    private String email;
    private String brokerAgenzia;     // DA verificare perchè nel JSON sono NULL

    private String collabor;    // DA verificare perchè nel JSON sono NULL

    private String brokerAnagrafica;     // DA verificare perchè nel JSON sono NULL

    private String brokerIndirizzo;
    private String brokerCap;
    private String brokerCitta;
    private String brokerProvincia;
    private String utilizzatore;    // DA verificare perchè nel JSON sono NULL
    private List<GaranziaDTO> listaGaranzie;

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

    public List<GaranziaDTO> getListaGaranzie() {
        return listaGaranzie;
    }

    public void setListaGaranzie(List<GaranziaDTO> listaGaranzie) {
        this.listaGaranzie = listaGaranzie;
    }

    public List<Map<Date, Date>> getDateSospensione() {
        return dateSospensione;
    }

    public void setDateSospensione(List<Map<Date, Date>> dateSospensione) {
        this.dateSospensione = dateSospensione;
    }


}
