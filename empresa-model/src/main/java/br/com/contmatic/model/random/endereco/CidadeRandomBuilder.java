package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Cidade;
import br.com.contmatic.model.endereco.TipoUf;

public class CidadeRandomBuilder {

    public static final int TAMANHO_REGULAR = 100;
    
    public static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";
    
    public static final String ESPACO = " ";
	
	public Cidade build() {
		final Cidade cidade = new Cidade();
		cidade.setNome(generateStringBySizeAndRegexWithSeparator(nextInt(1, TAMANHO_REGULAR + 1), VALIDO_NOME, ESPACO));
		cidade.setTipoUf(TipoUf.values()[nextInt(0, TipoUf.values().length)]);
		return cidade;
	}
	
}
