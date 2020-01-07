package br.com.contmatic.templates.endereco;

import static br.com.contmatic.utilidades.ConstantesTesteNumericas.EXCLUI_STRING_VAZIO;
import static br.com.contmatic.utilidades.ConstantesTesteNumericas.TAMANHO_REGULAR;
import static br.com.contmatic.utilidades.ConstantesTesteString.APENAS_ESPACO;
import static br.com.contmatic.utilidades.FuncoesRandomicas.apenasUmCaractere;
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
	
	public static Logradouro buildApenasEspacoNome() {
		Logradouro logradouro = buildValido();
		logradouro.setNome(somenteCaractere(nextInt(EXCLUI_STRING_VAZIO, TAMANHO_REGULAR), APENAS_ESPACO));
		return logradouro;
	}
	
	public static Logradouro buildMaiorTamanhoNome() {
		Logradouro logradouro = buildValido();
		logradouro.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(TAMANHO_REGULAR + 1, "[a-z]"));
		return logradouro;
	}
	
	public static Logradouro buildApenasUmCaractereNome() {
		Logradouro logradouro = buildValido();
		logradouro.setNome(somenteCaractere(1, "[A-Z]"));
		return logradouro;
	}

	public static Logradouro buildPrimeiroCaractereMinusculoNome() {
		Logradouro logradouro = buildValido();
		logradouro.setNome(somenteCaractere(1, "[a-z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return logradouro;
	}

	public static Logradouro buildNaoApenasLetraNome() {
		Logradouro logradouro = buildValido();
		logradouro.setNome(somenteCaractere(1, "[A-Z]") + apenasUmCaractere(nextInt(1, 10), "\\d", "[a-z]"));
		return logradouro;
	}

	public static Logradouro buildEspacoDuploNome() {
		Logradouro logradouro = buildValido();
		logradouro.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + "  " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return logradouro;
	}

}
