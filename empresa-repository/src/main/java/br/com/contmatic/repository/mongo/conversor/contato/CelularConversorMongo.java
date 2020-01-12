package br.com.contmatic.repository.mongo.conversor.contato;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.TipoContatoCelular;

public class CelularConversorMongo {

	private static final String FIELD_TIPO_CONTATO_CELULAR = "tipoContatoCelular";
	private static final String FIELD_NUMERO = "numero";
	private static final String FIELD_DDD = "ddd";

	public Document celularToDocument(Celular celular) {
		if (celular == null) {
			return null;
		}
		final Document docCelular = new Document();
		docCelular.append(FIELD_DDD, null);
		docCelular.append(FIELD_NUMERO, null);
		docCelular.append(FIELD_TIPO_CONTATO_CELULAR, null);
		if (celular.getDdd() != null) {
			docCelular.put(FIELD_DDD, celular.getDdd());
		}
		if (celular.getNumero() != null) {
			docCelular.put(FIELD_NUMERO, celular.getNumero());
		}
		if (celular.getTipoContatoCelular() != null) {
			docCelular.put(FIELD_TIPO_CONTATO_CELULAR, celular.getTipoContatoCelular().name());
		}
		return docCelular;
	}
	
	public Celular documentToCelular(Document docCelular) {
		if (docCelular == null) {
			return null;
		}
		final Celular celular = new Celular();
		if (docCelular.get(FIELD_DDD, String.class) != null) {
			celular.setDdd(docCelular.get(FIELD_DDD, String.class));	
		}
		if (docCelular.get(FIELD_NUMERO, String.class) != null) {
			celular.setNumero(docCelular.get(FIELD_NUMERO, String.class));	
		}
		if (docCelular.get(FIELD_TIPO_CONTATO_CELULAR, String.class) != null) {
			celular.setTipoContatoCelular(TipoContatoCelular.valueOf(docCelular.get(FIELD_TIPO_CONTATO_CELULAR, String.class)));	
		}
		return celular;
	}

	public List<Document> celularsToDocuments(Set<Celular> celulars) {
		if (celulars == null) {
			return null;
		}
		final List<Document> docCelulars = new ArrayList<>();
		for (Celular celular : celulars) {
			docCelulars.add(celularToDocument(celular));
		}
		return docCelulars;
	}
	
	public Set<Celular> documentsToCelulars(List<Document> docCelulars) {
		if (docCelulars == null) {
			return null;
		}
		final Set<Celular> celulars = new HashSet<>();
		for (Document docCelular : docCelulars) {
			celulars.add(documentToCelular(docCelular));
		}
		return celulars;
	}

}
