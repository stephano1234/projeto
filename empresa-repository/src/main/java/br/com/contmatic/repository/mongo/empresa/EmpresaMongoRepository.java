package br.com.contmatic.repository.mongo.empresa;

import java.util.List;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;

public interface EmpresaMongoRepository {
	
	public void create(Empresa empresa);
	
	public void delete(String cnpj);
	
	public void update(String cnpj, Empresa empresa);
	
	public Empresa readByCnpj(String cnpj);

	public List<List<String>> readCnpjAndRazaoSocialByRazaoSocial(String razaoSocial);
	
	public List<List<String>> readCnpjAndRazaoSocialByResponsavelCpf(String cpf);

	public List<List<String>> readCnpjAndRazaoSocialByTipoEmpresa(TipoEmpresa tipoEmpresa);
	
	public List<List<String>> readCnpjAndRazaoSocialByTipoPorteEmpresa(TipoPorteEmpresa tipoPorteEmpresa);
	
	public List<List<String>> readAllCnpjAndRazaoSocial();
	
	public long countAll();
	
}
