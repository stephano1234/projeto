package br.com.contmatic.model.random.pessoa;

import static org.apache.commons.lang3.RandomUtils.nextInt;

import org.joda.time.LocalDate;

import br.com.contmatic.model.pessoa.ContratoTrabalho;

public class ContratoTrabalhoTestRandomBuilder {

	private static final PessoaTestRandomBuilder randomPessoa = PessoaTestRandomBuilder.getInstance();

	private final ContratoTrabalho contratoTrabalhoValido = new ContratoTrabalhoRandomBuilder().build();

	private static ContratoTrabalhoTestRandomBuilder instance;

	public static ContratoTrabalhoTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new ContratoTrabalhoTestRandomBuilder();
		}
		return instance;
	}

	public static void cleanBuilder() {
		PessoaTestRandomBuilder.cleanBuilder();
		instance = null;
	}

	public ContratoTrabalho buildValid() {
		return contratoTrabalhoValido;
	}

	public ContratoTrabalho buildNuloPessoa() {
		ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
		contratoTrabalho.setPessoa(null);
		contratoTrabalho.setDataInicioContrato(contratoTrabalhoValido.getDataInicioContrato());
		contratoTrabalho.setTipoContratoTrabalho(contratoTrabalhoValido.getTipoContratoTrabalho());
		return contratoTrabalho;
	}

	public ContratoTrabalho buildInvalidaPessoa() {
		ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
		contratoTrabalho.setPessoa(randomPessoa.buildNuloCpf());
		contratoTrabalho.setDataInicioContrato(contratoTrabalhoValido.getDataInicioContrato());
		contratoTrabalho.setTipoContratoTrabalho(contratoTrabalhoValido.getTipoContratoTrabalho());
		return contratoTrabalho;
	}

	public ContratoTrabalho buildNuloDataInicioContrato() {
		ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
		contratoTrabalho.setPessoa(contratoTrabalhoValido.getPessoa());
		contratoTrabalho.setDataInicioContrato(null);
		contratoTrabalho.setTipoContratoTrabalho(contratoTrabalhoValido.getTipoContratoTrabalho());
		return contratoTrabalho;
	}

	public ContratoTrabalho buildDataFuturaDataInicioContrato() {
		ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
		contratoTrabalho.setPessoa(contratoTrabalhoValido.getPessoa());
		contratoTrabalho.setDataInicioContrato(LocalDate.now().plusDays(nextInt(1, 100)));
		contratoTrabalho.setTipoContratoTrabalho(contratoTrabalhoValido.getTipoContratoTrabalho());
		return contratoTrabalho;
	}

	public ContratoTrabalho buildNuloTipoContratoTrabalho() {
		ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
		contratoTrabalho.setPessoa(contratoTrabalhoValido.getPessoa());
		contratoTrabalho.setDataInicioContrato(contratoTrabalhoValido.getDataInicioContrato());
		contratoTrabalho.setTipoContratoTrabalho(null);
		return contratoTrabalho;
	}

}
