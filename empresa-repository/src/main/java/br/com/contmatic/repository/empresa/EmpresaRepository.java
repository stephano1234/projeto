package br.com.contmatic.repository.empresa;

import java.util.Collection;
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

public interface EmpresaRepository {
	
	public void createEmpresa(Empresa empresa);
	
	public void deleteEmpresa(String cnpj);
	
	public void updateEmpresa(String cnpj, Empresa empresa);
	
	public Empresa readByCnpj(String cnpj);

	public Collection<Empresa> readByRazaoSocial(String razaoSocial);
	
	public Collection<Empresa> readByRazaoSocial(Set<String> razoesSocial);
	
	public Collection<Empresa> readByDataAbertura(LocalDate dataAbertura);
	
	public Collection<Empresa> readByDataAbertura(Set<LocalDate> datasAbertura);
	
	public Collection<Empresa> readByResponsaveis(Set<Pessoa> responsaveis);

	public Collection<Empresa> readByGroupedResponsaveis(Set<Set<Pessoa>> groupedResponsaveis);
	
	public Collection<Empresa> readByContratosTrabalho(Set<ContratoTrabalho> contratosTrabalho);
	
	public Collection<Empresa> readByGroupedContratosTrabalho(Set<Set<ContratoTrabalho>> groupedContratosTrabalho);
	
	public Collection<Empresa> readByEnderecos(Set<Endereco> enderecos);
	
	public Collection<Empresa> readByGroupedEnderecos(Set<Set<Endereco>> groupedEnderecos);
	
	public Collection<Empresa> readByTelefonesFixo(Set<TelefoneFixo> telefonesFixo);
	
	public Collection<Empresa> readByGroupedTelefonesFixo(Set<Set<TelefoneFixo>> groupedTelefonesFixo);
	
	public Collection<Empresa> readByEmails(Set<Email> emails);
	
	public Collection<Empresa> readByGroupedEmails(Set<Set<Email>> groupedEmails);
	
	public Collection<Empresa> readByCelulares(Set<Celular> celulares);
	
	public Collection<Empresa> readByGroupedCelulares(Set<Set<Celular>> groupedCelulares);
	
	public Collection<Empresa> readByContas(Set<Conta> contas);
	
	public Collection<Empresa> readByGroupedContas(Set<Set<Conta>> groupedContas);
	
	public Collection<Empresa> readByTipoEmpresa(TipoEmpresa tipoEmpresa);
	
	public Collection<Empresa> readByTipoPorteEmpresa(TipoPorteEmpresa tipoPorteEmpresa);
	
	public Collection<Empresa> readAllEmpresas();
	
	public long countAllEmpresas();
	
}
