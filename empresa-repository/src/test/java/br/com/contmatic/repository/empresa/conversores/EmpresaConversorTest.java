package br.com.contmatic.repository.empresa.conversores;

import static br.com.contmatic.repository.empresa.conversores.EmpresaConversor.getInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.Test;

import br.com.contmatic.model.conta.Agencia;
import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.endereco.Bairro;
import br.com.contmatic.model.endereco.Cidade;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.endereco.Logradouro;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;
import br.com.contmatic.repository.empresa.conversores.EmpresaConversor;

public class EmpresaConversorTest {

	private static final Logger logger = Logger.getLogger(EmpresaConversorTest.class.getName());

	@AfterClass
	public static void tearDownAfterClass() {
		EmpresaConversor.closeConversor();
		EmpresaRandomBuilder.closeBuilder();
	}

	@Test
	public void metodos_empresaToDocument_e_documentToEmpresa_devem_fazer_as_conversoes_corretas_de_Empresa_para_Document_e_de_Document_para_Empresa_respectivamente() {
		Empresa empresaAntesConversao = new Empresa();
		Empresa empresaDepoisConversao = new Empresa();
		try {
			for (int i = 0; i < 100; i++) {
				empresaAntesConversao = EmpresaRandomBuilder.getInstance().build();
				empresaDepoisConversao = getInstance()
						.documentToEmpresa(getInstance().empresaToDocument(empresaAntesConversao));
				assertEquals(empresaAntesConversao.toString(), empresaDepoisConversao.toString());
			}
		} catch (AssertionError e) {
			logger.log(Level.SEVERE, "Objeto antes da conversão  : {0}", empresaAntesConversao);
			logger.log(Level.SEVERE, "Objeto depois da conversão : {0}", empresaDepoisConversao);
			fail();
		}
	}

	@Test
	public void metodos_empresaToDocument_e_documentToEmpresa_devem_fazer_as_conversoes_em_nulos_para_empresas_nulas() {
		try {
			assertNull(getInstance().documentToEmpresa(getInstance().empresaToDocument(null)));			
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void metodos_empresaToDocument_e_documentToEmpresa_devem_fazer_as_conversoes_sem_gerar_erros_com_empresa_com_atributos_nulos_1() {
		try {
			getInstance().documentToEmpresa(getInstance().empresaToDocument(new Empresa()));
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void metodos_empresaToDocument_e_documentToEmpresa_devem_fazer_as_conversoes_sem_gerar_erros_com_empresa_com_atributos_nulos_2() {
		Empresa empresa = new Empresa();
		empresa.setCelulares(new HashSet<>());
		empresa.getCelulares().add(new Celular());
		empresa.setTelefonesFixo(new HashSet<>());
		empresa.getTelefonesFixo().add(new TelefoneFixo());
		empresa.setEmails(new HashSet<>());
		empresa.getEmails().add(new Email());
		empresa.setEnderecos(new HashSet<>());
		Endereco endereco = new Endereco();
		Logradouro logradouro = new Logradouro();
		Bairro bairro = new Bairro();
		Cidade cidade = new Cidade();
		bairro.setCidade(cidade);
		logradouro.setBairro(bairro);
		endereco.setLogradouro(logradouro);
		empresa.getEnderecos().add(endereco);
		empresa.setResponsaveis(new HashSet<>());
		empresa.getResponsaveis().add(new Pessoa());
		empresa.setContas(new HashSet<>());
		Conta conta = new Conta();
		Agencia agencia = new Agencia();
		conta.setAgencia(agencia);
		empresa.getContas().add(conta);
		try {
			getInstance().documentToEmpresa(getInstance().empresaToDocument(empresa));
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void metodos_empresaToDocument_e_documentToEmpresa_devem_fazer_as_conversoes_sem_gerar_erros_com_empresa_com_atributos_nulos_3() {
		Empresa empresa = new Empresa();
		empresa.setCelulares(new HashSet<>());
		empresa.getCelulares().add(null);
		empresa.setTelefonesFixo(new HashSet<>());
		empresa.getTelefonesFixo().add(null);
		empresa.setEmails(new HashSet<>());
		empresa.getEmails().add(null);
		empresa.setEnderecos(new HashSet<>());
		empresa.getEnderecos().add(null);
		empresa.setResponsaveis(new HashSet<>());
		empresa.getResponsaveis().add(null);
		empresa.setContas(new HashSet<>());
		empresa.getContas().add(null);
		try {
			getInstance().documentToEmpresa(getInstance().empresaToDocument(empresa));
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void metodos_empresaToDocument_e_documentToEmpresa_devem_fazer_as_conversoes_sem_gerar_erros_com_empresa_com_atributos_nulos_4() {
		Empresa empresa1 = new Empresa();
		empresa1.setEnderecos(new HashSet<>());
		Endereco endereco1 = new Endereco();
		Logradouro logradouro1 = new Logradouro();
		Bairro bairro1 = new Bairro();
		logradouro1.setBairro(bairro1);
		endereco1.setLogradouro(logradouro1);
		empresa1.getEnderecos().add(endereco1);
		empresa1.setContas(new HashSet<>());
		Conta conta1 = new Conta();
		empresa1.getContas().add(conta1);

		Empresa empresa2 = new Empresa();
		empresa2.setEnderecos(new HashSet<>());
		Endereco endereco2 = new Endereco();
		Logradouro logradouro2 = new Logradouro();
		endereco2.setLogradouro(logradouro2);
		empresa2.getEnderecos().add(endereco2);

		Empresa empresa3 = new Empresa();
		empresa3.setEnderecos(new HashSet<>());
		Endereco endereco3 = new Endereco();
		empresa3.getEnderecos().add(endereco3);
		try {
			getInstance().documentToEmpresa(getInstance().empresaToDocument(empresa1));
			getInstance().documentToEmpresa(getInstance().empresaToDocument(empresa2));
			getInstance().documentToEmpresa(getInstance().empresaToDocument(empresa3));
		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
