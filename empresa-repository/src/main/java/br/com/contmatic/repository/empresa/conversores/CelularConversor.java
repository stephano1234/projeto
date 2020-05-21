package br.com.contmatic.repository.empresa.conversores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.model.contato.Celular;

public class CelularConversor {

	private static final String FIELD_NUMERO = "numero";

	public Document celularToDocument(Celular celular) {
		final Document docCelular = new Document();
		docCelular.append(FIELD_NUMERO, null);
		if (celular.getNumero() != null) {
			docCelular.put(FIELD_NUMERO, celular.getNumero());
		}
		return docCelular;
	}
	
	public Celular documentToCelular(Document docCelular) {
		final Celular celular = new Celular();
		if (docCelular.get(FIELD_NUMERO, String.class) != null) {
			celular.setNumero(docCelular.get(FIELD_NUMERO, String.class));	
		}
		return celular;
	}

	public List<Document> celularesToDocuments(Set<Celular> celulars) {
		final List<Document> docCelulars = new ArrayList<>();
		for (Celular celular : celulars) {
			docCelulars.add(celularToDocument(celular));
		}
		return docCelulars;
	}
	
	public Set<Celular> documentsToCelulares(List<Document> docCelulars) {
		final Set<Celular> celulars = new HashSet<>();
		for (Document docCelular : docCelulars) {
			celulars.add(documentToCelular(docCelular));
		}
		return celulars;
	}

}
