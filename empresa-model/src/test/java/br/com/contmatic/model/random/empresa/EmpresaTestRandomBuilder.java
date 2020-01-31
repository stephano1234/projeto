package br.com.contmatic.model.random.empresa;

import static br.com.contmatic.testes.utilidades.ConstantesTesteNumericas.CNPJ;
import static br.com.contmatic.testes.utilidades.ConstantesTesteNumericas.ELEMENTOS_ARRAY_GERADA;
import static br.com.contmatic.testes.utilidades.ConstantesTesteNumericas.TAMANHO_REGULAR;
import static br.com.contmatic.testes.utilidades.ConstantesTesteString.APENAS_NUMERAL;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.apenasUmCaractere;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.cnpjInvalido;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.cnpjValido;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.somenteCaractere;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.pessoa.ContratoTrabalho;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.random.conta.ContaRandomBuilder;
import br.com.contmatic.model.random.contato.CelularRandomBuilder;
import br.com.contmatic.model.random.contato.EmailRandomBuilder;
import br.com.contmatic.model.random.contato.TelefoneFixoRandomBuilder;
import br.com.contmatic.model.random.endereco.EnderecoRandomBuilder;
import br.com.contmatic.model.random.pessoa.ContratoTrabalhoRandomBuilder;
import br.com.contmatic.model.random.pessoa.PessoaRandomBuilder;

public class EmpresaTestRandomBuilder {

	public static Empresa buildValido() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(cnpjValido());
		empresa.setRazaoSocial(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(10, 20), "[A-Z&Ç'-]") + somenteCaractere(1, "[A-Z]"));
		empresa.setDataAbertura(LocalDate.now().minusDays(nextInt(1, 10)));
		Set<Pessoa> responsaveis = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			responsaveis.add(PessoaRandomBuilder.buildValido());
		}
		empresa.setResponsaveis(responsaveis);
		Set<ContratoTrabalho> contratosTrabalho = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			contratosTrabalho.add(ContratoTrabalhoRandomBuilder.buildValido());
		}
		empresa.setContratosTrabalho(contratosTrabalho);
		Set<Endereco> enderecos = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			enderecos.add(EnderecoRandomBuilder.buildValido());
		}
		empresa.setEnderecos(enderecos);
		Set<Celular> celulares = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			celulares.add(CelularRandomBuilder.buildValido());
		}
		empresa.setCelulares(celulares);
		Set<TelefoneFixo> telefonesFixo = new HashSet<TelefoneFixo>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			telefonesFixo.add(TelefoneFixoRandomBuilder.buildValido());
		}
		empresa.setTelefonesFixo(telefonesFixo);
		Set<Email> emails = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			emails.add(EmailRandomBuilder.buildValido());
		}
		empresa.setEmails(emails);
		Set<Conta> contas = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			contas.add(ContaRandomBuilder.buildValido());
		}
		empresa.setContas(contas);
		empresa.setTipoEmpresa(TipoEmpresa.values()[nextInt(0, TipoEmpresa.values().length)]);
		empresa.setTipoPorteEmpresa(TipoPorteEmpresa.values()[nextInt(0, TipoPorteEmpresa.values().length)]);
		return empresa;
	}
	
	public static Empresa buildMaiorTamanhoCnpj() {
		Empresa empresa = buildValido();
		empresa.setCnpj(somenteCaractere(CNPJ + 1, APENAS_NUMERAL));
		return empresa;
	}

	public static Empresa buildMenorTamanhoCnpj() {
		Empresa empresa = buildValido();
		empresa.setCnpj(somenteCaractere(CNPJ - 1, APENAS_NUMERAL));
		return empresa;
	}
	
	public static Empresa buildNaoApenasNumeralCnpj() {
		Empresa empresa = buildValido();
		empresa.setCnpj(apenasUmCaractere(CNPJ, "\\D", APENAS_NUMERAL));
		return empresa;
	}

	public static Empresa buildNumerosRepetidosCnpj() {
		Empresa empresa = buildValido();
		empresa.setCnpj(somenteCaractere(CNPJ, Integer.toString(nextInt(0, 10))));
		return empresa;
	}

	public static Empresa buildPrimeiroDigitoVerificadorInvalidoCnpj() {
		Empresa empresa = buildValido();
		empresa.setCnpj(cnpjInvalido(12));
		return empresa;
	}

	public static Empresa buildSegundoDigitoVerificadorInvalidoCnpj() {
		Empresa empresa = buildValido();
		empresa.setCnpj(cnpjInvalido(13));
		return empresa;
	}

	public static Empresa buildMaiorTamanhoRazaoSocial() {
		Empresa empresa = buildValido();
		empresa.setRazaoSocial(somenteCaractere(1, "[A-Z]") + somenteCaractere(TAMANHO_REGULAR + 1, "[a-z]"));
		return empresa;
	}

	public static Empresa buildApenasUmCaractereRazaoSocial() {
		Empresa empresa = buildValido();
		empresa.setRazaoSocial(somenteCaractere(1, "[A-Z]"));
		return empresa;
	}
	
	public static Empresa buildPrimeiroCaractereInvalido() {
		Empresa empresa = buildValido();
		empresa.setRazaoSocial(somenteCaractere(1, "[a-z]") + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return empresa;
	}
	
	public static Empresa buildUltimoCaractereInvalido() {
		Empresa empresa = buildValido();
		empresa.setRazaoSocial(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + somenteCaractere(1, "\\d"));
		return empresa;
	}
	
	public static Empresa buildCaractereInvalidoRazaoSocial() {
		Empresa empresa = buildValido();
		empresa.setRazaoSocial(somenteCaractere(1, "[A-Z]") + apenasUmCaractere(nextInt(1, 10), "\\d", "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return empresa;
	}
	
	public static Empresa buildEspacoDuploRazaoSocial() {
		Empresa empresa = buildValido();
		empresa.setRazaoSocial(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + "  " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return empresa;
	}
	
	public static Empresa buildPontoDuploRazaoSocial() {
		Empresa empresa = buildValido();
		empresa.setRazaoSocial(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + ".." + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return empresa;
	}
	
	public static Empresa buildPontoDuploNoFinalRazaoSocial() {
		Empresa empresa = buildValido();
		empresa.setRazaoSocial(somenteCaractere(10, "[A-Z]") + "..");
		return empresa;
	}

	public static Empresa buildDataAberturaDataFutura() {
		Empresa empresa = buildValido();
		empresa.setDataAbertura(LocalDate.now().plusYears(nextInt(1, 5)));
		return empresa;
	}
	
}
