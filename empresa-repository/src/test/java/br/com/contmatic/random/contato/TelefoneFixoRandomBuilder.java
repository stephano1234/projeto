package br.com.contmatic.random.contato;

import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;

import br.com.contmatic.model.contato.TelefoneFixo;

public class TelefoneFixoRandomBuilder {

	public static final int DDD = 2;
	
	public static final int NUMERO_TELEFONE_FIXO = 8;
	
	public static final String APENAS_NUMERAL = "[0-9]";

	public static TelefoneFixo buildValido() {
		TelefoneFixo telefoneFixo = new TelefoneFixo();
		telefoneFixo.setDdd(somenteCaractere(DDD, APENAS_NUMERAL));
		telefoneFixo.setNumero(somenteCaractere(NUMERO_TELEFONE_FIXO, APENAS_NUMERAL));
		return telefoneFixo;
	}

}
