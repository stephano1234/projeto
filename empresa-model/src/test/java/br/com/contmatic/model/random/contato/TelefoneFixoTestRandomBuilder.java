package br.com.contmatic.model.random.contato;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;

import br.com.contmatic.model.contato.TelefoneFixo;

public class TelefoneFixoTestRandomBuilder {

	public static final int NUMERO_TELEFONE_FIXO = 8;
	
	public static final String APENAS_NUMERAL = "[0-9]";

	private static final String SEM_NUMERAL = "[^0-9]";

	private final TelefoneFixo telefoneFixoValido = new TelefoneFixoRandomBuilder().build();
	
	private static TelefoneFixoTestRandomBuilder instance;
	
	private TelefoneFixoTestRandomBuilder() {
	}
	
	public static TelefoneFixoTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new TelefoneFixoTestRandomBuilder();
		}
		return instance;
	}
	
	public static void cleanBuilder() {
		instance = null;
	}

	public TelefoneFixo buildValid() {
		return telefoneFixoValido;
	}

	public TelefoneFixo buildNuloNumero() {
		TelefoneFixo telefoneFixo = new TelefoneFixo();
		telefoneFixo.setNumero(null);
		return telefoneFixo;
	}
	
	public TelefoneFixo buildVazioNumero() {
		TelefoneFixo telefoneFixo = new TelefoneFixo();
		telefoneFixo.setNumero("");
		return telefoneFixo;
	}
	
	public TelefoneFixo buildNaoApenasNumeralNumero() {
		TelefoneFixo telefoneFixo = new TelefoneFixo();
		telefoneFixo.setNumero(generateStringBySizeAndRegexWithOneCharByRegex(NUMERO_TELEFONE_FIXO, SEM_NUMERAL, APENAS_NUMERAL));
		return telefoneFixo;
	}
	
	public TelefoneFixo buildMenosQue8NumeraisNumero() {
		TelefoneFixo telefoneFixo = new TelefoneFixo();
		telefoneFixo.setNumero(generateStringBySizeAndRegex(NUMERO_TELEFONE_FIXO - 1, APENAS_NUMERAL));
		return telefoneFixo;
	}
	
	public TelefoneFixo buildMaisQue8NumeraisNumero() {
		TelefoneFixo telefoneFixo = new TelefoneFixo();
		telefoneFixo.setNumero(generateStringBySizeAndRegex(NUMERO_TELEFONE_FIXO + 1, APENAS_NUMERAL));
		return telefoneFixo;
	}

}
