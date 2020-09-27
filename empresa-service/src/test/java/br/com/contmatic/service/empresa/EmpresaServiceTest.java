package br.com.contmatic.service.empresa;

import static br.com.contmatic.service.empresa.EmpresaService.getInstance;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;
import br.com.contmatic.repository.empresa.mock.EmpresaRepositoryMock;
import br.com.contmatic.service.mensagens.MensagemServidor;
import br.com.contmatic.service.parametros.FindParams;

public class EmpresaServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCountByParams() {
		FindParams paramsWithToCount = new FindParams();
		paramsWithToCount.setToCount(true);
		assertEquals("1", getInstance(EmpresaRepositoryMock.getInstance()).countByParams(paramsWithToCount));
		FindParams paramsWithoutToCount = new FindParams();
		paramsWithToCount.setToCount(false);
		assertEquals(null, getInstance(EmpresaRepositoryMock.getInstance()).countByParams(paramsWithoutToCount));
	}

	@Test
	public void testUpdate() {
		Empresa empresa = EmpresaRandomBuilder.getInstance().build();
		while (empresa.getCnpj().equals(EmpresaRepositoryMock.CNPJ_MOCK)) {
			empresa = EmpresaRandomBuilder.getInstance().build();
		}
		MensagemServidor msgNotFound = getInstance(EmpresaRepositoryMock.getInstance()).update(empresa);
		assertEquals(404, msgNotFound.getStatusCode());
		assertEquals("Não existe empresa cadastrada com esse C.N.P.J. no banco de dados.", msgNotFound.getMensagens().get(0));
		empresa.setCnpj(EmpresaRepositoryMock.CNPJ_MOCK);
		MensagemServidor msgUpdate = getInstance(EmpresaRepositoryMock.getInstance()).update(empresa);
		assertEquals(200, msgUpdate.getStatusCode());
		assertEquals("Empresa alterada com sucesso.", msgUpdate.getMensagens().get(0));
		empresa.setRazaoSocial("");
		MensagemServidor msgErro = getInstance(EmpresaRepositoryMock.getInstance()).update(empresa);
		assertEquals(422, msgErro.getStatusCode());
		assertEquals("A empresa deve possuir uma razão social válida.", msgErro.getMensagens().get(0));
	}

	@Test
	public void testDelete() {
		MensagemServidor msgDelete = getInstance(EmpresaRepositoryMock.getInstance()).delete(EmpresaRepositoryMock.CNPJ_MOCK);
		assertEquals(200, msgDelete.getStatusCode());
		assertEquals("Empresa apagada com sucesso.", msgDelete.getMensagens().get(0));
		MensagemServidor msgNotFound = getInstance(EmpresaRepositoryMock.getInstance()).delete("");
		assertEquals(404, msgNotFound.getStatusCode());
		assertEquals("Não existe empresa cadastrada com esse C.N.P.J. no banco de dados.", msgNotFound.getMensagens().get(0));
	}

	@Test
	public void testCreate() {
		Empresa empresa = EmpresaRandomBuilder.getInstance().build();
		while (empresa.getCnpj().equals(EmpresaRepositoryMock.CNPJ_MOCK)) {
			empresa = EmpresaRandomBuilder.getInstance().build();
		}
		MensagemServidor msgUpdate = getInstance(EmpresaRepositoryMock.getInstance()).create(empresa);
		assertEquals(201, msgUpdate.getStatusCode());
		assertEquals("Empresa salva com sucesso.", msgUpdate.getMensagens().get(0));
		String razaoSocial = empresa.getRazaoSocial();
		empresa.setRazaoSocial("");
		MensagemServidor msgErro = getInstance(EmpresaRepositoryMock.getInstance()).create(empresa);
		assertEquals(422, msgErro.getStatusCode());
		assertEquals("A empresa deve possuir uma razão social válida.", msgErro.getMensagens().get(0));
		empresa.setRazaoSocial(razaoSocial);
		empresa.setCnpj(EmpresaRepositoryMock.CNPJ_MOCK);
		MensagemServidor msgJaExiste = getInstance(EmpresaRepositoryMock.getInstance()).create(empresa);
		assertEquals(409, msgJaExiste.getStatusCode());
		assertEquals("Já existe uma empresa armazenada com o mesmo C.N.P.J. no banco de dados.", msgJaExiste.getMensagens().get(0));
	}

	@Test
	public void testSendNotFoundResponse() {
		MensagemServidor msg = getInstance(EmpresaRepositoryMock.getInstance()).sendNotFoundResponse();
		assertEquals(404, msg.getStatusCode());
		assertEquals("Não existe empresa cadastrada com esse C.N.P.J. no banco de dados.", msg.getMensagens().get(0));
	}

}
