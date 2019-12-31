package br.com.contmatic.repository.empresa;

import static br.com.contmatic.repository.mongodb.ConstantesMongo.COLLECTION;
import static br.com.contmatic.repository.mongodb.ConstantesMongo.DATABASE;
import static br.com.contmatic.repository.mongodb.ConstantesMongo.KEY_FIELD;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bson.Document;
import org.joda.time.LocalDate;

import br.com.contmatic.model.conta.Agencia;
import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.conta.TipoConta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.contato.TipoContatoCelular;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;
import br.com.contmatic.model.endereco.Bairro;
import br.com.contmatic.model.endereco.Cidade;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.endereco.Logradouro;
import br.com.contmatic.model.endereco.TipoEndereco;
import br.com.contmatic.model.endereco.TipoUf;
import br.com.contmatic.model.pessoa.ContratoTrabalho;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.pessoa.TipoContratoTrabalho;
import br.com.contmatic.model.pessoa.TipoEstadoCivil;
import br.com.contmatic.model.pessoa.TipoGrauInstrucao;
import br.com.contmatic.model.pessoa.TipoSexo;
import br.com.contmatic.repository.mongodb.MongoRepository;

public final class EmpresaMongoRepositoryImp extends MongoRepository<Empresa> implements EmpresaRepository {

	public EmpresaMongoRepositoryImp() {
		super(DATABASE, COLLECTION, KEY_FIELD);
	}

	@Override
	public Empresa readByCnpj(String cnpj) {
		return super.readByKeyField(cnpj);
	}

	@Override
	public Collection<Empresa> readByRazaoSocial(String razaoSocial) {
		return super.read(razaoSocial, "razaoSocial");
	}

	@Override
	public Collection<Empresa> readByDataAbertura(LocalDate dataAbertura) {
		return super.read(dataAbertura, "dataAbertura");
	}

	@Override
	public Collection<Empresa> readByResponsaveis(Set<Pessoa> responsaveis) {
		Collection<Empresa> empresas = new HashSet<>();
		for (Pessoa responsavel : responsaveis) {
			empresas.addAll(super.read(Document.parse(responsavel.toString()), "responsaveis"));
		}
		return empresas;
	}

	@Override
	public Collection<Empresa> readByGroupedResponsaveis(Set<Set<Pessoa>> groupedResponsaveis) {
		Collection<Empresa> empresas = new HashSet<>();
		for (Set<Pessoa> responsaveis : groupedResponsaveis) {
			empresas.addAll(super.read(Document.parse(Arrays.deepToString(responsaveis.toArray())), "responsaveis"));
		}
		return empresas;
	}
	
	@Override
	public Collection<Empresa> readByContratosTrabalho(Set<ContratoTrabalho> contratosTrabalho) {
		
		return null;
	}

	@Override
	public Collection<Empresa> readByEnderecos(Set<Endereco> enderecos) {
		
		return null;
	}

	@Override
	public Collection<Empresa> readByTelefonesFixo(Set<TelefoneFixo> telefonesFixo) {
		
		return null;
	}

	@Override
	public Collection<Empresa> readByEmails(Set<Email> emails) {
		
		return null;
	}

	@Override
	public Collection<Empresa> readByCelulares(Set<Celular> celulares) {
		
		return null;
	}

	@Override
	public Collection<Empresa> readByContas(Set<Conta> contas) {
		
		return null;
	}

	@Override
	public Collection<Empresa> readByTipoEmpresa(TipoEmpresa tipoEmpresa) {
		return super.read(tipoEmpresa.name(), "tipoEmpresa");
	}

	@Override
	public Collection<Empresa> readByTipoPorteEmpresa(TipoPorteEmpresa tipoPorteEmpresa) {
		return super.read(tipoPorteEmpresa.name(), "tipoPorteEmpresa");
	}

