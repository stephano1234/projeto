package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Endereco;

public class EnderecoTestRandomBuilder {

	private static final String VALIDO_COMPLEMENTO = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-\\.)('ªº°,:\\/\\\\]";

	private static final String INVALIDO_COMPLEMENTO = "[^A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-\\.)('ªº°,:\\/\\\\ ]";
	
	private static final String SEM_NUMERAL = "[^0-9]";

	private static final int CEP = 8;
	
	private static final int MAX_NUMERO_ENDERECO = 6;
	
	private static final int TAMANHO_REGULAR = 100;
	
	private static final String APENAS_NUMERAL = "[0-9]";
	
	private final Endereco enderecoValido = new EnderecoRandomBuilder().build();

	private static EnderecoTestRandomBuilder instance;

	private EnderecoTestRandomBuilder() {
	}
	
	public static EnderecoTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new EnderecoTestRandomBuilder();
		}
		return instance;
	}

	public static void cleanBuilder() {
		LogradouroTestRandomBuilder.cleanBuilder();
		instance = null;
	}
	
	public Endereco buildValid() {
		return enderecoValido;
	}
	
	public Endereco buildNuloCep() {
		Endereco endereco = new Endereco();
		endereco.setCep(null);
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}

	public Endereco buildMaiorTamanhoCep() {
		Endereco endereco = new Endereco();
		endereco.setCep(generateStringBySizeAndRegex(CEP + 1, APENAS_NUMERAL));
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}
	
	public Endereco buildMenorTamanhoCep() {
		Endereco endereco = new Endereco();
		endereco.setCep(generateStringBySizeAndRegex(CEP - 1, APENAS_NUMERAL));
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}

	public Endereco buildNaoApenasNumeralCep() {
		Endereco endereco = new Endereco();
		endereco.setCep(generateStringBySizeAndRegexWithOneCharByRegex(CEP, SEM_NUMERAL, APENAS_NUMERAL));
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}

	public Endereco buildNuloNumero() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(null);
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}
	
	public Endereco buildVazioNumero() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero("");
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}
	
	public Endereco buildMaiorTamanhoNumero() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(generateStringBySizeAndRegex(MAX_NUMERO_ENDERECO + 1, APENAS_NUMERAL));
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}

	public Endereco buildNaoApenasNumeralNumero() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, MAX_NUMERO_ENDERECO + 1), SEM_NUMERAL, APENAS_NUMERAL));
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}

	public Endereco buildVazioComplemento() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento("");
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}
	
	public Endereco buildMaiorTamanhoComplemento() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(generateStringBySizeAndRegex(TAMANHO_REGULAR + 1, VALIDO_COMPLEMENTO));
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}

	public Endereco buildCaractereInvalidoComplemento() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR + 1), INVALIDO_COMPLEMENTO));
		endereco.setLogradouro(enderecoValido.getLogradouro());
		return endereco;
	}

	public Endereco buildNuloLogradouro() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(null);
		return endereco;
	}

	public Endereco buildLogradouroInvalido() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(LogradouroTestRandomBuilder.getInstance().buildNaoApenasLetraEspacoNome());
		return endereco;
	}

	public Endereco buildBairroInvalido() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(LogradouroTestRandomBuilder.getInstance().buildBairroInvalido());
		return endereco;
	}

	public Endereco buildCidadeInvalido() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(LogradouroTestRandomBuilder.getInstance().buildCidadeInvalido());
		return endereco;
	}

}
