package br.com.contmatic.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.contmatic.model.random.AllRandomBuilderTeste;

@RunWith(Suite.class)
@SuiteClasses({ AllTests.class, AllRandomBuilderTeste.class })
public class Coverage {

}
