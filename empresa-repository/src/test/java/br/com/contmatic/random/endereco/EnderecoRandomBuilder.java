package br.com.contmatic.random.endereco;

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
import br.com.contmatic.random.contato.TelefoneFixoRandomBuilder;

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
	
}
