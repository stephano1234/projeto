package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Bairro;

public class BairroRandomBuilder {
	
	private CidadeRandomBuilder cidadeRandomBuilder = new CidadeRandomBuilder();
	
    public static final int TAMANHO_REGULAR = 100;
    
    public static final String LETRAS_MAIUSCULAS = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ]";
    
    public static final String ESPACO = " ";

	public Bairro buildValido() {
		final Bairro bairro = new Bairro();
		bairro.setNome(generateStringBySizeAndRegexWithSeparator(nextInt(1, TAMANHO_REGULAR + 1), LETRAS_MAIUSCULAS, ESPACO));
		bairro.setCidade(cidadeRandomBuilder.build());
		return bairro;
	}
	
}
