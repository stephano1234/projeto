package br.com.contmatic.assembler.empresa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.LocalDate;

import br.com.contmatic.dto.empresa.v1.CelularResource;
import br.com.contmatic.dto.empresa.v1.ContaBancariaResource;
import br.com.contmatic.dto.empresa.v1.EmailResource;
import br.com.contmatic.dto.empresa.v1.EmpresaResource;
import br.com.contmatic.dto.empresa.v1.EnderecoResource;
import br.com.contmatic.dto.empresa.v1.ResponsavelResource;
import br.com.contmatic.dto.empresa.v1.TelefoneFixoResource;
import br.com.contmatic.model.conta.Agencia;
import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;
import br.com.contmatic.model.endereco.Bairro;
import br.com.contmatic.model.endereco.Cidade;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.endereco.Logradouro;
import br.com.contmatic.model.endereco.TipoUf;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.pessoa.TipoEstadoCivil;
import br.com.contmatic.model.pessoa.TipoGrauInstrucao;
import br.com.contmatic.model.pessoa.TipoSexo;

public class EmpresaResourceAssembler {

	private static EmpresaResourceAssembler instance;
	
	private EmpresaResourceAssembler() {
	}
	
	public static EmpresaResourceAssembler getInstance() {
		if (instance == null) {
			instance = new EmpresaResourceAssembler();
		}
		return instance;
	}
	
	public Empresa toEntity(EmpresaResource resource) {
		Empresa entity = null;
		if (resource != null) {
			entity = new Empresa();
			entity.setCnpj(resource.getCnpj());
			entity.setRazaoSocial(resource.getRazaoSocial());
			entity.setDataAbertura(toEntityDate(resource.getDataAbertura()));
			entity.setResponsaveis(new HashSet<>());
			for (ResponsavelResource responsavelResource : resource.getResponsaveis()) {
				entity.getResponsaveis().add(toEntityResponsavel(responsavelResource));
			}
			entity.setEnderecos(new HashSet<>());
			for (EnderecoResource enderecoResource : resource.getEnderecos()) {
				entity.getEnderecos().add(toEntityEndereco(enderecoResource));
			}
			entity.setTelefonesFixo(new HashSet<>());
			for (TelefoneFixoResource telefoneFixoResource : resource.getTelefonesFixos()) {
				entity.getTelefonesFixo().add(toEntityTelefoneFixo(telefoneFixoResource));
			}
			entity.setEmails(new HashSet<>());
			for (EmailResource emailResource : resource.getEmails()) {
				entity.getEmails().add(toEntityEmail(emailResource));
			}
			entity.setCelulares(new HashSet<>());
			for (CelularResource celularResource : resource.getCelulares()) {
				entity.getCelulares().add(toEntityCelular(celularResource));
			}
			entity.setContas(new HashSet<>());
			for (ContaBancariaResource contaResource : resource.getContasBancarias()) {
				entity.getContas().add(toEntityContaBancaria(contaResource));
			}
			if (resource.getTipoEmpresa() != null) {
				entity.setTipoEmpresa(TipoEmpresa.valueOf(resource.getTipoEmpresa()));				
			}
			if (resource.getTipoPorteEmpresa() != null) {
				entity.setTipoPorteEmpresa(TipoPorteEmpresa.valueOf(resource.getTipoPorteEmpresa()));				
			}
		}
		return entity;
	}

	public List<Empresa> toEntities(List<EmpresaResource> resources) {
		List<Empresa> entities = new ArrayList<>();
		for (EmpresaResource resource : resources) {
			entities.add(toEntity(resource));
		}
		return entities;
	}
	
