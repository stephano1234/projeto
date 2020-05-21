package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Logradouro;

public class LogradouroTestRandomBuilder {

	private static final String ESPACO = " ";

	public static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";

	public static final String INVALIDO_NOME = "[^A-ZÁÉÍÓÚÃÕÀÂÊÔÇ &\\-ªº\\.']";

	private static final int TAMANHO_REGULAR = 100;

	private final Logradouro logradouroValido = new LogradouroRandomBuilder().build();

	private static LogradouroTestRandomBuilder instance;

	private LogradouroTestRandomBuilder() {
	}
	
	public static LogradouroTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new LogradouroTestRandomBuilder();
		}
		return instance;
	}

	public static void cleanBuilder() {
		BairroTestRandomBuilder.cleanBuilder();
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
		logradouro.setNome(generateStringBySizeAndRegexWithSeparator(TAMANHO_REGULAR + 1, VALIDO_NOME, ESPACO));
		logradouro.setBairro(logradouroValido.getBairro());
		return logradouro;
	}

	public Logradouro buildNaoApenasLetraEspacoNome() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, TAMANHO_REGULAR + 1),
				INVALIDO_NOME, VALIDO_NOME));
		logradouro.setBairro(logradouroValido.getBairro());
		return logradouro;
	}

	public Logradouro buildEspacoSeguidoDeEspacoNome() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME) + "  "
				+ generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME));
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
		logradouro.setBairro(BairroTestRandomBuilder.getInstance().buildNaoApenasLetraEspacoNome());
		return logradouro;
	}

	public Logradouro buildCidadeInvalido() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(logradouroValido.getNome());
		logradouro.setBairro(BairroTestRandomBuilder.getInstance().buildCidadeInvalido());
		return logradouro;
	}

}
