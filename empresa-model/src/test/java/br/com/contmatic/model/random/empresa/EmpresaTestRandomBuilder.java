package br.com.contmatic.model.random.empresa;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.cnpjInvalido;
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
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.pessoa.ContratoTrabalho;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.random.conta.ContaTestRandomBuilder;
import br.com.contmatic.model.random.contato.CelularTestRandomBuilder;
import br.com.contmatic.model.random.contato.EmailTestRandomBuilder;
import br.com.contmatic.model.random.contato.TelefoneFixoTestRandomBuilder;
import br.com.contmatic.model.random.endereco.EnderecoTestRandomBuilder;
import br.com.contmatic.model.random.pessoa.ContratoTrabalhoTestRandomBuilder;
import br.com.contmatic.model.random.pessoa.PessoaTestRandomBuilder;

public class EmpresaTestRandomBuilder {

	private static final int TAMANHO_REGULAR = 100;

	private static final int CNPJ = 14;

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

	private static final PessoaTestRandomBuilder randomPessoa = PessoaTestRandomBuilder.getInstance();

	private static final ContratoTrabalhoTestRandomBuilder randomContratoTrabalho = ContratoTrabalhoTestRandomBuilder.getInstance();
	
	private final Empresa empresaValida = EmpresaRandomBuilder.getInstance().build();
	
	private static EmpresaTestRandomBuilder instance;
	
