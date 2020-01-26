package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.ConstantesTesteNumericas.EXCLUI_STRING_VAZIO;
import static br.com.contmatic.testes.utilidades.ConstantesTesteNumericas.TAMANHO_REGULAR;
import static br.com.contmatic.testes.utilidades.ConstantesTesteString.APENAS_ESPACO;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.apenasUmCaractere;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.somenteCaractere;
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
	
	public static Cidade buildApenasEspacoNome() {
		Cidade cidade = buildValido();
		cidade.setNome(somenteCaractere(nextInt(EXCLUI_STRING_VAZIO, TAMANHO_REGULAR), APENAS_ESPACO));
		return cidade;
	}
	
	public static Cidade buildMaiorTamanhoNome() {
		Cidade cidade = buildValido();
		cidade.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(TAMANHO_REGULAR + 1, "[a-z]"));
		return cidade;
	}
	
	public static Cidade buildApenasUmCaractereNome() {
		Cidade cidade = buildValido();
		cidade.setNome(somenteCaractere(1, "[A-Z]"));
		return cidade;
	}

	public static Cidade buildPrimeiroCaractereMinusculoNome() {
		Cidade cidade = buildValido();
		cidade.setNome(somenteCaractere(1, "[a-z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return cidade;
	}

	public static Cidade buildNaoApenasLetraNome() {
		Cidade cidade = buildValido();
		cidade.setNome(somenteCaractere(1, "[A-Z]") + apenasUmCaractere(nextInt(1, 10), "\\d", "[a-z]"));
		return cidade;
	}

	public static Cidade buildEspacoDuploNome() {
		Cidade cidade = buildValido();
		cidade.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + "  " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return cidade;
	}

}
