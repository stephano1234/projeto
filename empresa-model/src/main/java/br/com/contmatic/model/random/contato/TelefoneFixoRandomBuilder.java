package br.com.contmatic.model.random.contato;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;

import br.com.contmatic.model.contato.TelefoneFixo;

public class TelefoneFixoRandomBuilder {

	public static final int DDD = 2;
	
	public static final int NUMERO_TELEFONE_FIXO = 8;
	
	public static final String APENAS_NUMERAL = "[0-9]";
	
	public TelefoneFixo build() {
		final TelefoneFixo telefoneFixo = new TelefoneFixo();
		telefoneFixo.setDdd(generateStringBySizeAndRegex(DDD, APENAS_NUMERAL));
		telefoneFixo.setNumero(generateStringBySizeAndRegex(NUMERO_TELEFONE_FIXO, APENAS_NUMERAL));
		return telefoneFixo;
	}

}
