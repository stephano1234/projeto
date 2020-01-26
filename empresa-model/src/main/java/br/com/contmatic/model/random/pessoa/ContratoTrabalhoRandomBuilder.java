package br.com.contmatic.model.random.pessoa;

import static org.apache.commons.lang3.RandomUtils.nextInt;

import org.joda.time.LocalDate;

import br.com.contmatic.model.pessoa.ContratoTrabalho;
import br.com.contmatic.model.pessoa.TipoContratoTrabalho;

public class ContratoTrabalhoRandomBuilder {
	
	private PessoaRandomBuilder pessoaRandomBuilder = new PessoaRandomBuilder();

	public ContratoTrabalho buildValido() {
		final ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
		contratoTrabalho.setPessoa(pessoaRandomBuilder.buildValido());
		contratoTrabalho.setDataInicioContrato(LocalDate.now().minusDays(nextInt(1, 1000)));
		contratoTrabalho.setTipoContratoTrabalho(TipoContratoTrabalho.values()[nextInt(0, TipoContratoTrabalho.values().length)]);
		return contratoTrabalho;
	}
	
}
