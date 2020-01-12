package br.com.contmatic.random.empresa;

import static br.com.contmatic.utilidades.ConstantesTesteNumericas.ELEMENTOS_ARRAY_GERADA;
import static br.com.contmatic.utilidades.FuncoesRandomicas.cnpjValido;
import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;
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
import br.com.contmatic.random.conta.ContaRandomBuilder;
import br.com.contmatic.random.contato.CelularRandomBuilder;
import br.com.contmatic.random.contato.EmailRandomBuilder;
import br.com.contmatic.random.contato.TelefoneFixoRandomBuilder;
import br.com.contmatic.random.endereco.EnderecoRandomBuilder;
import br.com.contmatic.random.pessoa.ContratoTrabalhoRandomBuilder;
import br.com.contmatic.random.pessoa.PessoaRandomBuilder;

public class EmpresaRandomBuilder {

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
	
}
