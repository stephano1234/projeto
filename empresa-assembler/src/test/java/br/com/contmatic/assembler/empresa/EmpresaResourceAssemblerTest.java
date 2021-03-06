package br.com.contmatic.assembler.empresa;

import static br.com.contmatic.assembler.empresa.EmpresaResourceAssembler.getInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import br.com.contmatic.assembler.exception.AssemblerException;
import br.com.contmatic.dto.empresa.v1.CelularResource;
import br.com.contmatic.dto.empresa.v1.ContaBancariaResource;
import br.com.contmatic.dto.empresa.v1.EmailResource;
import br.com.contmatic.dto.empresa.v1.EmpresaResource;
import br.com.contmatic.dto.empresa.v1.EnderecoResource;
import br.com.contmatic.dto.empresa.v1.ResponsavelResource;
import br.com.contmatic.dto.empresa.v1.TelefoneFixoResource;
import br.com.contmatic.dto.random.EmpresaResourceV1RandomBuilder;
import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.random.empresa.EmpresaRandomBuilder;

public class EmpresaResourceAssemblerTest {

	private Empresa entity;

	private EmpresaResource resource;

	@AfterClass
	public static void tearDownAfterClass() {
		EmpresaRandomBuilder.closeBuilder();
		EmpresaResourceV1RandomBuilder.closeBuilder();
	}

	@Before
	public void setUp() throws Exception {
		entity = EmpresaRandomBuilder.getInstance().build();
		resource = EmpresaResourceV1RandomBuilder.getInstance().build();
	}