	private Pessoa toEntityResponsavel(ResponsavelResource resource) {
		Pessoa entity = null;
		if (resource != null) {
			entity = new Pessoa();
			entity.setCpf(resource.getCpf());
			entity.setNome(resource.getNome());
			entity.setDataNascimento(toEntityDate(resource.getDataNascimento()));
			if (resource.getTipoGrauInstrucao() != null) {
				entity.setTipoGrauInstrucao(TipoGrauInstrucao.valueOf(resource.getTipoGrauInstrucao()));				
			}
			if (resource.getTipoEstadoCivil() != null) {
				entity.setTipoEstadoCivil(TipoEstadoCivil.valueOf(resource.getTipoEstadoCivil()));				
			}
			if (resource.getTipoSexo() != null) {
				entity.setTipoSexo(TipoSexo.valueOf(resource.getTipoSexo()));				
			}
		}
		return entity;
	}

	private Endereco toEntityEndereco(EnderecoResource resource) {
		Endereco entity = null;
		if (resource != null) {
			entity = new Endereco();
			entity.setCep(resource.getCep());
			entity.setNumero(resource.getNumero());
			entity.setComplemento(resource.getComplemento());
			entity.setLogradouro(new Logradouro());
			entity.getLogradouro().setNome(resource.getLogradouro());
			entity.getLogradouro().setBairro(new Bairro());
			entity.getLogradouro().getBairro().setNome(resource.getBairro());
			entity.getLogradouro().getBairro().setCidade(new Cidade());
			entity.getLogradouro().getBairro().getCidade().setNome(resource.getCidade());
			if (resource.getTipoUf() != null) {
				entity.getLogradouro().getBairro().getCidade().setTipoUf(TipoUf.valueOf(resource.getTipoUf()));				
			}
		}
		return entity;
	}

	private TelefoneFixo toEntityTelefoneFixo(TelefoneFixoResource resource) {
		TelefoneFixo entity = null;
		if (resource != null) {
			entity = new TelefoneFixo();
			entity.setNumero(resource.getNumero());
		}
		return entity;
	}

	private Email toEntityEmail(EmailResource resource) {
		Email entity = null;
		if (resource != null) {
			entity = new Email();
			entity.setEndereco(resource.getEndereco());
		}
		return entity;
	}

	private Celular toEntityCelular(CelularResource resource) {
		Celular entity = null;
		if (resource != null) {
			entity = new Celular();
			entity.setNumero(resource.getNumero());
		}
		return entity;
	}

	private Conta toEntityContaBancaria(ContaBancariaResource resource) {
		Conta entity = null;
		if (resource != null) {
			entity = new Conta();
			entity.setNumero(resource.getNumero());
			entity.setAgencia(new Agencia());
			entity.getAgencia().setNumero(resource.getNumeroAgencia());
			entity.getAgencia().setCodigoBanco(resource.getCodigoBanco());
		}
		return entity;
	}

	private LocalDate toEntityDate(XMLGregorianCalendar resource) {
		LocalDate entity = null;
		if (resource != null) {
			entity = new LocalDate(resource.getYear(), resource.getMonth(), resource.getDay());
		}
		return entity;
	}

	public EmpresaResource toResource(Empresa entity) {
		EmpresaResource resource = null;
		if (entity != null) {
			resource = new EmpresaResource();
			resource.setCnpj(entity.getCnpj());
			resource.setRazaoSocial(entity.getRazaoSocial());
			resource.setDataAbertura(toResourceDate(entity.getDataAbertura()));
			for (Pessoa pessoa : entity.getResponsaveis()) {
				resource.getResponsaveis().add(toResourceResponsavel(pessoa));
			}
			for (Endereco endereco : entity.getEnderecos()) {
				resource.getEnderecos().add(toResourceEndereco(endereco));
			}
			for (TelefoneFixo telefoneFixo : entity.getTelefonesFixo()) {
				resource.getTelefonesFixos().add(toResourceTelefoneFixo(telefoneFixo));
			}
			for (Email email : entity.getEmails()) {
				resource.getEmails().add(toResourceEmail(email));
			}
			for (Celular celular : entity.getCelulares()) {
				resource.getCelulares().add(toResourceCelular(celular));
			}
			for (Conta conta : entity.getContas()) {
				resource.getContasBancarias().add(toResourceContaBancaria(conta));
			}
			resource.setTipoEmpresa(entity.getTipoEmpresa() != null ? entity.getTipoEmpresa().name() : null);
			resource.setTipoPorteEmpresa(
					entity.getTipoPorteEmpresa().name() != null ? entity.getTipoPorteEmpresa().name() : null);
		}
		return resource;
	}

