package br.com.contmatic.service.empresa;

import java.util.List;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.repository.configuracao.mongodb.MongoConnection;
import br.com.contmatic.repository.mongo.empresa.EmpresaMongoRepository;
import br.com.contmatic.repository.mongo.empresa.EmpresaMongoRepositoryImpl;
import br.com.contmatic.service.parametros.FindParams;

public class EmpresaService {
	
	private EmpresaMongoRepository empresaMongoRepository = EmpresaMongoRepositoryImpl.getInstance(MongoConnection.getInstance().getMongoDatabase());
	
	private static EmpresaService instance;
	
	private EmpresaService() {
	}
	
	public static EmpresaService getInstance() {
		if (instance == null) {
			instance = new EmpresaService();
		}
		return instance;
	}
	
	public List<Empresa> findByParams(FindParams params) {
		return empresaMongoRepository.findByParams(params.getFilter(), params.getSort(), params.getProjection(), params.getPageOffset(), params.getPageSize());
	}

	public long countByParams(FindParams params) {
		return empresaMongoRepository.countByParams(params.getFilter());
	}

	public void update(Empresa empresa) {
		empresaMongoRepository.update(empresa.getCnpj(), empresa);
	}
	
	public void delete(Empresa empresa) {
		empresaMongoRepository.delete(empresa.getCnpj());
	}

	public void create(Empresa empresa) {
		empresaMongoRepository.create(empresa);
	}

}
