package br.com.contmatic.repository.empresa.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.repository.empresa.EmpresaRepository;

public class EmpresaRepositoryMock implements EmpresaRepository {

	public static final String CNPJ_MOCK = "46149614000130";

	private static final Logger logger = Logger.getLogger(EmpresaRepositoryMock.class.getName());
	
	private static EmpresaRepositoryMock instance;

	public static EmpresaRepositoryMock getInstance() {
		if (instance == null) {
			instance = new EmpresaRepositoryMock();
		}
		return instance;
	}

	@Override
	public void create(Empresa empresa) {
		logger.log(Level.INFO, "Criou.");
	}

	@Override
	public void delete(String cnpj) {
		if (cnpj.equals(CNPJ_MOCK)) {
			logger.log(Level.INFO, "Apagou.");	
		}
		logger.log(Level.INFO, "Não apagou.");
	}

	@Override
	public void update(String cnpj, Empresa empresa) {
		if (cnpj.equals(CNPJ_MOCK)) {
			logger.log(Level.INFO, "Atualizou.");	
		}
		logger.log(Level.INFO, "Não atualizou.");
	}

	@Override
	public Empresa findByCnpj(String cnpj) {
		if (cnpj.equals(CNPJ_MOCK)) {
			return new Empresa();			
		}
		return null;
	}

	@Override
	public List<Empresa> findByParams(Document filter, Document sort, Document projection, int offset, int size) {
		List<Empresa> listEmpresasMock = new ArrayList<>();
		listEmpresasMock.add(new Empresa());
		return listEmpresasMock;
	}

	@Override
	public long countByParams(Document filter) {
		return 1;
	}

	@Override
	public boolean isExistent(String cnpj) {
		return cnpj.equals(CNPJ_MOCK);
	}

}
