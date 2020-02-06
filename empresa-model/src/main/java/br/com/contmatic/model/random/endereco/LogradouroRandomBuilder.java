package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Logradouro;

public class LogradouroRandomBuilder {
	
	private BairroRandomBuilder bairroRandomBuilder = new BairroRandomBuilder();

    public static final int TAMANHO_REGULAR = 100;
    
    public static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";
    
    public static final String ESPACO = " ";
	
	public Logradouro build() {
		final Logradouro logradouro = new Logradouro();
		logradouro.setNome(generateStringBySizeAndRegexWithSeparator(nextInt(1, TAMANHO_REGULAR + 1), VALIDO_NOME, ESPACO));
		logradouro.setBairro(bairroRandomBuilder.build());
		return logradouro;
	}
	
}
