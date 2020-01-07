package br.com.contmatic.templates.pessoa;

import static org.apache.commons.lang3.RandomUtils.nextInt;

import org.joda.time.LocalDate;

import br.com.contmatic.model.pessoa.ContratoTrabalho;
import br.com.contmatic.model.pessoa.TipoContratoTrabalho;

public class ContratoTrabalhoRandomBuilder {

	public static ContratoTrabalho buildValido() {
		ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
		contratoTrabalho.setPessoa(PessoaRandomBuilder.buildValido());
		contratoTrabalho.setDataInicioContrato(LocalDate.now().minusYears(nextInt(1, 30)));
		contratoTrabalho.setTipoContratoTrabalho(TipoContratoTrabalho.values()[nextInt(0, TipoContratoTrabalho.values().length)]);
		return contratoTrabalho;
	}
	
	public static ContratoTrabalho buildDataFuturaDataInicioContrato() {
		ContratoTrabalho contratoTrabalho = buildValido();
		contratoTrabalho.setDataInicioContrato(LocalDate.now().plusDays(nextInt(1, 100)));
		return contratoTrabalho;
	}
	
}
