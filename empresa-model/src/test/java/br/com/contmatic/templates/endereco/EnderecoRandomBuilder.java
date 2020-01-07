package br.com.contmatic.templates.endereco;

import static br.com.contmatic.utilidades.ConstantesTesteNumericas.CEP;
import static br.com.contmatic.utilidades.ConstantesTesteNumericas.ELEMENTOS_ARRAY_GERADA;
import static br.com.contmatic.utilidades.ConstantesTesteNumericas.EXCLUI_STRING_VAZIO;
import static br.com.contmatic.utilidades.ConstantesTesteNumericas.MAX_NUMERO_ENDERECO;
import static br.com.contmatic.utilidades.ConstantesTesteNumericas.TAMANHO_REGULAR;
import static br.com.contmatic.utilidades.ConstantesTesteString.APENAS_ESPACO;
import static br.com.contmatic.utilidades.ConstantesTesteString.APENAS_NUMERAL;
import static br.com.contmatic.utilidades.FuncoesRandomicas.apenasUmCaractere;
import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.HashSet;
import java.util.Set;

import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.endereco.TipoEndereco;
import br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder;

public class EnderecoRandomBuilder {

	public static Endereco buildValido() {
		Endereco endereco = new Endereco();
		endereco.setCep(somenteCaractere(CEP, APENAS_NUMERAL));
		endereco.setNumero(somenteCaractere(nextInt(EXCLUI_STRING_VAZIO, MAX_NUMERO_ENDERECO + 1), APENAS_NUMERAL));
        endereco.setLogradouro(LogradouroRandomBuilder.buildValido());
		endereco.setComplemento(apenasUmCaractere(nextInt(EXCLUI_STRING_VAZIO, TAMANHO_REGULAR + 1), "\\w", APENAS_ESPACO));
        Set<TelefoneFixo> telefonesFixo = new HashSet<TelefoneFixo>();        
        for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {        
            telefonesFixo.add(TelefoneFixoRandomBuilder.buildValido());        
        }
        endereco.setTelefonesFixo(telefonesFixo);
        endereco.setTipoEndereco(TipoEndereco.values()[nextInt(0, TipoEndereco.values().length)]);
		return endereco;
	}
	
	public static Endereco buildMaiorTamanhoCep() {
		Endereco endereco = buildValido();
		endereco.setCep(somenteCaractere(CEP + 1, APENAS_NUMERAL));
		return endereco;
	}
	
	public static Endereco buildMenorTamanhoCep() {
		Endereco endereco = buildValido();
		endereco.setCep(somenteCaractere(CEP - 1, APENAS_NUMERAL));
		return endereco;
	}

	public static Endereco buildNaoApenasNumeralCep() {
		Endereco endereco = buildValido();
		endereco.setCep(apenasUmCaractere(CEP, "[^0-9]", APENAS_NUMERAL));
		return endereco;
	}

	public static Endereco buildMaiorTamanhoNumero() {
		Endereco endereco = buildValido();
		endereco.setNumero(somenteCaractere(MAX_NUMERO_ENDERECO + 1, APENAS_NUMERAL));
		return endereco;
	}

	public static Endereco buildNaoApenasNumeralNumero() {
		Endereco endereco = buildValido();
		endereco.setNumero(apenasUmCaractere(nextInt(EXCLUI_STRING_VAZIO, MAX_NUMERO_ENDERECO + 1), "[^0-9]", APENAS_NUMERAL));
		return endereco;
	}

	public static Endereco buildMaiorTamanhoComplemento() {
		Endereco endereco = buildValido();
		endereco.setNumero(somenteCaractere(TAMANHO_REGULAR + 1, "[A-Za-z0-9]"));
		return endereco;
	}

	public static Endereco buildApenasEspacoComplemento() {
		Endereco endereco = buildValido();
		endereco.setNumero(somenteCaractere(nextInt(EXCLUI_STRING_VAZIO, TAMANHO_REGULAR + 1), APENAS_ESPACO));
		return endereco;
	}

}
