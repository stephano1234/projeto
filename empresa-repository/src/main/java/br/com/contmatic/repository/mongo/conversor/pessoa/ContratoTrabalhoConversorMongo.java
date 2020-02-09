package br.com.contmatic.repository.mongo.conversor.pessoa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.joda.time.LocalDate;

import br.com.contmatic.model.pessoa.ContratoTrabalho;
import br.com.contmatic.model.pessoa.TipoContratoTrabalho;

public class ContratoTrabalhoConversorMongo {

	private static final String FIELD_DATA_INICIO_CONTRATO = "dataInicioContrato";
	
	private static final String FIELD_TIPO_CONTRATO_TRABALHO = "tipoContratoTrabalho";
	
	private static final String FIELD_PESSOA = "pessoa";
	
	private PessoaConversorMongo pessoaConversorMongo = new PessoaConversorMongo();

	public Document contratoTrabalhoToDocument(ContratoTrabalho contratoTrabalho) {
		if (contratoTrabalho == null) {
			return null;
		}
		final Document docContratoTrabalho = new Document();
		docContratoTrabalho.append(FIELD_PESSOA, null);
		docContratoTrabalho.append(FIELD_TIPO_CONTRATO_TRABALHO, null);
		docContratoTrabalho.append(FIELD_DATA_INICIO_CONTRATO, null);
		docContratoTrabalho.put(FIELD_PESSOA, pessoaConversorMongo.pessoaToDocument(contratoTrabalho.getPessoa()));
		if (contratoTrabalho.getTipoContratoTrabalho() != null) {
			docContratoTrabalho.put(FIELD_TIPO_CONTRATO_TRABALHO, contratoTrabalho.getTipoContratoTrabalho().name());
		}
		if (contratoTrabalho.getDataInicioContrato() != null) {
			docContratoTrabalho.put(FIELD_DATA_INICIO_CONTRATO, contratoTrabalho.getDataInicioContrato().toString());
		}
		return docContratoTrabalho;
	}

	public ContratoTrabalho documentToContratoTrabalho(Document docContratoTrabalho) {
		if (docContratoTrabalho == null) {
			return null;
		}
		final ContratoTrabalho contratoTrabalho = new ContratoTrabalho();
		contratoTrabalho.setPessoa(pessoaConversorMongo.documentToPessoa(docContratoTrabalho.get(FIELD_PESSOA, Document.class)));
		if (docContratoTrabalho.get(FIELD_TIPO_CONTRATO_TRABALHO, String.class) != null) {
			contratoTrabalho.setTipoContratoTrabalho(TipoContratoTrabalho.valueOf(docContratoTrabalho.get(FIELD_TIPO_CONTRATO_TRABALHO, String.class)));			
		}
		if (docContratoTrabalho.get(FIELD_DATA_INICIO_CONTRATO, String.class) != null) {
			contratoTrabalho.setDataInicioContrato(LocalDate.parse(docContratoTrabalho.get(FIELD_DATA_INICIO_CONTRATO, String.class)));
		}
		return contratoTrabalho;
	}

	public List<Document> contratoTrabalhosToDocuments(Set<ContratoTrabalho> contratoTrabalhos) {
		if (contratoTrabalhos == null) {
			return new ArrayList<>();
		}
		final List<Document> docContratoTrabalhos = new ArrayList<>();
		for (ContratoTrabalho contratoTrabalho : contratoTrabalhos) {
			docContratoTrabalhos.add(contratoTrabalhoToDocument(contratoTrabalho));
		}
		return docContratoTrabalhos;
	}

	public Set<ContratoTrabalho> documentsToContratoTrabalhos(List<Document> docContratoTrabalhos) {
		if (docContratoTrabalhos == null) {
			return new HashSet<>();
		}
		final Set<ContratoTrabalho> contratoTrabalhos = new HashSet<>();
		for (Document docContratoTrabalho : docContratoTrabalhos) {
			contratoTrabalhos.add(documentToContratoTrabalho(docContratoTrabalho));
		}
		return contratoTrabalhos;
	}

}
