package br.com.contmatic.model.random.conta;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;

import br.com.contmatic.model.conta.Conta;

/**
 * The Class ContaRandomBuilder.
 */
public class ContaTestRandomBuilder {

	/** The max numero conta. */
	private static int MAX_NUMERO_CONTA = 12;
	
	/** The apenas letra numeral. */
	private static String APENAS_LETRA_NUMERAL = "[A-Za-z0-9]";

	/** The Constant SEM_LETRA_NUMERAL. */
	private static final String SEM_LETRA_NUMERAL = "[^A-Za-z0-9]";

	private static final AgenciaTestRandomBuilder randomAgencia = AgenciaTestRandomBuilder.getInstance();
	
	private final Conta contaValida = new ContaRandomBuilder().build();
	
	private static ContaTestRandomBuilder instance;
	
	public static ContaTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new ContaTestRandomBuilder();
		}
		return instance;
	}
	
	public static void cleanBuilder() {
		AgenciaTestRandomBuilder.cleanBuilder();
		instance = null;
	}
	
	public Conta buildValid() {
		return contaValida;
	}
	
	public Conta buildNuloAgencia() {
		final Conta conta = new Conta();
		conta.setAgencia(null);
		conta.setNumero(contaValida.getNumero());
		conta.setTipoConta(contaValida.getTipoConta());
		return conta;
	}	
	
	/**
	 * Builds the nao apenas numeral codigo banco.
	 *
	 * @return the conta
	 */
	public Conta buildAgenciaIvalido() {
		final Conta conta = new Conta();
		conta.setAgencia(randomAgencia.buildNaoApenasNumeralNumero());
		conta.setNumero(contaValida.getNumero());
		conta.setTipoConta(contaValida.getTipoConta());
		return conta;
	}

	public Conta buildNuloNumero() {
		final Conta conta = new Conta();
		conta.setAgencia(contaValida.getAgencia());
		conta.setNumero(null);
		conta.setTipoConta(contaValida.getTipoConta());
		return conta;
	}

	public Conta buildVazioNumero() {
		final Conta conta = new Conta();
		conta.setAgencia(contaValida.getAgencia());
		conta.setNumero("");
		conta.setTipoConta(contaValida.getTipoConta());
		return conta;
	}

	/**
	 * Builds the nao apenas numeral numero.
	 *
	 * @return the conta
	 */
	public Conta buildNaoApenasNumeralNumero() {
		final Conta conta = new Conta();
		conta.setAgencia(contaValida.getAgencia());
		conta.setNumero(generateStringBySizeAndRegexWithOneCharByRegex(MAX_NUMERO_CONTA, SEM_LETRA_NUMERAL, APENAS_LETRA_NUMERAL));
		conta.setTipoConta(contaValida.getTipoConta());
		return conta;
	}

	/**
	 * Builds the mais que 5 numerais numero.
	 *
	 * @return the conta
	 */
	public Conta buildMaisQue12NumeraisNumero() {
		final Conta conta = new Conta();
		conta.setAgencia(contaValida.getAgencia());
		conta.setNumero(generateStringBySizeAndRegex(MAX_NUMERO_CONTA + 1, APENAS_LETRA_NUMERAL));
		conta.setTipoConta(contaValida.getTipoConta());
		return conta;
	}
	
	public Conta buildNuloTipoConta() {
		final Conta conta = new Conta();
		conta.setAgencia(contaValida.getAgencia());
		conta.setNumero(contaValida.getNumero());
		conta.setTipoConta(null);
		return conta;
	}

}
