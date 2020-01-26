package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Logradouro;

public class LogradouroRandomBuilder {
	
	private BairroRandomBuilder bairroRandomBuilder = new BairroRandomBuilder();

    public static final int TAMANHO_REGULAR = 100;
    
    public static final String LETRAS_MAIUSCULAS = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ]";
    
    public static final String ESPACO = " ";
	
	public Logradouro buildValido() {
		final Logradouro logradouro = new Logradouro();
		logradouro.setNome(generateStringBySizeAndRegexWithSeparator(nextInt(1, TAMANHO_REGULAR + 1), LETRAS_MAIUSCULAS, ESPACO));
		logradouro.setBairro(bairroRandomBuilder.buildValido());
		return logradouro;
	}
	
}
