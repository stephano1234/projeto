package br.com.contmatic.repository.configuracao.mongodb;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnectionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		MongoConnection.getInstance().close();
	}

	@Test
	public void verifica_a_unicidade_da_instancia_de_conexao_com_o_mongodb() {
		MongoClient mongoClient1 = MongoConnection.getInstance().getMongoClient();
		MongoClient mongoClient2 = MongoConnection.getInstance().getMongoClient();
		assertTrue(mongoClient1 == mongoClient2);
	}

	@Test
	public void verifica_a_unicidade_da_instancia_de_database_do_mongodb() {
		MongoDatabase mongoDatabase1 = MongoConnection.getInstance().getMongoDatabase();
		MongoDatabase mongoDatabase2 = MongoConnection.getInstance().getMongoDatabase();
		assertTrue(mongoDatabase1 == mongoDatabase2);
	}	

	@Test
	public void deve_mudar_a_instancia_de_conexao_apos_fechar_e_abrir_as_conexoes_com_o_mongodb() {
		MongoClient mongoClient1 = MongoConnection.getInstance().getMongoClient();
		MongoConnection.getInstance().close();
		MongoClient mongoClient2 = MongoConnection.getInstance().getMongoClient();
		assertFalse(mongoClient1 == mongoClient2);
	}

	@Test
	public void deve_mudar_a_instancia_de_database_apos_fechar_e_abrir_as_conexoes_com_o_mongodb() {
		MongoDatabase mongoDatabase1 = MongoConnection.getInstance().getMongoDatabase();
		MongoConnection.getInstance().close();
		MongoDatabase mongoDatabase2 = MongoConnection.getInstance().getMongoDatabase();
		assertFalse(mongoDatabase1 == mongoDatabase2);
	}

}
