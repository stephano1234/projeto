package br.com.contmatic.repository.configuracao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.repository.configuracao.MongoConnection;

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
	public void verifica_a_unicidade_da_instancia_da_classe() {
		MongoConnection mongoConnection1 = MongoConnection.getInstance();
		MongoConnection mongoConnection2 = MongoConnection.getInstance();
		assertSame(mongoConnection1, mongoConnection2);
	}
	
	@Test
	public void verifica_a_unicidade_da_instancia_de_conexao_com_o_mongodb() {
		MongoClient mongoClient1 = MongoConnection.getInstance().getMongoClient();
		MongoClient mongoClient2 = MongoConnection.getInstance().getMongoClient();
		assertSame(mongoClient1, mongoClient2);
	}

	@Test
	public void verifica_a_unicidade_da_instancia_de_database_do_mongodb() {
		MongoDatabase mongoDatabase1 = MongoConnection.getInstance().getMongoDatabase();
		MongoDatabase mongoDatabase2 = MongoConnection.getInstance().getMongoDatabase();
		assertSame(mongoDatabase1, mongoDatabase2);
	}	

	@Test
	public void deve_mudar_a_instancia_de_conexao_apos_fechar_e_abrir_as_conexoes_com_o_mongodb() {
		MongoClient mongoClient1 = MongoConnection.getInstance().getMongoClient();
		MongoConnection.getInstance().close();
		MongoClient mongoClient2 = MongoConnection.getInstance().getMongoClient();
		assertNotSame(mongoClient1, mongoClient2);
	}

	@Test
	public void deve_mudar_a_instancia_de_database_apos_fechar_e_abrir_as_conexoes_com_o_mongodb() {
		MongoDatabase mongoDatabase1 = MongoConnection.getInstance().getMongoDatabase();
		MongoConnection.getInstance().close();
		MongoDatabase mongoDatabase2 = MongoConnection.getInstance().getMongoDatabase();
		assertNotSame(mongoDatabase1, mongoDatabase2);
	}

	@Test
	public void nao_deve_gerar_exeption_se_close_for_usado_antes_de_getMongoClient_ou_depois_de_outro_close() {
		try {
			MongoConnection.getInstance().close();
			MongoConnection.getInstance().getMongoClient();
			MongoConnection.getInstance().close();
			MongoConnection.getInstance().close();
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void deve_abrir_a_conexao_na_porta_27017_e_hostname_localhost() {
		MongoClient mongoClient = MongoConnection.getInstance().getMongoClient();
		assertEquals("localhost", mongoClient.getAddress().getHost());
		assertEquals(27017, mongoClient.getAddress().getPort());
	}

	@Test
	public void deve_abrir_acessar_a_database_projeto() {
		MongoDatabase mongoDatabase = MongoConnection.getInstance().getMongoDatabase();
		assertEquals("projeto", mongoDatabase.getName());
	}

}
