package br.com.contmatic.random.conta;

import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;

import br.com.contmatic.model.conta.Agencia;

/**
 * The Class AgenciaRandomBuilder.
 */
public class AgenciaRandomBuilder {

	/** The max numero agencia. */
	private static int MAX_NUMERO_AGENCIA = 5;
	
	/** The max codigo banco. */
	private static int MAX_CODIGO_BANCO = 4;

	/** The apenas letra numeral. */
	private static String APENAS_LETRA_NUMERAL = "[A-Za-z0-9]";

	/**
	 * Builds the valido.
	 *
	 * @return the agencia
	 */
	public static Agencia buildValido() {
		Agencia agencia = new Agencia();
		agencia.setNumero(somenteCaractere(MAX_NUMERO_AGENCIA, APENAS_LETRA_NUMERAL));
		agencia.setCodigoBanco(somenteCaractere(MAX_CODIGO_BANCO, APENAS_LETRA_NUMERAL));
		return agencia;
	}
	
}
