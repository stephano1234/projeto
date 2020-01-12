package br.com.contmatic.random.conta;

import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;

import org.apache.commons.lang3.RandomUtils;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.conta.TipoConta;

/**
 * The Class ContaRandomBuilder.
 */
public class ContaRandomBuilder {

	/** The max numero conta. */
	private static int MAX_NUMERO_CONTA = 12;
	
	/** The apenas letra numeral. */
	private static String APENAS_LETRA_NUMERAL = "[A-Za-z0-9]";

	/**
	 * Builds the valido.
	 *
	 * @return the conta
	 */
	public static Conta buildValido() {
		Conta conta = new Conta();
		conta.setNumero(somenteCaractere(MAX_NUMERO_CONTA, APENAS_LETRA_NUMERAL));
		conta.setAgencia(AgenciaRandomBuilder.buildValido());
		conta.setTipoConta(TipoConta.values()[RandomUtils.nextInt(0, TipoConta.values().length)]);
		return conta;
	}
	
}
