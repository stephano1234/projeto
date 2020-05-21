package br.com.contmatic.model.random.contato;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;

import br.com.contmatic.model.contato.TelefoneFixo;

public class TelefoneFixoRandomBuilder {

	private static final int NUMERO_TELEFONE_FIXO = 8;
	
	private static final String APENAS_NUMERAL = "[0-9]";
	
	public TelefoneFixo build() {
		final TelefoneFixo telefoneFixo = new TelefoneFixo();
		telefoneFixo.setNumero(generateStringBySizeAndRegex(NUMERO_TELEFONE_FIXO, APENAS_NUMERAL));
		return telefoneFixo;
	}

}
