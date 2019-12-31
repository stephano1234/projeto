package br.com.contmatic.repository.empresa;

import java.util.HashSet;
import java.util.Set;

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
		System.out.println(busca.count());
		System.out.println(empresa);
		busca.create(empresa);
		System.out.println(busca.count());
		System.out.println(busca.readByCnpj(""));
		busca.delete("");
		busca.delete("");
		busca.delete("");
		busca.delete("");
		System.out.println(busca.count());
	}

}
