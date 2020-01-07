package br.com.contmatic.repository.empresa;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class EmpresaMongoRepositoryImpTest {

	private static final MongodStarter mongoStarter = MongodStarter.getDefaultInstance();
	
	private MongodProcess mongodProcess;
	
	private EmpresaRepository empresaRepository;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION).net(new Net("localhost", 12345, Network.localhostIsIPv6())).build();
		mongodProcess = mongoStarter.prepare(mongodConfig).start();
		empresaRepository = new EmpresaMongoRepositoryImp("localhost", 12345);
	}

	@After
	public void tearDown() throws Exception {
		mongodProcess.stop();
	}

	@Test
	public void test() {
		Empresa empresa = new Empresa();
		empresaRepository.createEmpresa(empresa);
		assertEquals(1, empresaRepository.countAllEmpresas());
		assertEquals(empresa, empresaRepository.readByCnpj(null));
	}

}
