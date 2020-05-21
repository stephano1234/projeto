package br.com.contmatic.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.contmatic.repository.configuracao.MongoConnectionTest;
import br.com.contmatic.repository.empresa.EmpresaRepositoryTest;
import br.com.contmatic.repository.empresa.conversores.EmpresaConversorTest;

@RunWith(Suite.class)
@SuiteClasses({ MongoConnectionTest.class, EmpresaConversorTest.class, EmpresaRepositoryTest.class })
public class Coverage {

}
