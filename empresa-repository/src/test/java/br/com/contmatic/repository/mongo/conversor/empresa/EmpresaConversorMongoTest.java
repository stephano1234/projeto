package br.com.contmatic.repository.mongo.conversor.empresa;

import static org.junit.Assert.assertEquals;

import org.bson.Document;

import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;

public class EmpresaConversorMongoTest {

	private EmpresaConversorMongo empresaConversorMongo = new EmpresaConversorMongo();
	
	private EmpresaRandomBuilder empresaRandomBuilder = new EmpresaRandomBuilder();
	
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
			System.out.println((i + 1) + "º objeto antes da conversão  | " + jsonEmpresaAntesConversao);
			System.out.println((i + 1) + "º objeto depois da conversão | " + jsonEmpresaDepoisConversao);
			assertEquals(jsonEmpresaAntesConversao, jsonEmpresaDepoisConversao);
		}
	}

}
