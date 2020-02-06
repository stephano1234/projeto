package br.com.contmatic.model.random.pessoa;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.cpfInvalido;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.random.conta.ContaTestRandomBuilder;
import br.com.contmatic.model.random.contato.CelularTestRandomBuilder;
import br.com.contmatic.model.random.contato.EmailTestRandomBuilder;
import br.com.contmatic.model.random.contato.TelefoneFixoTestRandomBuilder;
import br.com.contmatic.model.random.endereco.EnderecoTestRandomBuilder;

public class PessoaTestRandomBuilder {

	private static final int TAMANHO_REGULAR = 100;

	private static final int CPF = 11;

	private static final String APENAS_NUMERAL = "[0-9]";

	private static final String SEM_NUMERAL = "[^0-9]";

	private static final String ESPACO = " ";

	public static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";

	public static final String INVALIDO_NOME = "[^A-ZÁÉÍÓÚÃÕÀÂÊÔÇ &\\-ªº\\.']";

	private static final TelefoneFixoTestRandomBuilder randomTelefoneFixo = TelefoneFixoTestRandomBuilder.getInstance();

	private static final EnderecoTestRandomBuilder randomEndereco = EnderecoTestRandomBuilder.getInstance();

	private static final ContaTestRandomBuilder randomConta = ContaTestRandomBuilder.getInstance();

	private static final CelularTestRandomBuilder randomCelular = CelularTestRandomBuilder.getInstance();

	private static final EmailTestRandomBuilder randomEmail = EmailTestRandomBuilder.getInstance();

	private final Pessoa pessoaValida = new PessoaRandomBuilder().build();

	private static PessoaTestRandomBuilder instance;

