package br.com.contmatic.repository.empresa.conversores;

import org.bson.Document;

import br.com.contmatic.model.endereco.Cidade;
import br.com.contmatic.model.endereco.TipoUf;

public class CidadeConversor {

	private static final String FIELD_TIPO_UF = "tipoUf";
	private static final String FIELD_NOME = "nome";

	public Document cidadeToDocument(Cidade cidade) {
		if (cidade == null) {
			return null;
		}
		final Document docCidade = new Document();
		docCidade.append(FIELD_NOME, null);
		docCidade.append(FIELD_TIPO_UF, null);
		if (cidade.getNome() != null) {
			docCidade.put(FIELD_NOME, cidade.getNome());
		}
		if (cidade.getTipoUf() != null) {
			docCidade.put(FIELD_TIPO_UF, cidade.getTipoUf().name());
		}
		return docCidade;
	}

	public Cidade documentToCidade(Document docCidade) {
		if (docCidade == null) {
			return null;
		}
		final Cidade cidade = new Cidade();
		if (docCidade.get(FIELD_NOME, String.class) != null) {
			cidade.setNome(docCidade.get(FIELD_NOME, String.class));
		}
		if (docCidade.get(FIELD_TIPO_UF, String.class) != null) {
			cidade.setTipoUf(TipoUf.valueOf(docCidade.get(FIELD_TIPO_UF, String.class)));	
		}
		return cidade;
	}
	
}
