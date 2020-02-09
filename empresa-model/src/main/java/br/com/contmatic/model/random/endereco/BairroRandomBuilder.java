package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Bairro;

public class BairroRandomBuilder {
	
	private CidadeRandomBuilder cidadeRandomBuilder = new CidadeRandomBuilder();
	
    public static final int TAMANHO_REGULAR = 100;
    
    public static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";
    
    public static final String ESPACO = " ";

	public Bairro build() {
		final Bairro bairro = new Bairro();
		bairro.setNome(generateStringBySizeAndRegexWithSeparator(nextInt(1, TAMANHO_REGULAR + 1), VALIDO_NOME, ESPACO));
		bairro.setCidade(cidadeRandomBuilder.build());
		return bairro;
	}
	
}
