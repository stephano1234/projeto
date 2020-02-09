package br.com.contmatic.model.random.conta;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;

import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.conta.Agencia;

/**
 * The Class AgenciaRandomBuilder.
 */
public class AgenciaRandomBuilder {

	/** The max numero agencia. */
	private static final int MAX_NUMERO_AGENCIA = 5;
	
	/** The max codigo banco. */
	private static final int MAX_CODIGO_BANCO = 4;

	/** The apenas letra numeral. */
	private static final String APENAS_NUMERAL = "[0-9]";

	/**
	 * Builds the valido.
	 *
	 * @return the agencia
	 */
	public Agencia build() {
		final Agencia agencia = new Agencia();
		agencia.setNumero(generateStringBySizeAndRegex(nextInt(1, MAX_NUMERO_AGENCIA + 1), APENAS_NUMERAL));
		agencia.setCodigoBanco(generateStringBySizeAndRegex(nextInt(1, MAX_CODIGO_BANCO + 1), APENAS_NUMERAL));
		return agencia;
	}
	
}
