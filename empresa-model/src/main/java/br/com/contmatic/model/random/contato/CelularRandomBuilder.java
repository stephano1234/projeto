package br.com.contmatic.model.random.contato;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;

import br.com.contmatic.model.contato.Celular;

public class CelularRandomBuilder {

	private static final int NUMERO_CELULAR = 9;
	
	private static final String APENAS_NUMERAL = "[0-9]";
	
	public Celular build() {
		final Celular celular = new Celular();
		celular.setNumero(generateStringBySizeAndRegex(NUMERO_CELULAR, APENAS_NUMERAL));
		return celular;
	}

}
