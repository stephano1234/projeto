package br.com.contmatic.repository.mongo.conversor.conta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.conta.TipoConta;

public class ContaConversorMongo {

	private static final String FIELD_TIPO_CONTA = "tipoConta";
	private static final String FIELD_AGENCIA = "agencia";
	private static final String FIELD_NUMERO = "numero";
	
	private AgenciaConversorMongo agenciaConversorMongo = new AgenciaConversorMongo();

	public Document contaToDocument(Conta conta) {
		if (conta == null) {
			return null;
		}
		final Document docConta = new Document();
		docConta.append(FIELD_NUMERO, null);
		docConta.append(FIELD_AGENCIA, null);
		docConta.append(FIELD_TIPO_CONTA, null);
		if (conta.getNumero() != null) {
			docConta.put(FIELD_NUMERO, conta.getNumero());
		}
		docConta.put(FIELD_AGENCIA, agenciaConversorMongo.agenciaToDocument(conta.getAgencia()));
		if (conta.getTipoConta() != null) {
			docConta.put(FIELD_TIPO_CONTA, conta.getTipoConta().name());
		}
		return docConta;
	}

	public Conta documentToConta(Document docConta) {
		if (docConta == null) {
			return null;
		}
		final Conta conta = new Conta();
		conta.setAgencia(agenciaConversorMongo.documentoToAgencia(docConta.get(FIELD_AGENCIA, Document.class)));
		if (docConta.get(FIELD_NUMERO, String.class) != null) {
			conta.setNumero(docConta.get(FIELD_NUMERO, String.class));
		}
		if (docConta.get(FIELD_TIPO_CONTA, String.class) != null) {
			conta.setTipoConta(TipoConta.valueOf(docConta.get(FIELD_TIPO_CONTA, String.class)));
		}
		return conta;
	}

	public List<Document> contasToDocuments(Set<Conta> contas) {
		if (contas == null) {
			return new ArrayList<>();
		}
		final List<Document> docContas = new ArrayList<>();
		for (Conta conta : contas) {
			docContas.add(contaToDocument(conta));
		}
		return docContas;
	}
	
	public Set<Conta> documentsToContas(List<Document> docContas) {
		if (docContas == null) {
			return new HashSet<>();
		}
		final Set<Conta> contas = new HashSet<>();
		for (Document docConta : docContas) {
			contas.add(documentToConta(docConta));
		}
		return contas;
	}

}
