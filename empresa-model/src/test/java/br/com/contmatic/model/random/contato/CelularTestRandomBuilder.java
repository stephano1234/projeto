package br.com.contmatic.model.random.contato;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;

import br.com.contmatic.model.contato.Celular;

public class CelularTestRandomBuilder {

	public static final int DDD = 2;
	
	public static final int NUMERO_CELULAR = 9;
	
	public static final String APENAS_NUMERAL = "[0-9]";

	private static final String SEM_NUMERAL = "[^0-9]";

	private final Celular celularValido = new CelularRandomBuilder().build();
	
	private static CelularTestRandomBuilder instance;
	
	public static CelularTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new CelularTestRandomBuilder();
		}
		return instance;
	}
	
	public static void cleanBuilder() {
		instance = null;
	}

	public Celular buildValid() {
		return celularValido;
	}

	public Celular buildNuloDdd() {
		Celular celular = new Celular();
		celular.setDdd(null);
		celular.setNumero(celularValido.getNumero());
		celular.setTipoContatoCelular(celularValido.getTipoContatoCelular());
		return celular;
	}
	
	public Celular buildNuloNumero() {
		Celular celular = new Celular();
		celular.setDdd(celularValido.getDdd());
		celular.setNumero(null);
		celular.setTipoContatoCelular(celularValido.getTipoContatoCelular());
		return celular;
	}
	
	public Celular buildVazioDdd() {
		Celular celular = new Celular();
		celular.setDdd("");
		celular.setNumero(celularValido.getNumero());
		celular.setTipoContatoCelular(celularValido.getTipoContatoCelular());
		return celular;
	}
	
	public Celular buildVazioNumero() {
		Celular celular = new Celular();
		celular.setDdd(celularValido.getDdd());
		celular.setNumero("");
		celular.setTipoContatoCelular(celularValido.getTipoContatoCelular());
		return celular;
	}
	
	public Celular buildNaoApenasNumeralDdd() {
		Celular celular = new Celular();
		celular.setDdd(generateStringBySizeAndRegexWithOneCharByRegex(DDD, SEM_NUMERAL, APENAS_NUMERAL));
		celular.setNumero(celularValido.getNumero());
		celular.setTipoContatoCelular(celularValido.getTipoContatoCelular());
		return celular;
	}
	
	public Celular buildNaoApenasNumeralNumero() {
		Celular celular = new Celular();
		celular.setDdd(celularValido.getDdd());
		celular.setNumero(generateStringBySizeAndRegexWithOneCharByRegex(NUMERO_CELULAR, SEM_NUMERAL, APENAS_NUMERAL));
		celular.setTipoContatoCelular(celularValido.getTipoContatoCelular());
		return celular;
	}
	
	public Celular buildMenosQue2NumeraisDdd() {
		Celular celular = new Celular();
		celular.setDdd(generateStringBySizeAndRegex(DDD - 1, APENAS_NUMERAL));
		celular.setNumero(celularValido.getNumero());
		celular.setTipoContatoCelular(celularValido.getTipoContatoCelular());
		return celular;
	}
	
	public Celular buildMenosQue9NumeraisNumero() {
		Celular celular = new Celular();
		celular.setDdd(celularValido.getDdd());
		celular.setNumero(generateStringBySizeAndRegex(NUMERO_CELULAR - 1, APENAS_NUMERAL));
		celular.setTipoContatoCelular(celularValido.getTipoContatoCelular());
		return celular;
	}
	
	public Celular buildMaisQue2NumeraisDdd() {
		Celular celular = new Celular();
		celular.setDdd(generateStringBySizeAndRegex(DDD + 1, APENAS_NUMERAL));
		celular.setNumero(celularValido.getNumero());
		celular.setTipoContatoCelular(celularValido.getTipoContatoCelular());
		return celular;
	}
	
	public Celular buildMaisQue9NumeraisNumero() {
		Celular celular = new Celular();
		celular.setDdd(celularValido.getDdd());
		celular.setNumero(generateStringBySizeAndRegex(NUMERO_CELULAR + 1, APENAS_NUMERAL));
		celular.setTipoContatoCelular(celularValido.getTipoContatoCelular());
		return celular;
	}

	public Celular buildNuloTipoContatoCelular() {
		Celular celular = new Celular();
		celular.setDdd(celularValido.getDdd());
		celular.setNumero(celularValido.getNumero());
		celular.setTipoContatoCelular(null);
		return celular;
	}

}
