package br.com.contmatic.repository.mongo.conversor.empresa;

import static org.junit.Assert.assertEquals;

import org.bson.Document;

import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;

import br.com.contmatic.random.empresa.EmpresaRandomBuilder;

public class EmpresaConversorMongoTest {

	private EmpresaConversorMongo empresaConversorMongo = new EmpresaConversorMongo();
	
	@Test
	public void metodos_empresaToDocument_e_documentToEmpresa_devem_fazer_as_conversoes_de_Document_para_Empresa_e_viceversa_corretamente() {
		Empresa empresa;
		Document docEmpresa;
		String descricaoEmpresaAntesConversao;
		String descricaoEmpresaDepoisConversao;
		for (int i = 0; i < 1000; i++) {
			empresa = EmpresaRandomBuilder.buildValido();
			descricaoEmpresaAntesConversao = empresa.toString();
			docEmpresa = empresaConversorMongo.empresaToDocument(empresa);
			empresa = empresaConversorMongo.documentToEmpresa(docEmpresa);
			descricaoEmpresaDepoisConversao = empresa.toString();
			assertEquals(descricaoEmpresaAntesConversao, descricaoEmpresaDepoisConversao);
		}
	}

}
