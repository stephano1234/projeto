package br.com.contmatic.repository.contato;

import static br.com.contmatic.repository.mongodb.ConstantesMongo.COLLECTION;
import static br.com.contmatic.repository.mongodb.ConstantesMongo.DATABASE;

import java.util.Collection;
import java.util.HashSet;

import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.repository.mongodb.ConsultaMongoRepository;

public class EmpresaByEmailRepositoryImplementation extends ConsultaMongoRepository<Empresa> implements EmpresaByEmailRepository {
		
	public EmpresaByEmailRepositoryImplementation() {
		super(DATABASE, COLLECTION);
	}

	@Override
	public Collection<Empresa> findEmpresasByEnderecoEmail(String endereco) {
		Collection<Empresa> empresasFiltradas = new HashSet<>();
		for (Empresa empresa : super.findAll()) {
			for (Email email : empresa.getEmails()) {
				if (email.getEndereco().equals(endereco)) {
					empresasFiltradas.add(empresa);
				}
			}
		}
		return empresasFiltradas;
	}

	@Override
	public Collection<Empresa> findEmpresasByEmail(Email email) {
		Collection<Empresa> empresasFiltradas = new HashSet<>();
		for (Empresa empresa : super.findAll()) {
			if (empresa.getEmails().contains(email)) {
				empresasFiltradas.add(empresa);
			}
		}
		return empresasFiltradas;
	}	

}
