package msa.application.config;

import msa.application.config.enumerator.MessageType;

public class Message {
	
	private MessageType tipo;
	private String text;
	
	public Message(MessageType tipo, String text) {
		super();
		this.tipo = tipo;
		this.text = text;
	}
	
	public MessageType getTipo() {
		return tipo;
	}
	public void setTipo(MessageType tipo) {
		this.tipo = tipo;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
