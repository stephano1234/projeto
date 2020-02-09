package br.com.contmatic.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.contmatic.model.conta.AgenciaTest;
import br.com.contmatic.model.conta.ContaTest;
import br.com.contmatic.model.conta.TipoContaTest;
import br.com.contmatic.model.contato.CelularTest;
import br.com.contmatic.model.contato.EmailTest;
import br.com.contmatic.model.contato.TelefoneFixoTest;
import br.com.contmatic.model.contato.TipoContatoCelularTest;
import br.com.contmatic.model.empresa.EmpresaTest;
import br.com.contmatic.model.empresa.TipoEmpresaTest;
import br.com.contmatic.model.empresa.TipoPorteEmpresaTest;
import br.com.contmatic.model.endereco.BairroTest;
import br.com.contmatic.model.endereco.CidadeTest;
import br.com.contmatic.model.endereco.EnderecoTest;
import br.com.contmatic.model.endereco.LogradouroTest;
import br.com.contmatic.model.endereco.TipoUfTest;
import br.com.contmatic.model.pessoa.ContratoTrabalhoTest;
import br.com.contmatic.model.pessoa.PessoaTest;
import br.com.contmatic.model.pessoa.TipoEstadoCivilTest;
import br.com.contmatic.model.pessoa.TipoGrauInstrucaoTest;
import br.com.contmatic.model.pessoa.TipoSexoTest;

@RunWith(Suite.class)
@SuiteClasses({ AgenciaTest.class, ContaTest.class, TipoContaTest.class, CelularTest.class, TelefoneFixoTest.class,
		EmailTest.class, TipoContatoCelularTest.class, CidadeTest.class, BairroTest.class, LogradouroTest.class,
		EnderecoTest.class, TipoUfTest.class, ContratoTrabalhoTest.class, PessoaTest.class, TipoGrauInstrucaoTest.class,
		TipoEstadoCivilTest.class, TipoSexoTest.class, EmpresaTest.class, TipoEmpresaTest.class,
		TipoPorteEmpresaTest.class })
public class AllTests {

}
