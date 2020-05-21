package br.com.contmatic.repository.empresa.conversores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.model.endereco.Endereco;

public class EnderecoConversor {
	
	private static final String FIELD_LOGRADOURO = "logradouro";

	private static final String FIELD_COMPLEMENTO = "complemento";

	private static final String FIELD_NUMERO = "numero";

	private static final String FIELD_CEP = "cep";

	private LogradouroConversor logradouroConversorMongo = new LogradouroConversor();
	
	public Document enderecoToDocument(Endereco endereco) {
		final Document docEndereco = new Document();
		docEndereco.append(FIELD_CEP, null);
		docEndereco.append(FIELD_NUMERO, null);
		docEndereco.append(FIELD_COMPLEMENTO, null);
		docEndereco.append(FIELD_LOGRADOURO, null);
		if (endereco.getCep() != null) {
			docEndereco.put(FIELD_CEP, endereco.getCep());
		}
		if (endereco.getNumero() != null) {
			docEndereco.put(FIELD_NUMERO, endereco.getNumero());
		}
		if (endereco.getComplemento() != null) {
			docEndereco.put(FIELD_COMPLEMENTO, endereco.getComplemento());
		}
		docEndereco.put(FIELD_LOGRADOURO, logradouroConversorMongo.logradouroToDocument(endereco.getLogradouro()));
		return docEndereco;
	}
	
	public Endereco documentToEndereco(Document docEndereco) {
		final Endereco endereco = new Endereco();
		if (docEndereco.get(FIELD_CEP, String.class) != null) {
			endereco.setCep(docEndereco.get(FIELD_CEP, String.class));			
		}
		if (docEndereco.get(FIELD_NUMERO, String.class) != null) {
			endereco.setNumero(docEndereco.get(FIELD_NUMERO, String.class));			
		}
		if (docEndereco.get(FIELD_COMPLEMENTO, String.class) != null) {
			endereco.setComplemento(docEndereco.get(FIELD_COMPLEMENTO, String.class));			
		}
		endereco.setLogradouro(logradouroConversorMongo.documentToLogradouro(docEndereco.get(FIELD_LOGRADOURO, Document.class)));
		return endereco;
	}

	public List<Document> enderecosToDocuments(Set<Endereco> enderecos) {
		final List<Document> docEnderecos = new ArrayList<>();
		for (Endereco endereco : enderecos) {
			docEnderecos.add(enderecoToDocument(endereco));
		}
		return docEnderecos;
	}
	
	public Set<Endereco> documentsToEnderecos(List<Document> docEnderecos) {
		final Set<Endereco> enderecos = new HashSet<>();
		for (Document docEndereco : docEnderecos) {
			enderecos.add(documentToEndereco(docEndereco));
		}
		return enderecos;
	}

}
