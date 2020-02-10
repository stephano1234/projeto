package br.com.contmatic.model.random;

import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static org.junit.Assert.assertFalse;

import org.junit.AfterClass;
import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;
import br.com.contmatic.validacoes.groups.Post;

public class AllRandomBuilderTeste {
	
	private Empresa empresa;
	
	@AfterClass
	public static void tearDownAfterClass() {
		EmpresaRandomBuilder.closeBuilder();
	}
	
	@Test
	public void gera_1000_objetos_randomicos_e_verifica_se_todos_sao_validos_de_acordo_com_as_regras_estabelecidas() {
		for (int i = 0; i < 1000; i++) {
			empresa = EmpresaRandomBuilder.getInstance().build();
			System.out.println((i + 1) + "º objeto randômico gerado: " + empresa);
			assertFalse(procuraQualquerViolacao(empresa, Post.class));
		}
	}

}
