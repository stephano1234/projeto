package br.com.contmatic.model.random.endereco;

import static br.com.contmatic.testes.utilidades.ConstantesTesteNumericas.EXCLUI_STRING_VAZIO;
import static br.com.contmatic.testes.utilidades.ConstantesTesteNumericas.TAMANHO_REGULAR;
import static br.com.contmatic.testes.utilidades.ConstantesTesteString.APENAS_ESPACO;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.apenasUmCaractere;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.somenteCaractere;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.endereco.Bairro;

public class BairroRandomBuilder {

	public static Bairro buildValido() {
		Bairro bairro = new Bairro();
		bairro.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		bairro.setCidade(CidadeRandomBuilder.buildValido());
		return bairro;
	}
	
	public static Bairro buildApenasEspacoNome() {
		Bairro bairro = buildValido();
		bairro.setNome(somenteCaractere(nextInt(EXCLUI_STRING_VAZIO, TAMANHO_REGULAR), APENAS_ESPACO));
		return bairro;
	}
	
	public static Bairro buildMaiorTamanhoNome() {
		Bairro bairro = buildValido();
		bairro.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(TAMANHO_REGULAR + 1, "[a-z]"));
		return bairro;
	}
	
	public static Bairro buildApenasUmCaractereNome() {
		Bairro bairro = buildValido();
		bairro.setNome(somenteCaractere(1, "[A-Z]"));
		return bairro;
	}

	public static Bairro buildPrimeiroCaractereMinusculoNome() {
		Bairro bairro = buildValido();
		bairro.setNome(somenteCaractere(1, "[a-z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return bairro;
	}

	public static Bairro buildNaoApenasLetraNome() {
		Bairro bairro = buildValido();
		bairro.setNome(somenteCaractere(1, "[A-Z]") + apenasUmCaractere(nextInt(1, 10), "\\d", "[a-z]"));
		return bairro;
	}

	public static Bairro buildEspacoDuploNome() {
		Bairro bairro = buildValido();
		bairro.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + "  " + somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		return bairro;
	}

}
