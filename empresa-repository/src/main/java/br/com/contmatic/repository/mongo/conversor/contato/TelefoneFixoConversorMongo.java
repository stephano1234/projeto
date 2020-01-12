package br.com.contmatic.repository.mongo.conversor.contato;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.model.contato.TelefoneFixo;

public class TelefoneFixoConversorMongo {

	private static final String FIELD_NUMERO = "numero";
	private static final String FIELD_DDD = "ddd";

	public Document telefoneFixoToDocument(TelefoneFixo telefoneFixo) {
		if (telefoneFixo == null) {
			return null;
		}
		final Document docTelefoneFixo = new Document();
		docTelefoneFixo.append(FIELD_DDD, null);
		docTelefoneFixo.append(FIELD_NUMERO, null);
		if (telefoneFixo.getDdd() != null) {
			docTelefoneFixo.put(FIELD_DDD, telefoneFixo.getDdd());
		}
		if (telefoneFixo.getNumero() != null) {
			docTelefoneFixo.put(FIELD_NUMERO, telefoneFixo.getNumero());
		}
		return docTelefoneFixo;
	}
	
	public TelefoneFixo documentToTelefoneFixo(Document docTelefoneFixo) {
		if (docTelefoneFixo == null) {
			return null;
		}
		final TelefoneFixo telefoneFixo = new TelefoneFixo();
		if (docTelefoneFixo.get(FIELD_DDD, String.class) != null) {
			telefoneFixo.setDdd(docTelefoneFixo.get(FIELD_DDD, String.class));	
		}
		if (docTelefoneFixo.get(FIELD_NUMERO, String.class) != null) {
			telefoneFixo.setNumero(docTelefoneFixo.get(FIELD_NUMERO, String.class));	
		}
		return telefoneFixo;
	}
	
	public List<Document> telefonesFixoToDocuments(Set<TelefoneFixo> telefonesFixo) {
		if (telefonesFixo == null) {
			return null;
		}
		final List<Document> docTelefonesFixo = new ArrayList<>();
		for (TelefoneFixo telefoneFixo : telefonesFixo) {
			docTelefonesFixo.add(telefoneFixoToDocument(telefoneFixo));
		}
		return docTelefonesFixo;
	}
	
	public Set<TelefoneFixo> documentsToTelefonesFixo(List<Document> docTelefonesFixo) {
		if (docTelefonesFixo == null) {
			return null;
		}
		final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
		for (Document docTelefoneFixo : docTelefonesFixo) {
			telefonesFixo.add(documentToTelefoneFixo(docTelefoneFixo));
		}
		return telefonesFixo;
	}
	
}