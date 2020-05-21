package br.com.contmatic.model.random.empresa;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.cnpjValido;
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
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.random.conta.ContaRandomBuilder;
import br.com.contmatic.model.random.contato.CelularRandomBuilder;
import br.com.contmatic.model.random.contato.EmailRandomBuilder;
import br.com.contmatic.model.random.contato.TelefoneFixoRandomBuilder;
import br.com.contmatic.model.random.endereco.EnderecoRandomBuilder;
import br.com.contmatic.model.random.pessoa.PessoaRandomBuilder;

public class EmpresaRandomBuilder {

	private static final int MAX_ELEMENTOS_ARRAY_GERADA = 10;
    
    private static final int TAMANHO_REGULAR = 100;
    
    private static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";
    
    private static final String ESPACO = " ";
	
	private PessoaRandomBuilder pessoaRandomBuilder = new PessoaRandomBuilder();
	
	private EnderecoRandomBuilder enderecoRandomBuilder = new EnderecoRandomBuilder();
	
	private CelularRandomBuilder celularRandomBuilder = new CelularRandomBuilder();
	
	private TelefoneFixoRandomBuilder telefoneFixoRandomBuilder = new TelefoneFixoRandomBuilder();
	
	private EmailRandomBuilder emailRandomBuilder = new EmailRandomBuilder();
	
	private ContaRandomBuilder contaRandomBuilder = new ContaRandomBuilder();
	
	private static EmpresaRandomBuilder instance;
	
	private EmpresaRandomBuilder() {	
	}
	
	public static EmpresaRandomBuilder getInstance() {
		if (instance == null) {
			instance = new EmpresaRandomBuilder();
		}
		return instance;
	}
	
	public static void closeBuilder() {
		instance = null;
	}
	
	public Empresa build() {
		final Empresa empresa = new Empresa();
		empresa.setCnpj(cnpjValido());
		empresa.setRazaoSocial(generateStringBySizeAndRegexWithSeparator(nextInt(1, TAMANHO_REGULAR + 1), VALIDO_NOME, ESPACO));
		empresa.setDataAbertura(LocalDate.now().minusDays(nextInt(1, 10000)));
		Set<Pessoa> responsaveis = new HashSet<>();
		int quantidadeCollection = nextInt(1, MAX_ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			responsaveis.add(pessoaRandomBuilder.build());
		}
		empresa.setResponsaveis(responsaveis);
		Set<Endereco> enderecos = new HashSet<>();
		quantidadeCollection = nextInt(1, MAX_ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			enderecos.add(enderecoRandomBuilder.build());
		}
		empresa.setEnderecos(enderecos);
		Set<Celular> celulares = new HashSet<>();
		quantidadeCollection = nextInt(0, MAX_ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			celulares.add(celularRandomBuilder.build());
		}
		empresa.setCelulares(celulares);
		Set<TelefoneFixo> telefonesFixo = new HashSet<>();
		quantidadeCollection = nextInt(0, MAX_ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			telefonesFixo.add(telefoneFixoRandomBuilder.build());
		}
		empresa.setTelefonesFixo(telefonesFixo);
		Set<Email> emails = new HashSet<>();
		quantidadeCollection = nextInt(0, MAX_ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			emails.add(emailRandomBuilder.build());
		}
		empresa.setEmails(emails);
		Set<Conta> contas = new HashSet<>();
		quantidadeCollection = nextInt(0, MAX_ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			contas.add(contaRandomBuilder.build());
		}
		empresa.setContas(contas);
		empresa.setTipoEmpresa(TipoEmpresa.values()[nextInt(0, TipoEmpresa.values().length)]);
		empresa.setTipoPorteEmpresa(TipoPorteEmpresa.values()[nextInt(0, TipoPorteEmpresa.values().length)]);
		return empresa;
	}
	
}
