package br.com.contmatic.model.random.contato;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.emailAleatorio;

import br.com.contmatic.model.contato.Email;

public class EmailRandomBuilder {

	public Email build() {
		final Email email = new Email();
		email.setEndereco(emailAleatorio());
		return email;
	}
	
}
