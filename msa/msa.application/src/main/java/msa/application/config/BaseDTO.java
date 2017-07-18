package msa.application.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import msa.application.config.enumerator.Esito;

public class BaseDTO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1479480688346891374L;

	
	private Esito esito;
	private T result;
	private List<Message> messaggi = new ArrayList<Message>();
	
	public Esito getEsito() {
		return esito;
	}
	public void setEsito(Esito esito) {
		this.esito = esito;
	}
	public List<Message> getMessaggi() {
		return messaggi;
	}
	public void setMessaggi(List<Message> messaggi) {
		this.messaggi = messaggi;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
}
