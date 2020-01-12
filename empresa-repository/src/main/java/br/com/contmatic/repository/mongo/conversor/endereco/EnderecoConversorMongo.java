package br.com.contmatic.repository.mongo.conversor.endereco;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.endereco.TipoEndereco;
import br.com.contmatic.repository.mongo.conversor.contato.TelefoneFixoConversorMongo;

public class EnderecoConversorMongo {
	
	private static final String FIELD_TIPO_ENDERECO = "tipoEndereco";

	private static final String FIELD_TELEFONES_FIXO = "telefonesFixo";

	private static final String FIELD_LOGRADOURO = "logradouro";

	private static final String FIELD_COMPLEMENTO = "complemento";

	private static final String FIELD_NUMERO = "numero";

	private static final String FIELD_CEP = "cep";

	private LogradouroConversorMongo logradouroConversorMongo = new LogradouroConversorMongo();
	
	private TelefoneFixoConversorMongo telefoneFixoConversorMongo = new TelefoneFixoConversorMongo();

	public Document enderecoToDocument(Endereco endereco) {
		if (endereco == null) {
			return null;
		}
		final Document docEndereco = new Document();
		docEndereco.append(FIELD_CEP, null);
		docEndereco.append(FIELD_NUMERO, null);
		docEndereco.append(FIELD_COMPLEMENTO, null);
		docEndereco.append(FIELD_LOGRADOURO, null);
		docEndereco.append(FIELD_TELEFONES_FIXO, null);
		docEndereco.append(FIELD_TIPO_ENDERECO, null);
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
		docEndereco.put(FIELD_TELEFONES_FIXO, telefoneFixoConversorMongo.telefonesFixoToDocuments(endereco.getTelefonesFixo()));
		if (endereco.getTipoEndereco() != null) {
			docEndereco.put(FIELD_TIPO_ENDERECO, endereco.getTipoEndereco().name());
		}
		return docEndereco;
	}
	
	public Endereco documentToEndereco(Document docEndereco) {
		if (docEndereco == null) {
			return null;
		}
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
		endereco.setTelefonesFixo(telefoneFixoConversorMongo.documentsToTelefonesFixo(docEndereco.getList(FIELD_TELEFONES_FIXO, Document.class)));
		if (docEndereco.get(FIELD_TIPO_ENDERECO, String.class) != null) {
			endereco.setTipoEndereco(TipoEndereco.valueOf(docEndereco.get(FIELD_TIPO_ENDERECO, String.class)));			
		}
		return endereco;
	}

	public List<Document> enderecosToDocuments(Set<Endereco> enderecos) {
		if (enderecos == null) {
			return null;
		}
		final List<Document> docEnderecos = new ArrayList<>();
		for (Endereco endereco : enderecos) {
			docEnderecos.add(enderecoToDocument(endereco));
		}
		return docEnderecos;
	}
	
	public Set<Endereco> documentsToEnderecos(List<Document> docEnderecos) {
		if (docEnderecos == null) {
			return null;
		}
		final Set<Endereco> enderecos = new HashSet<>();
		for (Document docEndereco : docEnderecos) {
			enderecos.add(documentToEndereco(docEndereco));
		}
		return enderecos;
	}

}
