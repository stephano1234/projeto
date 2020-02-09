package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Cidade;

public class CidadeTestRandomBuilder {

	private static final String ESPACO = " ";

	public static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";

	public static final String INVALIDO_NOME = "[^A-ZÁÉÍÓÚÃÕÀÂÊÔÇ &\\-ªº\\.']";

	private static final int TAMANHO_REGULAR = 100;

	private final Cidade cidadeValida = new CidadeRandomBuilder().build();

	private static CidadeTestRandomBuilder instance;

	private CidadeTestRandomBuilder() {
	}
	
	public static CidadeTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new CidadeTestRandomBuilder();
		}
		return instance;
	}

	public static void cleanBuilder() {
		instance = null;
	}

	public Cidade buildValid() {
		return cidadeValida;
	}

	public Cidade buildNuloNome() {
		Cidade cidade = new Cidade();
		cidade.setNome(null);
		cidade.setTipoUf(cidadeValida.getTipoUf());
		return cidade;
	}

	public Cidade buildVazioNome() {
		Cidade cidade = new Cidade();
		cidade.setNome("");
		cidade.setTipoUf(cidadeValida.getTipoUf());
		return cidade;
	}

	public Cidade buildApenasEspacoNome() {
		Cidade cidade = new Cidade();
		cidade.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR + 1), ESPACO));
		cidade.setTipoUf(cidadeValida.getTipoUf());
		return cidade;
	}

	public Cidade buildInicioEspacoNome() {
		Cidade cidade = new Cidade();
		cidade.setNome(ESPACO + cidadeValida.getNome());
		cidade.setTipoUf(cidadeValida.getTipoUf());
		return cidade;
	}

	public Cidade buildFimEspacoNome() {
		Cidade cidade = new Cidade();
		cidade.setNome(cidadeValida.getNome() + ESPACO);
		cidade.setTipoUf(cidadeValida.getTipoUf());
		return cidade;
	}

	public Cidade buildMaiorTamanhoNome() {
		Cidade cidade = new Cidade();
		cidade.setNome(generateStringBySizeAndRegexWithSeparator(TAMANHO_REGULAR + 1, VALIDO_NOME, ESPACO));
		cidade.setTipoUf(cidadeValida.getTipoUf());
		return cidade;
	}

	public Cidade buildNaoApenasLetraEspacoNome() {
		Cidade cidade = new Cidade();
		cidade.setNome(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, TAMANHO_REGULAR + 1),
				INVALIDO_NOME, VALIDO_NOME));
		cidade.setTipoUf(cidadeValida.getTipoUf());
		return cidade;
	}

	public Cidade buildEspacoSeguidoDeEspacoNome() {
		Cidade cidade = new Cidade();
		cidade.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME) + "  "
				+ generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME));
		cidade.setTipoUf(cidadeValida.getTipoUf());
		return cidade;
	}

	public Cidade buildNuloTipoUf() {
		Cidade cidade = new Cidade();
		cidade.setNome(cidadeValida.getNome());
		cidade.setTipoUf(null);
		return cidade;
	}

}