	public static PessoaTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new PessoaTestRandomBuilder();
		}
		return instance;
	}

	public static void cleanBuilder() {
		EmailTestRandomBuilder.cleanBuilder();
		CelularTestRandomBuilder.cleanBuilder();
		ContaTestRandomBuilder.cleanBuilder();
		EnderecoTestRandomBuilder.cleanBuilder();
		TelefoneFixoTestRandomBuilder.cleanBuilder();
		instance = null;
	}

	public Pessoa buildValid() {
		return pessoaValida;
	}

	public Pessoa buildNuloCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(null);
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildMaiorTamanhoCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(generateStringBySizeAndRegex(CPF + 1, APENAS_NUMERAL));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildMenorTamanhoCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(generateStringBySizeAndRegex(CPF - 1, APENAS_NUMERAL));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNaoApenasNumeralCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(generateStringBySizeAndRegexWithOneCharByRegex(CPF, SEM_NUMERAL, APENAS_NUMERAL));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNumerosRepetidosCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(generateStringBySizeAndRegex(CPF, Integer.toString(nextInt(0, 10))));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildPrimeiroDigitoVerificadorInvalidoCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpfInvalido(9));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildSegundoDigitoVerificadorInvalidoCpf() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpfInvalido(10));
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNuloNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(null);
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildVazioNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome("");
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildApenasEspacoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR + 1), ESPACO));
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildInicioEspacoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(ESPACO + pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildFimEspacoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome() + ESPACO);
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildMaiorTamanhoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(generateStringBySizeAndRegexWithSeparator(TAMANHO_REGULAR + 1, VALIDO_NOME, ESPACO));
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNaoApenasLetraEspacoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, TAMANHO_REGULAR + 1),
				INVALIDO_NOME, VALIDO_NOME));
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildEspacoSeguidoDeEspacoNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME) + "  "
				+ generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME));
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNuloEnderecos() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(null);
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildVazioEnderecos() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(new HashSet<>());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildEnderecosComElementoNulo() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		Set<Endereco> enderecosComElementoNulo = new HashSet<>();
		enderecosComElementoNulo.addAll(pessoaValida.getEnderecos());
		enderecosComElementoNulo.add(null);
		pessoa.setEnderecos(enderecosComElementoNulo);
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildEnderecosComElementoInvalido() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		Set<Endereco> enderecosComElementoInvalido = new HashSet<>();
		enderecosComElementoInvalido.addAll(pessoaValida.getEnderecos());
		enderecosComElementoInvalido.add(randomEndereco.buildNaoApenasNumeralCep());
		pessoa.setEnderecos(enderecosComElementoInvalido);
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNuloTelefonesFixo() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(null);
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildTelefonesFixoComElementoNulo() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		Set<TelefoneFixo> telefonesFixoComElementoNulo = new HashSet<>();
		telefonesFixoComElementoNulo.addAll(pessoaValida.getTelefonesFixo());
		telefonesFixoComElementoNulo.add(null);
		pessoa.setTelefonesFixo(telefonesFixoComElementoNulo);
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildTelefonesFixoComElementoInvalido() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		Set<TelefoneFixo> telefonesFixoComElementoInvalido = new HashSet<>();
		telefonesFixoComElementoInvalido.addAll(pessoaValida.getTelefonesFixo());
		telefonesFixoComElementoInvalido.add(randomTelefoneFixo.buildNaoApenasNumeralNumero());
		pessoa.setTelefonesFixo(telefonesFixoComElementoInvalido);
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNuloCelulares() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(null);
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildCelularesComElementoNulo() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		Set<Celular> celularesComElementoNulo = new HashSet<>();
		celularesComElementoNulo.addAll(pessoaValida.getCelulares());
		celularesComElementoNulo.add(null);
		pessoa.setCelulares(celularesComElementoNulo);
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildCelularesComElementoInvalido() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		Set<Celular> celularesComElementoInvalido = new HashSet<>();
		celularesComElementoInvalido.addAll(pessoaValida.getCelulares());
		celularesComElementoInvalido.add(randomCelular.buildNaoApenasNumeralNumero());
		pessoa.setCelulares(celularesComElementoInvalido);
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNuloEmails() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(null);
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildEmailsComElementoNulo() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		Set<Email> emailsComElementoNulo = new HashSet<>();
		emailsComElementoNulo.addAll(pessoaValida.getEmails());
		emailsComElementoNulo.add(null);
		pessoa.setEmails(emailsComElementoNulo);
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildEmailsComElementoInvalido() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		Set<Email> emailsComElementoInvalido = new HashSet<>();
		emailsComElementoInvalido.addAll(pessoaValida.getEmails());
		emailsComElementoInvalido.add(randomEmail.buildNuloEndereco());
		pessoa.setEmails(emailsComElementoInvalido);
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNuloContas() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(null);
		return pessoa;
	}

	public Pessoa buildContasComElementoNulo() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		Set<Conta> contasComElementoNulo = new HashSet<>();
		contasComElementoNulo.addAll(pessoaValida.getContas());
		contasComElementoNulo.add(null);
		pessoa.setContas(contasComElementoNulo);
		return pessoa;
	}

	public Pessoa buildContasComElementoInvalido() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		Set<Conta> contasComElementoInvalido = new HashSet<>();
		contasComElementoInvalido.addAll(pessoaValida.getContas());
		contasComElementoInvalido.add(randomConta.buildNaoApenasNumeralNumero());
		pessoa.setContas(contasComElementoInvalido);
		return pessoa;
	}

	public Pessoa buildNuloDataNascimento() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(null);
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildDataFuturaDataNascimento() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(LocalDate.now().plusDays(nextInt(1, 100)));
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNuloTipoGrauInstrucao() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(null);
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNuloTipoEstadoCivil() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(null);
		pessoa.setTipoSexo(pessoaValida.getTipoSexo());
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

	public Pessoa buildNuloTipoSexo() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaValida.getCpf());
		pessoa.setNome(pessoaValida.getNome());
		pessoa.setEnderecos(pessoaValida.getEnderecos());
		pessoa.setDataNascimento(pessoaValida.getDataNascimento());
		pessoa.setCelulares(pessoaValida.getCelulares());
		pessoa.setTelefonesFixo(pessoaValida.getTelefonesFixo());
		pessoa.setEmails(pessoaValida.getEmails());
		pessoa.setTipoGrauInstrucao(pessoaValida.getTipoGrauInstrucao());
		pessoa.setTipoEstadoCivil(pessoaValida.getTipoEstadoCivil());
		pessoa.setTipoSexo(null);
		pessoa.setContas(pessoaValida.getContas());
		return pessoa;
	}

}
