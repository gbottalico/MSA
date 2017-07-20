package msa.infrastructure.persistence;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "province")

public class ProvinciaDBO{
@Id
private String id;
@Field("Provincia")
private String nomeProvincia;
@Field("CapProvincia")
private Integer cap;
@Field("CodIvassProvincia")
private Integer codIvass;
@Field("SiglaTargaProvincia")
private String siglaTargaProvincia;
@Field("SiglaTargaProvvisoria")
private String siglaTargaProvv;
@Field("SiglaTargaSita")
private String siglaTargaSita;
@Field("DataInserimento")
private Date dataInserimento;
@Field("DataVariazione")
private Date dataVariazione;
@Field("IdNazione")
private Integer idNazione;
@Field("CodFornitore")
private String codFornitore;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getNomeProvincia() {
	return nomeProvincia;
}
public void setNomeProvincia(String nomeProvincia) {
	this.nomeProvincia = nomeProvincia;
}
public Integer getCap() {
	return cap;
}
public void setCap(Integer cap) {
	this.cap = cap;
}
public Integer getCodIvass() {
	return codIvass;
}
public void setCodIvass(Integer codIvass) {
	this.codIvass = codIvass;
}
public String getSiglaTargaProvincia() {
	return siglaTargaProvincia;
}
public void setSiglaTargaProvincia(String siglaTargaProvincia) {
	this.siglaTargaProvincia = siglaTargaProvincia;
}
public String getSiglaTargaProvv() {
	return siglaTargaProvv;
}
public void setSiglaTargaProvv(String siglaTargaProvv) {
	this.siglaTargaProvv = siglaTargaProvv;
}
public String getSiglaTargaSita() {
	return siglaTargaSita;
}
public void setSiglaTargaSita(String siglaTargaSita) {
	this.siglaTargaSita = siglaTargaSita;
}
public Date getDataInserimento() {
	return dataInserimento;
}
public void setDataInserimento(Date dataInserimento) {
	this.dataInserimento = dataInserimento;
}
public Date getDataVariazione() {
	return dataVariazione;
}
public void setDataVariazione(Date dataVariazione) {
	this.dataVariazione = dataVariazione;
}
public Integer getIdNazione() {
	return idNazione;
}
public void setIdNazione(Integer idNazione) {
	this.idNazione = idNazione;
}
public String getCodFornitore() {
	return codFornitore;
}
public void setCodFornitore(String codFornitore) {
	this.codFornitore = codFornitore;
}




}
