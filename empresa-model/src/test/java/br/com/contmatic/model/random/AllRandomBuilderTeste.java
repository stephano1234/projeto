package br.com.contmatic.model.random;

import static br.com.contmatic.model.random.empresa.EmpresaRandomBuilder.closeBuilder;
import static br.com.contmatic.model.random.empresa.EmpresaRandomBuilder.getInstance;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static org.junit.Assert.assertFalse;

import org.junit.AfterClass;
import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.restricoes.grupos.Post;

public class AllRandomBuilderTeste {
	
	private Empresa empresa;
	
	@AfterClass
	public static void tearDownAfterClass() {
		closeBuilder();
	}
	
	@Test
	public void gera_100_objetos_randomicos_e_verifica_se_todos_sao_validos() {
		for (int i = 0; i < 100; i++) {
			empresa = getInstance().build();
			assertFalse(procuraQualquerViolacao(empresa, Post.class));
		}
	}

}
