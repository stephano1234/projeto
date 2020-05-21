package br.com.contmatic.model.random.contato;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;

import br.com.contmatic.model.contato.Celular;

public class CelularTestRandomBuilder {

	public static final int NUMERO_CELULAR = 9;
	
	public static final String APENAS_NUMERAL = "[0-9]";

	private static final String SEM_NUMERAL = "[^0-9]";

	private final Celular celularValido = new CelularRandomBuilder().build();
	
	private static CelularTestRandomBuilder instance;
	
	private CelularTestRandomBuilder() {
	}
	
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

	public Celular buildNuloNumero() {
		Celular celular = new Celular();
		celular.setNumero(null);
		return celular;
	}
	
	public Celular buildNaoApenasNumeralNumero() {
		Celular celular = new Celular();
		celular.setNumero(generateStringBySizeAndRegexWithOneCharByRegex(NUMERO_CELULAR, SEM_NUMERAL, APENAS_NUMERAL));
		return celular;
	}
	
	public Celular buildMenosQue9NumeraisNumero() {
		Celular celular = new Celular();
		celular.setNumero(celularValido.getNumero().substring(0, 8));
		return celular;
	}
	
	public Celular buildMaisQue9NumeraisNumero() {
		Celular celular = new Celular();
		celular.setNumero(generateStringBySizeAndRegex(NUMERO_CELULAR + 1, APENAS_NUMERAL));
		return celular;
	}

}