	public List<EmpresaResource> toResources(List<Empresa> entities) {
		List<EmpresaResource> resources = new ArrayList<>();
		for (Empresa entity : entities) {
			resources.add(toResource(entity));
		}
		return resources;
	}
	
	private ResponsavelResource toResourceResponsavel(Pessoa entity) {
		ResponsavelResource resource = null;
		if (entity != null) {
			resource = new ResponsavelResource();
			resource.setCpf(entity.getCpf());
			resource.setNome(entity.getNome());
			resource.setDataNascimento(toResourceDate(entity.getDataNascimento()));
			resource.setTipoGrauInstrucao(
					entity.getTipoGrauInstrucao() != null ? entity.getTipoGrauInstrucao().name() : null);
			resource.setTipoEstadoCivil(
					entity.getTipoEstadoCivil() != null ? entity.getTipoEstadoCivil().name() : null);
			resource.setTipoSexo(entity.getTipoSexo() != null ? entity.getTipoSexo().name() : null);
		}
		return resource;
	}

	private EnderecoResource toResourceEndereco(Endereco entity) {
		EnderecoResource resource = null;
		if (entity != null) {
			resource = new EnderecoResource();
			resource.setCep(entity.getCep());
			resource.setNumero(entity.getNumero());
			resource.setComplemento(entity.getComplemento());
			resource.setLogradouro(entity.getLogradouro() != null ? entity.getLogradouro().getNome() : null);
			resource.setBairro(entity.getLogradouro() != null && entity.getLogradouro().getBairro() != null
					? entity.getLogradouro().getBairro().getNome()
					: null);
			resource.setCidade(entity.getLogradouro() != null && entity.getLogradouro().getBairro() != null
					&& entity.getLogradouro().getBairro().getCidade() != null
							? entity.getLogradouro().getBairro().getCidade().getNome()
							: null);
			resource.setTipoUf(entity.getLogradouro() != null && entity.getLogradouro().getBairro() != null
					&& entity.getLogradouro().getBairro().getCidade() != null
					&& entity.getLogradouro().getBairro().getCidade().getTipoUf() != null
							? entity.getLogradouro().getBairro().getCidade().getTipoUf().name()
							: null);
		}
		return resource;
	}

	private TelefoneFixoResource toResourceTelefoneFixo(TelefoneFixo entity) {
		TelefoneFixoResource resource = null;
		if (entity != null) {
			resource = new TelefoneFixoResource();
			resource.setNumero(entity.getNumero());
		}
		return resource;
	}

	private EmailResource toResourceEmail(Email entity) {
		EmailResource resource = null;
		if (entity != null) {
			resource = new EmailResource();
			resource.setEndereco(entity.getEndereco());
		}
		return resource;
	}

	private CelularResource toResourceCelular(Celular entity) {
		CelularResource resource = null;
		if (entity != null) {
			resource = new CelularResource();
			resource.setNumero(entity.getNumero());
		}
		return resource;
	}

	private ContaBancariaResource toResourceContaBancaria(Conta entity) {
		ContaBancariaResource resource = null;
		if (entity != null) {
			resource = new ContaBancariaResource();
			resource.setNumero(entity.getNumero());
			resource.setNumeroAgencia(entity.getAgencia() != null ? entity.getAgencia().getNumero() : null);
			resource.setCodigoBanco(entity.getAgencia() != null ? entity.getAgencia().getCodigoBanco() : null);
		}
		return resource;
	}

	private XMLGregorianCalendar toResourceDate(LocalDate entity) {
		XMLGregorianCalendar resource = null;
		if (entity != null) {
			try {
				resource = DatatypeFactory.newInstance().newXMLGregorianCalendar(entity.toString());
			} catch (DatatypeConfigurationException e) {
				return null;
			}
		}
		return resource;
	}

}
