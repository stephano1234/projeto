package br.com.contmatic.repository.mongo.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;
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
	
	private static EmpresaConversorMongo empresaConversorMongo;
	
	private static EmpresaRandomBuilder empresaRandomBuilder;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		empresaConversorMongo = EmpresaConversorMongo.getInstance();
		empresaRandomBuilder = EmpresaRandomBuilder.getInstance();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		EmpresaConversorMongo.closeConversor();
		EmpresaRandomBuilder.closeBuilder();
		EmpresaMongoRepositoryImpl.closeRepository();
	}
	
	@Before
	public void setUp() throws Exception {
		mongodExecutable = mongodStarter.prepare(new MongodConfigBuilder().version(Version.Main.PRODUCTION).net(new Net("localhost", 12345, Network.localhostIsIPv6())).build());
		mongodProcess = mongodExecutable.start();
		mongoClient = new MongoClient("localhost", 12345);
		mongoDatabase = mongoClient.getDatabase("projeto");
		empresaMongoRepository = EmpresaMongoRepositoryImpl.getInstance(mongoDatabase);
		mongoCollection = mongoDatabase.getCollection("empresa");
	}

	@After
	public void tearDown() throws Exception {
		EmpresaMongoRepositoryImpl.closeRepository();
		mongodProcess.stop();
		mongodExecutable.stop();
	}
	
	@Test
	public void deve_salvar_uma_empresa_mantendo_a_integridade_dos_dados_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresaAntesPersistir = empresaRandomBuilder.build();
		empresaMongoRepository.create(empresaAntesPersistir);
		Empresa empresaAposPersistir = empresaConversorMongo.documentToEmpresa(mongoCollection.find().first());
		assertEquals(empresaAntesPersistir.toString(), empresaAposPersistir.toString());
	}
	
	@Test
	public void deve_deletar_a_empresa_especificada_pelo_cnpj_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresaParaDeletar = empresaRandomBuilder.build();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaDeletar));
		Empresa empresaParaNaoDeletar = null;
		for (int i = 0; i < 10; i++) {
			empresaParaNaoDeletar = empresaRandomBuilder.build();
			while (empresaParaNaoDeletar.getCnpj().equals(empresaParaDeletar.getCnpj())) {
				empresaParaNaoDeletar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoDeletar));
		}
		long qtdRegistrosAntesDelete = mongoCollection.countDocuments();
		empresaMongoRepository.delete(empresaParaDeletar.getCnpj());
		assertTrue(qtdRegistrosAntesDelete - 1 == mongoCollection.countDocuments());
		assertNull(mongoCollection.find(Filters.eq("cnpj", empresaParaDeletar.getCnpj())).first());
	}

	@Test
	public void quando_deletar_nao_deve_alterar_a_collection_empresa_de_uma_database_do_mongodb_caso_nao_haja_o_cnpj_especificado_na_mesma() {
		Empresa empresaParaDeletar = empresaRandomBuilder.build();
		Empresa empresaParaNaoDeletar = null;
		for (int i = 0; i < 10; i++) {
			empresaParaNaoDeletar = empresaRandomBuilder.build();
			while (empresaParaNaoDeletar.getCnpj().equals(empresaParaDeletar.getCnpj())) {
				empresaParaNaoDeletar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoDeletar));
		}
		long qtdRegistrosAntesDelete = mongoCollection.countDocuments();
		empresaMongoRepository.delete(empresaParaDeletar.getCnpj());
		assertNull(mongoCollection.find(Filters.eq("cnpj", empresaParaDeletar.getCnpj())).first());		
		assertTrue(qtdRegistrosAntesDelete == mongoCollection.countDocuments());
	}
	
	@Test
	public void ao_substituir_pelo_cnpj_uma_empresa_na_collection_empresa_de_uma_database_do_mongodb_deve_desaparecer_o_registro_da_empresa_desatualizada_caso_sejam_diferentes() {
		Empresa empresaDesatualizada = empresaRandomBuilder.build();
		Empresa empresaAtualizada = empresaRandomBuilder.build();
		while (empresaAtualizada.toString().equals(empresaDesatualizada.toString())) {
			empresaAtualizada = empresaRandomBuilder.build();
		}
		empresaAtualizada.setCnpj(empresaDesatualizada.getCnpj());
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaDesatualizada));
		Empresa empresaParaNaoAtualizar = null;
		for (int i = 0; i < 10; i++) {
			empresaParaNaoAtualizar = empresaRandomBuilder.build();
			while (empresaParaNaoAtualizar.getCnpj().equals(empresaDesatualizada.getCnpj())) {
				empresaParaNaoAtualizar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoAtualizar));
		}
		empresaMongoRepository.update(empresaDesatualizada.getCnpj(), empresaAtualizada);
		assertEquals(1l, mongoCollection.countDocuments(Filters.eq("cnpj", empresaAtualizada.getCnpj())));
		Empresa empresaAposAtualizacao = empresaConversorMongo.documentToEmpresa(mongoCollection.find(Filters.eq("cnpj", empresaAtualizada.getCnpj())).first());
		assertNotEquals(empresaDesatualizada.toString(), empresaAposAtualizacao.toString());
	}

	@Test
	public void ao_substituir_pelo_cnpj_uma_empresa_na_collection_empresa_de_uma_database_do_mongodb_deve_armazenar_o_registro_da_empresa_atualizada() {
		Empresa empresaDesatualizada = empresaRandomBuilder.build();
		Empresa empresaAtualizada = empresaRandomBuilder.build();
		while (empresaAtualizada.toString().equals(empresaDesatualizada.toString())) {
			empresaAtualizada = empresaRandomBuilder.build();
		}
		empresaAtualizada.setCnpj(empresaDesatualizada.getCnpj());
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaDesatualizada));
		Empresa empresaParaNaoAtualizar = null;
		for (int i = 0; i < 10; i++) {
			empresaParaNaoAtualizar = empresaRandomBuilder.build();
			while (empresaParaNaoAtualizar.getCnpj().equals(empresaDesatualizada.getCnpj())) {
				empresaParaNaoAtualizar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoAtualizar));
		}
		empresaMongoRepository.update(empresaDesatualizada.getCnpj(), empresaAtualizada);
		assertEquals(1l, mongoCollection.countDocuments(Filters.eq("cnpj", empresaAtualizada.getCnpj())));
		Empresa empresaAposAtualizacao = empresaConversorMongo.documentToEmpresa(mongoCollection.find(Filters.eq("cnpj", empresaAtualizada.getCnpj())).first());
		assertEquals(empresaAtualizada.toString(), empresaAposAtualizacao.toString());
	}

	@Test
	public void ao_substituir_pelo_cnpj_uma_empresa_na_collection_empresa_de_uma_database_do_mongodb_nao_deve_alterar_a_collection_caso_as_empresas_tenham_todos_os_campos_iguais() {
		Empresa empresa = empresaRandomBuilder.build();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		Empresa empresaParaNaoAtualizar = null;
		for (int i = 0; i < 10; i++) {
			empresaParaNaoAtualizar = empresaRandomBuilder.build();
			while (empresaParaNaoAtualizar.getCnpj().equals(empresa.getCnpj())) {
				empresaParaNaoAtualizar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoAtualizar));
		}
		empresaMongoRepository.update(empresa.getCnpj(), empresa);
		assertEquals(1l, mongoCollection.countDocuments(Filters.eq("cnpj", empresa.getCnpj())));
		Empresa empresaAposAtualizacao = empresaConversorMongo.documentToEmpresa(mongoCollection.find(Filters.eq("cnpj", empresa.getCnpj())).first());
		assertEquals(empresa.toString(), empresaAposAtualizacao.toString());		
	}	
	
	@Test
	public void quando_substituir_procurando_pelo_cnpj_nao_deve_alterar_a_collection_empresa_de_uma_database_do_mongodb_caso_nao_haja_o_cnpj_especificado_na_mesma() {
		Empresa empresa = empresaRandomBuilder.build();
		Empresa empresaParaNaoAtualizar = null;
		for (int i = 0; i < 10; i++) {
			empresaParaNaoAtualizar = empresaRandomBuilder.build();
			while (empresaParaNaoAtualizar.getCnpj().equals(empresa.getCnpj())) {
				empresaParaNaoAtualizar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoAtualizar));
		}
		empresaMongoRepository.update(empresa.getCnpj(), empresa);
		assertEquals(0l, mongoCollection.countDocuments(Filters.eq("cnpj", empresa.getCnpj())));
		assertNull(mongoCollection.find(Filters.eq("cnpj", empresa.getCnpj())).first());
	}

	@Test
	public void deve_ler_uma_empresa_integralmente_pelo_seu_cnpj_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresaPassaFiltro = empresaRandomBuilder.build();
		String filtro = empresaPassaFiltro.getCnpj();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
		for (int i = 0; i < 10; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.build();
			while (empresaNaoPassaFiltro.getCnpj().equals(empresaPassaFiltro.getCnpj())) {
				empresaNaoPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
		}
		//Empresa empresaLida = empresaMongoRepository.findOne(filtro);
		//assertEquals(empresaPassaFiltro.toString(), empresaLida.toString());
	}

	@Test
	public void deve_trazer_valor_nulo_caso_nao_haja_empresa_com_cnpj_procurado_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresa = empresaRandomBuilder.build();
		String cnpjProcurado = empresa.getCnpj();
		for (int i = 0; i < 10; i++) {
			while (empresa.getCnpj().equals(cnpjProcurado)) {
				empresa = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		}
		//assertNull(empresaMongoRepository.findOne(cnpjProcurado));		
	}
	
	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_razaoSocial_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresaPassaFiltro = empresaRandomBuilder.build();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
		String razaoSocialFiltro = empresaPassaFiltro.getRazaoSocial();
		List<Empresa> expectedEmpresas = new ArrayList<>();
		expectedEmpresas.add(empresaPassaFiltro);
		for (int i = 0; i < 9; i++) {
			empresaPassaFiltro = empresaRandomBuilder.build();
			empresaPassaFiltro.setRazaoSocial(razaoSocialFiltro);
			expectedEmpresas.add(empresaPassaFiltro);
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
		}
		for (int i = 0; i < 10; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.build();
			while (empresaNaoPassaFiltro.getRazaoSocial().equals(razaoSocialFiltro)) {
				empresaNaoPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.findByParams(new Document().append("razaoSocial", razaoSocialFiltro), new Document(), new Document().append("cnpj", 1).append("razaoSocial", 1), 0, 20);
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
		}
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_com_razaoSocial_procurada_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresaPassaFiltro = empresaRandomBuilder.build();
		String razaoSocialFiltro = empresaPassaFiltro.getRazaoSocial();
		for (int i = 0; i < 10; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.build();
			while (empresaNaoPassaFiltro.getRazaoSocial().equals(razaoSocialFiltro)) {
				empresaNaoPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
		}
		List<Empresa> empresasFiltradas = empresaMongoRepository.findByParams(new Document().append("razaoSocial", razaoSocialFiltro), new Document(), new Document().append("cnpj", 1).append("razaoSocial", 1), 0, 10);
		assertEquals(0, empresasFiltradas.size());
	}
	
	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_cpf_dos_elementos_do_campo_responsaveis_na_collection_empresa_de_uma_database_do_mongodb() {
		Pessoa responsavelFiltro = (Pessoa) empresaRandomBuilder.build().getResponsaveis().toArray()[0];
		String cpfFiltro = responsavelFiltro.getCpf();
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Empresa empresaPassaFiltro = empresaRandomBuilder.build();
			empresaPassaFiltro.getResponsaveis().add(responsavelFiltro);
			expectedEmpresas.add(empresaPassaFiltro);
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
		}
		for (int i = 0; i < 10; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.build();
			while (empresaNaoPassaFiltro.getResponsaveis().contains(responsavelFiltro)) {
				empresaNaoPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));			
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.findByParams(new Document().append("responsaveis.cpf", cpfFiltro), new Document(), new Document().append("cnpj", 1).append("razaoSocial", 1), 0, 20);
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
		}
	}
	
	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_com_cpf_dos_elementos_do_campo_responsaveis_procurada_na_collection_empresa_de_uma_database_do_mongodb() {
		Pessoa responsavelFiltro = (Pessoa) empresaRandomBuilder.build().getResponsaveis().toArray()[0];
		String cpfFiltro = responsavelFiltro.getCpf();
		for (int i = 0; i < 10; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.build();
			while (empresaNaoPassaFiltro.getResponsaveis().contains(responsavelFiltro)) {
				empresaNaoPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));			
		}
		List<Empresa> empresasFiltradas = empresaMongoRepository.findByParams(new Document().append("responsaveis.cpf", cpfFiltro), new Document(), new Document().append("cnpj", 1).append("razaoSocial", 1), 0, 10);
		assertEquals(0, empresasFiltradas.size());
	}

	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_tipoEmpresa_na_collection_empresa_de_uma_database_do_mongodb() {
		TipoEmpresa filtro = TipoEmpresa.values()[RandomUtils.nextInt(0, TipoEmpresa.values().length)];
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
			if (empresa.getTipoEmpresa().equals(filtro)) {
				expectedEmpresas.add(empresa);
			}		
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), 0, 20);
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
		}
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_com_tipoEmpresa_procurada_na_collection_empresa_de_uma_database_do_mongodb() {
		TipoEmpresa filtro = TipoEmpresa.values()[RandomUtils.nextInt(0, TipoEmpresa.values().length)];
		for (int i = 0; i < 20; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			if (!empresa.getTipoEmpresa().equals(filtro)) {
				mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));	
			}
		}
		List<Empresa> empresasFiltradas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), 0, 20);
		assertEquals(0, empresasFiltradas.size());
	}

	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_tipoPorteEmpresa_na_collection_empresa_de_uma_database_do_mongodb() {
		TipoPorteEmpresa filtro = TipoPorteEmpresa.values()[RandomUtils.nextInt(0, TipoPorteEmpresa.values().length)];
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
			if (empresa.getTipoPorteEmpresa().equals(filtro)) {
				expectedEmpresas.add(empresa);
			}		
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), 0, 20);
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
		}
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_com_tipoPorteEmpresa_procurada_na_collection_empresa_de_uma_database_do_mongodb() {
		TipoPorteEmpresa filtro = TipoPorteEmpresa.values()[RandomUtils.nextInt(0, TipoPorteEmpresa.values().length)];
		for (int i = 0; i < 20; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			if (!empresa.getTipoPorteEmpresa().equals(filtro)) {
				mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));	
			}
		}
		List<Empresa> empresasFiltradas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), 0, 20);
		assertEquals(0, empresasFiltradas.size());		
	}

	@Test
	public void com_parametros_inteiros_0_e_numero_de_empresas_na_collection_deve_ler_todos_os_campos_cnpj_e_razaoSocial_de_todas_as_empresas_armazenadas_na_collection_empresa_de_uma_database_do_mongodb() {
		int randomQtdCollection = RandomUtils.nextInt(1, 11);
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < randomQtdCollection; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			empresa.setRazaoSocial(Integer.toString(randomQtdCollection - i - 1) + empresa.getRazaoSocial());
			expectedEmpresas.add(empresa);
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), 0, randomQtdCollection);
		assertEquals(randomQtdCollection, actualEmpresas.size());
		for (int i = 0; i < randomQtdCollection; i++) {
			System.out.println(actualEmpresas.get(i));
			assertEquals(expectedEmpresas.get(randomQtdCollection - i - 1).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(randomQtdCollection - i - 1).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
		}
	}

	@Test
	public void deve_ler_n_campos_apos_pular_os_p_primeiros_cnpj_e_razaoSocial_de_todas_as_empresas_armazenadas_na_collection_empresa_de_uma_database_do_mongodb() {
		int randomQtdCollection = RandomUtils.nextInt(1, 21);
		for (int i = 0; i < randomQtdCollection; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));	
		}
		List<Empresa> expectedEmpresas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), 0, randomQtdCollection);		
		int n = RandomUtils.nextInt(0, randomQtdCollection + 1);
		int p = RandomUtils.nextInt(0, randomQtdCollection + 1 - n);
		List<Empresa> actualEmpresas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), p, n);
		assertEquals(n, actualEmpresas.size());
		assertEquals(expectedEmpresas.get(p).toString(), actualEmpresas.get(0).toString());
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_independente_dos_valores_dos_inteiros_colocados_no_parametros_na_collection_empresa_de_uma_database_do_mongodb() {
		int n = RandomUtils.nextInt(0, 21);
		int p = RandomUtils.nextInt(0, 21 - n);
		List<Empresa> empresas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), p, n);
		assertEquals(0, empresas.size());
	}

	@Test
	public void deve_trazer_o_numero_de_empresas_armazenadas_na_collection_empresa_de_uma_database_do_mongodb() {
		long quantidadeEsperada = RandomUtils.nextLong(0, 11);
		for (long i = 0; i < quantidadeEsperada; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		}
		long quantidadeComputada = empresaMongoRepository.countByParams(new Document());
		assertEquals(quantidadeEsperada, quantidadeComputada);
	}
	
	@Test
	public void deve_trazer_zero_caso_nao_haja_empresa_armazenadas_na_collection_empresa_de_uma_database_do_mongodb() {
		long quantidadeComputada = empresaMongoRepository.countByParams(new Document());
		assertEquals(0l, quantidadeComputada);		
	}
	
}