	@Test
	public void testToEntity() {
		try {
			assertNull(getInstance().toEntity(null));
			Empresa entity = getInstance().toEntity(resource);
			assertEquals(resource.getCnpj(), entity.getCnpj());
			assertEquals(resource.getRazaoSocial(), entity.getRazaoSocial());
			assertEquals(resource.getDataAbertura().getYear(), entity.getDataAbertura().getYear());
			assertEquals(resource.getDataAbertura().getMonth(), entity.getDataAbertura().getMonthOfYear());
			assertEquals(resource.getDataAbertura().getDay(), entity.getDataAbertura().getDayOfMonth());
			assertEquals(resource.getTipoEmpresa(), entity.getTipoEmpresa().name());
			assertEquals(resource.getTipoPorteEmpresa(), entity.getTipoPorteEmpresa().name());
			assertEquals(resource.getCelulares().size(), entity.getCelulares().size());
			int indexOfResourceArray = 0;
			List<Celular> listCelular = Lists.newArrayList(entity.getCelulares());
			Comparator<Celular> compCelular = (Celular a, Celular b) -> a.getNumero().compareTo(b.getNumero());
			Collections.sort(listCelular, compCelular);
			Comparator<CelularResource> compCelularResource = (CelularResource a, CelularResource b) -> a.getNumero()
					.compareTo(b.getNumero());
			Collections.sort(resource.getCelulares(), compCelularResource);
			for (Celular celular : listCelular) {
				assertEquals(resource.getCelulares().get(indexOfResourceArray).getNumero(), celular.getNumero());
				indexOfResourceArray++;
			}
			assertEquals(resource.getTelefonesFixos().size(), entity.getTelefonesFixo().size());
			indexOfResourceArray = 0;
			List<TelefoneFixo> listTelefoneFixo = Lists.newArrayList(entity.getTelefonesFixo());
			Comparator<TelefoneFixo> compTelefoneFixo = (TelefoneFixo a, TelefoneFixo b) -> a.getNumero()
					.compareTo(b.getNumero());
			Collections.sort(listTelefoneFixo, compTelefoneFixo);
			Comparator<TelefoneFixoResource> compTelefoneFixoResource = (TelefoneFixoResource a,
					TelefoneFixoResource b) -> a.getNumero().compareTo(b.getNumero());
			Collections.sort(resource.getTelefonesFixos(), compTelefoneFixoResource);
			for (TelefoneFixo telefoneFixo : listTelefoneFixo) {
				assertEquals(resource.getTelefonesFixos().get(indexOfResourceArray).getNumero(),
						telefoneFixo.getNumero());
				indexOfResourceArray++;
			}
			assertEquals(resource.getEmails().size(), entity.getEmails().size());
			indexOfResourceArray = 0;
			List<Email> listEmail = Lists.newArrayList(entity.getEmails());
			Comparator<Email> compEmail = (Email a, Email b) -> a.getEndereco().compareTo(b.getEndereco());
			Collections.sort(listEmail, compEmail);
			Comparator<EmailResource> compEmailResource = (EmailResource a, EmailResource b) -> a.getEndereco()
					.compareTo(b.getEndereco());
			Collections.sort(resource.getEmails(), compEmailResource);
			for (Email email : listEmail) {
				assertEquals(resource.getEmails().get(indexOfResourceArray).getEndereco(), email.getEndereco());
				indexOfResourceArray++;
			}
			assertEquals(resource.getEnderecos().size(), entity.getEnderecos().size());
			indexOfResourceArray = 0;
			List<Endereco> listEndereco = Lists.newArrayList(entity.getEnderecos());
			Comparator<Endereco> compEndereco = (Endereco a, Endereco b) -> a.getCep().compareTo(b.getCep());
			Collections.sort(listEndereco, compEndereco);
			Comparator<EnderecoResource> compEnderecoResource = (EnderecoResource a, EnderecoResource b) -> a.getCep()
					.compareTo(b.getCep());
			Collections.sort(resource.getEnderecos(), compEnderecoResource);
			for (Endereco endereco : listEndereco) {
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getCep(), endereco.getCep());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getNumero(), endereco.getNumero());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getComplemento(),
						endereco.getComplemento());
				assertNotNull(endereco.getLogradouro());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getLogradouro(),
						endereco.getLogradouro().getNome());
				assertNotNull(endereco.getLogradouro().getBairro());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getBairro(),
						endereco.getLogradouro().getBairro().getNome());
				assertNotNull(endereco.getLogradouro().getBairro().getCidade());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getCidade(),
						endereco.getLogradouro().getBairro().getCidade().getNome());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getTipoUf(),
						endereco.getLogradouro().getBairro().getCidade().getTipoUf().name());
				indexOfResourceArray++;
			}
			assertEquals(resource.getResponsaveis().size(), entity.getResponsaveis().size());
			indexOfResourceArray = 0;
			List<Pessoa> listPessoa = Lists.newArrayList(entity.getResponsaveis());
			Comparator<Pessoa> compPessoa = (Pessoa a, Pessoa b) -> a.getCpf().compareTo(b.getCpf());
			Collections.sort(listPessoa, compPessoa);
			Comparator<ResponsavelResource> compPessoaResource = (ResponsavelResource a, ResponsavelResource b) -> a
					.getCpf().compareTo(b.getCpf());
			Collections.sort(resource.getResponsaveis(), compPessoaResource);
			for (Pessoa pessoa : listPessoa) {
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getCpf(), pessoa.getCpf());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getNome(), pessoa.getNome());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getDataNascimento().getYear(),
						pessoa.getDataNascimento().getYear());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getDataNascimento().getMonth(),
						pessoa.getDataNascimento().getMonthOfYear());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getDataNascimento().getDay(),
						pessoa.getDataNascimento().getDayOfMonth());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getTipoGrauInstrucao(),
						pessoa.getTipoGrauInstrucao().name());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getTipoEstadoCivil(),
						pessoa.getTipoEstadoCivil().name());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getTipoSexo(),
						pessoa.getTipoSexo().name());
				indexOfResourceArray++;
			}
			assertEquals(resource.getContasBancarias().size(), entity.getContas().size());
			indexOfResourceArray = 0;
			List<Conta> listConta = Lists.newArrayList(entity.getContas());
			Comparator<Conta> compConta = (Conta a, Conta b) -> a.getNumero().compareTo(b.getNumero());
			Collections.sort(listConta, compConta);
			Comparator<ContaBancariaResource> compContaResource = (ContaBancariaResource a,
					ContaBancariaResource b) -> a.getNumero().compareTo(b.getNumero());
			Collections.sort(resource.getContasBancarias(), compContaResource);
			for (Conta conta : listConta) {
				assertEquals(resource.getContasBancarias().get(indexOfResourceArray).getNumero(), conta.getNumero());
				assertNotNull(conta.getAgencia());
				assertEquals(resource.getContasBancarias().get(indexOfResourceArray).getNumeroAgencia(),
						conta.getAgencia().getNumero());
				assertEquals(resource.getContasBancarias().get(indexOfResourceArray).getCodigoBanco(),
						conta.getAgencia().getCodigoBanco());
				assertEquals(resource.getContasBancarias().get(indexOfResourceArray).getTipoContaBancaria(),
						conta.getTipoConta().name());
				indexOfResourceArray++;
			}
		} catch (AssemblerException e) {
			fail(e.toString());
		}
	}

	@Test
	public void testToEntities() {
		try {
			assertNull(getInstance().toEntities(null));
			List<EmpresaResource> resources = new ArrayList<>();
			assertEquals(Collections.EMPTY_LIST, getInstance().toEntities(resources));
			for (int i = 0; i < 10; i++) {
				resources.add(EmpresaResourceV1RandomBuilder.getInstance().build());
			}
			assertEquals(10, getInstance().toEntities(resources).size());			
		} catch (AssemblerException e) {
			fail(e.toString());
		}
	}

	@Test
	public void testToResource() {
		try {
			assertNull(getInstance().toResource(null));
			EmpresaResource resource = getInstance().toResource(entity);
			assertEquals(resource.getCnpj(), entity.getCnpj());
			assertEquals(resource.getRazaoSocial(), entity.getRazaoSocial());
			assertEquals(resource.getDataAbertura().getYear(), entity.getDataAbertura().getYear());
			assertEquals(resource.getDataAbertura().getMonth(), entity.getDataAbertura().getMonthOfYear());
			assertEquals(resource.getDataAbertura().getDay(), entity.getDataAbertura().getDayOfMonth());
			assertEquals(resource.getTipoEmpresa(), entity.getTipoEmpresa().name());
			assertEquals(resource.getTipoPorteEmpresa(), entity.getTipoPorteEmpresa().name());
			assertEquals(resource.getCelulares().size(), entity.getCelulares().size());
			int indexOfResourceArray = 0;
			List<Celular> listCelular = Lists.newArrayList(entity.getCelulares());
			Comparator<Celular> compCelular = (Celular a, Celular b) -> a.getNumero().compareTo(b.getNumero());
			Collections.sort(listCelular, compCelular);
			Comparator<CelularResource> compCelularResource = (CelularResource a, CelularResource b) -> a.getNumero()
					.compareTo(b.getNumero());
			Collections.sort(resource.getCelulares(), compCelularResource);
			for (Celular celular : listCelular) {
				assertEquals(resource.getCelulares().get(indexOfResourceArray).getNumero(), celular.getNumero());
				indexOfResourceArray++;
			}
			assertEquals(resource.getTelefonesFixos().size(), entity.getTelefonesFixo().size());
			indexOfResourceArray = 0;
			List<TelefoneFixo> listTelefoneFixo = Lists.newArrayList(entity.getTelefonesFixo());
			Comparator<TelefoneFixo> compTelefoneFixo = (TelefoneFixo a, TelefoneFixo b) -> a.getNumero()
					.compareTo(b.getNumero());
			Collections.sort(listTelefoneFixo, compTelefoneFixo);
			Comparator<TelefoneFixoResource> compTelefoneFixoResource = (TelefoneFixoResource a,
					TelefoneFixoResource b) -> a.getNumero().compareTo(b.getNumero());
			Collections.sort(resource.getTelefonesFixos(), compTelefoneFixoResource);
			for (TelefoneFixo telefoneFixo : listTelefoneFixo) {
				assertEquals(resource.getTelefonesFixos().get(indexOfResourceArray).getNumero(),
						telefoneFixo.getNumero());
				indexOfResourceArray++;
			}
			assertEquals(resource.getEmails().size(), entity.getEmails().size());
			indexOfResourceArray = 0;
			List<Email> listEmail = Lists.newArrayList(entity.getEmails());
			Comparator<Email> compEmail = (Email a, Email b) -> a.getEndereco().compareTo(b.getEndereco());
			Collections.sort(listEmail, compEmail);
			Comparator<EmailResource> compEmailResource = (EmailResource a, EmailResource b) -> a.getEndereco()
					.compareTo(b.getEndereco());
			Collections.sort(resource.getEmails(), compEmailResource);
			for (Email email : listEmail) {
				assertEquals(resource.getEmails().get(indexOfResourceArray).getEndereco(), email.getEndereco());
				indexOfResourceArray++;
			}
			assertEquals(resource.getEnderecos().size(), entity.getEnderecos().size());
			indexOfResourceArray = 0;
			List<Endereco> listEndereco = Lists.newArrayList(entity.getEnderecos());
			Comparator<Endereco> compEndereco = (Endereco a, Endereco b) -> a.getCep().compareTo(b.getCep());
			Collections.sort(listEndereco, compEndereco);
			Comparator<EnderecoResource> compEnderecoResource = (EnderecoResource a, EnderecoResource b) -> a.getCep()
					.compareTo(b.getCep());
			Collections.sort(resource.getEnderecos(), compEnderecoResource);
			for (Endereco endereco : listEndereco) {
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getCep(), endereco.getCep());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getNumero(), endereco.getNumero());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getComplemento(),
						endereco.getComplemento());
				assertNotNull(endereco.getLogradouro());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getLogradouro(),
						endereco.getLogradouro().getNome());
				assertNotNull(endereco.getLogradouro().getBairro());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getBairro(),
						endereco.getLogradouro().getBairro().getNome());
				assertNotNull(endereco.getLogradouro().getBairro().getCidade());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getCidade(),
						endereco.getLogradouro().getBairro().getCidade().getNome());
				assertEquals(resource.getEnderecos().get(indexOfResourceArray).getTipoUf(),
						endereco.getLogradouro().getBairro().getCidade().getTipoUf().name());
				indexOfResourceArray++;
			}
			assertEquals(resource.getResponsaveis().size(), entity.getResponsaveis().size());
			indexOfResourceArray = 0;
			List<Pessoa> listPessoa = Lists.newArrayList(entity.getResponsaveis());
			Comparator<Pessoa> compPessoa = (Pessoa a, Pessoa b) -> a.getCpf().compareTo(b.getCpf());
			Collections.sort(listPessoa, compPessoa);
			Comparator<ResponsavelResource> compPessoaResource = (ResponsavelResource a, ResponsavelResource b) -> a
					.getCpf().compareTo(b.getCpf());
			Collections.sort(resource.getResponsaveis(), compPessoaResource);
			for (Pessoa pessoa : listPessoa) {
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getCpf(), pessoa.getCpf());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getNome(), pessoa.getNome());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getDataNascimento().getYear(),
						pessoa.getDataNascimento().getYear());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getDataNascimento().getMonth(),
						pessoa.getDataNascimento().getMonthOfYear());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getDataNascimento().getDay(),
						pessoa.getDataNascimento().getDayOfMonth());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getTipoGrauInstrucao(),
						pessoa.getTipoGrauInstrucao().name());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getTipoEstadoCivil(),
						pessoa.getTipoEstadoCivil().name());
				assertEquals(resource.getResponsaveis().get(indexOfResourceArray).getTipoSexo(),
						pessoa.getTipoSexo().name());
				indexOfResourceArray++;
			}
			assertEquals(resource.getContasBancarias().size(), entity.getContas().size());
			indexOfResourceArray = 0;
			List<Conta> listConta = Lists.newArrayList(entity.getContas());
			Comparator<Conta> compConta = (Conta a, Conta b) -> a.getNumero().compareTo(b.getNumero());
			Collections.sort(listConta, compConta);
			Comparator<ContaBancariaResource> compContaResource = (ContaBancariaResource a,
					ContaBancariaResource b) -> a.getNumero().compareTo(b.getNumero());
			Collections.sort(resource.getContasBancarias(), compContaResource);
			for (Conta conta : listConta) {
				assertEquals(resource.getContasBancarias().get(indexOfResourceArray).getNumero(), conta.getNumero());
				assertNotNull(conta.getAgencia());
				assertEquals(resource.getContasBancarias().get(indexOfResourceArray).getNumeroAgencia(),
						conta.getAgencia().getNumero());
				assertEquals(resource.getContasBancarias().get(indexOfResourceArray).getCodigoBanco(),
						conta.getAgencia().getCodigoBanco());
				assertEquals(resource.getContasBancarias().get(indexOfResourceArray).getTipoContaBancaria(),
						conta.getTipoConta().name());
				indexOfResourceArray++;
			}
		} catch (AssemblerException e) {
			fail(e.toString());
		}
	}

	@Test
	public void testToResources() {
		try {
			assertNull(getInstance().toResources(null));
			List<Empresa> entities = new ArrayList<>();
			assertEquals(Collections.EMPTY_LIST, getInstance().toResources(entities));
			for (int i = 0; i < 10; i++) {
				entities.add(EmpresaRandomBuilder.getInstance().build());
			}
			assertEquals(10, getInstance().toResources(entities).size());
		} catch (AssemblerException e) {
			fail(e.toString());
		}
	}

}
