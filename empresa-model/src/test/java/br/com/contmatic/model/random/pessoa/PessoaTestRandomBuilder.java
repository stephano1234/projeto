package br.com.contmatic.model.random.pessoa;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.cpfInvalido;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import org.joda.time.LocalDate;

import br.com.contmatic.model.pessoa.Pessoa;

public class PessoaTestRandomBuilder {

	private static final int TAMANHO_REGULAR = 100;

	private static final int CPF = 11;

	private static final String APENAS_NUMERAL = "[0-9]";

	private static final String SEM_NUMERAL = "[^0-9]";

	private static final String ESPACO = " ";

	public static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";

	public static final String INVALIDO_NOME = "[^A-ZÁÉÍÓÚÃÕÀÂÊÔÇ &\\-ªº\\.']";

	private final Pessoa pessoaValida = new PessoaRandomBuilder().build();

	private PessoaTestRandomBuilder() {
	}
	
	private static PessoaTestRandomBuilder instance;

	public static PessoaTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new PessoaTestRandomBuilder();
		}
		return instance;
	}

	public static void cleanBuilder() {
		instance = null;
	}

	public Pessoa buildValid() {
		return pessoaValida;
	}

	public Pessoa buildNuloCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(null);
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildMaiorTamanhoCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(generateStringBySizeAndRegex(CPF + 1, APENAS_NUMERAL));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildMenorTamanhoCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(generateStringBySizeAndRegex(CPF - 1, APENAS_NUMERAL));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildNaoApenasNumeralCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(generateStringBySizeAndRegexWithOneCharByRegex(CPF, SEM_NUMERAL, APENAS_NUMERAL));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildNumerosRepetidosCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(generateStringBySizeAndRegex(CPF, Integer.toString(nextInt(0, 10))));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildPrimeiroDigitoVerificadorInvalidoCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpfInvalido(9));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildSegundoDigitoVerificadorInvalidoCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpfInvalido(10));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildNuloNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(null);
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildVazioNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome("");
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildApenasEspacoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR + 1), ESPACO));
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildInicioEspacoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(ESPACO + pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildFimEspacoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome() + ESPACO);
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildMaiorTamanhoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(generateStringBySizeAndRegexWithSeparator(TAMANHO_REGULAR + 1, VALIDO_NOME, ESPACO));
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildNaoApenasLetraEspacoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, TAMANHO_REGULAR + 1),
				INVALIDO_NOME, VALIDO_NOME));
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildEspacoSeguidoDeEspacoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME) + "  "
				+ generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME));
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildNuloDataNascimento() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(null);
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildDataFuturaDataNascimento() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(LocalDate.now().plusDays(nextInt(1, 100)));
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildNuloTipoGrauInstrucao() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(null);
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildNuloTipoEstadoCivil() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(null);
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		return pessoa;
	}

	public Pessoa buildNuloTipoSexo() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(null);
		return pessoa;
	}

}
