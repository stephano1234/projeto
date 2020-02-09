package br.com.contmatic.repository.mongo.conversor.conta;

import org.bson.Document;

import br.com.contmatic.model.conta.Agencia;

public class AgenciaConversorMongo {

	private static final String FIELD_CODIGO_BANCO = "codigoBanco";
	private static final String FIELD_NUMERO = "numero";

	public Document agenciaToDocument(Agencia agencia) {
		if (agencia == null) {
			return null;
		}
		final Document docAgencia = new Document();
		docAgencia.append(FIELD_NUMERO, null);
		docAgencia.append(FIELD_CODIGO_BANCO, null);
		if (agencia.getNumero() != null) {
			docAgencia.put(FIELD_NUMERO, agencia.getNumero());
		}
		if (agencia.getCodigoBanco() != null) {
			docAgencia.put(FIELD_CODIGO_BANCO, agencia.getCodigoBanco());
		}
		return docAgencia;
	}
	
	public Agencia documentoToAgencia(Document docAgencia) {
		if (docAgencia == null) {
			return null;
		}
		final Agencia agencia = new Agencia();
		if (docAgencia.get(FIELD_NUMERO, String.class) != null) {
			agencia.setNumero(docAgencia.get(FIELD_NUMERO, String.class));	
		}
		if (docAgencia.get(FIELD_CODIGO_BANCO, String.class) != null) {
			agencia.setCodigoBanco(docAgencia.get(FIELD_CODIGO_BANCO, String.class));	
		}
		return agencia;
	}
	
}
