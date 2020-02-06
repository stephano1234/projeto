package br.com.contmatic.repository.mongo.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;
import br.com.contmatic.repository.configuracao.mongodb.MongoConnection;

public class PopulateEmpresaMongoCollection {

	private EmpresaMongoRepository empresaMongoRepository;

	private static final EmpresaRandomBuilder empresaRandomBuilder = EmpresaRandomBuilder.getInstance();

	@Test
	public void popula_collection_empresa_com_20000_dados_validos_e_com_cnpjs_unicos() {
		try {
			Empresa empresa = null;
			empresaMongoRepository = EmpresaMongoRepositoryImpl.getInstance(MongoConnection.getInstance().getMongoDatabase());
			int i = 0;
			while (i < 20000) {
				empresa = empresaRandomBuilder.build();
				if (empresaMongoRepository.readByCnpj(empresa.getCnpj()) == null) {
					empresaMongoRepository.create(empresa);
					i++;
					System.out.println(i + "º registro armazenado");
					empresa = null;
				}
			}
			assertEquals(20000l, empresaMongoRepository.countAll());
		} finally {
			EmpresaMongoRepositoryImpl.closeRepository();
			MongoConnection.getInstance().close();
		}
	}

	@Test
	public void zera_database_projeto() {
		try {
			MongoConnection.getInstance().getMongoDatabase().drop();
			empresaMongoRepository = EmpresaMongoRepositoryImpl.getInstance(MongoConnection.getInstance().getMongoDatabase());
			assertTrue(empresaMongoRepository.countAll() == 0l);
		} finally {
			EmpresaMongoRepositoryImpl.closeRepository();
			MongoConnection.getInstance().close();
		}
	}

}
