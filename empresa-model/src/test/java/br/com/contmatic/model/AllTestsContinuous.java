package br.com.contmatic.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AllTestsContinuous {

	@Test
	public void repete_todos_os_testes_1000_vezes_e_verifica_se_todos_passam() {
		for (int i = 0; i < 1000; i++) {
			Result resultado = JUnitCore.runClasses(AllTests.class);
			for (Failure falha : resultado.getFailures()) {
				System.out.println(falha.getTrace());
			}
			System.out.println((i + 1) + "º rodada de testes.");
			assertTrue(resultado.wasSuccessful());			
		}
	}

}
