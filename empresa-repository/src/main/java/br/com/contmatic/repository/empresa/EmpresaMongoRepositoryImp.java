package br.com.contmatic.repository.empresa;

import static br.com.contmatic.repository.mongodb.ConstantesMongo.COLLECTION;
import static br.com.contmatic.repository.mongodb.ConstantesMongo.DATABASE;
import static br.com.contmatic.repository.mongodb.ConstantesMongo.KEY_FIELD;
import static br.com.contmatic.repository.mongodb.ConstantesMongo.LOCAL_HOST;
import static br.com.contmatic.repository.mongodb.ConstantesMongo.PORTA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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

	private static final String FIELD_TIPO_PORTE_EMPRESA = "tipoPorteEmpresa";
	private static final String FIELD_TIPO_EMPRESA = "tipoEmpresa";
	private static final String FIELD_CONTAS = "contas";
	private static final String FIELD_CELULARES = "celulares";
	private static final String FIELD_EMAILS = "emails";
	private static final String FIELD_TELEFONES_FIXO = "telefonesFixo";
	private static final String FIELD_ENDERECOS = "enderecos";
	private static final String FIELD_CONTRATOS_TRABALHO = "contratosTrabalho";
	private static final String FIELD_RESPONSAVEIS = "responsaveis";
	private static final String FIELD_DATA_ABERTURA = "dataAbertura";
	private static final String FIELD_RAZAO_SOCIAL = "razaoSocial";

	public EmpresaMongoRepositoryImp(String hostName, int porta) {
		super(DATABASE, COLLECTION, KEY_FIELD, hostName, porta);
	}

	public EmpresaMongoRepositoryImp() {
		super(DATABASE, COLLECTION, KEY_FIELD, LOCAL_HOST, PORTA);
	}
	
	@Override
	public void createEmpresa(Empresa empresa) {
		super.create(empresa);
	}

	@Override
	public void deleteEmpresa(String cnpj) {
		super.delete(cnpj);
	}

	@Override
	public void updateEmpresa(String cnpj, Empresa empresa) {
		super.update(cnpj, empresa);
	}

	@Override
	public Empresa readByCnpj(String cnpj) {
		return super.readByKeyField(cnpj);
	}

	@Override
	public Collection<Empresa> readByRazaoSocial(String razaoSocial) {
		return super.read(razaoSocial, FIELD_RAZAO_SOCIAL);
	}

	@Override
	public Collection<Empresa> readByRazaoSocial(Set<String> razoesSocial) {
		return super.readMany(razoesSocial, FIELD_RAZAO_SOCIAL);
	}

	@Override
	public Collection<Empresa> readByDataAbertura(LocalDate dataAbertura) {
		return super.read(dataAbertura.toString(), FIELD_DATA_ABERTURA);
	}

	@Override
	public Collection<Empresa> readByDataAbertura(Set<LocalDate> datasAbertura) {
		Set<String> datasAberturaString = new HashSet<>();
		for (LocalDate dataAbertura : datasAbertura) {
			datasAberturaString.add(dataAbertura.toString());
		}
		return super.readMany(datasAberturaString, FIELD_DATA_ABERTURA);
	}

	@Override
	public Collection<Empresa> readByResponsaveis(Set<Pessoa> responsaveis) {
		Set<Document> docResponsaveis = new HashSet<>();
		for (Pessoa responsavel : responsaveis) {
			docResponsaveis.add(Document.parse(responsavel.toString()));
		}
		return super.readMany(docResponsaveis, FIELD_RESPONSAVEIS);
	}

	@Override
	public Collection<Empresa> readByGroupedResponsaveis(Set<Set<Pessoa>> groupedResponsaveis) {
		Set<List<Document>> groupedDocResponsaveis = new HashSet<>();
		for (Set<Pessoa> responsaveis : groupedResponsaveis) {
			List<Document> docResponsaveis = new ArrayList<>();
			for (Pessoa responsavel : responsaveis) {
				docResponsaveis.add(Document.parse(responsavel.toString()));
			}
			groupedDocResponsaveis.add(docResponsaveis);
		}
		return super.readMany(groupedDocResponsaveis, FIELD_RESPONSAVEIS);
	}

	@Override
	public Collection<Empresa> readByContratosTrabalho(Set<ContratoTrabalho> contratosTrabalho) {
		Set<Document> docContratosTrabalho = new HashSet<>();
		for (ContratoTrabalho contratoTrabalho : contratosTrabalho) {
			docContratosTrabalho.add(Document.parse(contratoTrabalho.toString()));
		}
		return super.readMany(docContratosTrabalho, FIELD_CONTRATOS_TRABALHO);
	}

	@Override
	public Collection<Empresa> readByGroupedContratosTrabalho(Set<Set<ContratoTrabalho>> groupedContratosTrabalho) {
		Set<List<Document>> groupedDocContratosTrabalho = new HashSet<>();
		for (Set<ContratoTrabalho> contratosTrabalho : groupedContratosTrabalho) {
			List<Document> docContratosTrabalho = new ArrayList<>();
			for (ContratoTrabalho contratoTrabalho : contratosTrabalho) {
				docContratosTrabalho.add(Document.parse(contratoTrabalho.toString()));
			}
			groupedDocContratosTrabalho.add(docContratosTrabalho);
		}
		return super.readMany(groupedDocContratosTrabalho, FIELD_CONTRATOS_TRABALHO);
	}

	@Override
	public Collection<Empresa> readByEnderecos(Set<Endereco> enderecos) {
		Set<Document> docEnderecos = new HashSet<>();
		for (Endereco endereco : enderecos) {
			docEnderecos.add(Document.parse(endereco.toString()));
		}
		return super.readMany(docEnderecos, FIELD_ENDERECOS);
	}

	@Override
	public Collection<Empresa> readByGroupedEnderecos(Set<Set<Endereco>> groupedEnderecos) {
		Set<List<Document>> groupedDocEnderecos = new HashSet<>();
		for (Set<Endereco> enderecos : groupedEnderecos) {
			List<Document> docEnderecos = new ArrayList<>();
			for (Endereco endereco : enderecos) {
				docEnderecos.add(Document.parse(endereco.toString()));
			}
			groupedDocEnderecos.add(docEnderecos);
		}
		return super.readMany(groupedDocEnderecos, FIELD_ENDERECOS);
	}

	@Override
	public Collection<Empresa> readByTelefonesFixo(Set<TelefoneFixo> telefonesFixo) {
		Set<Document> docTelefonesFixo = new HashSet<>();
		for (TelefoneFixo telefoneFixo : telefonesFixo) {
			docTelefonesFixo.add(Document.parse(telefoneFixo.toString()));
		}
		return super.readMany(docTelefonesFixo, FIELD_TELEFONES_FIXO);
	}

	@Override
	public Collection<Empresa> readByGroupedTelefonesFixo(Set<Set<TelefoneFixo>> groupedTelefonesFixo) {
		Set<List<Document>> groupedDocTelefonesFixo = new HashSet<>();
		for (Set<TelefoneFixo> telefonesFixo : groupedTelefonesFixo) {
			List<Document> docTelefonesFixo = new ArrayList<>();
			for (TelefoneFixo telefoneFixo : telefonesFixo) {
				docTelefonesFixo.add(Document.parse(telefoneFixo.toString()));
			}
			groupedDocTelefonesFixo.add(docTelefonesFixo);
		}
		return super.readMany(groupedDocTelefonesFixo, FIELD_TELEFONES_FIXO);
	}

	@Override
	public Collection<Empresa> readByEmails(Set<Email> emails) {
		Set<Document> docEmails = new HashSet<>();
		for (Email email : emails) {
			docEmails.add(Document.parse(email.toString()));
		}
		return super.readMany(docEmails, FIELD_EMAILS);
	}

	@Override
	public Collection<Empresa> readByGroupedEmails(Set<Set<Email>> groupedEmails) {
		Set<List<Document>> groupedDocEmails = new HashSet<>();
		for (Set<Email> emails : groupedEmails) {
			List<Document> docEmails = new ArrayList<>();
			for (Email email : emails) {
				docEmails.add(Document.parse(email.toString()));
			}
			groupedDocEmails.add(docEmails);
		}
		return super.readMany(groupedDocEmails, FIELD_EMAILS);
	}

	@Override
	public Collection<Empresa> readByCelulares(Set<Celular> celulares) {
		Set<Document> docCelulares = new HashSet<>();
		for (Celular celular : celulares) {
			docCelulares.add(Document.parse(celular.toString()));
		}
		return super.readMany(docCelulares, FIELD_CELULARES);
	}

	@Override
	public Collection<Empresa> readByGroupedCelulares(Set<Set<Celular>> groupedCelulares) {
		Set<List<Document>> groupedDocCelulares = new HashSet<>();
		for (Set<Celular> celulares : groupedCelulares) {
			List<Document> docCelulares = new ArrayList<>();
			for (Celular celular : celulares) {
				docCelulares.add(Document.parse(celular.toString()));
			}
			groupedDocCelulares.add(docCelulares);
		}
		return super.readMany(groupedDocCelulares, FIELD_CELULARES);
	}

	@Override
	public Collection<Empresa> readByContas(Set<Conta> contas) {
		Set<Document> docContas = new HashSet<>();
		for (Conta conta : contas) {
			docContas.add(Document.parse(conta.toString()));
		}
		return super.readMany(docContas, FIELD_CONTAS);
	}

	@Override
	public Collection<Empresa> readByGroupedContas(Set<Set<Conta>> groupedContas) {
		Set<List<Document>> groupedDocContas = new HashSet<>();
		for (Set<Conta> contas : groupedContas) {
			List<Document> docContas = new ArrayList<>();
			for (Conta conta : contas) {
				docContas.add(Document.parse(conta.toString()));
			}
			groupedDocContas.add(docContas);
		}
		return super.readMany(groupedDocContas, FIELD_CONTAS);
	}

	@Override
	public Collection<Empresa> readByTipoEmpresa(TipoEmpresa tipoEmpresa) {
		return super.read(tipoEmpresa.name(), FIELD_TIPO_EMPRESA);
	}

	@Override
	public Collection<Empresa> readByTipoPorteEmpresa(TipoPorteEmpresa tipoPorteEmpresa) {
		return super.read(tipoPorteEmpresa.name(), FIELD_TIPO_PORTE_EMPRESA);
	}

	@Override
	public Collection<Empresa> readAllEmpresas() {
		return super.readAll();
	}

	@Override
	public long countAllEmpresas() {
		return super.count();
	}

	@Override
	protected Empresa toObject(Document bson) {
		if (bson == null) {
			return null;
		}
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
		empresa.setRazaoSocial(bson.get(FIELD_RAZAO_SOCIAL, String.class));
		if (bson.get(FIELD_DATA_ABERTURA, String.class) != null) {
			empresa.setDataAbertura(LocalDate.parse(bson.get(FIELD_DATA_ABERTURA, String.class)));
		}
		if (bson.getList(FIELD_RESPONSAVEIS, Document.class) != null) {
			final Set<Pessoa> responsaveis = new HashSet<>();
			for (Document docResponsavel : bson.getList(FIELD_RESPONSAVEIS, Document.class)) {
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
				if (docResponsavel.getList(FIELD_ENDERECOS, Document.class) != null) {
					final Set<Endereco> enderecos = new HashSet<>();
					for (Document docEndereco : docResponsavel.getList(FIELD_ENDERECOS, Document.class)) {
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
									cidade.setTipoUf(docCidade.get("tipoUf", String.class) != null
											? TipoUf.valueOf(docCidade.get("tipoUf", String.class))
											: null);
									bairro.setCidade(cidade);
								}
								logradouro.setBairro(bairro);
							}
							endereco.setLogradouro(logradouro);
						}
						if (docEndereco.getList(FIELD_TELEFONES_FIXO, Document.class) != null) {
							final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
							for (Document docTelefoneFixo : docEndereco.getList(FIELD_TELEFONES_FIXO, Document.class)) {
								final TelefoneFixo telefoneFixo = new TelefoneFixo();
								telefoneFixo.setDdd(null);
								telefoneFixo.setNumero(null);
								telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
								telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
								telefonesFixo.add(telefoneFixo);
							}
							endereco.setTelefonesFixo(telefonesFixo);
						}
						endereco.setTipoEndereco(docEndereco.get("tipoEndereco", String.class) != null
								? TipoEndereco.valueOf(docEndereco.get("tipoEndereco", String.class))
								: null);
						enderecos.add(endereco);
					}
					pessoa.setEnderecos(enderecos);
				}
				if (docResponsavel.get("dataNascimento", String.class) != null) {
					pessoa.setDataNascimento(LocalDate.parse(docResponsavel.get("dataNascimento", String.class)));
				}
				if (docResponsavel.getList(FIELD_CELULARES, Document.class) != null) {
					final Set<Celular> celulares = new HashSet<>();
					for (Document docCelular : docResponsavel.getList(FIELD_CELULARES, Document.class)) {
						final Celular celular = new Celular();
						celular.setDdd(null);
						celular.setNumero(null);
						celular.setTipoContatoCelular(null);
						celular.setDdd(docCelular.get("ddd", String.class));
						celular.setNumero(docCelular.get("numero", String.class));
						celular.setTipoContatoCelular(docCelular.get("tipoContatoCelular", String.class) != null
								? TipoContatoCelular.valueOf(docCelular.get("tipoContatoCelular", String.class))
								: null);
						celulares.add(celular);
					}
					pessoa.setCelulares(celulares);
				}
				if (docResponsavel.getList(FIELD_TELEFONES_FIXO, Document.class) != null) {
					final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
					for (Document docTelefoneFixo : docResponsavel.getList(FIELD_TELEFONES_FIXO, Document.class)) {
						final TelefoneFixo telefoneFixo = new TelefoneFixo();
						telefoneFixo.setDdd(null);
						telefoneFixo.setNumero(null);
						telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
						telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
						telefonesFixo.add(telefoneFixo);
					}
					pessoa.setTelefonesFixo(telefonesFixo);
				}
				if (docResponsavel.getList(FIELD_EMAILS, Document.class) != null) {
					final Set<Email> emails = new HashSet<>();
					for (Document docEmail : docResponsavel.getList(FIELD_EMAILS, Document.class)) {
						final Email email = new Email();
						email.setEndereco(null);
						email.setEndereco(docEmail.get("endereco", String.class));
						emails.add(email);
					}
					pessoa.setEmails(emails);
				}
				pessoa.setTipoGrauInstrucao(docResponsavel.get("tipoGrauInstrucao", String.class) != null
						? TipoGrauInstrucao.valueOf(docResponsavel.get("tipoGrauInstrucao", String.class))
						: null);
				pessoa.setTipoEstadoCivil(docResponsavel.get("tipoEstadoCivil", String.class) != null
						? TipoEstadoCivil.valueOf(docResponsavel.get("tipoEstadoCivil", String.class))
						: null);
				pessoa.setTipoSexo(docResponsavel.get("tipoSexo", String.class) != null
						? TipoSexo.valueOf(docResponsavel.get("tipoSexo", String.class))
						: null);
				if (docResponsavel.getList(FIELD_CONTAS, Document.class) != null) {
					final Set<Conta> contas = new HashSet<>();
					for (Document docConta : docResponsavel.getList(FIELD_CONTAS, Document.class)) {
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
						conta.setTipoConta(docConta.get("tipoConta", String.class) != null
								? TipoConta.valueOf(docConta.get("tipoConta", String.class))
								: null);
						contas.add(conta);
					}
					pessoa.setContas(contas);
				}
				responsaveis.add(pessoa);
			}
			empresa.setResponsaveis(responsaveis);
		}
		if (bson.getList(FIELD_CONTRATOS_TRABALHO, Document.class) != null) {
			final Set<ContratoTrabalho> contratosTrabalho = new HashSet<>();
			for (Document docContratoTrabalho : bson.getList(FIELD_CONTRATOS_TRABALHO, Document.class)) {
				final ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
				contratoTrabalho.setDataInicioContrato(null);
				contratoTrabalho.setPessoa(null);
				contratoTrabalho.setTipoContratoTrabalho(null);
				if (docContratoTrabalho.get("dataInicioContrato", String.class) != null) {
					contratoTrabalho.setDataInicioContrato(
							LocalDate.parse(docContratoTrabalho.get("dataInicioContrato", String.class)));
				}
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
					if (docPessoa.getList(FIELD_ENDERECOS, Document.class) != null) {
						final Set<Endereco> enderecos = new HashSet<>();
						for (Document docEndereco : docPessoa.getList(FIELD_ENDERECOS, Document.class)) {
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
										cidade.setTipoUf(docCidade.get("tipoUf", String.class) != null
												? TipoUf.valueOf(docCidade.get("tipoUf", String.class))
												: null);
										bairro.setCidade(cidade);
									}
									logradouro.setBairro(bairro);
								}
								endereco.setLogradouro(logradouro);
							}
							if (docEndereco.getList(FIELD_TELEFONES_FIXO, Document.class) != null) {
								final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
								for (Document docTelefoneFixo : docEndereco.getList(FIELD_TELEFONES_FIXO,
										Document.class)) {
									final TelefoneFixo telefoneFixo = new TelefoneFixo();
									telefoneFixo.setDdd(null);
									telefoneFixo.setNumero(null);
									telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
									telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
									telefonesFixo.add(telefoneFixo);
								}
								endereco.setTelefonesFixo(telefonesFixo);
							}
							endereco.setTipoEndereco(docEndereco.get("tipoEndereco", String.class) != null
									? TipoEndereco.valueOf(docEndereco.get("tipoEndereco", String.class))
									: null);
							enderecos.add(endereco);
						}
						pessoa.setEnderecos(enderecos);
					}
					if (docPessoa.get("dataNascimento", String.class) != null) {
						pessoa.setDataNascimento(LocalDate.parse(docPessoa.get("dataNascimento", String.class)));
					}
					if (docPessoa.getList(FIELD_CELULARES, Document.class) != null) {
						final Set<Celular> celulares = new HashSet<>();
						for (Document docCelular : docPessoa.getList(FIELD_CELULARES, Document.class)) {
							final Celular celular = new Celular();
							celular.setDdd(null);
							celular.setNumero(null);
							celular.setTipoContatoCelular(null);
							celular.setDdd(docCelular.get("ddd", String.class));
							celular.setNumero(docCelular.get("numero", String.class));
							celular.setTipoContatoCelular(docCelular.get("tipoContatoCelular", String.class) != null
									? TipoContatoCelular.valueOf(docCelular.get("tipoContatoCelular", String.class))
									: null);
							celulares.add(celular);
						}
						pessoa.setCelulares(celulares);
					}
					if (docPessoa.getList(FIELD_TELEFONES_FIXO, Document.class) != null) {
						final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
						for (Document docTelefoneFixo : docPessoa.getList(FIELD_TELEFONES_FIXO, Document.class)) {
							final TelefoneFixo telefoneFixo = new TelefoneFixo();
							telefoneFixo.setDdd(null);
							telefoneFixo.setNumero(null);
							telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
							telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
							telefonesFixo.add(telefoneFixo);
						}
						pessoa.setTelefonesFixo(telefonesFixo);
					}
					if (docPessoa.getList(FIELD_EMAILS, Document.class) != null) {
						final Set<Email> emails = new HashSet<>();
						for (Document docEmail : docPessoa.getList(FIELD_EMAILS, Document.class)) {
							final Email email = new Email();
							email.setEndereco(null);
							email.setEndereco(docEmail.get("endereco", String.class));
							emails.add(email);
						}
						pessoa.setEmails(emails);
					}
					pessoa.setTipoGrauInstrucao(docPessoa.get("tipoGrauInstrucao", String.class) != null
							? TipoGrauInstrucao.valueOf(docPessoa.get("tipoGrauInstrucao", String.class))
							: null);
					pessoa.setTipoEstadoCivil(docPessoa.get("tipoEstadoCivil", String.class) != null
							? TipoEstadoCivil.valueOf(docPessoa.get("tipoEstadoCivil", String.class))
							: null);
					pessoa.setTipoSexo(docPessoa.get("tipoSexo", String.class) != null
							? TipoSexo.valueOf(docPessoa.get("tipoSexo", String.class))
							: null);
					if (docPessoa.getList(FIELD_CONTAS, Document.class) != null) {
						final Set<Conta> contas = new HashSet<>();
						for (Document docConta : docPessoa.getList(FIELD_CONTAS, Document.class)) {
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
							conta.setTipoConta(docConta.get("tipoConta", String.class) != null
									? TipoConta.valueOf(docConta.get("tipoConta", String.class))
									: null);
							contas.add(conta);
						}
						pessoa.setContas(contas);
					}
					contratoTrabalho.setPessoa(pessoa);
				}
				contratoTrabalho.setTipoContratoTrabalho(
						docContratoTrabalho.get("tipoContratoTrabalho", String.class) != null ? TipoContratoTrabalho
								.valueOf(docContratoTrabalho.get("tipoContratoTrabalho", String.class)) : null);
				contratosTrabalho.add(contratoTrabalho);
			}
			empresa.setContratosTrabalho(contratosTrabalho);
		}
		if (bson.getList(FIELD_ENDERECOS, Document.class) != null) {
			final Set<Endereco> enderecos = new HashSet<>();
			for (Document docEndereco : bson.getList(FIELD_ENDERECOS, Document.class)) {
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
							cidade.setTipoUf(docCidade.get("tipoUf", String.class) != null
									? TipoUf.valueOf(docCidade.get("tipoUf", String.class))
									: null);
							bairro.setCidade(cidade);
						}
						logradouro.setBairro(bairro);
					}
					endereco.setLogradouro(logradouro);
				}
				if (docEndereco.getList(FIELD_TELEFONES_FIXO, Document.class) != null) {
					final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
					for (Document docTelefoneFixo : docEndereco.getList(FIELD_TELEFONES_FIXO, Document.class)) {
						final TelefoneFixo telefoneFixo = new TelefoneFixo();
						telefoneFixo.setDdd(null);
						telefoneFixo.setNumero(null);
						telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
						telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
						telefonesFixo.add(telefoneFixo);
					}
					endereco.setTelefonesFixo(telefonesFixo);
				}
				endereco.setTipoEndereco(docEndereco.get("tipoEndereco", String.class) != null
						? TipoEndereco.valueOf(docEndereco.get("tipoEndereco", String.class))
						: null);
				enderecos.add(endereco);
			}
			empresa.setEnderecos(enderecos);
		}
		if (bson.getList(FIELD_TELEFONES_FIXO, Document.class) != null) {
			final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
			for (Document docTelefoneFixo : bson.getList(FIELD_TELEFONES_FIXO, Document.class)) {
				final TelefoneFixo telefoneFixo = new TelefoneFixo();
				telefoneFixo.setDdd(null);
				telefoneFixo.setNumero(null);
				telefoneFixo.setDdd(docTelefoneFixo.get("ddd", String.class));
				telefoneFixo.setNumero(docTelefoneFixo.get("numero", String.class));
				telefonesFixo.add(telefoneFixo);
			}
			empresa.setTelefonesFixo(telefonesFixo);
		}
		if (bson.getList(FIELD_EMAILS, Document.class) != null) {
			final Set<Email> emails = new HashSet<>();
			for (Document docEmail : bson.getList(FIELD_EMAILS, Document.class)) {
				final Email email = new Email();
				email.setEndereco(null);
				email.setEndereco(docEmail.get("endereco", String.class));
				emails.add(email);
			}
			empresa.setEmails(emails);
		}
		if (bson.getList(FIELD_CELULARES, Document.class) != null) {
			final Set<Celular> celulares = new HashSet<>();
			for (Document docCelular : bson.getList(FIELD_CELULARES, Document.class)) {
				final Celular celular = new Celular();
				celular.setDdd(null);
				celular.setNumero(null);
				celular.setTipoContatoCelular(null);
				celular.setDdd(docCelular.get("ddd", String.class));
				celular.setNumero(docCelular.get("numero", String.class));
				celular.setTipoContatoCelular(docCelular.get("tipoContatoCelular", String.class) != null
						? TipoContatoCelular.valueOf(docCelular.get("tipoContatoCelular", String.class))
						: null);
				celulares.add(celular);
			}
			empresa.setCelulares(celulares);
		}
		if (bson.getList(FIELD_CONTAS, Document.class) != null) {
			final Set<Conta> contas = new HashSet<>();
			for (Document docConta : bson.getList(FIELD_CONTAS, Document.class)) {
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
				conta.setTipoConta(docConta.get("tipoConta", String.class) != null
						? TipoConta.valueOf(docConta.get("tipoConta", String.class))
						: null);
				contas.add(conta);
			}
			empresa.setContas(contas);
		}
		empresa.setTipoEmpresa(bson.get(FIELD_TIPO_EMPRESA, String.class) != null
				? TipoEmpresa.valueOf(bson.get(FIELD_TIPO_EMPRESA, String.class))
				: null);
		empresa.setTipoPorteEmpresa(bson.get(FIELD_TIPO_PORTE_EMPRESA, String.class) != null
				? TipoPorteEmpresa.valueOf(bson.get(FIELD_TIPO_PORTE_EMPRESA, String.class))
				: null);
		return empresa;
	}

}
