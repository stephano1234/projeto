package br.com.contmatic.service.mensagens;

import java.util.ArrayList;
import java.util.List;

public class MensagemServidor {

	private int statusCode;
	
	private List<String> mensagens;

	public MensagemServidor(int statusCode, String mensagem) {
		this.statusCode = statusCode;
		this.mensagens = new ArrayList<>();
		this.mensagens.add(mensagem);
	}
	
	public MensagemServidor(int statusCode, List<String> mensagens) {
		this.statusCode = statusCode;
		this.mensagens = mensagens;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

}
