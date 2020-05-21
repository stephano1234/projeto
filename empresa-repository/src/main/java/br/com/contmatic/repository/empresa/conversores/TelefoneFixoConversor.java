package br.com.contmatic.repository.empresa.conversores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.model.contato.TelefoneFixo;

public class TelefoneFixoConversor {

	private static final String FIELD_NUMERO = "numero";

	public Document telefoneFixoToDocument(TelefoneFixo telefoneFixo) {
		final Document docTelefoneFixo = new Document();
		docTelefoneFixo.append(FIELD_NUMERO, null);
		if (telefoneFixo.getNumero() != null) {
			docTelefoneFixo.put(FIELD_NUMERO, telefoneFixo.getNumero());
		}
		return docTelefoneFixo;
	}
	
	public TelefoneFixo documentToTelefoneFixo(Document docTelefoneFixo) {
		final TelefoneFixo telefoneFixo = new TelefoneFixo();
		if (docTelefoneFixo.get(FIELD_NUMERO, String.class) != null) {
			telefoneFixo.setNumero(docTelefoneFixo.get(FIELD_NUMERO, String.class));	
		}
		return telefoneFixo;
	}
	
	public List<Document> telefonesFixoToDocuments(Set<TelefoneFixo> telefonesFixo) {
		final List<Document> docTelefonesFixo = new ArrayList<>();
		for (TelefoneFixo telefoneFixo : telefonesFixo) {
			docTelefonesFixo.add(telefoneFixoToDocument(telefoneFixo));
		}
		return docTelefonesFixo;
	}
	
	public Set<TelefoneFixo> documentsToTelefonesFixo(List<Document> docTelefonesFixo) {
		final Set<TelefoneFixo> telefonesFixo = new HashSet<>();
		for (Document docTelefoneFixo : docTelefonesFixo) {
			telefonesFixo.add(documentToTelefoneFixo(docTelefoneFixo));
		}
		return telefonesFixo;
	}
	
}
