package br.com.contmatic.model.random.conta;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;

import br.com.contmatic.model.conta.Agencia;

/**
 * The Class AgenciaRandomBuilder.
 */
public class AgenciaTestRandomBuilder {

	/** The max numero agencia. */
	private static final int MAX_NUMERO_AGENCIA = 5;
	
	/** The max codigo banco. */
	private static final int MAX_CODIGO_BANCO = 4;

	/** The apenas letra numeral. */
	private static final String APENAS_LETRA_NUMERAL = "[A-Za-z0-9]";

	/** The Constant SEM_LETRA_NUMERAL. */
	private static final String SEM_LETRA_NUMERAL = "[^A-Za-z0-9]";
	
	private final Agencia agenciaValida = new AgenciaRandomBuilder().build();
	
	private static AgenciaTestRandomBuilder instance;
	
	private AgenciaTestRandomBuilder() {
	}
	
	public static AgenciaTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new AgenciaTestRandomBuilder();
		}
		return instance;
	}
	
	public static void cleanBuilder() {
		instance = null;
	}
	
	public Agencia buildValid() {
		return agenciaValida;
	}
	
	public Agencia buildNuloCodigoBanco() {
		final Agencia agencia = new Agencia();
		agencia.setNumero(agenciaValida.getNumero());
		agencia.setCodigoBanco(null);
		return agencia;		
	}

	public Agencia buildVazioCodigoBanco() {
		final Agencia agencia = new Agencia();
		agencia.setNumero(agenciaValida.getNumero());
		agencia.setCodigoBanco("");
		return agencia;		
	}

	public Agencia buildNuloNumero() {
		final Agencia agencia = new Agencia();
		agencia.setCodigoBanco(agenciaValida.getCodigoBanco());
		agencia.setNumero(null);
		return agencia;		
	}

	public Agencia buildVazioNumero() {
		final Agencia agencia = new Agencia();
		agencia.setCodigoBanco(agenciaValida.getCodigoBanco());
		agencia.setNumero("");
		return agencia;		
	}
	
	/**
	 * Builds the nao apenas numeral codigo banco.
	 *
	 * @return the agencia
	 */
	public Agencia buildNaoApenasNumeralCodigoBanco() {
		final Agencia agencia = new Agencia();
		agencia.setNumero(agenciaValida.getNumero());
		agencia.setCodigoBanco(generateStringBySizeAndRegexWithOneCharByRegex(MAX_CODIGO_BANCO, SEM_LETRA_NUMERAL, APENAS_LETRA_NUMERAL));
		return agencia;
	}

	/**
	 * Builds the nao apenas numeral numero.
	 *
	 * @return the agencia
	 */
	public Agencia buildNaoApenasNumeralNumero() {
		final Agencia agencia = new Agencia();
		agencia.setCodigoBanco(agenciaValida.getCodigoBanco());
		agencia.setNumero(generateStringBySizeAndRegexWithOneCharByRegex(MAX_NUMERO_AGENCIA, SEM_LETRA_NUMERAL, APENAS_LETRA_NUMERAL));
		return agencia;
	}

	/**
	 * Builds the mais que 4 numerais codigo banco.
	 *
	 * @return the agencia
	 */
	public Agencia buildMaisQue4NumeraisCodigoBanco() {
		final Agencia agencia = new Agencia();
		agencia.setNumero(agenciaValida.getNumero());
		agencia.setCodigoBanco(generateStringBySizeAndRegex(MAX_CODIGO_BANCO + 1, APENAS_LETRA_NUMERAL));
		return agencia;
	}

	/**
	 * Builds the mais que 5 numerais numero.
	 *
	 * @return the agencia
	 */
	public Agencia buildMaisQue5NumeraisNumero() {
		final Agencia agencia = new Agencia();
		agencia.setCodigoBanco(agenciaValida.getCodigoBanco());
		agencia.setNumero(generateStringBySizeAndRegex(MAX_NUMERO_AGENCIA + 1, APENAS_LETRA_NUMERAL));
		return agencia;
	}
	
}
