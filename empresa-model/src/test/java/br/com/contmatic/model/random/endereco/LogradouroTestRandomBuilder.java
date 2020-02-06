package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Logradouro;

public class LogradouroTestRandomBuilder {

	private static final String ESPACO = " ";

	public static final String LETRAS_MAIUSCULAS = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ]";

	public static final String INVALIDO_NOME = "[^A-ZÁÉÍÓÚÃÕÀÂÊÔÇ &\\-ªº\\.']";

	private static final int TAMANHO_REGULAR = 100;

	private static final BairroTestRandomBuilder randomBairro = BairroTestRandomBuilder.getInstance();
	
	private final Logradouro logradouroValido = new LogradouroRandomBuilder().build();

	private static LogradouroTestRandomBuilder instance;

	public static LogradouroTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new LogradouroTestRandomBuilder();
		}
		return instance;
	}

	public void cleanBuilder() {
		randomBairro.cleanBuilder();
		instance = null;
	}

	public Logradouro buildValid() {
		return logradouroValido;
	}

	public Logradouro buildNuloNome() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(null);
		logradouro.setBairro(logradouroValido.getBairro());
		return logradouro;
	}

	public Logradouro buildVazioNome() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome("");
		logradouro.setBairro(logradouroValido.getBairro());
		return logradouro;
	}

	public Logradouro buildApenasEspacoNome() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR + 1), ESPACO));
		logradouro.setBairro(logradouroValido.getBairro());
		return logradouro;
	}

	public Logradouro buildInicioEspacoNome() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(ESPACO + logradouroValido.getNome());
		logradouro.setBairro(logradouroValido.getBairro());
		return logradouro;
	}

	public Logradouro buildFimEspacoNome() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(logradouroValido.getNome() + ESPACO);
		logradouro.setBairro(logradouroValido.getBairro());
		return logradouro;
	}

	public Logradouro buildMaiorTamanhoNome() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(generateStringBySizeAndRegexWithSeparator(TAMANHO_REGULAR + 1, LETRAS_MAIUSCULAS, ESPACO));
		logradouro.setBairro(logradouroValido.getBairro());
		return logradouro;
	}

	public Logradouro buildNaoApenasLetraEspacoNome() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, TAMANHO_REGULAR + 1),
				INVALIDO_NOME, LETRAS_MAIUSCULAS));
		logradouro.setBairro(logradouroValido.getBairro());
		return logradouro;
	}

	public Logradouro buildEspacoSeguidoDeEspacoNome() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), LETRAS_MAIUSCULAS) + "  "
				+ generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), LETRAS_MAIUSCULAS));
		logradouro.setBairro(logradouroValido.getBairro());
		return logradouro;
	}

	public Logradouro buildNuloBairro() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(logradouroValido.getNome());
		logradouro.setBairro(null);
		return logradouro;
	}
	
	public Logradouro buildBairroInvalido() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(logradouroValido.getNome());
		logradouro.setBairro(randomBairro.buildNaoApenasLetraEspacoNome());
		return logradouro;
	}

}
