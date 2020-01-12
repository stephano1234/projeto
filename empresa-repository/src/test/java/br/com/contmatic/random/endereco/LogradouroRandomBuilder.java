package br.com.contmatic.random.endereco;

import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Logradouro;

public class LogradouroRandomBuilder {

	public static Logradouro buildValido() {
		Logradouro logradouro = new Logradouro();
		logradouro.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		logradouro.setBairro(BairroRandomBuilder.buildValido());
		return logradouro;
	}
	
}
