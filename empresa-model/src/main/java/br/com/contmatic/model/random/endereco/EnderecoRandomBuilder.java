package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import org.apache.commons.lang3.RandomUtils;

import br.com.contmatic.model.endereco.Endereco;

public class EnderecoRandomBuilder {

	private static final String VALIDO_COMPLEMENTO = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-\\.)('ªº°,:\\/\\\\]";

    /** The Constant TAMANHO_REGULAR. */
	private static final int TAMANHO_REGULAR = 100;
    
    /** The Constant CEP. */
    private static final int CEP = 8;
    
    /** The Constant MAX_NUMERO_ENDERECO. */
    private static final int MAX_NUMERO_ENDERECO = 6;

    private static final String APENAS_NUMERAL = "[0-9]";
    
	private LogradouroRandomBuilder logradouroRandomBuilder = new LogradouroRandomBuilder();
	
	public Endereco build() {
		final Endereco endereco = new Endereco();
		endereco.setCep(generateStringBySizeAndRegex(CEP, APENAS_NUMERAL));
		endereco.setNumero(RandomUtils.nextInt(0, 2) == 0 ? generateStringBySizeAndRegex(nextInt(1, MAX_NUMERO_ENDERECO + 1), APENAS_NUMERAL) : null);
        endereco.setLogradouro(logradouroRandomBuilder.build());
		endereco.setComplemento(RandomUtils.nextInt(0, 2) == 0 ? generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR + 1), VALIDO_COMPLEMENTO) : null);
		return endereco;
	}
	
}
