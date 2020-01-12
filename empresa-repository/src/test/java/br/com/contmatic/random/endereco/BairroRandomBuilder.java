package br.com.contmatic.random.endereco;

import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Bairro;

public class BairroRandomBuilder {

	public static Bairro buildValido() {
		Bairro bairro = new Bairro();
		bairro.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		bairro.setCidade(CidadeRandomBuilder.buildValido());
		return bairro;
	}
	
}
