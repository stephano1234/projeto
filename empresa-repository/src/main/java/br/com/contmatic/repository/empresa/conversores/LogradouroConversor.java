package br.com.contmatic.repository.empresa.conversores;

import org.bson.Document;

import br.com.contmatic.model.endereco.Logradouro;

public class LogradouroConversor {
	
	private static final String FIELD_BAIRRO = "bairro";
	private static final String FIELD_NOME = "nome";
	
	private BairroConversor bairroConversorMongo = new BairroConversor();

	public Document logradouroToDocument(Logradouro logradouro) {
		if (logradouro == null) {
			return null;
		}
		final Document docLogradouro = new Document();
		docLogradouro.append(FIELD_NOME, null);
		docLogradouro.append(FIELD_BAIRRO, null);
		if (logradouro.getNome() != null) {
			docLogradouro.put(FIELD_NOME, logradouro.getNome());
		}
		docLogradouro.put(FIELD_BAIRRO, bairroConversorMongo.bairroToDocument(logradouro.getBairro()));
		return docLogradouro;
	}
	
	public Logradouro documentToLogradouro(Document docLogradouro) {
		if (docLogradouro == null) {
			return null;
		}
		final Logradouro logradouro = new Logradouro();
		if (docLogradouro.get(FIELD_NOME, String.class) != null) {
			logradouro.setNome(docLogradouro.get(FIELD_NOME, String.class));	
		}
		logradouro.setBairro(bairroConversorMongo.documentToBairro(docLogradouro.get(FIELD_BAIRRO, Document.class)));
		return logradouro;
	}
	
}
