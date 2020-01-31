package br.com.contmatic.repository.mongo.conversor.empresa;

import static org.junit.Assert.assertEquals;

import org.bson.Document;

import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.random.empresa.EmpresaTestRandomBuilder;

public class EmpresaConversorMongoTest {

	private EmpresaConversorMongo empresaConversorMongo = new EmpresaConversorMongo();
	
	private EmpresaTestRandomBuilder empresaRandomBuilder = new EmpresaTestRandomBuilder();
	
	@Test
	public void metodos_empresaToDocument_e_documentToEmpresa_devem_fazer_as_conversoes_corretas_de_Empresa_para_Document_e_de_Document_para_Empresa_respectivamente() {
		Empresa empresa;
		Document docEmpresa;
		String jsonEmpresaAntesConversao;
		String jsonEmpresaDepoisConversao;
		for (int i = 0; i < 100; i++) {
			empresa = empresaRandomBuilder.build();
			jsonEmpresaAntesConversao = empresa.toString();
			docEmpresa = empresaConversorMongo.empresaToDocument(empresa);
			empresa = empresaConversorMongo.documentToEmpresa(docEmpresa);
			jsonEmpresaDepoisConversao = empresa.toString();
			assertEquals(jsonEmpresaAntesConversao, jsonEmpresaDepoisConversao);
		}
	}

}
