package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Bairro;

public class BairroTestRandomBuilder {

	private static final String ESPACO = " ";

	public static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";

	public static final String INVALIDO_NOME = "[^A-ZÁÉÍÓÚÃÕÀÂÊÔÇ &\\-ªº\\.']";

	private static final int TAMANHO_REGULAR = 100;

	private final Bairro bairroValido = new BairroRandomBuilder().build();

	private static BairroTestRandomBuilder instance;

	private BairroTestRandomBuilder() {
	}
	
	public static BairroTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new BairroTestRandomBuilder();
		}
		return instance;
	}

	public static void cleanBuilder() {
		CidadeTestRandomBuilder.cleanBuilder();
		instance = null;
	}

	public Bairro buildValid() {
		return bairroValido;
	}

	public Bairro buildNuloNome() {
		Bairro bairro = new Bairro();
		bairro.setNome(null);
		bairro.setCidade(bairroValido.getCidade());
		return bairro;
	}

	public Bairro buildVazioNome() {
		Bairro bairro = new Bairro();
		bairro.setNome("");
		bairro.setCidade(bairroValido.getCidade());
		return bairro;
	}

	public Bairro buildApenasEspacoNome() {
		Bairro bairro = new Bairro();
		bairro.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR + 1), ESPACO));
		bairro.setCidade(bairroValido.getCidade());
		return bairro;
	}

	public Bairro buildInicioEspacoNome() {
		Bairro bairro = new Bairro();
		bairro.setNome(ESPACO + bairroValido.getNome());
		bairro.setCidade(bairroValido.getCidade());
		return bairro;
	}

	public Bairro buildFimEspacoNome() {
		Bairro bairro = new Bairro();
		bairro.setNome(bairroValido.getNome() + ESPACO);
		bairro.setCidade(bairroValido.getCidade());
		return bairro;
	}

	public Bairro buildMaiorTamanhoNome() {
		Bairro bairro = new Bairro();
		bairro.setNome(generateStringBySizeAndRegexWithSeparator(TAMANHO_REGULAR + 1, VALIDO_NOME, ESPACO));
		bairro.setCidade(bairroValido.getCidade());
		return bairro;
	}

	public Bairro buildNaoApenasLetraEspacoNome() {
		Bairro bairro = new Bairro();
		bairro.setNome(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, TAMANHO_REGULAR + 1),
				INVALIDO_NOME, VALIDO_NOME));
		bairro.setCidade(bairroValido.getCidade());
		return bairro;
	}

	public Bairro buildEspacoSeguidoDeEspacoNome() {
		Bairro bairro = new Bairro();
		bairro.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME) + "  "
				+ generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME));
		bairro.setCidade(bairroValido.getCidade());
		return bairro;
	}

	public Bairro buildNuloCidade() {
		Bairro bairro = new Bairro();
		bairro.setNome(bairroValido.getNome());
		bairro.setCidade(null);
		return bairro;
	}
	
	public Bairro buildCidadeInvalido() {
		Bairro bairro = new Bairro();
		bairro.setNome(bairroValido.getNome());
		bairro.setCidade(CidadeTestRandomBuilder.getInstance().buildNaoApenasLetraEspacoNome());
		return bairro;
	}

}
