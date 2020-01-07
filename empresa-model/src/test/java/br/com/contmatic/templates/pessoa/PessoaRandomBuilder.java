package br.com.contmatic.templates.pessoa;

import static br.com.contmatic.utilidades.ConstantesTesteNumericas.EXCLUI_STRING_VAZIO;
import static br.com.contmatic.utilidades.ConstantesTesteNumericas.TAMANHO_REGULAR;
import static br.com.contmatic.utilidades.ConstantesTesteString.APENAS_ESPACO;
import static br.com.contmatic.utilidades.ConstantesTesteString.APENAS_NUMERAL;
import static br.com.contmatic.utilidades.FuncoesRandomicas.apenasUmCaractere;
import static br.com.contmatic.utilidades.FuncoesRandomicas.cpfInvalido;
import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static br.com.contmatic.utilidades.ConstantesTesteNumericas.CPF;
import static br.com.contmatic.utilidades.ConstantesTesteNumericas.ELEMENTOS_ARRAY_GERADA;
import static br.com.contmatic.utilidades.FuncoesRandomicas.cpfValido;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.pessoa.TipoEstadoCivil;
import br.com.contmatic.model.pessoa.TipoGrauInstrucao;
import br.com.contmatic.model.pessoa.TipoSexo;
import br.com.contmatic.templates.conta.ContaRandomBuilder;
import br.com.contmatic.templates.contato.CelularRandomBuilder;
import br.com.contmatic.templates.contato.EmailRandomBuilder;
import br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder;
import br.com.contmatic.templates.endereco.EnderecoRandomBuilder;

public class PessoaRandomBuilder {

	public static Pessoa buildValido() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpfValido());
		pessoa.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " "
				+ somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		Set<Endereco> enderecos = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			enderecos.add(EnderecoRandomBuilder.buildValido());
		}
		pessoa.setEnderecos(enderecos);
		pessoa.setDataNascimento(LocalDate.now().minusYears(nextInt(1, 30)));
		Set<Celular> celulares = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			celulares.add(CelularRandomBuilder.buildValido());
		}
		pessoa.setCelulares(celulares);
		Set<TelefoneFixo> telefonesFixo = new HashSet<TelefoneFixo>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			telefonesFixo.add(TelefoneFixoRandomBuilder.buildValido());
		}
		pessoa.setTelefonesFixo(telefonesFixo);
		Set<Email> emails = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			emails.add(EmailRandomBuilder.buildValido());
		}
		pessoa.setEmails(emails);
		pessoa.setTipoGrauInstrucao(TipoGrauInstrucao.values()[nextInt(0, TipoGrauInstrucao.values().length)]);
		pessoa.setTipoEstadoCivil(TipoEstadoCivil.values()[nextInt(0, TipoEstadoCivil.values().length)]);
		pessoa.setTipoSexo(TipoSexo.values()[nextInt(0, TipoSexo.values().length)]);
		Set<Conta> contas = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			contas.add(ContaRandomBuilder.buildValido());
		}
		pessoa.setContas(contas);
		return pessoa;
	}
	
	public static Pessoa buildMaiorTamanhoCpf() {
		Pessoa pessoa = buildValido();
		pessoa.setCpf(somenteCaractere(CPF + 1, APENAS_NUMERAL));
		return pessoa;
	}	

	public static Pessoa buildMenorTamanhoCpf() {
		Pessoa pessoa = buildValido();
		pessoa.setCpf(somenteCaractere(CPF - 1, APENAS_NUMERAL));
		return pessoa;
	}	

	public static Pessoa buildNaoApenasNumeralCpf() {
		Pessoa pessoa = buildValido();
		pessoa.setCpf(apenasUmCaractere(CPF, "\\D", APENAS_NUMERAL));
		return pessoa;
	}	

	public static Pessoa buildNumerosRepetidosCpf() {
		Pessoa pessoa = buildValido();
		pessoa.setCpf(somenteCaractere(CPF, Integer.toString(nextInt(0, 10))));
		return pessoa;
	}	

	public static Pessoa buildPrimeiroDigitoVerificadorInvalidoCpf() {
		Pessoa pessoa = buildValido();
		pessoa.setCpf(cpfInvalido(9));
		return pessoa;
	}	

	public static Pessoa buildSegundoDigitoVerificadorInvalidoCpf() {
		Pessoa pessoa = buildValido();
		pessoa.setCpf(cpfInvalido(10));
		return pessoa;
	}	

	public static Pessoa buildApenasEspacoNome() {
		Pessoa pessoa = buildValido();
		pessoa.setNome(somenteCaractere(nextInt(EXCLUI_STRING_VAZIO, TAMANHO_REGULAR), APENAS_ESPACO));
		return pessoa;
	}
	
	public static Pessoa buildMaiorTamanhoNome() {
		Pessoa pessoa = buildValido();
		pessoa.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(TAMANHO_REGULAR + 1, "[a-z]"));
		return pessoa;
	}
	
	public static Pessoa buildApenasUmCaractereNome() {
		Pessoa pessoa = buildValido();
		pessoa.setNome(somenteCaractere(1, "[A-Z]"));
		return pessoa;
	}

	public static Pessoa buildPrimeiroCaractereMinusculoNome() {
		Pessoa pessoa = buildValido();
		pessoa.setNome(somenteCaractere(1, "[a-z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return pessoa;
	}

	public static Pessoa buildNaoApenasLetraNome() {
		Pessoa pessoa = buildValido();
		pessoa.setNome(somenteCaractere(1, "[A-Z]") + apenasUmCaractere(nextInt(1, 10), "\\d", "[a-z]"));
		return pessoa;
	}

	public static Pessoa buildEspacoDuploNome() {
		Pessoa pessoa = buildValido();
		pessoa.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + "  " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return pessoa;
	}

	public static Pessoa buildDataFuturaDataNascimento() {
		Pessoa pessoa = buildValido();
		pessoa.setDataNascimento(LocalDate.now().plusDays(nextInt(1, 100)));
		return pessoa;
	}

}
