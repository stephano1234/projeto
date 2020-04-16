package br.com.contmatic.repository.mongo.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;
import br.com.contmatic.repository.mongo.conversor.empresa.EmpresaConversorMongo;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class EmpresaMongoRepositoryIT {
	
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
	public void deve_salvar_uma_empresa_mantendo_a_integridade_dos_dados() {
		assertEquals(0l, mongoCollection.countDocuments());
		Empresa empresaAntesPersistir = empresaRandomBuilder.build();
		Document docEmpresaAntesPersistir = empresaConversorMongo.empresaToDocument(empresaAntesPersistir);
		empresaMongoRepository.create(empresaAntesPersistir);
		assertEquals(1l, mongoCollection.countDocuments());
		Document docEmpresaAposPersistir = mongoCollection.find().first();
		assertEquals(empresaAntesPersistir.getCnpj(), docEmpresaAposPersistir.get("_id"));
		assertEquals(docEmpresaAntesPersistir, docEmpresaAposPersistir);
	}

	@Test
	public void deve_considerar_o_campo_cnpj_como_chave_primaria() {
		assertEquals(0l, mongoCollection.countDocuments());
		Empresa empresaPersistida = empresaRandomBuilder.build();
		empresaMongoRepository.create(empresaPersistida);
		Document docEmpresaPersistida = mongoCollection.find().first();
		assertEquals(empresaPersistida.getCnpj(), docEmpresaPersistida.get("_id"));
		Empresa empresaPersistidaChaveDuplicada = empresaRandomBuilder.build();
		empresaPersistidaChaveDuplicada.setCnpj(empresaPersistida.getCnpj());
		try {
			empresaMongoRepository.create(empresaPersistidaChaveDuplicada);
			fail();
		} catch (MongoException e) {
			assertNotNull(e);
		} finally {
			assertEquals(1l, mongoCollection.countDocuments());			
		}
	}
	
	@Test
	public void deve_deletar_a_empresa_especificada_pelo_cnpj() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		Empresa empresaParaDeletar = empresaRandomBuilder.build();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaDeletar));
		Empresa empresaParaNaoDeletar = null;
		for (int i = 0; i < 10; i++) {
			empresaParaNaoDeletar = empresaRandomBuilder.build();
			while (empresaParaNaoDeletar.getCnpj().equals(empresaParaDeletar.getCnpj()) || cnpjsCadastrados.contains(empresaParaNaoDeletar.getCnpj())) {
				empresaParaNaoDeletar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoDeletar));
			cnpjsCadastrados.add(empresaParaNaoDeletar.getCnpj());
		}
		long qtdRegistrosAntesDelete = mongoCollection.countDocuments();
		empresaMongoRepository.delete(empresaParaDeletar.getCnpj());
		assertTrue(qtdRegistrosAntesDelete - 1 == mongoCollection.countDocuments());
		assertNull(mongoCollection.find(Filters.eq("_id", empresaParaDeletar.getCnpj())).first());
	}

	@Test
	public void quando_deletar_nao_deve_alterar_a_collection_caso_nao_haja_o_cnpj_especificado_na_mesma() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		Empresa empresaParaDeletar = empresaRandomBuilder.build();
		Empresa empresaParaNaoDeletar = null;
		for (int i = 0; i < 10; i++) {
			empresaParaNaoDeletar = empresaRandomBuilder.build();
			while (empresaParaNaoDeletar.getCnpj().equals(empresaParaDeletar.getCnpj()) || cnpjsCadastrados.contains(empresaParaNaoDeletar.getCnpj())) {
				empresaParaNaoDeletar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoDeletar));
			cnpjsCadastrados.add(empresaParaNaoDeletar.getCnpj());
		}
		long qtdRegistrosAntesDelete = mongoCollection.countDocuments();
		empresaMongoRepository.delete(empresaParaDeletar.getCnpj());
		assertNull(mongoCollection.find(Filters.eq("_id", empresaParaDeletar.getCnpj())).first());		
		assertTrue(qtdRegistrosAntesDelete == mongoCollection.countDocuments());
	}
	
	@Test
	public void ao_substituir_pelo_cnpj_uma_empresa_na_collection_deve_desaparecer_o_registro_da_empresa_desatualizada_caso_sejam_diferentes() {
		List<String> cnpjsCadastrados = new ArrayList<>();
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
			while (empresaParaNaoAtualizar.getCnpj().equals(empresaDesatualizada.getCnpj()) || cnpjsCadastrados.contains(empresaParaNaoAtualizar.getCnpj())) {
				empresaParaNaoAtualizar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoAtualizar));
			cnpjsCadastrados.add(empresaParaNaoAtualizar.getCnpj());
		}
		empresaMongoRepository.update(empresaDesatualizada.getCnpj(), empresaAtualizada);
		assertEquals(1l, mongoCollection.countDocuments(Filters.eq("_id", empresaAtualizada.getCnpj())));
		Empresa empresaAposAtualizacao = empresaConversorMongo.documentToEmpresa(mongoCollection.find(Filters.eq("_id", empresaAtualizada.getCnpj())).first());
		assertNotEquals(empresaDesatualizada.toString(), empresaAposAtualizacao.toString());
	}

	@Test
	public void ao_substituir_pelo_cnpj_uma_empresa_na_collection_deve_armazenar_o_registro_da_empresa_atualizada() {
		List<String> cnpjsCadastrados = new ArrayList<>();
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
			while (empresaParaNaoAtualizar.getCnpj().equals(empresaDesatualizada.getCnpj()) || cnpjsCadastrados.contains(empresaParaNaoAtualizar.getCnpj())) {
				empresaParaNaoAtualizar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoAtualizar));
			cnpjsCadastrados.add(empresaParaNaoAtualizar.getCnpj());
		}
		empresaMongoRepository.update(empresaDesatualizada.getCnpj(), empresaAtualizada);
		assertEquals(1l, mongoCollection.countDocuments(Filters.eq("_id", empresaAtualizada.getCnpj())));
		Empresa empresaAposAtualizacao = empresaConversorMongo.documentToEmpresa(mongoCollection.find(Filters.eq("_id", empresaAtualizada.getCnpj())).first());
		assertEquals(empresaAtualizada.toString(), empresaAposAtualizacao.toString());
	}

	@Test
	public void ao_substituir_pelo_cnpj_uma_empresa_na_collection_nao_deve_alterar_a_collection_caso_as_empresas_tenham_todos_os_campos_iguais() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		Empresa empresa = empresaRandomBuilder.build();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		Empresa empresaParaNaoAtualizar = null;
		for (int i = 0; i < 10; i++) {
			empresaParaNaoAtualizar = empresaRandomBuilder.build();
			while (empresaParaNaoAtualizar.getCnpj().equals(empresa.getCnpj()) || cnpjsCadastrados.contains(empresaParaNaoAtualizar.getCnpj())) {
				empresaParaNaoAtualizar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoAtualizar));
			cnpjsCadastrados.add(empresaParaNaoAtualizar.getCnpj());
		}
		empresaMongoRepository.update(empresa.getCnpj(), empresa);
		assertEquals(1l, mongoCollection.countDocuments(Filters.eq("_id", empresa.getCnpj())));
		Empresa empresaAposAtualizacao = empresaConversorMongo.documentToEmpresa(mongoCollection.find(Filters.eq("_id", empresa.getCnpj())).first());
		assertEquals(empresa.toString(), empresaAposAtualizacao.toString());		
	}	
	
	@Test
	public void quando_substituir_procurando_pelo_cnpj_nao_deve_alterar_a_collection_caso_nao_haja_o_cnpj_especificado_na_mesma() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		Empresa empresa = empresaRandomBuilder.build();
		Empresa empresaParaNaoAtualizar = null;
		for (int i = 0; i < 10; i++) {
			empresaParaNaoAtualizar = empresaRandomBuilder.build();
			while (empresaParaNaoAtualizar.getCnpj().equals(empresa.getCnpj()) || cnpjsCadastrados.contains(empresaParaNaoAtualizar.getCnpj())) {
				empresaParaNaoAtualizar = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoAtualizar));
			cnpjsCadastrados.add(empresaParaNaoAtualizar.getCnpj());
		}
		empresaMongoRepository.update(empresa.getCnpj(), empresa);
		assertEquals(0l, mongoCollection.countDocuments(Filters.eq("_id", empresa.getCnpj())));
		assertNull(mongoCollection.find(Filters.eq("cnpj", empresa.getCnpj())).first());
	}

	@Test
	public void deve_ler_uma_empresa_integralmente_pelo_seu_cnpj() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		Empresa empresaParaLer = empresaRandomBuilder.build();
		String filtro = empresaParaLer.getCnpj();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaLer));
		for (int i = 0; i < 10; i++) {
			Empresa empresaParaNaoLer = empresaRandomBuilder.build();
			while (empresaParaNaoLer.getCnpj().equals(empresaParaLer.getCnpj()) || cnpjsCadastrados.contains(empresaParaNaoLer.getCnpj())) {
				empresaParaNaoLer = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaParaNaoLer));
			cnpjsCadastrados.add(empresaParaNaoLer.getCnpj());
		}
		Empresa empresaLida = empresaMongoRepository.findByParams(new Document().append("_id", filtro), new Document(), new Document(), 0, 1).get(0);
		assertEquals(empresaParaLer.toString(), empresaLida.toString());
	}

	@Test
	public void deve_trazer_valor_nulo_caso_nao_haja_empresa_com_cnpj_procurado() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		String filtro = empresaRandomBuilder.build().getCnpj();
		for (int i = 0; i < 10; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			while (empresa.getCnpj().equals(filtro) || cnpjsCadastrados.contains(empresa.getCnpj())) {
				empresa = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
			cnpjsCadastrados.add(empresa.getCnpj());
		}
		assertTrue(empresaMongoRepository.findByParams(new Document().append("_id", filtro), new Document(), new Document(), 0, 1).isEmpty());		
	}
	
	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_e_dataAbertura_na_ordem_alfabetica_da_razaoSocial_das_empresas_filtradas_pelo_campo_tipoEmpresa() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		Document projection = new Document().append("razaoSocial", 1).append("dataAbertura", 1);
		Document sort = new Document().append("razaoSocial", 1);
		TipoEmpresa filtro = TipoEmpresa.values()[RandomUtils.nextInt(0, TipoEmpresa.values().length)];
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.build();
			while (empresaNaoPassaFiltro.getTipoEmpresa().equals(filtro) || cnpjsCadastrados.contains(empresaNaoPassaFiltro.getCnpj())) {
				empresaNaoPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
			cnpjsCadastrados.add(empresaNaoPassaFiltro.getCnpj());
		}
		for (int i = 0; i < 10; i++) {
			Empresa empresaPassaFiltro = empresaRandomBuilder.build();
			while (!empresaPassaFiltro.getTipoEmpresa().equals(filtro) || cnpjsCadastrados.contains(empresaPassaFiltro.getCnpj())) {
				empresaPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
			expectedEmpresas.add(empresaPassaFiltro);
			cnpjsCadastrados.add(empresaPassaFiltro.getCnpj());
		}
		Comparator<Empresa> byRazaoSocial = (Empresa empresa1, Empresa empresa2) -> empresa1.getRazaoSocial().compareTo(empresa2.getRazaoSocial());
		Collections.sort(expectedEmpresas, byRazaoSocial);
		List<Empresa> actualEmpresas = empresaMongoRepository.findByParams(new Document().append("tipoEmpresa", filtro.name()), sort, projection, 0, 20);
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
			assertEquals(expectedEmpresas.get(i).getDataAbertura(), actualEmpresas.get(i).getDataAbertura());
		}
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_com_o_tipoEmpresa_procurado() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		TipoEmpresa filtro = TipoEmpresa.values()[RandomUtils.nextInt(0, TipoEmpresa.values().length)];
		for (int i = 0; i < 20; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.build();
			while (empresaNaoPassaFiltro.getTipoEmpresa().equals(filtro) || cnpjsCadastrados.contains(empresaNaoPassaFiltro.getCnpj())) {
				empresaNaoPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
			cnpjsCadastrados.add(empresaNaoPassaFiltro.getCnpj());
		}
		List<Empresa> empresasFiltradas = empresaMongoRepository.findByParams(new Document().append("tipoEmpresa", filtro.name()), new Document(), new Document(), 0, 20);
		assertEquals(0, empresasFiltradas.size());		
	}

	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_e_dataAbertura_na_ordem_alfabetica_da_razaoSocial_das_empresas_filtradas_pelo_campo_tipoPorteEmpresa() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		Document projection = new Document().append("razaoSocial", 1).append("dataAbertura", 1);
		Document sort = new Document().append("razaoSocial", 1);
		TipoPorteEmpresa filtro = TipoPorteEmpresa.values()[RandomUtils.nextInt(0, TipoPorteEmpresa.values().length)];
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.build();
			while (empresaNaoPassaFiltro.getTipoPorteEmpresa().equals(filtro) || cnpjsCadastrados.contains(empresaNaoPassaFiltro.getCnpj())) {
				empresaNaoPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
			cnpjsCadastrados.add(empresaNaoPassaFiltro.getCnpj());
		}
		for (int i = 0; i < 10; i++) {
			Empresa empresaPassaFiltro = empresaRandomBuilder.build();
			while (!empresaPassaFiltro.getTipoPorteEmpresa().equals(filtro) || cnpjsCadastrados.contains(empresaPassaFiltro.getCnpj())) {
				empresaPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
			expectedEmpresas.add(empresaPassaFiltro);
			cnpjsCadastrados.add(empresaPassaFiltro.getCnpj());
		}
		Comparator<Empresa> byRazaoSocial = (Empresa empresa1, Empresa empresa2) -> empresa1.getRazaoSocial().compareTo(empresa2.getRazaoSocial());
		Collections.sort(expectedEmpresas, byRazaoSocial);
		List<Empresa> actualEmpresas = empresaMongoRepository.findByParams(new Document().append("tipoPorteEmpresa", filtro.name()), sort, projection, 0, 20);
		assertEquals(expectedEmpresas.size(), actualEmpresas.size());
		for (int i = 0; i < expectedEmpresas.size(); i++) {
			assertEquals(expectedEmpresas.get(i).getCnpj(), actualEmpresas.get(i).getCnpj());
			assertEquals(expectedEmpresas.get(i).getRazaoSocial(), actualEmpresas.get(i).getRazaoSocial());
			assertEquals(expectedEmpresas.get(i).getDataAbertura(), actualEmpresas.get(i).getDataAbertura());
		}
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_com_o_tipoPorteEmpresa_procurado() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		TipoPorteEmpresa filtro = TipoPorteEmpresa.values()[RandomUtils.nextInt(0, TipoPorteEmpresa.values().length)];
		for (int i = 0; i < 20; i++) {
			Empresa empresaNaoPassaFiltro = empresaRandomBuilder.build();
			while (empresaNaoPassaFiltro.getTipoPorteEmpresa().equals(filtro) || cnpjsCadastrados.contains(empresaNaoPassaFiltro.getCnpj())) {
				empresaNaoPassaFiltro = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
			cnpjsCadastrados.add(empresaNaoPassaFiltro.getCnpj());
		}
		List<Empresa> empresasFiltradas = empresaMongoRepository.findByParams(new Document().append("tipoPorteEmpresa", filtro.name()), new Document(), new Document(), 0, 20);
		assertEquals(0, empresasFiltradas.size());		
	}

	@Test
	public void com_parametros_inteiros_0_e_numero_de_empresas_na_collection_deve_ler_todas_as_empresas_armazenadas() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		int randomQtdCollection = RandomUtils.nextInt(1, 11);
		List<Empresa> expectedEmpresas = new ArrayList<>();
		for (int i = 0; i < randomQtdCollection; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			while (cnpjsCadastrados.contains(empresa.getCnpj())) {
				empresa = empresaRandomBuilder.build();
			}
			expectedEmpresas.add(empresa);
			cnpjsCadastrados.add(empresa.getCnpj());
		}
		for (Empresa empresa : expectedEmpresas) {
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		}
		List<Empresa> actualEmpresas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), 0, randomQtdCollection);
		assertEquals(randomQtdCollection, actualEmpresas.size());
		for (int i = 0; i < randomQtdCollection; i++) {
			assertEquals(expectedEmpresas.get(i).toString(), actualEmpresas.get(i).toString());
		}
	}

	@Test
	public void com_parametros_inteiros_p_e_n_deve_ler_n_empresas_apos_pular_as_p_primeiras() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			while (cnpjsCadastrados.contains(empresa.getCnpj())) {
				empresa = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
			cnpjsCadastrados.add(empresa.getCnpj());
		}
		List<Empresa> expectedEmpresas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), 0, 20);		
		int n = RandomUtils.nextInt(0, 20 + 1);
		int p = RandomUtils.nextInt(0, 20 + 1 - n);
		List<Empresa> actualEmpresas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), p, n);
		assertEquals(n, actualEmpresas.size());
		assertEquals(expectedEmpresas.get(p).toString(), actualEmpresas.get(0).toString());
	}

	@Test
	public void deve_trazer_conjunto_vazio_caso_nao_haja_empresa_independente_dos_valores_dos_inteiros_colocados_nos_parametros() {
		int n = RandomUtils.nextInt(0, 21);
		int p = RandomUtils.nextInt(0, 21 - n);
		List<Empresa> empresas = empresaMongoRepository.findByParams(new Document(), new Document(), new Document(), p, n);
		assertEquals(0, empresas.size());
	}

	@Test
	public void deve_trazer_o_numero_de_empresas_armazenadas_na_collection() {
		List<String> cnpjsCadastrados = new ArrayList<>();
		long quantidadeEsperada = RandomUtils.nextLong(1, 11);
		for (long i = 0; i < quantidadeEsperada; i++) {
			Empresa empresa = empresaRandomBuilder.build();
			while (cnpjsCadastrados.contains(empresa.getCnpj())) {
				empresa = empresaRandomBuilder.build();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
			cnpjsCadastrados.add(empresa.getCnpj());
		}
		long quantidadeComputada = empresaMongoRepository.countByParams(new Document());
		assertEquals(quantidadeEsperada, quantidadeComputada);
	}
	
	@Test
	public void deve_trazer_zero_caso_nao_haja_empresa_armazenadas_na_collection() {
		long quantidadeComputada = empresaMongoRepository.countByParams(new Document());
		assertEquals(0l, quantidadeComputada);		
	}
	
}
