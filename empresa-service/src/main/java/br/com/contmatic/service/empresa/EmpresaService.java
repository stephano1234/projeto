package br.com.contmatic.service.empresa;

import java.util.List;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.repository.configuracao.mongodb.MongoConnection;
import br.com.contmatic.repository.mongo.empresa.EmpresaMongoRepository;
import br.com.contmatic.repository.mongo.empresa.EmpresaMongoRepositoryImpl;

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
	
	public List<Empresa> findAll(int pageSize) {
		return empresaMongoRepository.readAllCnpjAndRazaoSocial(pageSize);
	}
	
}
