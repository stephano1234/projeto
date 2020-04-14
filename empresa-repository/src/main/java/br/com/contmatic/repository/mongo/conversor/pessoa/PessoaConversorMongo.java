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

public class PessoaConversorMongo {
	
	private static final String FIELD_TIPO_SEXO = "tipoSexo";

	private static final String FIELD_TIPO_ESTADO_CIVIL = "tipoEstadoCivil";

	private static final String FIELD_TIPO_GRAU_INSTRUCAO = "tipoGrauInstrucao";

	private static final String FIELD_DATA_NASCIMENTO = "dataNascimento";

	private static final String FIELD_NOME = "nome";

	private static final String FIELD_CPF = "cpf";

	public Document pessoaToDocument(Pessoa pessoa) {
		if (pessoa == null) {
			return null;
		}
		final Document docPessoa = new Document();
		docPessoa.append(FIELD_CPF, null);
		docPessoa.append(FIELD_NOME, null);
		docPessoa.append(FIELD_DATA_NASCIMENTO, null);
		docPessoa.append(FIELD_TIPO_GRAU_INSTRUCAO, null);
		docPessoa.append(FIELD_TIPO_ESTADO_CIVIL, null);
		docPessoa.append(FIELD_TIPO_SEXO, null);
		if (pessoa.getCpf() != null) {
			docPessoa.put(FIELD_CPF, pessoa.getCpf());
		}
		if (pessoa.getNome() != null) {
			docPessoa.put(FIELD_NOME, pessoa.getNome());
		}
		if (pessoa.getDataNascimento() != null) {
			docPessoa.put(FIELD_DATA_NASCIMENTO, pessoa.getDataNascimento().toString());
		}
		if (pessoa.getTipoGrauInstrucao() != null) {
			docPessoa.put(FIELD_TIPO_GRAU_INSTRUCAO, pessoa.getTipoGrauInstrucao().name());
		}
		if (pessoa.getTipoEstadoCivil() != null) {
			docPessoa.put(FIELD_TIPO_ESTADO_CIVIL, pessoa.getTipoEstadoCivil().name());
		}
		if (pessoa.getTipoSexo() != null) {
			docPessoa.put(FIELD_TIPO_SEXO, pessoa.getTipoSexo().name());
		}
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
		if (docPessoa.get(FIELD_DATA_NASCIMENTO, String.class) != null) {
			pessoa.setDataNascimento(LocalDate.parse(docPessoa.get(FIELD_DATA_NASCIMENTO, String.class)));
		}
		if (docPessoa.get(FIELD_TIPO_GRAU_INSTRUCAO, String.class) != null) {
			pessoa.setTipoGrauInstrucao(TipoGrauInstrucao.valueOf(docPessoa.get(FIELD_TIPO_GRAU_INSTRUCAO, String.class)));			
		}
		if (docPessoa.get(FIELD_TIPO_ESTADO_CIVIL, String.class) != null) {
			pessoa.setTipoEstadoCivil(TipoEstadoCivil.valueOf(docPessoa.get(FIELD_TIPO_ESTADO_CIVIL, String.class)));			
		}
		if (docPessoa.get(FIELD_TIPO_SEXO, String.class) != null) {
			pessoa.setTipoSexo(TipoSexo.valueOf(docPessoa.get(FIELD_TIPO_SEXO, String.class)));			
		}
		return pessoa;
	}
	
	public List<Document> pessoasToDocuments(Set<Pessoa> pessoas) {
		if (pessoas == null) {
			return new ArrayList<>();
		}
		final List<Document> docPessoas = new ArrayList<>();
		for (Pessoa pessoa : pessoas) {
			docPessoas.add(pessoaToDocument(pessoa));
		}
		return docPessoas;
	}
	
	public Set<Pessoa> documentsToPessoas(List<Document> docPessoas) {
		if (docPessoas == null) {
			return new HashSet<>();
		}
		final Set<Pessoa> pessoas = new HashSet<>();
		for (Document docPessoa : docPessoas) {
			pessoas.add(documentToPessoa(docPessoa));
		}
		return pessoas;
	}

}
