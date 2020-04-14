package br.com.contmatic.repository.mongo.conversor.contato;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.model.contato.Celular;

public class CelularConversorMongo {

	private static final String FIELD_NUMERO = "numero";

	public Document celularToDocument(Celular celular) {
		if (celular == null) {
			return null;
		}
		final Document docCelular = new Document();
		docCelular.append(FIELD_NUMERO, null);
		if (celular.getNumero() != null) {
			docCelular.put(FIELD_NUMERO, celular.getNumero());
		}
		return docCelular;
	}
	
	public Celular documentToCelular(Document docCelular) {
		if (docCelular == null) {
			return null;
		}
		final Celular celular = new Celular();
		if (docCelular.get(FIELD_NUMERO, String.class) != null) {
			celular.setNumero(docCelular.get(FIELD_NUMERO, String.class));	
		}
		return celular;
	}

	public List<Document> celularsToDocuments(Set<Celular> celulars) {
		if (celulars == null) {
			return new ArrayList<>();
		}
		final List<Document> docCelulars = new ArrayList<>();
		for (Celular celular : celulars) {
			docCelulars.add(celularToDocument(celular));
		}
		return docCelulars;
	}
	
	public Set<Celular> documentsToCelulars(List<Document> docCelulars) {
		if (docCelulars == null) {
			return new HashSet<>();
		}
		final Set<Celular> celulars = new HashSet<>();
		for (Document docCelular : docCelulars) {
			celulars.add(documentToCelular(docCelular));
		}
		return celulars;
	}

}
