package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.HashSet;
import java.util.Set;

import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.random.contato.TelefoneFixoTestRandomBuilder;

public class EnderecoTestRandomBuilder {

	private static final String VALIDO_COMPLEMENTO = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-\\.)('ªº°,:\\/\\\\]";

	private static final String INVALIDO_COMPLEMENTO = "[^A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-\\.)('ªº°,:\\/\\\\ ]";
	
	private static final String SEM_NUMERAL = "[^0-9]";

	private static final int CEP = 8;
	
	private static final int MAX_NUMERO_ENDERECO = 6;
	
	private static final int TAMANHO_REGULAR = 100;
	
	private static final String APENAS_NUMERAL = "[0-9]";
	
	private static final LogradouroTestRandomBuilder randomLogradouro = LogradouroTestRandomBuilder.getInstance();
	
	private static final TelefoneFixoTestRandomBuilder randomTelefoneFixo = TelefoneFixoTestRandomBuilder.getInstance();
	
	private final Endereco enderecoValido = new EnderecoRandomBuilder().build();

	private static EnderecoTestRandomBuilder instance;

	public static EnderecoTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new EnderecoTestRandomBuilder();
		}
		return instance;
	}

	public static void cleanBuilder() {
		LogradouroTestRandomBuilder.cleanBuilder();
		TelefoneFixoTestRandomBuilder.cleanBuilder();
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
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildMaiorTamanhoCep() {
		Endereco endereco = new Endereco();
		endereco.setCep(generateStringBySizeAndRegex(CEP + 1, APENAS_NUMERAL));
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}
	
	public Endereco buildMenorTamanhoCep() {
		Endereco endereco = new Endereco();
		endereco.setCep(generateStringBySizeAndRegex(CEP - 1, APENAS_NUMERAL));
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildNaoApenasNumeralCep() {
		Endereco endereco = new Endereco();
		endereco.setCep(generateStringBySizeAndRegexWithOneCharByRegex(CEP, SEM_NUMERAL, APENAS_NUMERAL));
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildNuloNumero() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(null);
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}
	
	public Endereco buildVazioNumero() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero("");
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}
	
	public Endereco buildMaiorTamanhoNumero() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(generateStringBySizeAndRegex(MAX_NUMERO_ENDERECO + 1, APENAS_NUMERAL));
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildNaoApenasNumeralNumero() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, MAX_NUMERO_ENDERECO + 1), SEM_NUMERAL, APENAS_NUMERAL));
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildVazioComplemento() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento("");
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}
	
	public Endereco buildMaiorTamanhoComplemento() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(generateStringBySizeAndRegex(TAMANHO_REGULAR + 1, VALIDO_COMPLEMENTO));
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildCaractereInvalidoComplemento() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR + 1), INVALIDO_COMPLEMENTO));
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildNuloLogradouro() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(null);
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildLogradouroInvalido() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(randomLogradouro.buildNaoApenasLetraEspacoNome());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildNuloTelefonesFixo() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(null);
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildTelefonesFixoComElementoNulo() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		Set<TelefoneFixo> telefonesFixoComElementoNulo = new HashSet<>();
		telefonesFixoComElementoNulo.addAll(enderecoValido.getTelefonesFixo());
		telefonesFixoComElementoNulo.add(null);
		endereco.setTelefonesFixo(telefonesFixoComElementoNulo);
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildTelefonesFixoComElementoInvalido() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		Set<TelefoneFixo> telefonesFixoComElementoInvalido = new HashSet<>();
		telefonesFixoComElementoInvalido.addAll(enderecoValido.getTelefonesFixo());
		telefonesFixoComElementoInvalido.add(randomTelefoneFixo.buildNaoApenasNumeralNumero());
		endereco.setTelefonesFixo(telefonesFixoComElementoInvalido);
		endereco.setTipoEndereco(enderecoValido.getTipoEndereco());
		return endereco;
	}

	public Endereco buildNuloTipoEndereco() {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoValido.getCep());
		endereco.setNumero(enderecoValido.getNumero());
		endereco.setComplemento(enderecoValido.getComplemento());
		endereco.setLogradouro(enderecoValido.getLogradouro());
		endereco.setTelefonesFixo(enderecoValido.getTelefonesFixo());
		endereco.setTipoEndereco(null);
		return endereco;
	}

}
