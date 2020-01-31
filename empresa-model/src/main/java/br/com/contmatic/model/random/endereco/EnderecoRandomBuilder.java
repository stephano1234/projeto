package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.endereco.TipoEndereco;
import br.com.contmatic.model.random.contato.TelefoneFixoRandomBuilder;

public class EnderecoRandomBuilder {

	private static final String ANY = ".";

	/** The Constant ELEMENTOS_ARRAY_GERADA. */
	private static final int ELEMENTOS_ARRAY_GERADA = 10;
        
    /** The Constant TAMANHO_REGULAR. */
	private static final int TAMANHO_REGULAR = 100;
    
    /** The Constant CEP. */
    private static final int CEP = 8;
    
    /** The Constant MAX_NUMERO_ENDERECO. */
    private static final int MAX_NUMERO_ENDERECO = 6;

    private static final String APENAS_NUMERAL = "[0-9]";
    
	private LogradouroRandomBuilder logradouroRandomBuilder = new LogradouroRandomBuilder();
	
	private TelefoneFixoRandomBuilder telefoneFixoRandomBuilder = new TelefoneFixoRandomBuilder();

	public Endereco build() {
		final Endereco endereco = new Endereco();
		endereco.setCep(generateStringBySizeAndRegex(CEP, APENAS_NUMERAL));
		endereco.setNumero(RandomUtils.nextInt(0, 2) == 0 ? generateStringBySizeAndRegex(nextInt(1, MAX_NUMERO_ENDERECO + 1), APENAS_NUMERAL) : null);
        endereco.setLogradouro(logradouroRandomBuilder.build());
		endereco.setComplemento(RandomUtils.nextInt(0, 2) == 0 ? generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR + 1), ANY) : null);
        Set<TelefoneFixo> telefonesFixo = new HashSet<>();        
		int quantidadeCollection = nextInt(0, ELEMENTOS_ARRAY_GERADA);
        for (int i = 0; i < quantidadeCollection; i++) {        
            telefonesFixo.add(telefoneFixoRandomBuilder.build());        
        }
        endereco.setTelefonesFixo(telefonesFixo);
        endereco.setTipoEndereco(TipoEndereco.values()[nextInt(0, TipoEndereco.values().length)]);
		return endereco;
	}
	
}
