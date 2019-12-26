package br.com.contmatic.repository.mongodb;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.endereco.Bairro;
import br.com.contmatic.model.endereco.Cidade;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.endereco.Logradouro;
import br.com.contmatic.model.endereco.TipoEndereco;
import br.com.contmatic.model.endereco.TipoUf;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.pessoa.TipoEstadoCivil;

public class Teste {

	public static void main(String[] afaf) {
		PrincipalMongoRepository<Empresa> busca = new PrincipalMongoRepository<Empresa>("empresa", "empresa") {
		};
		Empresa empresa = new Empresa("", "", LocalDate.parse("1990-01-01"), new HashSet<>(), new HashSet<>(), null, null);
		empresa.setCelulares(new HashSet<>());
		empresa.setContas(new HashSet<>());
		empresa.setContratosTrabalho(new HashSet<>());
		empresa.setEmails(new HashSet<>());
		empresa.setTelefonesFixo(new HashSet<>());
		Endereco endereco1 = new Endereco("1", "", new Logradouro("", new Bairro("", new Cidade("", TipoUf.AC))), TipoEndereco.COMERCIAL);
		Endereco endereco2 = new Endereco("2", "", new Logradouro("", new Bairro("", new Cidade("", TipoUf.AC))), TipoEndereco.COMERCIAL);
		Endereco endereco3 = new Endereco("3", "", new Logradouro("", new Bairro("", new Cidade("", TipoUf.AC))), TipoEndereco.COMERCIAL);
		Endereco endereco4 = new Endereco("4", "", new Logradouro("", new Bairro("", new Cidade("", TipoUf.AC))), TipoEndereco.COMERCIAL);
		Endereco endereco5 = new Endereco("5", "", new Logradouro("", new Bairro("", new Cidade("", TipoUf.AC))), TipoEndereco.COMERCIAL);
		Endereco endereco6 = new Endereco("6", "", new Logradouro("", new Bairro("", new Cidade("", TipoUf.AC))), TipoEndereco.COMERCIAL);
		Endereco endereco7 = new Endereco("7", "", new Logradouro("", new Bairro("", new Cidade("", TipoUf.AC))), TipoEndereco.COMERCIAL);
		Endereco endereco8 = new Endereco("8", "", new Logradouro("", new Bairro("", new Cidade("", TipoUf.AC))), TipoEndereco.COMERCIAL);
		Set<Endereco> enderecos = new HashSet<>();
		enderecos.add(endereco1);
		enderecos.add(endereco2);
		enderecos.add(endereco3);
		enderecos.add(endereco4);
		enderecos.add(endereco5);
		enderecos.add(endereco6);
		enderecos.add(endereco7);
		enderecos.add(endereco8);
		empresa.setEnderecos(enderecos);
		Pessoa pessoa1 = new Pessoa("1", "", enderecos, LocalDate.parse("1990-01-01"), null, TipoEstadoCivil.CASADO, null);
		Pessoa pessoa2 = new Pessoa("2", "", enderecos, LocalDate.parse("1990-01-01"), null, TipoEstadoCivil.CASADO, null);
		Pessoa pessoa3 = new Pessoa("3", "", enderecos, LocalDate.parse("1990-01-01"), null, TipoEstadoCivil.CASADO, null);
		Pessoa pessoa4 = new Pessoa("4", "", enderecos, LocalDate.parse("1990-01-01"), null, TipoEstadoCivil.CASADO, null);
		Pessoa pessoa5 = new Pessoa("5", "", enderecos, LocalDate.parse("1990-01-01"), null, TipoEstadoCivil.CASADO, null);
		Pessoa pessoa6 = new Pessoa("6", "", enderecos, LocalDate.parse("1990-01-01"), null, TipoEstadoCivil.CASADO, null);		
		Set<Pessoa> pessoas = new HashSet<Pessoa>();
		pessoas.add(pessoa1);
		pessoas.add(pessoa2);
		pessoas.add(pessoa3);
		pessoas.add(pessoa4);
		pessoas.add(pessoa5);
		pessoas.add(pessoa6);
		empresa.setResponsaveis(pessoas);
		System.out.println(busca.count());
		busca.create(empresa);
		System.out.println(busca.findAll());
		System.out.println(busca.count());
		busca.delete(empresa);
		System.out.println(busca.count());
		busca.close();
	}
	
}
