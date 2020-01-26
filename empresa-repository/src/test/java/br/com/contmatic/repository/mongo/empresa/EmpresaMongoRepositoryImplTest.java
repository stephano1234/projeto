package br.com.contmatic.repository.mongo.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;
import br.com.contmatic.model.random.pessoa.PessoaRandomBuilder;
import br.com.contmatic.repository.mongo.conversor.empresa.EmpresaConversorMongo;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class EmpresaMongoRepositoryImplTest {
	
	private static final MongodStarter mongodStarter = MongodStarter.getDefaultInstance();

	private MongodExecutable mongodExecutable;
	
	private MongodProcess mongodProcess;
	
	private MongoClient mongoClient;
	
	private MongoDatabase mongoDatabase;
	
	private MongoCollection<Document> mongoCollection;
	
	private EmpresaMongoRepository empresaMongoRepository;
	
	private EmpresaConversorMongo empresaConversorMongo = new EmpresaConversorMongo();
	
	private EmpresaRandomBuilder empresaRandomBuilder = new EmpresaRandomBuilder();
	
	private PessoaRandomBuilder pessoaRandomBuilder = new PessoaRandomBuilder();
	
	@Before
	public void setUp() throws Exception {
		mongodExecutable = mongodStarter.prepare(new MongodConfigBuilder().version(Version.Main.PRODUCTION).net(new Net("localhost", 12345, Network.localhostIsIPv6())).build());
		mongodProcess = mongodExecutable.start();
		mongoClient = new MongoClient("localhost", 12345);
		mongoDatabase = mongoClient.getDatabase("projeto");
		empresaMongoRepository = new EmpresaMongoRepositoryImpl(mongoDatabase);
		mongoCollection = mongoDatabase.getCollection("empresa");
	}

	@After
	public void tearDown() throws Exception {
		mongodProcess.stop();
		mongodExecutable.stop();
	}

	@Test
	public void deve_salvar_uma_empresa_mantendo_a_integridade_dos_dados_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresa = empresaRandomBuilder.buildValido();
		empresaMongoRepository.create(empresa);
		Document actualDocument = mongoCollection.find().first();
		actualDocument.remove("_id");
		Document expectedDocument = empresaConversorMongo.empresaToDocument(empresa);
		assertEquals(expectedDocument, actualDocument);
	}
	
	@Test
	public void deve_deletar_a_empresa_especificada_pelo_cnpj_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresa = empresaRandomBuilder.buildValido();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		empresaMongoRepository.delete(empresa.getCnpj());
		assertTrue(mongoCollection.countDocuments() == 0l);
	}

	@Test
	public void quando_deletar_nao_deve_alterar_a_collection_empresa_de_uma_database_do_mongodb_caso_nao_haja_o_cnpj_especificado_na_mesma() {
		
	}
	
	@Test
	public void ao_substituir_pelo_cnpj_uma_empresa_na_collection_empresa_de_uma_database_do_mongodb_deve_desaparecer_o_registro_da_empresa_desatualizada_caso_sejam_diferentes() {
		Empresa empresaDesatualizada = empresaRandomBuilder.buildValido();
		Empresa empresaAtualizada = empresaRandomBuilder.buildValido();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaDesatualizada));
		empresaMongoRepository.update(empresaDesatualizada.getCnpj(), empresaAtualizada);
		Document actualDocument = mongoCollection.find().first();
		actualDocument.remove("_id");
		Document expectedDocument = empresaConversorMongo.empresaToDocument(empresaAtualizada);
		assertEquals(expectedDocument, actualDocument);
	}

	@Test
	public void ao_substituir_pelo_cnpj_uma_empresa_na_collection_empresa_de_uma_database_do_mongodb_deve_armazenar_o_registro_da_empresa_atualizada() {
		
	}

	@Test
	public void ao_substituir_pelo_cnpj_uma_empresa_na_collection_empresa_de_uma_database_do_mongodb_nao_deve_alterar_a_collection_caso_as_empresas_tenham_todos_os_campos_iguais() {
		
	}	
	
	@Test
	public void quando_substituir_procurando_pelo_cnpj_nao_deve_alterar_a_collection_empresa_de_uma_database_do_mongodb_caso_nao_haja_o_cnpj_especificado_na_mesma() {
		
	}

	@Test
	public void deve_ler_uma_empresa_integralmente_pelo_seu_cnpj_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresaPassaFiltro = empresaRandomBuilder.buildValido();
		String filtro = empresaPassaFiltro.getCnpj();
		Document expectedDocument = empresaConversorMongo.empresaToDocument(empresaPassaFiltro);
		mongoCollection.insertOne(expectedDocument);
		expectedDocument.remove("_id");
		for (int i = 0; i < 10; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.buildValido();
			while (empresaNaoPassaFiltro.getCnpj().equals(empresaPassaFiltro.getCnpj())) {
				empresaNaoPassaFiltro = empresaRandomBuilder.buildValido();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
		}
		Empresa empresaLida = empresaMongoRepository.readByCnpj(filtro);
		Document actualDocument = empresaConversorMongo.empresaToDocument(empresaLida);
		assertEquals(expectedDocument, actualDocument);
	}

	@Test
	public void deve_trazer_valor_nulo_caso_nao_haja_empresa_com_cnpj_procurado_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresa = empresaRandomBuilder.buildValido();
		String cnpjProcurado = empresa.getCnpj();
		for (int i = 0; i < 10; i++) {
			while (empresa.getCnpj().equals(cnpjProcurado)) {
				empresa = empresaRandomBuilder.buildValido();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		}
		assertNull(empresaMongoRepository.readByCnpj(cnpjProcurado));		
	}
	
	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_razaoSocial_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresaPassaFiltro = empresaRandomBuilder.buildValido();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
		String razaoSocialFiltro = empresaPassaFiltro.getRazaoSocial();
		List<Empresa> expectedEmpresas = new ArrayList<>();
		expectedEmpresas.add(empresaPassaFiltro);
		for (int i = 0; i < 10; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.buildValido();
			while (empresaNaoPassaFiltro.getRazaoSocial().equals(razaoSocialFiltro)) {
				empresaNaoPassaFiltro = empresaRandomBuilder.buildValido();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.readCnpjAndRazaoSocialByRazaoSocial(razaoSocialFiltro);
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
		}
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_com_razaoSocial_procurada_na_collection_empresa_de_uma_database_do_mongodb() {
		
	}
	
	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_cpf_dos_elementos_do_campo_responsaveis_na_collection_empresa_de_uma_database_do_mongodb() {
		Pessoa responsavelFiltro = pessoaRandomBuilder.buildValido();
		String cpfFiltro = responsavelFiltro.getCpf();
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Empresa empresaPassaFiltro = empresaRandomBuilder.buildValido();
			empresaPassaFiltro.getResponsaveis().add(responsavelFiltro);
			expectedEmpresas.add(empresaPassaFiltro);
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
		}
		for (int i = 0; i < 10; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.buildValido();
			while (empresaNaoPassaFiltro.getResponsaveis().contains(responsavelFiltro)) {
				empresaNaoPassaFiltro = empresaRandomBuilder.buildValido();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));			
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.readCnpjAndRazaoSocialByResponsavelCpf(cpfFiltro);
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
		}
	}
	
	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_com_cpf_dos_elementos_do_campo_responsaveis_procurada_na_collection_empresa_de_uma_database_do_mongodb() {
		
	}

	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_tipoEmpresa_na_collection_empresa_de_uma_database_do_mongodb() {
		TipoEmpresa filtro = TipoEmpresa.values()[RandomUtils.nextInt(0, TipoEmpresa.values().length)];
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Empresa empresa = empresaRandomBuilder.buildValido();
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
			if (empresa.getTipoEmpresa().equals(filtro)) {
				expectedEmpresas.add(empresa);
			}		
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.readCnpjAndRazaoSocialByTipoEmpresa(filtro);
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
		}
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_com_tipoEmpresa_procurada_na_collection_empresa_de_uma_database_do_mongodb() {
		
	}

	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_tipoPorteEmpresa_na_collection_empresa_de_uma_database_do_mongodb() {
		TipoPorteEmpresa filtro = TipoPorteEmpresa.values()[RandomUtils.nextInt(0, TipoPorteEmpresa.values().length)];
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Empresa empresa = empresaRandomBuilder.buildValido();
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
			if (empresa.getTipoPorteEmpresa().equals(filtro)) {
				expectedEmpresas.add(empresa);
			}		
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.readCnpjAndRazaoSocialByTipoPorteEmpresa(filtro);
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
		}
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_com_tipoPorteEmpresa_procurada_na_collection_empresa_de_uma_database_do_mongodb() {
		
	}

	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_de_todas_as_empresas_armazenadas_na_collection_empresa_de_uma_database_do_mongodb() {
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Empresa empresa = empresaRandomBuilder.buildValido();
			expectedEmpresas.add(empresa);
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));	
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.readAllCnpjAndRazaoSocial();
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
		}
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_na_collection_empresa_de_uma_database_do_mongodb() {
		
	}

	@Test
	public void deve_trazer_o_numero_de_empresas_armazenadas_na_collection_empresa_de_uma_database_do_mongodb() {
		long quantidadeEsperada = RandomUtils.nextLong(0, 11);
		for (long i = 0; i < quantidadeEsperada; i++) {
			Empresa empresa = empresaRandomBuilder.buildValido();
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		}
		long quantidadeComputada = empresaMongoRepository.countAll();
		assertEquals(quantidadeEsperada, quantidadeComputada);
	}
	
	@Test
	public void deve_trazer_zero_caso_nao_haja_empresa_armazenadas_na_collection_empresa_de_uma_database_do_mongodb() {
		
	}
	
}