	public static EmpresaTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new EmpresaTestRandomBuilder();
		}
		return instance;
	}
	
	public Empresa buildValid() {
		return empresaValida;
	}
	
	public static void cleanBuilder() {
		EmailTestRandomBuilder.cleanBuilder();
		CelularTestRandomBuilder.cleanBuilder();
		ContaTestRandomBuilder.cleanBuilder();
		EnderecoTestRandomBuilder.cleanBuilder();
		TelefoneFixoTestRandomBuilder.cleanBuilder();
		PessoaTestRandomBuilder.cleanBuilder();
		ContratoTrabalhoTestRandomBuilder.cleanBuilder();
		instance = null;
	}

	public Empresa buildNuloCnpj() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(null);
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildMaiorTamanhoCnpj() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(generateStringBySizeAndRegex(CNPJ + 1, APENAS_NUMERAL));
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildMenorTamanhoCnpj() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(generateStringBySizeAndRegex(CNPJ - 1, APENAS_NUMERAL));
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNaoApenasNumeralCnpj() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(generateStringBySizeAndRegexWithOneCharByRegex(CNPJ, SEM_NUMERAL, APENAS_NUMERAL));
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNumerosRepetidosCnpj() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(generateStringBySizeAndRegex(CNPJ, Integer.toString(nextInt(0, 10))));
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildPrimeiroDigitoVerificadorInvalidoCnpj() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(cnpjInvalido(12));
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildSegundoDigitoVerificadorInvalidoCnpj() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(cnpjInvalido(13));
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNuloRazaoSocial() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(null);
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildVazioRazaoSocial() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial("");
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildApenasEspacoRazaoSocial() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR + 1), ESPACO));
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildInicioEspacoRazaoSocial() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(ESPACO + empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildFimEspacoRazaoSocial() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial() + ESPACO);
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildMaiorTamanhoRazaoSocial() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(generateStringBySizeAndRegexWithSeparator(TAMANHO_REGULAR + 1, VALIDO_NOME, ESPACO));
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNaoApenasLetraEspacoRazaoSocial() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, TAMANHO_REGULAR + 1),
				INVALIDO_NOME, VALIDO_NOME));
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildEspacoSeguidoDeEspacoRazaoSocial() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME) + "  "
				+ generateStringBySizeAndRegex(nextInt(1, TAMANHO_REGULAR / 2), VALIDO_NOME));
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNuloEnderecos() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(null);
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildVazioEnderecos() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(new HashSet<>());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildEnderecosComElementoNulo() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		Set<Endereco> enderecosComElementoNulo = new HashSet<>();
		enderecosComElementoNulo.addAll(empresaValida.getEnderecos());
		enderecosComElementoNulo.add(null);
		empresa.setEnderecos(enderecosComElementoNulo);
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildEnderecosComElementoInvalido() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		Set<Endereco> enderecosComElementoInvalido = new HashSet<>();
		enderecosComElementoInvalido.addAll(empresaValida.getEnderecos());
		enderecosComElementoInvalido.add(randomEndereco.buildNaoApenasNumeralCep());
		empresa.setEnderecos(enderecosComElementoInvalido);
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNuloContratosTrabalho() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(null);
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildContratosTrabalhoComElementoNulo() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		Set<ContratoTrabalho> contratosTrabalhoComElementoNulo = new HashSet<>();
		contratosTrabalhoComElementoNulo.addAll(empresaValida.getContratosTrabalho());
		contratosTrabalhoComElementoNulo.add(null);
		empresa.setContratosTrabalho(contratosTrabalhoComElementoNulo);
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildContratosTrabalhoComElementoInvalido() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		Set<ContratoTrabalho> contratosTrabalhoComElementoInvalido = new HashSet<>();
		contratosTrabalhoComElementoInvalido.addAll(empresaValida.getContratosTrabalho());
		contratosTrabalhoComElementoInvalido.add(randomContratoTrabalho.buildNuloPessoa());
		empresa.setContratosTrabalho(contratosTrabalhoComElementoInvalido);
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNuloResponsaveis() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(null);
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildVazioResponsaveis() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(new HashSet<>());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildResponsaveisComElementoNulo() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		Set<Pessoa> responsaveisComElementoNulo = new HashSet<>();
		responsaveisComElementoNulo.addAll(empresaValida.getResponsaveis());
		responsaveisComElementoNulo.add(null);
		empresa.setResponsaveis(responsaveisComElementoNulo);
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildResponsaveisComElementoInvalido() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		Set<Pessoa> responsaveisComElementoInvalido = new HashSet<>();
		responsaveisComElementoInvalido.addAll(empresaValida.getResponsaveis());
		responsaveisComElementoInvalido.add(randomPessoa.buildNuloCpf());
		empresa.setResponsaveis(responsaveisComElementoInvalido);
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNuloTelefonesFixo() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(null);
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildTelefonesFixoComElementoNulo() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		Set<TelefoneFixo> telefonesFixoComElementoNulo = new HashSet<>();
		telefonesFixoComElementoNulo.addAll(empresaValida.getTelefonesFixo());
		telefonesFixoComElementoNulo.add(null);
		empresa.setTelefonesFixo(telefonesFixoComElementoNulo);
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildTelefonesFixoComElementoInvalido() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		Set<TelefoneFixo> telefonesFixoComElementoInvalido = new HashSet<>();
		telefonesFixoComElementoInvalido.addAll(empresaValida.getTelefonesFixo());
		telefonesFixoComElementoInvalido.add(randomTelefoneFixo.buildNaoApenasNumeralNumero());
		empresa.setTelefonesFixo(telefonesFixoComElementoInvalido);
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNuloCelulares() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(null);
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildCelularesComElementoNulo() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		Set<Celular> celularesComElementoNulo = new HashSet<>();
		celularesComElementoNulo.addAll(empresaValida.getCelulares());
		celularesComElementoNulo.add(null);
		empresa.setCelulares(celularesComElementoNulo);
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildCelularesComElementoInvalido() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		Set<Celular> celularesComElementoInvalido = new HashSet<>();
		celularesComElementoInvalido.addAll(empresaValida.getCelulares());
		celularesComElementoInvalido.add(randomCelular.buildNaoApenasNumeralNumero());
		empresa.setCelulares(celularesComElementoInvalido);
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNuloEmails() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(null);
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildEmailsComElementoNulo() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		Set<Email> emailsComElementoNulo = new HashSet<>();
		emailsComElementoNulo.addAll(empresaValida.getEmails());
		emailsComElementoNulo.add(null);
		empresa.setEmails(emailsComElementoNulo);
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildEmailsComElementoInvalido() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		Set<Email> emailsComElementoInvalido = new HashSet<>();
		emailsComElementoInvalido.addAll(empresaValida.getEmails());
		emailsComElementoInvalido.add(randomEmail.buildNuloEndereco());
		empresa.setEmails(emailsComElementoInvalido);
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNuloContas() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(null);
		return empresa;
	}

	public Empresa buildContasComElementoNulo() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		Set<Conta> contasComElementoNulo = new HashSet<>();
		contasComElementoNulo.addAll(empresaValida.getContas());
		contasComElementoNulo.add(null);
		empresa.setContas(contasComElementoNulo);
		return empresa;
	}

	public Empresa buildContasComElementoInvalido() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		Set<Conta> contasComElementoInvalido = new HashSet<>();
		contasComElementoInvalido.addAll(empresaValida.getContas());
		contasComElementoInvalido.add(randomConta.buildNaoApenasNumeralNumero());
		empresa.setContas(contasComElementoInvalido);
		return empresa;
	}

	public Empresa buildNuloDataAbertura() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(null);
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildDataFuturaDataAbertura() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(LocalDate.now().plusDays(nextInt(1, 100)));
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNuloTipoEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(null);
		empresa.setTipoPorteEmpresa(empresaValida.getTipoPorteEmpresa());
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}

	public Empresa buildNuloTipoPorteEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setCnpj(empresaValida.getCnpj());
		empresa.setRazaoSocial(empresaValida.getRazaoSocial());
		empresa.setResponsaveis(empresaValida.getResponsaveis());
		empresa.setContratosTrabalho(empresaValida.getContratosTrabalho());
		empresa.setEnderecos(empresaValida.getEnderecos());
		empresa.setDataAbertura(empresaValida.getDataAbertura());
		empresa.setCelulares(empresaValida.getCelulares());
		empresa.setTelefonesFixo(empresaValida.getTelefonesFixo());
		empresa.setEmails(empresaValida.getEmails());
		empresa.setTipoEmpresa(empresaValida.getTipoEmpresa());
		empresa.setTipoPorteEmpresa(null);
		empresa.setContas(empresaValida.getContas());
		return empresa;
	}
	
}
