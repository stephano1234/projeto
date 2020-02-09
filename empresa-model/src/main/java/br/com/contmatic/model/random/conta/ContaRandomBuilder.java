package br.com.contmatic.model.random.conta;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.conta.TipoConta;

/**
 * The Class ContaRandomBuilder.
 */
public class ContaRandomBuilder {

	/** The max numero conta. */
	private static final int MAX_NUMERO_CONTA = 12;
	
	/** The apenas letra numeral. */
	private static final String APENAS_NUMERAL = "[0-9]";

	private AgenciaRandomBuilder agenciaRandomBuilder = new AgenciaRandomBuilder();
	
	/**
	 * Builds the valido.
	 *
	 * @return the conta
	 */
	public Conta build() {
		final Conta conta = new Conta();
		conta.setNumero(generateStringBySizeAndRegex(nextInt(1, MAX_NUMERO_CONTA + 1), APENAS_NUMERAL));
		conta.setAgencia(agenciaRandomBuilder.build());
		conta.setTipoConta(TipoConta.values()[nextInt(0, TipoConta.values().length)]);
		return conta;
	}
	
}
