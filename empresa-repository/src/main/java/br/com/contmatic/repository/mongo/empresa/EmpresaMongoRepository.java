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

	public List<Empresa> readCnpjAndRazaoSocialByRazaoSocial(String razaoSocial);
	
	public List<Empresa> readCnpjAndRazaoSocialByResponsavelCpf(String cpf);

	public List<Empresa> readCnpjAndRazaoSocialByTipoEmpresa(TipoEmpresa tipoEmpresa);
	
	public List<Empresa> readCnpjAndRazaoSocialByTipoPorteEmpresa(TipoPorteEmpresa tipoPorteEmpresa);
	
	public List<Empresa> readAllCnpjAndRazaoSocial(int pageSize);
	
	public long countAll();
	
}
