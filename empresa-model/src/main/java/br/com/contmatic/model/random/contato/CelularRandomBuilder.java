package br.com.contmatic.model.random.contato;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.TipoContatoCelular;

public class CelularRandomBuilder {

	public static final int DDD = 2;
	
	public static final int NUMERO_CELULAR = 9;
	
	public static final String APENAS_NUMERAL = "[0-9]";
	
	public Celular build() {
		final Celular celular = new Celular();
		celular.setDdd(generateStringBySizeAndRegex(DDD, APENAS_NUMERAL));
		celular.setNumero(generateStringBySizeAndRegex(NUMERO_CELULAR, APENAS_NUMERAL));
		celular.setTipoContatoCelular(TipoContatoCelular.values()[nextInt(0, TipoContatoCelular.values().length)]);
		return celular;
	}

}
