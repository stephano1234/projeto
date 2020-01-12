package br.com.contmatic.repository.empresa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import org.joda.time.LocalDate;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

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

public final class EmpresaMongoRepositoryImpl implements EmpresaMongoRepository {

	private static final String FIELD_CNPJ = "cnpj";
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

	private MongoCollection<Document> mongoCollection;

	public EmpresaMongoRepositoryImpl(MongoDatabase mongoDatabase) {
		this.mongoCollection = mongoDatabase.getCollection("empresa");
	}

	@Override
	public void create(Empresa empresa) {
		this.mongoCollection.insertOne(this.empresaToDocument(empresa));
	}

	@Override
	public void delete(String cnpj) {
		this.mongoCollection.deleteOne(Filters.eq(FIELD_CNPJ, cnpj));
	}

	@Override
	public void update(String cnpj, Empresa empresa) {
		this.mongoCollection.replaceOne(Filters.eq(FIELD_CNPJ, cnpj), this.empresaToDocument(empresa));
	}

	@Override
	public Empresa readByCnpj(String cnpj) {
		return this.documentToEmpresa(this.mongoCollection.find(Filters.eq(FIELD_CNPJ, cnpj))
				.projection(Projections.fields(Projections.excludeId())).first());
	}

	@Override
	public List<List<String>> readCnpjAndRazaoSocialByRazaoSocial(String razaoSocial) {
		List<List<String>> cnpjsAndRazoesSociais = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find(Filters.eq(FIELD_RAZAO_SOCIAL, razaoSocial))
				.projection(Projections.fields(Projections.include(FIELD_CNPJ, FIELD_RAZAO_SOCIAL), Projections.excludeId()))
				.iterator();
		try {
			while (mongoCursor.hasNext()) {
				List<String> cnpjAndRazaoSocial = new ArrayList<>();
				Document filteredDocument = mongoCursor.next();
				cnpjAndRazaoSocial.add(filteredDocument.getString(FIELD_CNPJ));
				cnpjAndRazaoSocial.add(filteredDocument.getString(FIELD_RAZAO_SOCIAL));
				cnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);
			}
		} finally {
			mongoCursor.close();
		}
		return cnpjsAndRazoesSociais;
	}

	@Override
	public List<List<String>> readCnpjAndRazaoSocialByResponsavelCpf(String cpf) {
		List<List<String>> cnpjsAndRazoesSociais = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find(Filters.eq(FIELD_RESPONSAVEIS + ".cpf", cpf))
				.projection(Projections.fields(Projections.include(FIELD_CNPJ, FIELD_RAZAO_SOCIAL), Projections.excludeId()))
				.iterator();
		try {
			while (mongoCursor.hasNext()) {
				List<String> cnpjAndRazaoSocial = new ArrayList<>();
				Document filteredDocument = mongoCursor.next();
				cnpjAndRazaoSocial.add(filteredDocument.getString(FIELD_CNPJ));
				cnpjAndRazaoSocial.add(filteredDocument.getString(FIELD_RAZAO_SOCIAL));
				cnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);
			}
		} finally {
			mongoCursor.close();
		}
		return cnpjsAndRazoesSociais;
	}

	@Override
	public List<List<String>> readCnpjAndRazaoSocialByTipoEmpresa(TipoEmpresa tipoEmpresa) {
		List<List<String>> cnpjsAndRazoesSociais = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find(Filters.eq(FIELD_TIPO_EMPRESA, tipoEmpresa.name()))
				.projection(Projections.fields(Projections.include(FIELD_CNPJ, FIELD_RAZAO_SOCIAL), Projections.excludeId()))
				.iterator();
		try {
			while (mongoCursor.hasNext()) {
				List<String> cnpjAndRazaoSocial = new ArrayList<>();
				Document filteredDocument = mongoCursor.next();
				cnpjAndRazaoSocial.add(filteredDocument.getString(FIELD_CNPJ));
				cnpjAndRazaoSocial.add(filteredDocument.getString(FIELD_RAZAO_SOCIAL));
				cnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);
			}
		} finally {
			mongoCursor.close();
		}
		return cnpjsAndRazoesSociais;
	}

	@Override
	public List<List<String>> readCnpjAndRazaoSocialByTipoPorteEmpresa(TipoPorteEmpresa tipoPorteEmpresa) {
		List<List<String>> cnpjsAndRazoesSociais = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find(Filters.eq(FIELD_TIPO_PORTE_EMPRESA, tipoPorteEmpresa.name()))
				.projection(Projections.fields(Projections.include(FIELD_CNPJ, FIELD_RAZAO_SOCIAL), Projections.excludeId()))
				.iterator();
		try {
			while (mongoCursor.hasNext()) {
				List<String> cnpjAndRazaoSocial = new ArrayList<>();
				Document filteredDocument = mongoCursor.next();
				cnpjAndRazaoSocial.add(filteredDocument.getString(FIELD_CNPJ));
				cnpjAndRazaoSocial.add(filteredDocument.getString(FIELD_RAZAO_SOCIAL));
				cnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);
			}
		} finally {
			mongoCursor.close();
		}
		return cnpjsAndRazoesSociais;
	}

	@Override
	public List<List<String>> readAllCnpjAndRazaoSocial() {
		List<List<String>> cnpjsAndRazoesSociais = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find()
				.projection(Projections.fields(Projections.include(FIELD_CNPJ, FIELD_RAZAO_SOCIAL), Projections.excludeId()))
				.iterator();
		try {
			while (mongoCursor.hasNext()) {
				List<String> cnpjAndRazaoSocial = new ArrayList<>();
				Document filteredDocument = mongoCursor.next();
				cnpjAndRazaoSocial.add(filteredDocument.getString(FIELD_CNPJ));
				cnpjAndRazaoSocial.add(filteredDocument.getString(FIELD_RAZAO_SOCIAL));
				cnpjsAndRazoesSociais.add(cnpjAndRazaoSocial);
			}
		} finally {
			mongoCursor.close();
		}
		return cnpjsAndRazoesSociais;
	}

	@Override
	public long countAll() {
		return this.mongoCollection.countDocuments();
	}

	public Empresa documentToEmpresa(Document bson) {
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
		empresa.setCnpj(bson.get(FIELD_CNPJ, String.class));
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

	public Document empresaToDocument(Empresa empresa) {
		final Document docEmpresa = new Document();
		docEmpresa.clear();
		docEmpresa.append(FIELD_CNPJ, null);
		docEmpresa.append(FIELD_RAZAO_SOCIAL, null);
		docEmpresa.append(FIELD_DATA_ABERTURA, null);
		docEmpresa.append(FIELD_RESPONSAVEIS, null);
		docEmpresa.append(FIELD_CONTRATOS_TRABALHO, null);
		docEmpresa.append(FIELD_ENDERECOS, null);
		docEmpresa.append(FIELD_TELEFONES_FIXO, null);
		docEmpresa.append(FIELD_EMAILS, null);
		docEmpresa.append(FIELD_CELULARES, null);
		docEmpresa.append(FIELD_CONTAS, null);
		docEmpresa.append(FIELD_TIPO_EMPRESA, null);
		docEmpresa.append(FIELD_TIPO_PORTE_EMPRESA, null);
		if (empresa.getCnpj() != null) {
			docEmpresa.put(FIELD_CNPJ, empresa.getCnpj());
		}
		if (empresa.getRazaoSocial() != null) {
			docEmpresa.put(FIELD_RAZAO_SOCIAL, empresa.getRazaoSocial());
		}
		if (empresa.getDataAbertura() != null) {
			docEmpresa.put(FIELD_DATA_ABERTURA, empresa.getDataAbertura().toString());
		}
		if (empresa.getResponsaveis() != null) {
			final List<Document> docResponsaveis = new ArrayList<>();
			for (Pessoa responsavel : empresa.getResponsaveis()) {
				final Document docResponsavel = new Document();
				docResponsavel.clear();
				docResponsavel.append("cpf", null);
				docResponsavel.append("nome", null);
				docResponsavel.append("enderecos", null);
				docResponsavel.append("dataNascimento", null);
				docResponsavel.append("celulares", null);
				docResponsavel.append("telefonesFixo", null);
				docResponsavel.append("emails", null);
				docResponsavel.append("tipoGrauInstrucao", null);
				docResponsavel.append("tipoEstadoCivil", null);
				docResponsavel.append("tipoSexo", null);
				docResponsavel.append("contas", null);
				if (responsavel.getCpf() != null) {
					docResponsavel.put("cpf", responsavel.getCpf());
				}
				if (responsavel.getNome() != null) {
					docResponsavel.put("nome", responsavel.getNome());
				}
				if (responsavel.getEnderecos() != null) {
					final List<Document> docEnderecos = new ArrayList<>();
					for (Endereco endereco : responsavel.getEnderecos()) {
						final Document docEndereco = new Document();
						docEndereco.clear();
						docEndereco.append("cep", null);
						docEndereco.append("numero", null);
						docEndereco.append("complemento", null);
						docEndereco.append("logradouro", null);
						docEndereco.append("telefonesFixo", null);
						docEndereco.append("tipoEndereco", null);
						if (endereco.getCep() != null) {
							docEndereco.put("cep", endereco.getCep());
						}
						if (endereco.getNumero() != null) {
							docEndereco.put("numero", endereco.getNumero());
						}
						if (endereco.getComplemento() != null) {
							docEndereco.put("complemento", endereco.getComplemento());
						}
						if (endereco.getLogradouro() != null) {
							final Document docLogradouro = new Document();
							docLogradouro.clear();
							docLogradouro.append("nome", null);
							docLogradouro.append("bairro", null);
							if (endereco.getLogradouro().getNome() != null) {
								docLogradouro.put("nome", endereco.getLogradouro().getNome());
							}
							if (endereco.getLogradouro().getBairro() != null) {
								final Document docBairro = new Document();
								docBairro.clear();
								docBairro.append("nome", null);
								docBairro.append("cidade", null);
								if (endereco.getLogradouro().getBairro().getNome() != null) {
									docBairro.put("nome", endereco.getLogradouro().getBairro().getNome());
								}
								if (endereco.getLogradouro().getBairro().getCidade() != null) {
									final Document docCidade = new Document();
									docCidade.clear();
									docCidade.append("nome", null);
									docCidade.append("tipoUf", null);
									if (endereco.getLogradouro().getBairro().getCidade().getNome() != null) {
										docCidade.put("nome",
												endereco.getLogradouro().getBairro().getCidade().getNome());
									}
									if (endereco.getLogradouro().getBairro().getCidade().getTipoUf() != null) {
										docCidade.put("tipoUf",
												endereco.getLogradouro().getBairro().getCidade().getTipoUf().name());
									}
									docBairro.put("cidade", docCidade);
								}
								docLogradouro.put("bairro", docBairro);
							}
							docEndereco.put("logradouro", docLogradouro);
						}
						if (endereco.getTelefonesFixo() != null) {
							final List<Document> docTelefonesFixo = new ArrayList<>();
							for (TelefoneFixo telefoneFixo : endereco.getTelefonesFixo()) {
								final Document docTelefoneFixo = new Document();
								docTelefoneFixo.clear();
								docTelefoneFixo.append("ddd", null);
								docTelefoneFixo.append("numero", null);
								if (telefoneFixo.getDdd() != null) {
									docTelefoneFixo.put("ddd", telefoneFixo.getDdd());
								}
								if (telefoneFixo.getNumero() != null) {
									docTelefoneFixo.put("numero", telefoneFixo.getNumero());
								}
								docTelefonesFixo.add(docTelefoneFixo);
							}
							docEndereco.put("telefonesFixo", docTelefonesFixo);
						}
						if (endereco.getTipoEndereco() != null) {
							docEndereco.put("tipoEndereco", endereco.getTipoEndereco().name());
						}
						docEnderecos.add(docEndereco);
					}
					docResponsavel.put("enderecos", docEnderecos);
				}
				if (responsavel.getDataNascimento() != null) {
					docResponsavel.put("dataNascimento", responsavel.getDataNascimento().toString());
				}
				if (responsavel.getCelulares() != null) {
					final List<Document> docCelulares = new ArrayList<>();
					for (Celular celular : responsavel.getCelulares()) {
						final Document docCelular = new Document();
						docCelular.clear();
						docCelular.append("ddd", null);
						docCelular.append("numero", null);
						docCelular.append("tipoContatoCelular", null);
						if (celular.getDdd() != null) {
							docCelular.put("ddd", celular.getDdd());
						}
						if (celular.getNumero() != null) {
							docCelular.put("numero", celular.getNumero());
						}
						if (celular.getTipoContatoCelular() != null) {
							docCelular.put("tipoContatoCelular", celular.getTipoContatoCelular().name());
						}
						docCelulares.add(docCelular);
					}
					docResponsavel.put("celulares", docCelulares);
				}
				if (responsavel.getTelefonesFixo() != null) {
					final List<Document> docTelefonesFixo = new ArrayList<>();
					for (TelefoneFixo telefoneFixo : responsavel.getTelefonesFixo()) {
						final Document docTelefoneFixo = new Document();
						docTelefoneFixo.clear();
						docTelefoneFixo.append("ddd", null);
						docTelefoneFixo.append("numero", null);
						if (telefoneFixo.getDdd() != null) {
							docTelefoneFixo.put("ddd", telefoneFixo.getDdd());
						}
						if (telefoneFixo.getNumero() != null) {
							docTelefoneFixo.put("numero", telefoneFixo.getNumero());
						}
						docTelefonesFixo.add(docTelefoneFixo);
					}
					docResponsavel.put("telefonesFixo", docTelefonesFixo);
				}
				if (responsavel.getEmails() != null) {
					final List<Document> docEmails = new ArrayList<>();
					for (Email email : responsavel.getEmails()) {
						final Document docEmail = new Document();
						docEmail.clear();
						docEmail.append("endereco", null);
						if (email.getEndereco() != null) {
							docEmail.put("endereco", email.getEndereco());
						}
						docEmails.add(docEmail);
					}
					docResponsavel.put("emails", docEmails);
				}
				if (responsavel.getTipoGrauInstrucao() != null) {
					docResponsavel.put("tipoGrauInstrucao", responsavel.getTipoGrauInstrucao().name());
				}
				if (responsavel.getTipoEstadoCivil() != null) {
					docResponsavel.put("tipoEstadoCivil", responsavel.getTipoEstadoCivil().name());
				}
				if (responsavel.getTipoSexo() != null) {
					docResponsavel.put("tipoSexo", responsavel.getTipoSexo().name());
				}
				if (responsavel.getContas() != null) {
					final List<Document> docContas = new ArrayList<>();
					for (Conta conta : responsavel.getContas()) {
						final Document docConta = new Document();
						docConta.clear();
						docConta.append("numero", null);
						docConta.append("agencia", null);
						docConta.append("tipoConta", null);
						if (conta.getNumero() != null) {
							docConta.put("numero", conta.getNumero());
						}
						if (conta.getAgencia() != null) {
							final Document docAgencia = new Document();
							docAgencia.clear();
							docAgencia.append("numero", null);
							docAgencia.append("codigoBanco", null);
							if (conta.getAgencia().getNumero() != null) {
								docAgencia.put("numero", conta.getAgencia().getNumero());
							}
							if (conta.getAgencia().getCodigoBanco() != null) {
								docAgencia.put("codigoBanco", conta.getAgencia().getCodigoBanco());
							}
							docConta.put("agencia", docAgencia);
						}
						if (conta.getTipoConta() != null) {
							docConta.put("tipoConta", conta.getTipoConta().name());
						}
						docContas.add(docConta);
					}
					docResponsavel.put("contas", docContas);
				}
				docResponsaveis.add(docResponsavel);
			}
			docEmpresa.put(FIELD_RESPONSAVEIS, docResponsaveis);
		}
		if (empresa.getContratosTrabalho() != null) {
			final List<Document> docContratosTrabalho = new ArrayList<>();
			for (ContratoTrabalho contratoTrabalho : empresa.getContratosTrabalho()) {
				final Document docContratoTrabalho = new Document();
				docContratoTrabalho.clear();
				docContratoTrabalho.append("pessoa", null);
				docContratoTrabalho.append("tipoContratoTrabalho", null);
				docContratoTrabalho.append("dataInicioContrato", null);
				if (contratoTrabalho.getPessoa() != null) {
					final Document docPessoa = new Document();
					docPessoa.clear();
					docPessoa.append("cpf", null);
					docPessoa.append("nome", null);
					docPessoa.append("enderecos", null);
					docPessoa.append("dataNascimento", null);
					docPessoa.append("celulares", null);
					docPessoa.append("telefonesFixo", null);
					docPessoa.append("emails", null);
					docPessoa.append("tipoGrauInstrucao", null);
					docPessoa.append("tipoEstadoCivil", null);
					docPessoa.append("tipoSexo", null);
					docPessoa.append("contas", null);
					if (contratoTrabalho.getPessoa().getCpf() != null) {
						docPessoa.put("cpf", contratoTrabalho.getPessoa().getCpf());
					}
					if (contratoTrabalho.getPessoa().getNome() != null) {
						docPessoa.put("nome", contratoTrabalho.getPessoa().getNome());
					}
					if (contratoTrabalho.getPessoa().getEnderecos() != null) {
						final List<Document> docEnderecos = new ArrayList<>();
						for (Endereco endereco : contratoTrabalho.getPessoa().getEnderecos()) {
							final Document docEndereco = new Document();
							docEndereco.clear();
							docEndereco.append("cep", null);
							docEndereco.append("numero", null);
							docEndereco.append("complemento", null);
							docEndereco.append("logradouro", null);
							docEndereco.append("telefonesFixo", null);
							docEndereco.append("tipoEndereco", null);
							if (endereco.getCep() != null) {
								docEndereco.put("cep", endereco.getCep());
							}
							if (endereco.getNumero() != null) {
								docEndereco.put("numero", endereco.getNumero());
							}
							if (endereco.getComplemento() != null) {
								docEndereco.put("complemento", endereco.getComplemento());
							}
							if (endereco.getLogradouro() != null) {
								final Document docLogradouro = new Document();
								docLogradouro.clear();
								docLogradouro.append("nome", null);
								docLogradouro.append("bairro", null);
								if (endereco.getLogradouro().getNome() != null) {
									docLogradouro.put("nome", endereco.getLogradouro().getNome());
								}
								if (endereco.getLogradouro().getBairro() != null) {
									final Document docBairro = new Document();
									docBairro.clear();
									docBairro.append("nome", null);
									docBairro.append("cidade", null);
									if (endereco.getLogradouro().getBairro().getNome() != null) {
										docBairro.put("nome", endereco.getLogradouro().getBairro().getNome());
									}
									if (endereco.getLogradouro().getBairro().getCidade() != null) {
										final Document docCidade = new Document();
										docCidade.clear();
										docCidade.append("nome", null);
										docCidade.append("tipoUf", null);
										if (endereco.getLogradouro().getBairro().getCidade().getNome() != null) {
											docCidade.put("nome",
													endereco.getLogradouro().getBairro().getCidade().getNome());
										}
										if (endereco.getLogradouro().getBairro().getCidade().getTipoUf() != null) {
											docCidade.put("tipoUf", endereco.getLogradouro().getBairro().getCidade()
													.getTipoUf().name());
										}
										docBairro.put("cidade", docCidade);
									}
									docLogradouro.put("bairro", docBairro);
								}
								docEndereco.put("logradouro", docLogradouro);
							}
							if (endereco.getTelefonesFixo() != null) {
								final List<Document> docTelefonesFixo = new ArrayList<>();
								for (TelefoneFixo telefoneFixo : endereco.getTelefonesFixo()) {
									final Document docTelefoneFixo = new Document();
									docTelefoneFixo.clear();
									docTelefoneFixo.append("ddd", null);
									docTelefoneFixo.append("numero", null);
									if (telefoneFixo.getDdd() != null) {
										docTelefoneFixo.put("ddd", telefoneFixo.getDdd());
									}
									if (telefoneFixo.getNumero() != null) {
										docTelefoneFixo.put("numero", telefoneFixo.getNumero());
									}
									docTelefonesFixo.add(docTelefoneFixo);
								}
								docEndereco.put("telefonesFixo", docTelefonesFixo);
							}
							if (endereco.getTipoEndereco() != null) {
								docEndereco.put("tipoEndereco", endereco.getTipoEndereco().name());
							}
							docEnderecos.add(docEndereco);
						}
						docPessoa.put("enderecos", docEnderecos);
					}
					if (contratoTrabalho.getPessoa().getDataNascimento() != null) {
						docPessoa.put("dataNascimento", contratoTrabalho.getPessoa().getDataNascimento().toString());
					}
					if (contratoTrabalho.getPessoa().getCelulares() != null) {
						final List<Document> docCelulares = new ArrayList<>();
						for (Celular celular : contratoTrabalho.getPessoa().getCelulares()) {
							final Document docCelular = new Document();
							docCelular.clear();
							docCelular.append("ddd", null);
							docCelular.append("numero", null);
							docCelular.append("tipoContatoCelular", null);
							if (celular.getDdd() != null) {
								docCelular.put("ddd", celular.getDdd());
							}
							if (celular.getNumero() != null) {
								docCelular.put("numero", celular.getNumero());
							}
							if (celular.getTipoContatoCelular() != null) {
								docCelular.put("tipoContatoCelular", celular.getTipoContatoCelular().name());
							}
							docCelulares.add(docCelular);
						}
						docPessoa.put("celulares", docCelulares);
					}
					if (contratoTrabalho.getPessoa().getTelefonesFixo() != null) {
						final List<Document> docTelefonesFixo = new ArrayList<>();
						for (TelefoneFixo telefoneFixo : contratoTrabalho.getPessoa().getTelefonesFixo()) {
							final Document docTelefoneFixo = new Document();
							docTelefoneFixo.clear();
							docTelefoneFixo.append("ddd", null);
							docTelefoneFixo.append("numero", null);
							if (telefoneFixo.getDdd() != null) {
								docTelefoneFixo.put("ddd", telefoneFixo.getDdd());
							}
							if (telefoneFixo.getNumero() != null) {
								docTelefoneFixo.put("numero", telefoneFixo.getNumero());
							}
							docTelefonesFixo.add(docTelefoneFixo);
						}
						docPessoa.put("telefonesFixo", docTelefonesFixo);
					}
					if (contratoTrabalho.getPessoa().getEmails() != null) {
						final List<Document> docEmails = new ArrayList<>();
						for (Email email : contratoTrabalho.getPessoa().getEmails()) {
							final Document docEmail = new Document();
							docEmail.clear();
							docEmail.append("endereco", null);
							if (email.getEndereco() != null) {
								docEmail.put("endereco", email.getEndereco());
							}
							docEmails.add(docEmail);
						}
						docPessoa.put("emails", docEmails);
					}
					if (contratoTrabalho.getPessoa().getTipoGrauInstrucao() != null) {
						docPessoa.put("tipoGrauInstrucao", contratoTrabalho.getPessoa().getTipoGrauInstrucao().name());
					}
					if (contratoTrabalho.getPessoa().getTipoEstadoCivil() != null) {
						docPessoa.put("tipoEstadoCivil", contratoTrabalho.getPessoa().getTipoEstadoCivil().name());
					}
					if (contratoTrabalho.getPessoa().getTipoSexo() != null) {
						docPessoa.put("tipoSexo", contratoTrabalho.getPessoa().getTipoSexo().name());
					}
					if (contratoTrabalho.getPessoa().getContas() != null) {
						final List<Document> docContas = new ArrayList<>();
						for (Conta conta : contratoTrabalho.getPessoa().getContas()) {
							final Document docConta = new Document();
							docConta.clear();
							docConta.append("numero", null);
							docConta.append("agencia", null);
							docConta.append("tipoConta", null);
							if (conta.getNumero() != null) {
								docConta.put("numero", conta.getNumero());
							}
							if (conta.getAgencia() != null) {
								final Document docAgencia = new Document();
								docAgencia.clear();
								docAgencia.append("numero", null);
								docAgencia.append("codigoBanco", null);
								if (conta.getAgencia().getNumero() != null) {
									docAgencia.put("numero", conta.getAgencia().getNumero());
								}
								if (conta.getAgencia().getCodigoBanco() != null) {
									docAgencia.put("codigoBanco", conta.getAgencia().getCodigoBanco());
								}
								docConta.put("agencia", docAgencia);
							}
							if (conta.getTipoConta() != null) {
								docConta.put("tipoConta", conta.getTipoConta().name());
							}
							docContas.add(docConta);
						}
						docPessoa.put("contas", docContas);
					}
					docContratoTrabalho.put("pessoa", docPessoa);
				}
				if (contratoTrabalho.getTipoContratoTrabalho() != null) {
					docContratoTrabalho.put("tipoContratoTrabalho", contratoTrabalho.getTipoContratoTrabalho().name());
				}
				if (contratoTrabalho.getDataInicioContrato() != null) {
					docContratoTrabalho.put("dataInicioContrato", contratoTrabalho.getDataInicioContrato().toString());
				}
				docContratosTrabalho.add(docContratoTrabalho);
			}
			docEmpresa.put(FIELD_CONTRATOS_TRABALHO, docContratosTrabalho);
		}
		if (empresa.getEnderecos() != null) {
			final List<Document> docEnderecos = new ArrayList<>();
			for (Endereco endereco : empresa.getEnderecos()) {
				final Document docEndereco = new Document();
				docEndereco.clear();
				docEndereco.append("cep", null);
				docEndereco.append("numero", null);
				docEndereco.append("complemento", null);
				docEndereco.append("logradouro", null);
				docEndereco.append("telefonesFixo", null);
				docEndereco.append("tipoEndereco", null);
				if (endereco.getCep() != null) {
					docEndereco.put("cep", endereco.getCep());
				}
				if (endereco.getNumero() != null) {
					docEndereco.put("numero", endereco.getNumero());
				}
				if (endereco.getComplemento() != null) {
					docEndereco.put("complemento", endereco.getComplemento());
				}
				if (endereco.getLogradouro() != null) {
					final Document docLogradouro = new Document();
					docLogradouro.clear();
					docLogradouro.append("nome", null);
					docLogradouro.append("bairro", null);
					if (endereco.getLogradouro().getNome() != null) {
						docLogradouro.put("nome", endereco.getLogradouro().getNome());
					}
					if (endereco.getLogradouro().getBairro() != null) {
						final Document docBairro = new Document();
						docBairro.clear();
						docBairro.append("nome", null);
						docBairro.append("cidade", null);
						if (endereco.getLogradouro().getBairro().getNome() != null) {
							docBairro.put("nome", endereco.getLogradouro().getBairro().getNome());
						}
						if (endereco.getLogradouro().getBairro().getCidade() != null) {
							final Document docCidade = new Document();
							docCidade.clear();
							docCidade.append("nome", null);
							docCidade.append("tipoUf", null);
							if (endereco.getLogradouro().getBairro().getCidade().getNome() != null) {
								docCidade.put("nome", endereco.getLogradouro().getBairro().getCidade().getNome());
							}
							if (endereco.getLogradouro().getBairro().getCidade().getTipoUf() != null) {
								docCidade.put("tipoUf",
										endereco.getLogradouro().getBairro().getCidade().getTipoUf().name());
							}
							docBairro.put("cidade", docCidade);
						}
						docLogradouro.put("bairro", docBairro);
					}
					docEndereco.put("logradouro", docLogradouro);
				}
				if (endereco.getTelefonesFixo() != null) {
					final List<Document> docTelefonesFixo = new ArrayList<>();
					for (TelefoneFixo telefoneFixo : endereco.getTelefonesFixo()) {
						final Document docTelefoneFixo = new Document();
						docTelefoneFixo.clear();
						docTelefoneFixo.append("ddd", null);
						docTelefoneFixo.append("numero", null);
						if (telefoneFixo.getDdd() != null) {
							docTelefoneFixo.put("ddd", telefoneFixo.getDdd());
						}
						if (telefoneFixo.getNumero() != null) {
							docTelefoneFixo.put("numero", telefoneFixo.getNumero());
						}
						docTelefonesFixo.add(docTelefoneFixo);
					}
					docEndereco.put("telefonesFixo", docTelefonesFixo);
				}
				if (endereco.getTipoEndereco() != null) {
					docEndereco.put("tipoEndereco", endereco.getTipoEndereco().name());
				}
				docEnderecos.add(docEndereco);
			}
			docEmpresa.put(FIELD_ENDERECOS, docEnderecos);
		}
		if (empresa.getTelefonesFixo() != null) {
			final List<Document> docTelefonesFixo = new ArrayList<>();
			for (TelefoneFixo telefoneFixo : empresa.getTelefonesFixo()) {
				final Document docTelefoneFixo = new Document();
				docTelefoneFixo.clear();
				docTelefoneFixo.append("ddd", null);
				docTelefoneFixo.append("numero", null);
				if (telefoneFixo.getDdd() != null) {
					docTelefoneFixo.put("ddd", telefoneFixo.getDdd());
				}
				if (telefoneFixo.getNumero() != null) {
					docTelefoneFixo.put("numero", telefoneFixo.getNumero());
				}
				docTelefonesFixo.add(docTelefoneFixo);
			}
			docEmpresa.put(FIELD_TELEFONES_FIXO, docTelefonesFixo);
		}
		if (empresa.getEmails() != null) {
			final List<Document> docEmails = new ArrayList<>();
			for (Email email : empresa.getEmails()) {
				final Document docEmail = new Document();
				docEmail.clear();
				docEmail.append("endereco", null);
				if (email.getEndereco() != null) {
					docEmail.put("endereco", email.getEndereco());
				}
				docEmails.add(docEmail);
			}
			docEmpresa.put(FIELD_EMAILS, docEmails);
		}
		if (empresa.getCelulares() != null) {
			final List<Document> docCelulares = new ArrayList<>();
			for (Celular celular : empresa.getCelulares()) {
				final Document docCelular = new Document();
				docCelular.clear();
				docCelular.append("ddd", null);
				docCelular.append("numero", null);
				docCelular.append("tipoContatoCelular", null);
				if (celular.getDdd() != null) {
					docCelular.put("ddd", celular.getDdd());
				}
				if (celular.getNumero() != null) {
					docCelular.put("numero", celular.getNumero());
				}
				if (celular.getTipoContatoCelular() != null) {
					docCelular.put("tipoContatoCelular", celular.getTipoContatoCelular().name());
				}
				docCelulares.add(docCelular);
			}
			docEmpresa.put(FIELD_CELULARES, docCelulares);
		}
		if (empresa.getContas() != null) {
			final List<Document> docContas = new ArrayList<>();
			for (Conta conta : empresa.getContas()) {
				final Document docConta = new Document();
				docConta.clear();
				docConta.append("numero", null);
				docConta.append("agencia", null);
				docConta.append("tipoConta", null);
				if (conta.getNumero() != null) {
					docConta.put("numero", conta.getNumero());
				}
				if (conta.getAgencia() != null) {
					final Document docAgencia = new Document();
					docAgencia.clear();
					docAgencia.append("numero", null);
					docAgencia.append("codigoBanco", null);
					if (conta.getAgencia().getNumero() != null) {
						docAgencia.put("numero", conta.getAgencia().getNumero());
					}
					if (conta.getAgencia().getCodigoBanco() != null) {
						docAgencia.put("codigoBanco", conta.getAgencia().getCodigoBanco());
					}
					docConta.put("agencia", docAgencia);
				}
				if (conta.getTipoConta() != null) {
					docConta.put("tipoConta", conta.getTipoConta().name());
				}
				docContas.add(docConta);
			}
			docEmpresa.put(FIELD_CONTAS, docContas);
		}
		if (empresa.getTipoEmpresa() != null) {
			docEmpresa.put(FIELD_TIPO_EMPRESA, empresa.getTipoEmpresa().name());
		}
		if (empresa.getTipoPorteEmpresa() != null) {
			docEmpresa.put(FIELD_TIPO_PORTE_EMPRESA, empresa.getTipoPorteEmpresa().name());
		}
		return docEmpresa;
	}

}
