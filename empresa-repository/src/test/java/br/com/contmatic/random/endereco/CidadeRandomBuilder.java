package br.com.contmatic.random.endereco;

import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Cidade;
import br.com.contmatic.model.endereco.TipoUf;

public class CidadeRandomBuilder {

	public static Cidade buildValido() {
		Cidade cidade = new Cidade();
		cidade.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		cidade.setTipoUf(TipoUf.values()[nextInt(0, TipoUf.values().length)]);
		return cidade;
	}
	
}
