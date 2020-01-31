package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Bairro;

public class BairroTestRandomBuilder {

	private static final String ESPACO = " ";

	public static final String LETRAS_MAIUSCULAS = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ]";

	public static final String CARACTERES_INVALIDOS_NOME = "[^A-ZÁÉÍÓÚÃÕÀÂÊÔÇ ]";

	private static final int TAMANHO_REGULAR = 100;

	private static final CidadeTestRandomBuilder randomCidade = CidadeTestRandomBuilder.getInstance();
	
	private final Bairro bairroValido = new BairroRandomBuilder().build();

	private static BairroTestRandomBuilder instance;

	public static BairroTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new BairroTestRandomBuilder();
		}
		return instance;
	}

	public void cleanBuilder() {
		randomCidade.cleanBuilder();
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
		bairro.setNome(generateStringBySizeAndRegexWithSeparator(TAMANHO_REGULAR + 1, LETRAS_MAIUSCULAS, ESPACO));
		bairro.setCidade(bairroValido.getCidade());
		return bairro;
	}

	public Bairro buildNaoApenasLetraEspacoNome() {
		Bairro bairro = new Bairro();
		bairro.setNome(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, TAMANHO_REGULAR + 1),
				CARACTERES_INVALIDOS_NOME, LETRAS_MAIUSCULAS));
		bairro.setCidade(bairroValido.getCidade());
		return bairro;
	}

	public Bairro buildEspacoSeguidoDeEspacoNome() {
		Bairro bairro = new Bairro();
		bairro.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), LETRAS_MAIUSCULAS) + "  "
				+ generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), LETRAS_MAIUSCULAS));
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
		bairro.setCidade(randomCidade.buildNaoApenasLetraEspacoNome());
		return bairro;
	}

}
