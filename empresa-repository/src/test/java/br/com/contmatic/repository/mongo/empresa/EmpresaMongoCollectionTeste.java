package br.com.contmatic.repository.mongo.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.bson.Document;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;
import br.com.contmatic.repository.configuracao.mongodb.MongoConnection;

public class EmpresaMongoCollectionTeste {

	private static EmpresaMongoRepository empresaMongoRepository;

	private static EmpresaRandomBuilder empresaRandomBuilder;

	@BeforeClass
	public static void setUpBeforeClass() {
		empresaMongoRepository = EmpresaMongoRepositoryImpl.getInstance(MongoConnection.getInstance().getMongoDatabase());
		empresaRandomBuilder = EmpresaRandomBuilder.getInstance();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		EmpresaRandomBuilder.closeBuilder();
		EmpresaMongoRepositoryImpl.closeRepository();
	}
	
	@Test
	public void popula_collection_empresa_com_n_dados_validos_e_com_cnpjs_unicos() {
		try {
			long n = 500;
			Empresa empresa = null;
			long i = 0;
			while (i < n) {
				empresa = empresaRandomBuilder.build();
				//if (empresaMongoRepository.findOne(empresa.getCnpj()) == null) {
					empresaMongoRepository.create(empresa);
					i++;
					System.out.println(i + "º registro armazenado");
					empresa = null;
				//}
			}
			assertEquals(n, empresaMongoRepository.countByParams(new Document()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			MongoConnection.getInstance().close();
		}
	}

	@Test
	public void zera_database_projeto() {
		try {
			MongoConnection.getInstance().getMongoDatabase().drop();
			assertEquals(0l, empresaMongoRepository.countByParams(new Document()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			MongoConnection.getInstance().close();
		}
	}

}
