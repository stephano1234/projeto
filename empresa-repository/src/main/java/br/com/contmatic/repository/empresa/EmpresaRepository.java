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
import br.com.contmatic.repository.Repository;

public interface EmpresaRepository extends Repository<Empresa> {
	
	public Empresa readByCnpj(String cnpj);

	public Collection<Empresa> readByRazaoSocial(String razaoSocial);
	
	public Collection<Empresa> readByDataAbertura(LocalDate dataAbertura);
	
	public Collection<Empresa> readByResponsaveis(Set<Pessoa> responsaveis);

	public Collection<Empresa> readByGroupedResponsaveis(Set<Set<Pessoa>> groupedResponsaveis);
	
	public Collection<Empresa> readByContratosTrabalho(Set<ContratoTrabalho> contratosTrabalho);
	
	public Collection<Empresa> readByEnderecos(Set<Endereco> enderecos);
	
	public Collection<Empresa> readByTelefonesFixo(Set<TelefoneFixo> telefonesFixo);
	
	public Collection<Empresa> readByEmails(Set<Email> emails);
	
	public Collection<Empresa> readByCelulares(Set<Celular> celulares);
	
	public Collection<Empresa> readByContas(Set<Conta> contas);
	
	public Collection<Empresa> readByTipoEmpresa(TipoEmpresa tipoEmpresa);
	
	public Collection<Empresa> readByTipoPorteEmpresa(TipoPorteEmpresa tipoPorteEmpresa);
	
}
