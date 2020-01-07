package br.com.contmatic.repository.empresa;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.pessoa.ContratoTrabalho;
import br.com.contmatic.model.pessoa.Pessoa;

public class Teste {

	public static void main(String[] args) {
		EmpresaRepository busca = new EmpresaMongoRepositoryImp();
		Empresa empresa = new Empresa();
		Set<Pessoa> responsaveis = new HashSet<>();
		Pessoa pessoa = new Pessoa();
		Set<Endereco> enderecos = new HashSet<>();
		Endereco endereco = new Endereco();
		endereco.setCep("aqui!!!");
		enderecos.add(endereco);
		pessoa.setCpf("123");
		pessoa.setEnderecos(enderecos);
		responsaveis.add(pessoa);
		empresa.setResponsaveis(responsaveis);
		Set<ContratoTrabalho> contratos = new HashSet<>();
		ContratoTrabalho contrato = new ContratoTrabalho();
		contrato.setPessoa(pessoa);
		contratos.add(contrato);
		empresa.setDataAbertura(LocalDate.parse("1990-01-01"));
		System.out.println(busca.countAllEmpresas());
		System.out.println(empresa);
		busca.createEmpresa(empresa);
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setCpf("321");
		pessoa1.setEnderecos(enderecos);		
		responsaveis.add(pessoa1);
		empresa.setCnpj("1");
		System.out.println(empresa);
		busca.createEmpresa(empresa);
		System.out.println(busca.countAllEmpresas());
		System.out.println(busca.readByCnpj(""));
		Set<LocalDate> datas = new HashSet<>();
		datas.add(LocalDate.parse("1990-01-01"));
		System.out.println(busca.readByDataAbertura(datas));
		System.out.println(busca.readByResponsaveis(responsaveis));
		Set<Set<Pessoa>> groupedResponsaveis = new HashSet<>();
		groupedResponsaveis.add(responsaveis);
		System.out.println(busca.readByGroupedResponsaveis(groupedResponsaveis));
		busca.deleteEmpresa(null);
		busca.deleteEmpresa(null);
		busca.deleteEmpresa(null);
		busca.deleteEmpresa("");
		busca.deleteEmpresa("1");
		busca.deleteEmpresa("1");
		busca.deleteEmpresa("1");
		busca.deleteEmpresa("1");
		System.out.println(busca.countAllEmpresas());
	}

}
