package br.com.contmatic.repository.contato;

import java.util.HashSet;
import java.util.Collection;

import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.empresa.Empresa;

public interface EmpresaByEmailRepository {

	public Collection<Empresa> findEmpresasByEnderecoEmail(String endereco);
	
	public default Collection<Empresa> findEmpresaByEnderecosEmail(Collection<String> enderecos) {
		Collection<Empresa> empresas = new HashSet<>();
		for (String endereco : enderecos) {
			empresas.addAll(findEmpresasByEnderecoEmail(endereco));
		}
		return empresas;
	}
	
	public Collection<Empresa> findEmpresasByEmail(Email email);
	
	public default Collection<Empresa> findEmpresaByEmails(Collection<Email> emails) {
		Collection<Empresa> empresas = new HashSet<>();
		for (Email email : emails) {
			empresas.addAll(findEmpresasByEmail(email));
		}
		return empresas;
	}
	
}