	@Override
	protected Empresa toObject(Document bson) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(null);
		empresa.setRazaoSocial(null);
		empresa.setDataAbertura(null);
		empresa.setResponsaveis(null);
		empresa.setContratosTrabalho(null);
		empresa.setEnderecos(null);
		empresa.setTelefonesFixo(null);
		empresa.setEmails(null);
		empresa.setCelulares(null);
		empresa.setContas(null);
		empresa.setTipoEmpresa(null);
		empresa.setTipoPorteEmpresa(null);
		empresa.setCnpj(bson.get(KEY_FIELD, String.class));
		empresa.setRazaoSocial(bson.get("razaoSocial", String.class));
		empresa.setDataAbertura(LocalDate.parse(bson.get("dataAbertura", String.class)));
		final Set<Pessoa> responsaveis = new HashSet<>();
		for (Document docResponsavel : bson.getList("responsaveis", Document.class)) {
			final Pessoa pessoa = new Pessoa();
			pessoa.setCpf(null);
			pessoa.setNome(null);
			pessoa.setEnderecos(null);
			pessoa.setDataNascimento(null);
			pessoa.setCelulares(null);
			pessoa.setTelefonesFixo(null);
			pessoa.setEmails(null);
			pessoa.setTipoGrauInstrucao(null);
			pessoa.setTipoEstadoCivil(null);
			pessoa.setTipoSexo(null);
			pessoa.setContas(null);
			pessoa.setCpf(docResponsavel.get("cpf", String.class));
			pessoa.setNome(docResponsavel.get("nome", String.class));
			final Set<Endereco> enderecos = new HashSet<>();
			for (Document docEndereco : docResponsavel.getList("enderecos", Document.class)) {
				final Endereco endereco = new Endereco();
				endereco.setCep(null);
				endereco.setNumero(null);
				endereco.setComplemento(null);
				endereco.setLogradouro(null);
				endereco.setTelefonesFixo(null);
				endereco.setTipoEndereco(null);
				endereco.setCep(docEndereco.get("cep", String.class));
				endereco.setNumero(docEndereco.get("numero", String.class));
				endereco.setComplemento(docEndereco.get("complemento", String.class));
				if (docEndereco.get("logradouro", Document.class) != null) {
					final Document docLogradouro = docEndereco.get("logradouro", Document.class);
					final Logradouro logradouro = new Logradouro();
					logradouro.setNome(null);
					logradouro.setBairro(null);
					logradouro.setNome(docLogradouro.get("nome", String.class));
					if (docLogradouro.get("bairro", Document.class) != null) {
						final Document docBairro = docLogradouro.get("bairro", Document.class);
						final Bairro bairro = new Bairro();
						bairro.setNome(null);
						bairro.setCidade(null);
						bairro.setNome(docBairro.get("nome", String.class));
						if (docBairro.get("cidade", Document.class) != null) {
							final Document docCidade = docBairro.get("cidade", Document.class);
							final Cidade cidade = new Cidade();
							cidade.setNome(null);
							cidade.setTipoUf(null);
							cidade.setNome(docCidade.get("nome", String.class));
							cidade.setTipoUf(docCidade.get("tipoUf", String.class) != null ? TipoUf.valueOf(docCidade.get("tipoUf", String.class)) : null);
							bairro.setCidade(cidade);
						}
						logradouro.setBairro(bairro);
					}
					endereco.setLogradouro(logradouro);
				}
				final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
				for (Document docTelefoneFixo : docEndereco.getList("telefonesFixo", Document.class)) {
					final TelefoneFixo telefoneFixo = new TelefoneFixo();
					telefoneFixo.setDdd(null);
					telefoneFixo.setNumero(null);
					telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
					telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
					telefonesFixo.add(telefoneFixo);
				}
				endereco.setTelefonesFixo(telefonesFixo);
				endereco.setTipoEndereco(docEndereco.get("tipoEndereco", String.class) != null ? TipoEndereco.valueOf(docEndereco.get("tipoEndereco", String.class)) : null);
				enderecos.add(endereco);
			}
			pessoa.setEnderecos(enderecos);
			pessoa.setDataNascimento(LocalDate.parse(docResponsavel.get("dataNascimento", String.class)));
			final Set<Celular> celulares = new HashSet<>();
			for (Document docCelular : docResponsavel.getList("celulares", Document.class)) {
				final Celular celular = new Celular();
				celular.setDdd(null);
				celular.setNumero(null);
				celular.setTipoContatoCelular(null);
				celular.setDdd(docCelular.get("ddd", String.class));
				celular.setNumero(docCelular.get("numero", String.class));
				celular.setTipoContatoCelular(docCelular.get("tipoContatoCelular", String.class) != null ? TipoContatoCelular.valueOf(docCelular.get("tipoContatoCelular", String.class)) : null);
				celulares.add(celular);
			}
			pessoa.setCelulares(celulares);
			final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
			for (Document docTelefoneFixo : docResponsavel.getList("telefonesFixo", Document.class)) {
				final TelefoneFixo telefoneFixo = new TelefoneFixo();
				telefoneFixo.setDdd(null);
				telefoneFixo.setNumero(null);
				telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
				telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
				telefonesFixo.add(telefoneFixo);
			}
			pessoa.setTelefonesFixo(telefonesFixo);
			final Set<Email> emails = new HashSet<>();
			for (Document docEmail : docResponsavel.getList("emails", Document.class)) {
				final Email email = new Email();
				email.setEndereco(null);
				email.setEndereco(docEmail.get("endereco", String.class));
				emails.add(email);
			}
			pessoa.setEmails(emails);
			pessoa.setTipoGrauInstrucao(docResponsavel.get("tipoGrauInstrucao", String.class) != null ? TipoGrauInstrucao.valueOf(docResponsavel.get("tipoGrauInstrucao", String.class)) : null);
			pessoa.setTipoEstadoCivil(docResponsavel.get("tipoEstadoCivil", String.class) != null ? TipoEstadoCivil.valueOf(docResponsavel.get("tipoEstadoCivil", String.class)) : null);
			pessoa.setTipoSexo(docResponsavel.get("tipoSexo", String.class) != null ? TipoSexo.valueOf(docResponsavel.get("tipoSexo", String.class)) : null);
			final Set<Conta> contas = new HashSet<>();
			for (Document docConta : docResponsavel.getList("contas", Document.class)) {
				final Conta conta = new Conta();
				conta.setAgencia(null);
				conta.setNumero(null);
				conta.setTipoConta(null);
				if (docConta.get("agencia", Document.class) != null) {
					final Document docAgencia = docConta.get("agencia", Document.class);
					final Agencia agencia = new Agencia();
					agencia.setCodigoBanco(null);
					agencia.setNumero(null);
					agencia.setCodigoBanco(docAgencia.get("codigoBanco", String.class));
					agencia.setNumero(docAgencia.get("numero", String.class));
					conta.setAgencia(agencia);
				}
				conta.setNumero(docConta.get("numero", String.class));
				conta.setTipoConta(docConta.get("tipoConta", String.class) != null ? TipoConta.valueOf(docConta.get("tipoConta", String.class)) : null);
				contas.add(conta);
			}
			pessoa.setContas(contas);
			responsaveis.add(pessoa);
		}
		empresa.setResponsaveis(responsaveis);
		final Set<ContratoTrabalho> contratosTrabalho = new HashSet<>();
		for (Document docContratoTrabalho : bson.getList("contratosTrabalho", Document.class)) {
			final ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
			contratoTrabalho.setDataInicioContrato(null);
			contratoTrabalho.setPessoa(null);
			contratoTrabalho.setTipoContratoTrabalho(null);
			contratoTrabalho.setDataInicioContrato(LocalDate.parse(docContratoTrabalho.get("dataInicioContrato", String.class)));
			if (docContratoTrabalho.get("pessoa", Document.class) != null) {	
				Document docPessoa = docContratoTrabalho.get("pessoa", Document.class);
				final Pessoa pessoa = new Pessoa();
				pessoa.setCpf(null);
				pessoa.setNome(null);
				pessoa.setEnderecos(null);
				pessoa.setDataNascimento(null);
				pessoa.setCelulares(null);
				pessoa.setTelefonesFixo(null);
				pessoa.setEmails(null);
				pessoa.setTipoGrauInstrucao(null);
				pessoa.setTipoEstadoCivil(null);
				pessoa.setTipoSexo(null);
				pessoa.setContas(null);
				pessoa.setCpf(docPessoa.get("cpf", String.class));
				pessoa.setNome(docPessoa.get("nome", String.class));
				final Set<Endereco> enderecos = new HashSet<>();
				for (Document docEndereco : docPessoa.getList("enderecos", Document.class)) {
					final Endereco endereco = new Endereco();
					endereco.setCep(null);
					endereco.setNumero(null);
					endereco.setComplemento(null);
					endereco.setLogradouro(null);
					endereco.setTelefonesFixo(null);
					endereco.setTipoEndereco(null);
					endereco.setCep(docEndereco.get("cep", String.class));
					endereco.setNumero(docEndereco.get("numero", String.class));
					endereco.setComplemento(docEndereco.get("complemento", String.class));
					if (docEndereco.get("logradouro", Document.class) != null) {
						final Document docLogradouro = docEndereco.get("logradouro", Document.class);
						final Logradouro logradouro = new Logradouro();
						logradouro.setNome(null);
						logradouro.setBairro(null);
						logradouro.setNome(docLogradouro.get("nome", String.class));
						if (docLogradouro.get("bairro", Document.class) != null) {
							final Document docBairro = docLogradouro.get("bairro", Document.class);
							final Bairro bairro = new Bairro();
							bairro.setNome(null);
							bairro.setCidade(null);
							bairro.setNome(docBairro.get("nome", String.class));
							if (docBairro.get("cidade", Document.class) != null) {
								final Document docCidade = docBairro.get("cidade", Document.class);
								final Cidade cidade = new Cidade();
								cidade.setNome(null);
								cidade.setTipoUf(null);
								cidade.setNome(docCidade.get("nome", String.class));
								cidade.setTipoUf(docCidade.get("tipoUf", String.class) != null ? TipoUf.valueOf(docCidade.get("tipoUf", String.class)) : null);
								bairro.setCidade(cidade);
							}
							logradouro.setBairro(bairro);
						}
						endereco.setLogradouro(logradouro);
					}
					final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
					for (Document docTelefoneFixo : docEndereco.getList("telefonesFixo", Document.class)) {
						final TelefoneFixo telefoneFixo = new TelefoneFixo();
						telefoneFixo.setDdd(null);
						telefoneFixo.setNumero(null);
						telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
						telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
						telefonesFixo.add(telefoneFixo);
					}
					endereco.setTelefonesFixo(telefonesFixo);
					endereco.setTipoEndereco(docEndereco.get("tipoEndereco", String.class) != null ? TipoEndereco.valueOf(docEndereco.get("tipoEndereco", String.class)) : null);
					enderecos.add(endereco);
				}
				pessoa.setEnderecos(enderecos);
				pessoa.setDataNascimento(LocalDate.parse(docPessoa.get("dataNascimento", String.class)));
				final Set<Celular> celulares = new HashSet<>();
				for (Document docCelular : docPessoa.getList("celulares", Document.class)) {
					final Celular celular = new Celular();
					celular.setDdd(null);
					celular.setNumero(null);
					celular.setTipoContatoCelular(null);
					celular.setDdd(docCelular.get("ddd", String.class));
					celular.setNumero(docCelular.get("numero", String.class));
					celular.setTipoContatoCelular(docCelular.get("tipoContatoCelular", String.class) != null ? TipoContatoCelular.valueOf(docCelular.get("tipoContatoCelular", String.class)) : null);
					celulares.add(celular);
				}
				pessoa.setCelulares(celulares);
				final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
				for (Document docTelefoneFixo : docPessoa.getList("telefonesFixo", Document.class)) {
					final TelefoneFixo telefoneFixo = new TelefoneFixo();
					telefoneFixo.setDdd(null);
					telefoneFixo.setNumero(null);
					telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
					telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
					telefonesFixo.add(telefoneFixo);
				}
				pessoa.setTelefonesFixo(telefonesFixo);
				final Set<Email> emails = new HashSet<>();
				for (Document docEmail : docPessoa.getList("emails", Document.class)) {
					final Email email = new Email();
					email.setEndereco(null);
					email.setEndereco(docEmail.get("endereco", String.class));
					emails.add(email);
				}
				pessoa.setEmails(emails);
				pessoa.setTipoGrauInstrucao(docPessoa.get("tipoGrauInstrucao", String.class) != null ? TipoGrauInstrucao.valueOf(docPessoa.get("tipoGrauInstrucao", String.class)) : null);
				pessoa.setTipoEstadoCivil(docPessoa.get("tipoEstadoCivil", String.class) != null ? TipoEstadoCivil.valueOf(docPessoa.get("tipoEstadoCivil", String.class)) : null);
				pessoa.setTipoSexo(docPessoa.get("tipoSexo", String.class) != null ? TipoSexo.valueOf(docPessoa.get("tipoSexo", String.class)) : null);
				final Set<Conta> contas = new HashSet<>();
				for (Document docConta : docPessoa.getList("contas", Document.class)) {
					final Conta conta = new Conta();
					conta.setAgencia(null);
					conta.setNumero(null);
					conta.setTipoConta(null);
					if (docConta.get("agencia", Document.class) != null) {
						final Document docAgencia = docConta.get("agencia", Document.class);
						final Agencia agencia = new Agencia();
						agencia.setCodigoBanco(null);
						agencia.setNumero(null);
						agencia.setCodigoBanco(docAgencia.get("codigoBanco", String.class));
						agencia.setNumero(docAgencia.get("numero", String.class));
						conta.setAgencia(agencia);
					}
					conta.setNumero(docConta.get("numero", String.class));
					conta.setTipoConta(docConta.get("tipoConta", String.class) != null ? TipoConta.valueOf(docConta.get("tipoConta", String.class)) : null);
					contas.add(conta);
				}
				pessoa.setContas(contas);
				contratoTrabalho.setPessoa(pessoa);
			}
			contratoTrabalho.setTipoContratoTrabalho(docContratoTrabalho.get("tipoContratoTrabalho", String.class) != null ? TipoContratoTrabalho.valueOf(docContratoTrabalho.get("tipoContratoTrabalho", String.class)) : null);
			contratosTrabalho.add(contratoTrabalho);
		}
		empresa.setContratosTrabalho(contratosTrabalho);
		final Set<Endereco> enderecos = new HashSet<>();
		for (Document docEndereco : bson.getList("enderecos", Document.class)) {
			final Endereco endereco = new Endereco();
			endereco.setCep(null);
			endereco.setNumero(null);
			endereco.setComplemento(null);
			endereco.setLogradouro(null);
			endereco.setTelefonesFixo(null);
			endereco.setTipoEndereco(null);
			endereco.setCep(docEndereco.get("cep", String.class));
			endereco.setNumero(docEndereco.get("numero", String.class));
			endereco.setComplemento(docEndereco.get("complemento", String.class));
			if (docEndereco.get("logradouro", Document.class) != null) {
				final Document docLogradouro = docEndereco.get("logradouro", Document.class);
				final Logradouro logradouro = new Logradouro();
				logradouro.setNome(null);
				logradouro.setBairro(null);
				logradouro.setNome(docLogradouro.get("nome", String.class));
				if (docLogradouro.get("bairro", Document.class) != null) {
					final Document docBairro = docLogradouro.get("bairro", Document.class);
					final Bairro bairro = new Bairro();
					bairro.setNome(null);
					bairro.setCidade(null);
					bairro.setNome(docBairro.get("nome", String.class));
					if (docBairro.get("cidade", Document.class) != null) {
						final Document docCidade = docBairro.get("cidade", Document.class);
						final Cidade cidade = new Cidade();
						cidade.setNome(null);
						cidade.setTipoUf(null);
						cidade.setNome(docCidade.get("nome", String.class));
						cidade.setTipoUf(docCidade.get("tipoUf", String.class) != null ? TipoUf.valueOf(docCidade.get("tipoUf", String.class)) : null);
						bairro.setCidade(cidade);
					}
					logradouro.setBairro(bairro);
				}
				endereco.setLogradouro(logradouro);
			}
			final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
			for (Document docTelefoneFixo : docEndereco.getList("telefonesFixo", Document.class)) {
				final TelefoneFixo telefoneFixo = new TelefoneFixo();
				telefoneFixo.setDdd(null);
				telefoneFixo.setNumero(null);
				telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
				telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
				telefonesFixo.add(telefoneFixo);
			}
			endereco.setTelefonesFixo(telefonesFixo);
			endereco.setTipoEndereco(docEndereco.get("tipoEndereco", String.class) != null ? TipoEndereco.valueOf(docEndereco.get("tipoEndereco", String.class)) : null);
			enderecos.add(endereco);
		}
		empresa.setEnderecos(enderecos);
		final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
		for (Document docTelefoneFixo : bson.getList("telefonesFixo", Document.class)) {
			final TelefoneFixo telefoneFixo = new TelefoneFixo();
			telefoneFixo.setDdd(null);
			telefoneFixo.setNumero(null);
			telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
			telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
			telefonesFixo.add(telefoneFixo);
		}
		empresa.setTelefonesFixo(telefonesFixo);
		final Set<Email> emails = new HashSet<>();
		for (Document docEmail : bson.getList("emails", Document.class)) {
			final Email email = new Email();
			email.setEndereco(null);
			email.setEndereco(docEmail.get("endereco", String.class));
			emails.add(email);
		}
		empresa.setEmails(emails);
		final Set<Celular> celulares = new HashSet<>();
		for (Document docCelular : bson.getList("celulares", Document.class)) {
			final Celular celular = new Celular();
			celular.setDdd(null);
			celular.setNumero(null);
			celular.setTipoContatoCelular(null);
			celular.setDdd(docCelular.get("ddd", String.class));
			celular.setNumero(docCelular.get("numero", String.class));
			celular.setTipoContatoCelular(docCelular.get("tipoContatoCelular", String.class) != null ? TipoContatoCelular.valueOf(docCelular.get("tipoContatoCelular", String.class)) : null);
			celulares.add(celular);
		}
		empresa.setCelulares(celulares);
		final Set<Conta> contas = new HashSet<>();
		for (Document docConta : bson.getList("contas", Document.class)) {
			final Conta conta = new Conta();
			conta.setAgencia(null);
			conta.setNumero(null);
			conta.setTipoConta(null);
			if (docConta.get("agencia", Document.class) != null) {
				final Document docAgencia = docConta.get("agencia", Document.class);
				final Agencia agencia = new Agencia();
				agencia.setCodigoBanco(null);
				agencia.setNumero(null);
				agencia.setCodigoBanco(docAgencia.get("codigoBanco", String.class));
				agencia.setNumero(docAgencia.get("numero", String.class));
				conta.setAgencia(agencia);
			}
			conta.setNumero(docConta.get("numero", String.class));
			conta.setTipoConta(docConta.get("tipoConta", String.class) != null ? TipoConta.valueOf(docConta.get("tipoConta", String.class)) : null);
			contas.add(conta);
		}
		empresa.setContas(contas);
		empresa.setTipoEmpresa(bson.get("tipoEmpresa", String.class) != null ? TipoEmpresa.valueOf(bson.get("tipoEmpresa", String.class)) : null);
		empresa.setTipoPorteEmpresa(bson.get("tipoPorteEmpresa", String.class) != null ? TipoPorteEmpresa.valueOf(bson.get("tipoPorteEmpresa", String.class)) : null);
		return empresa;
	}

	@Override
	protected Document toBson(Empresa object) {
		return Document.parse(object.toString());
	}

}
