package br.com.contmatic.repository.mongo.conversor.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;

public class EmpresaConversorMongoTest {

	private static EmpresaConversorMongo empresaConversorMongo;
	
	private static EmpresaRandomBuilder empresaRandomBuilder;
	
	private static final Logger logger = Logger.getLogger(EmpresaConversorMongoTest.class.getName());
	
	@BeforeClass
	public static void setUpBeforeClass() {
		empresaConversorMongo = EmpresaConversorMongo.getInstance();
		empresaRandomBuilder = EmpresaRandomBuilder.getInstance();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		EmpresaConversorMongo.closeConversor();
		EmpresaRandomBuilder.closeBuilder();
	}
	
	@Test
	public void metodos_empresaToDocument_e_documentToEmpresa_devem_fazer_as_conversoes_corretas_de_Empresa_para_Document_e_de_Document_para_Empresa_respectivamente() {
		Empresa empresaAntesConversao = new Empresa();
		Empresa empresaDepoisConversao = new Empresa();
		try {
			for (int i = 0; i < 1000; i++) {
				empresaAntesConversao = empresaRandomBuilder.build();
				empresaDepoisConversao = empresaConversorMongo.documentToEmpresa(empresaConversorMongo.empresaToDocument(empresaAntesConversao));
				logger.log(Level.INFO, "{0}º conversão testada", i + 1);
				assertEquals(empresaAntesConversao.toString(), empresaDepoisConversao.toString());
			}			
		} catch(AssertionError e) {
			logger.log(Level.SEVERE, "Objeto antes da conversão  : {0}", empresaAntesConversao);
			logger.log(Level.SEVERE, "Objeto depois da conversão : {0}", empresaDepoisConversao);
			fail();
		}
	}

}
