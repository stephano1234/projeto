package br.com.contmatic.templates.conta;

import static br.com.contmatic.utilidades.FuncoesRandomicas.apenasUmCaractere;
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

	/** The Constant SEM_LETRA_NUMERAL. */
	private static final String SEM_LETRA_NUMERAL = "[^A-Za-z0-9]";

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
	
	/**
	 * Builds the nao apenas numeral codigo banco.
	 *
	 * @return the agencia
	 */
	public static Agencia buildNaoApenasNumeralCodigoBanco() {
		Agencia agencia = buildValido();
		agencia.setCodigoBanco(apenasUmCaractere(MAX_CODIGO_BANCO, SEM_LETRA_NUMERAL, APENAS_LETRA_NUMERAL));
		return agencia;
	}

	/**
	 * Builds the nao apenas numeral numero.
	 *
	 * @return the agencia
	 */
	public static Agencia buildNaoApenasNumeralNumero() {
		Agencia agencia = buildValido();
		agencia.setNumero(apenasUmCaractere(MAX_NUMERO_AGENCIA, SEM_LETRA_NUMERAL, APENAS_LETRA_NUMERAL));
		return agencia;
	}

	/**
	 * Builds the mais que 4 numerais codigo banco.
	 *
	 * @return the agencia
	 */
	public static Agencia buildMaisQue4NumeraisCodigoBanco() {
		Agencia agencia = buildValido();
		agencia.setCodigoBanco(somenteCaractere(MAX_CODIGO_BANCO + 1, APENAS_LETRA_NUMERAL));
		return agencia;
	}

	/**
	 * Builds the mais que 5 numerais numero.
	 *
	 * @return the agencia
	 */
	public static Agencia buildMaisQue5NumeraisNumero() {
		Agencia agencia = buildValido();
		agencia.setNumero(somenteCaractere(MAX_NUMERO_AGENCIA + 1, APENAS_LETRA_NUMERAL));
		return agencia;
	}
	
}
