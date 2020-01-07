package br.com.contmatic.templates.contato;

import static br.com.contmatic.utilidades.FuncoesRandomicas.apenasUmCaractere;
import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;

import br.com.contmatic.model.contato.TelefoneFixo;

public class TelefoneFixoRandomBuilder {

	public static final int DDD = 2;
	
	public static final int NUMERO_TELEFONE_FIXO = 8;
	
	public static final String APENAS_NUMERAL = "[0-9]";

	private static final String SEM_NUMERAL = "[^0-9]";

	public static TelefoneFixo buildValido() {
		TelefoneFixo telefoneFixo = new TelefoneFixo();
		telefoneFixo.setDdd(somenteCaractere(DDD, APENAS_NUMERAL));
		telefoneFixo.setNumero(somenteCaractere(NUMERO_TELEFONE_FIXO, APENAS_NUMERAL));
		return telefoneFixo;
	}

	public static TelefoneFixo buildNaoApenasNumeralDdd() {
		TelefoneFixo telefoneFixo = buildValido();
		telefoneFixo.setDdd(apenasUmCaractere(DDD, SEM_NUMERAL, APENAS_NUMERAL));
		return telefoneFixo;
	}
	
	public static TelefoneFixo buildNaoApenasNumeralNumero() {
		TelefoneFixo telefoneFixo = buildValido();
		telefoneFixo.setNumero(apenasUmCaractere(NUMERO_TELEFONE_FIXO, SEM_NUMERAL, APENAS_NUMERAL));
		return telefoneFixo;
	}
	
	public static TelefoneFixo buildMenosQue2NumeraisDdd() {
		TelefoneFixo telefoneFixo = buildValido();
		telefoneFixo.setDdd(somenteCaractere(DDD - 1, APENAS_NUMERAL));
		return telefoneFixo;
	}
	
	public static TelefoneFixo buildMenosQue8NumeraisNumero() {
		TelefoneFixo telefoneFixo = buildValido();
		telefoneFixo.setNumero(somenteCaractere(NUMERO_TELEFONE_FIXO - 1, APENAS_NUMERAL));
		return telefoneFixo;
	}
	
	public static TelefoneFixo buildMaisQue2NumeraisDdd() {
		TelefoneFixo telefoneFixo = buildValido();
		telefoneFixo.setDdd(somenteCaractere(DDD + 1, APENAS_NUMERAL));
		return telefoneFixo;
	}
	
	public static TelefoneFixo buildMaisQue8NumeraisNumero() {
		TelefoneFixo telefoneFixo = buildValido();
		telefoneFixo.setNumero(somenteCaractere(NUMERO_TELEFONE_FIXO + 1, APENAS_NUMERAL));
		return telefoneFixo;
	}
	
}
