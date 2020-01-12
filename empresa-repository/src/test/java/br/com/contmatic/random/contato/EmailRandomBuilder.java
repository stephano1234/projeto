package br.com.contmatic.random.contato;

import static br.com.contmatic.utilidades.FuncoesRandomicas.emailAleatorio;

import br.com.contmatic.model.contato.Email;

public class EmailRandomBuilder {

	public static Email buildValido() {
		Email email = new Email();
		email.setEndereco(emailAleatorio());
		return email;
	}
	
}
