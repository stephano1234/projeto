package br.com.contmatic.templates.contato;

import static br.com.contmatic.utilidades.FuncoesRandomicas.apenasUmCaractere;
import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;

import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.TipoContatoCelular;

public class CelularRandomBuilder {

	public static final int DDD = 2;
	
	public static final int NUMERO_CELULAR = 9;
	
	public static final String APENAS_NUMERAL = "[0-9]";

	private static final String SEM_NUMERAL = "[^0-9]";

	public static Celular buildValido() {
		Celular celular = new Celular();
		celular.setDdd(somenteCaractere(DDD, APENAS_NUMERAL));
		celular.setNumero(somenteCaractere(NUMERO_CELULAR, APENAS_NUMERAL));
		celular.setTipoContatoCelular(TipoContatoCelular.values()[nextInt(0, TipoContatoCelular.values().length)]);
		return celular;
	}

	public static Celular buildNaoApenasNumeralDdd() {
		Celular celular = buildValido();
		celular.setDdd(apenasUmCaractere(DDD, SEM_NUMERAL, APENAS_NUMERAL));
		return celular;
	}
	
	public static Celular buildNaoApenasNumeralNumero() {
		Celular celular = buildValido();
		celular.setNumero(apenasUmCaractere(NUMERO_CELULAR, SEM_NUMERAL, APENAS_NUMERAL));
		return celular;
	}
	
	public static Celular buildMenosQue2NumeraisDdd() {
		Celular celular = buildValido();
		celular.setDdd(somenteCaractere(DDD - 1, APENAS_NUMERAL));
		return celular;
	}
	
	public static Celular buildMenosQue9NumeraisNumero() {
		Celular celular = buildValido();
		celular.setNumero(somenteCaractere(NUMERO_CELULAR - 1, APENAS_NUMERAL));
		return celular;
	}
	
	public static Celular buildMaisQue2NumeraisDdd() {
		Celular celular = buildValido();
		celular.setDdd(somenteCaractere(DDD + 1, APENAS_NUMERAL));
		return celular;
	}
	
	public static Celular buildMaisQue9NumeraisNumero() {
		Celular celular = buildValido();
		celular.setNumero(somenteCaractere(NUMERO_CELULAR + 1, APENAS_NUMERAL));
		return celular;
	}
	
}
