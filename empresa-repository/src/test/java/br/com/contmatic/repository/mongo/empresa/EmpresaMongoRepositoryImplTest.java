package br.com.contmatic.repository.mongo.empresa;

import static org.junit.Assert.assertEquals;
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

import br.com.contmatic.random.empresa.EmpresaRandomBuilder;

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
		Empresa empresa = EmpresaRandomBuilder.buildValido();
		empresaMongoRepository.create(empresa);
		Document actualDocument = mongoCollection.find().first();
		actualDocument.remove("_id");
		Document expectedDocument = empresaConversorMongo.empresaToDocument(empresa);
		assertEquals(expectedDocument, actualDocument);
	}

	@Test
	public void deve_deletar_a_empresa_especificada_pelo_cnpj_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresa = EmpresaRandomBuilder.buildValido();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		empresaMongoRepository.delete(empresa.getCnpj());
		assertTrue(mongoCollection.countDocuments() == 0l);
	}
	
	@Test
	public void deve_substituir_procurando_pelo_cnpj_uma_empresa_na_collection_empresa_de_uma_database_do_mongodb_por_uma_nova_empresa() {
		Empresa empresaDesatualizada = EmpresaRandomBuilder.buildValido();
		Empresa empresaAtualizada = EmpresaRandomBuilder.buildValido();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaDesatualizada));
		empresaMongoRepository.update(empresaDesatualizada.getCnpj(), empresaAtualizada);
		Document actualDocument = mongoCollection.find().first();
		actualDocument.remove("_id");
		Document expectedDocument = empresaConversorMongo.empresaToDocument(empresaAtualizada);
		assertEquals(expectedDocument, actualDocument);
	}
	
	@Test
	public void deve_ler_uma_empresa_integralmente_pelo_seu_cnpj_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresaPassaFiltro = EmpresaRandomBuilder.buildValido();
		String filtro = empresaPassaFiltro.getCnpj();
		Document expectedDocument = empresaConversorMongo.empresaToDocument(empresaPassaFiltro);
		mongoCollection.insertOne(expectedDocument);
		expectedDocument.remove("_id");
		for (int i = 0; i < RandomUtils.nextInt(0, 10); i++) {
			Empresa empresaNaoPassaFiltro = EmpresaRandomBuilder.buildValido();
			while (empresaNaoPassaFiltro.getCnpj().equals(empresaPassaFiltro.getCnpj())) {
				empresaNaoPassaFiltro = EmpresaRandomBuilder.buildValido();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
		}
		Empresa empresaLida = empresaMongoRepository.readByCnpj(filtro);
		Document actualDocument = empresaConversorMongo.empresaToDocument(empresaLida);
		assertEquals(expectedDocument, actualDocument);
	}

	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_razaoSocial_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresaPassaFiltro = EmpresaRandomBuilder.buildValido();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
		String filtro = empresaPassaFiltro.getRazaoSocial();
		List<List<String>> expectedCnpjsAndRazoesSociais = new ArrayList<>();
		List<String> cnpjAndRazaoSocial = new ArrayList<>();
		cnpjAndRazaoSocial.add(empresaPassaFiltro.getCnpj());
		cnpjAndRazaoSocial.add(empresaPassaFiltro.getRazaoSocial());
		expectedCnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);		
		for (int i = 0; i < RandomUtils.nextInt(0, 10); i++) {
			Empresa empresaNaoPassaFiltro = EmpresaRandomBuilder.buildValido();
			while (empresaNaoPassaFiltro.getRazaoSocial().equals(empresaPassaFiltro.getRazaoSocial())) {
				empresaNaoPassaFiltro = EmpresaRandomBuilder.buildValido();
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
		}
		List<List<String>> actualCnpjsAndRazoesSociais = empresaMongoRepository.readCnpjAndRazaoSocialByRazaoSocial(filtro);
		assertEquals(expectedCnpjsAndRazoesSociais, actualCnpjsAndRazoesSociais);
	}

	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_cpf_dos_elementos_do_campo_responsaveis_na_collection_empresa_de_uma_database_do_mongodb() {
		Empresa empresaPassaFiltro = EmpresaRandomBuilder.buildValido();
		mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
		String[] cpfsRespomsaveis = new String[empresaPassaFiltro.getResponsaveis().size()];
		int i = 0;
		for (Pessoa responsavel : empresaPassaFiltro.getResponsaveis()) {
			cpfsRespomsaveis[i] = responsavel.getCpf();
			i++;
		}
		String filtro = cpfsRespomsaveis[RandomUtils.nextInt(0, cpfsRespomsaveis.length)];
		List<List<String>> expectedCnpjsAndRazoesSociais = new ArrayList<>();
		List<String> cnpjAndRazaoSocial = new ArrayList<>();
		cnpjAndRazaoSocial.add(empresaPassaFiltro.getCnpj());
		cnpjAndRazaoSocial.add(empresaPassaFiltro.getRazaoSocial());
		expectedCnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);
		cnpjAndRazaoSocial = new ArrayList<>();
		for (i = 0; i < RandomUtils.nextInt(0, 5); i++) {
			empresaPassaFiltro = EmpresaRandomBuilder.buildValido();
			int posicaoResponsavelPassaFiltro = RandomUtils.nextInt(0, empresaPassaFiltro.getResponsaveis().size());
			int j = 0;
			for (Pessoa responsavel : empresaPassaFiltro.getResponsaveis()) {
				if (posicaoResponsavelPassaFiltro == j) {
					responsavel.setCpf(filtro);
					break;
				}
				j++;
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaPassaFiltro));
			cnpjAndRazaoSocial.add(empresaPassaFiltro.getCnpj());
			cnpjAndRazaoSocial.add(empresaPassaFiltro.getRazaoSocial());
			expectedCnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);
			cnpjAndRazaoSocial = new ArrayList<>();
		}
		for (i = 0; i < RandomUtils.nextInt(0, 11); i++) {
			Empresa empresaNaoPassaFiltro = EmpresaRandomBuilder.buildValido();
			boolean naoPassaFiltro = false;
			while(!naoPassaFiltro) {
				naoPassaFiltro = true;
				for (Pessoa responsavelNaoPassaFiltro : empresaNaoPassaFiltro.getResponsaveis()) {
					if (responsavelNaoPassaFiltro.getCpf().equals(filtro)) {
						empresaNaoPassaFiltro = EmpresaRandomBuilder.buildValido();
						naoPassaFiltro = false;	
						break;
					}
				}
			}
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresaNaoPassaFiltro));
		}
		List<List<String>> actualCnpjsAndRazoesSociais = empresaMongoRepository.readCnpjAndRazaoSocialByResponsavelCpf(filtro);
		assertEquals(expectedCnpjsAndRazoesSociais, actualCnpjsAndRazoesSociais);
	}
	
	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_tipoEmpresa_na_collection_empresa_de_uma_database_do_mongodb() {
		TipoEmpresa filtro = TipoEmpresa.values()[RandomUtils.nextInt(0, TipoEmpresa.values().length)];
		List<List<String>> expectedCnpjsAndRazoesSociais = new ArrayList<>();
		for (int i = 0; i < RandomUtils.nextInt(0, 21); i++) {
			Empresa empresa = EmpresaRandomBuilder.buildValido();
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
			if (empresa.getTipoEmpresa().equals(filtro)) {
				List<String> cnpjAndRazaoSocial = new ArrayList<>();
				cnpjAndRazaoSocial.add(empresa.getCnpj());
				cnpjAndRazaoSocial.add(empresa.getRazaoSocial());
				expectedCnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);
			}		
		}
		List<List<String>> actualCnpjsAndRazoesSociais = empresaMongoRepository.readCnpjAndRazaoSocialByTipoEmpresa(filtro);
		assertEquals(expectedCnpjsAndRazoesSociais, actualCnpjsAndRazoesSociais);
	}

	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_das_empresas_filtradas_pelo_campo_tipoPorteEmpresa_na_collection_empresa_de_uma_database_do_mongodb() {
		TipoPorteEmpresa filtro = TipoPorteEmpresa.values()[RandomUtils.nextInt(0, TipoPorteEmpresa.values().length)];
		List<List<String>> expectedCnpjsAndRazoesSociais = new ArrayList<>();
		for (int i = 0; i < RandomUtils.nextInt(0, 21); i++) {
			Empresa empresa = EmpresaRandomBuilder.buildValido();
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
			if (empresa.getTipoPorteEmpresa().equals(filtro)) {
				List<String> cnpjAndRazaoSocial = new ArrayList<>();
				cnpjAndRazaoSocial.add(empresa.getCnpj());
				cnpjAndRazaoSocial.add(empresa.getRazaoSocial());
				expectedCnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);
			}		
		}
		List<List<String>> actualCnpjsAndRazoesSociais = empresaMongoRepository.readCnpjAndRazaoSocialByTipoPorteEmpresa(filtro);
		assertEquals(expectedCnpjsAndRazoesSociais, actualCnpjsAndRazoesSociais);
	}

	@Test
	public void deve_ler_os_campos_cnpj_e_razaoSocial_de_todas_as_empresas_armazenadas_na_collection_empresa_de_uma_database_do_mongodb() {
		List<List<String>> expectedCnpjsAndRazoesSociais = new ArrayList<>();
		for (int i = 0; i < RandomUtils.nextInt(0, 11); i++) {
			List<String> cnpjAndRazaoSocial = new ArrayList<>();
			Empresa empresa = EmpresaRandomBuilder.buildValido();
			cnpjAndRazaoSocial.add(empresa.getCnpj());
			cnpjAndRazaoSocial.add(empresa.getRazaoSocial());
			expectedCnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));	
		}
		List<List<String>> actualCnpjsAndRazoesSociais = empresaMongoRepository.readAllCnpjAndRazaoSocial();
		assertEquals(expectedCnpjsAndRazoesSociais, actualCnpjsAndRazoesSociais);
	}
	
	@Test
	public void deve_trazer_o_numero_de_empresas_armazenadas_na_collection_empresa_de_uma_database_do_mongodb() {
		long quantidadeEsperada = RandomUtils.nextLong(0, 11);
		for (long i = 0; i < quantidadeEsperada; i++) {
			Empresa empresa = EmpresaRandomBuilder.buildValido();
			mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
		}
		long quantidadeComputada = empresaMongoRepository.countAll();
		assertEquals(quantidadeEsperada, quantidadeComputada);
	}
	
}
