package br.com.contmatic.repository.mongo.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;
import br.com.contmatic.repository.configuracao.mongodb.MongoConnection;

public class PopulateEmpresaMongoCollection {

	private EmpresaMongoRepository empresaMongoRepository;

	private EmpresaRandomBuilder random = new EmpresaRandomBuilder();

	@Test
	public void popula_collection_empresa_com_20000_dados_validos_e_com_cnpjs_unicos() {
		try {
			Empresa empresa = null;
			empresaMongoRepository = new EmpresaMongoRepositoryImpl(MongoConnection.getInstance().getMongoDatabase());
			int i = 0;
			while (i < 20000) {
				empresa = random.build();
				if (empresaMongoRepository.readByCnpj(empresa.getCnpj()) == null) {
					empresaMongoRepository.create(empresa);
					i++;
					System.out.println(i + "º registro armazenado");
					empresa = null;
				}
			}
			assertEquals(20000l, empresaMongoRepository.countAll());
		} finally {
			MongoConnection.getInstance().close();
		}
	}

	@Test
	public void zera_database_projeto() {
		try {
			MongoConnection.getInstance().getMongoDatabase().drop();
			empresaMongoRepository = new EmpresaMongoRepositoryImpl(MongoConnection.getInstance().getMongoDatabase());
			assertTrue(empresaMongoRepository.countAll() == 0l);
		} finally {
			MongoConnection.getInstance().close();
		}
	}

}
//