package br.com.contmatic.model.random.pessoa;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.cpfValido;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import org.joda.time.LocalDate;

import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.pessoa.TipoEstadoCivil;
import br.com.contmatic.model.pessoa.TipoGrauInstrucao;
import br.com.contmatic.model.pessoa.TipoSexo;

public class PessoaRandomBuilder {
	
    private static final int TAMANHO_REGULAR = 100;
    
    private static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";
    
    private static final String ESPACO = " ";
    
	public Pessoa build() {
		final Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpfValido());
		pessoa.setNome(generateStringBySizeAndRegexWithSeparator(nextInt(1, TAMANHO_REGULAR + 1), VALIDO_NOME, ESPACO));
		pessoa.setDataNascimento(LocalDate.now().minusYears(nextInt(1, 30)));
		pessoa.setTipoGrauInstrucao(TipoGrauInstrucao.values()[nextInt(0, TipoGrauInstrucao.values().length)]);
		pessoa.setTipoEstadoCivil(TipoEstadoCivil.values()[nextInt(0, TipoEstadoCivil.values().length)]);
		pessoa.setTipoSexo(TipoSexo.values()[nextInt(0, TipoSexo.values().length)]);
		return pessoa;
	}
	
}
