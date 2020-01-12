package br.com.contmatic.repository.mongo.conversor.pessoa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.joda.time.LocalDate;

import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.pessoa.TipoEstadoCivil;
import br.com.contmatic.model.pessoa.TipoGrauInstrucao;
import br.com.contmatic.model.pessoa.TipoSexo;
import br.com.contmatic.repository.mongo.conversor.conta.ContaConversorMongo;
import br.com.contmatic.repository.mongo.conversor.contato.CelularConversorMongo;
import br.com.contmatic.repository.mongo.conversor.contato.EmailConversorMongo;
import br.com.contmatic.repository.mongo.conversor.contato.TelefoneFixoConversorMongo;
import br.com.contmatic.repository.mongo.conversor.endereco.EnderecoConversorMongo;

public class PessoaConversorMongo {
	
	private static final String FIELD_CONTAS = "contas";

	private static final String FIELD_TIPO_SEXO = "tipoSexo";

	private static final String FIELD_TIPO_ESTADO_CIVIL = "tipoEstadoCivil";

	private static final String FIELD_TIPO_GRAU_INSTRUCAO = "tipoGrauInstrucao";

	private static final String FIELD_EMAILS = "emails";

	private static final String FIELD_TELEFONES_FIXO = "telefonesFixo";

	private static final String FIELD_CELULARES = "celulares";

	private static final String FIELD_DATA_NASCIMENTO = "dataNascimento";

	private static final String FIELD_ENDERECOS = "enderecos";

	private static final String FIELD_NOME = "nome";

	private static final String FIELD_CPF = "cpf";

	private EnderecoConversorMongo enderecoConversorMongo = new EnderecoConversorMongo();
	
	private CelularConversorMongo celularConversorMongo = new CelularConversorMongo();
	
	private EmailConversorMongo emailConversorMongo = new EmailConversorMongo();
	
	private TelefoneFixoConversorMongo telefoneFixoConversorMongo = new TelefoneFixoConversorMongo();
	
	private ContaConversorMongo contaConversorMongo = new ContaConversorMongo();

	public Document pessoaToDocument(Pessoa pessoa) {
		if (pessoa == null) {
			return null;
		}
		final Document docPessoa = new Document();
		docPessoa.append(FIELD_CPF, null);
		docPessoa.append(FIELD_NOME, null);
		docPessoa.append(FIELD_ENDERECOS, null);
		docPessoa.append(FIELD_DATA_NASCIMENTO, null);
		docPessoa.append(FIELD_CELULARES, null);
		docPessoa.append(FIELD_TELEFONES_FIXO, null);
		docPessoa.append(FIELD_EMAILS, null);
		docPessoa.append(FIELD_TIPO_GRAU_INSTRUCAO, null);
		docPessoa.append(FIELD_TIPO_ESTADO_CIVIL, null);
		docPessoa.append(FIELD_TIPO_SEXO, null);
		docPessoa.append(FIELD_CONTAS, null);
		if (pessoa.getCpf() != null) {
			docPessoa.put(FIELD_CPF, pessoa.getCpf());
		}
		if (pessoa.getNome() != null) {
			docPessoa.put(FIELD_NOME, pessoa.getNome());
		}
		docPessoa.put(FIELD_ENDERECOS, enderecoConversorMongo.enderecosToDocuments(pessoa.getEnderecos()));
		if (pessoa.getDataNascimento() != null) {
			docPessoa.put(FIELD_DATA_NASCIMENTO, pessoa.getDataNascimento().toString());
		}
		docPessoa.put(FIELD_CELULARES, celularConversorMongo.celularsToDocuments(pessoa.getCelulares()));
		docPessoa.put(FIELD_TELEFONES_FIXO, telefoneFixoConversorMongo.telefonesFixoToDocuments(pessoa.getTelefonesFixo()));
		docPessoa.put(FIELD_EMAILS, emailConversorMongo.emailsToDocuments(pessoa.getEmails()));
		if (pessoa.getTipoGrauInstrucao() != null) {
			docPessoa.put(FIELD_TIPO_GRAU_INSTRUCAO, pessoa.getTipoGrauInstrucao().name());
		}
		if (pessoa.getTipoEstadoCivil() != null) {
			docPessoa.put(FIELD_TIPO_ESTADO_CIVIL, pessoa.getTipoEstadoCivil().name());
		}
		if (pessoa.getTipoSexo() != null) {
			docPessoa.put(FIELD_TIPO_SEXO, pessoa.getTipoSexo().name());
		}
		docPessoa.put(FIELD_CONTAS, contaConversorMongo.contasToDocuments(pessoa.getContas()));
		return docPessoa;
	}
	
	public Pessoa documentToPessoa(Document docPessoa) {
		if (docPessoa == null) {
			return null;
		}
		final Pessoa pessoa = new Pessoa();
		if (docPessoa.get(FIELD_CPF, String.class) != null) {
			pessoa.setCpf(docPessoa.get(FIELD_CPF, String.class));			
		}
		if (docPessoa.get(FIELD_NOME, String.class) != null) {
			pessoa.setNome(docPessoa.get(FIELD_NOME, String.class));			
		}
		pessoa.setEnderecos(enderecoConversorMongo.documentsToEnderecos(docPessoa.getList(FIELD_ENDERECOS, Document.class)));
		if (docPessoa.get(FIELD_DATA_NASCIMENTO, String.class) != null) {
			pessoa.setDataNascimento(LocalDate.parse(docPessoa.get(FIELD_DATA_NASCIMENTO, String.class)));
		}
		pessoa.setCelulares(celularConversorMongo.documentsToCelulars(docPessoa.getList(FIELD_CELULARES, Document.class)));
		pessoa.setTelefonesFixo(telefoneFixoConversorMongo.documentsToTelefonesFixo(docPessoa.getList(FIELD_TELEFONES_FIXO, Document.class)));
		pessoa.setEmails(emailConversorMongo.documentsToEmails(docPessoa.getList(FIELD_EMAILS, Document.class)));
		if (docPessoa.get(FIELD_TIPO_GRAU_INSTRUCAO, String.class) != null) {
			pessoa.setTipoGrauInstrucao(TipoGrauInstrucao.valueOf(docPessoa.get(FIELD_TIPO_GRAU_INSTRUCAO, String.class)));			
		}
		if (docPessoa.get(FIELD_TIPO_ESTADO_CIVIL, String.class) != null) {
			pessoa.setTipoEstadoCivil(TipoEstadoCivil.valueOf(docPessoa.get(FIELD_TIPO_ESTADO_CIVIL, String.class)));			
		}
		if (docPessoa.get(FIELD_TIPO_SEXO, String.class) != null) {
			pessoa.setTipoSexo(TipoSexo.valueOf(docPessoa.get(FIELD_TIPO_SEXO, String.class)));			
		}
		pessoa.setContas(contaConversorMongo.documentsToContas(docPessoa.getList(FIELD_CONTAS, Document.class)));
		return pessoa;
	}
	
	public List<Document> pessoasToDocuments(Set<Pessoa> pessoas) {
		if (pessoas == null) {
			return null;
		}
		final List<Document> docPessoas = new ArrayList<>();
		for (Pessoa pessoa : pessoas) {
			docPessoas.add(pessoaToDocument(pessoa));
		}
		return docPessoas;
	}
	
	public Set<Pessoa> documentsToPessoas(List<Document> docPessoas) {
		if (docPessoas == null) {
			return null;
		}
		final Set<Pessoa> pessoas = new HashSet<>();
		for (Document docPessoa : docPessoas) {
			pessoas.add(documentToPessoa(docPessoa));
		}
		return pessoas;
	}

}
