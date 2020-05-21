package br.com.contmatic.repository.empresa;

import java.util.List;

import org.bson.Document;

import br.com.contmatic.model.empresa.Empresa;

public interface EmpresaRepository {
	
	public void create(Empresa empresa);
	
	public void delete(String cnpj);
	
	public void update(String cnpj, Empresa empresa);
	
	public Empresa findByCnpj(String cnpj);
	
	public List<Empresa> findByParams(Document filter, Document sort, Document projection, int offset, int size);
	
	public long countByParams(Document filter);
	
	public boolean isExistent(String cnpj);
	
}
