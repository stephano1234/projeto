package br.com.contmatic.repository.mongo.conversor.endereco;

import org.bson.Document;

import br.com.contmatic.model.endereco.Bairro;

public class BairroConversorMongo {
	
	private static final String FIELD_CIDADE = "cidade";
	private static final String FIELD_NOME = "nome";
	
	private CidadeConversorMongo cidadeConversorMongo = new CidadeConversorMongo();

	public Document bairroToDocument(Bairro bairro) {
		if (bairro == null) {
			return null;
		}
		final Document docBairro = new Document();
		docBairro.append(FIELD_NOME, null);
		docBairro.append(FIELD_CIDADE, null);
		if (bairro.getNome() != null) {
			docBairro.put(FIELD_NOME, bairro.getNome());
		}
		docBairro.put(FIELD_CIDADE, cidadeConversorMongo.cidadeToDocument(bairro.getCidade()));
		return docBairro;
	}
	
	public Bairro documentToBairro(Document docBairro) {
		if (docBairro == null) {
			return null;
		}
		final Bairro bairro = new Bairro();
		if (docBairro.get(FIELD_NOME, String.class) != null) {
			bairro.setNome(docBairro.get(FIELD_NOME, String.class));	
		}
		bairro.setCidade(cidadeConversorMongo.documentToCidade(docBairro.get(FIELD_CIDADE, Document.class)));
		return bairro;
	}
	
}
