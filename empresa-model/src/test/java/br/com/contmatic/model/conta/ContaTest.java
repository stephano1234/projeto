package br.com.contmatic.model.conta;

import static br.com.contmatic.model.random.conta.ContaTestRandomBuilder.cleanBuilder;
import static br.com.contmatic.model.random.conta.ContaTestRandomBuilder.getInstance;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.AGENCIA_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.CODIGO_BANCO_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NUMERO_AGENCIA_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NUMERO_CONTA_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_CONTA_INVALIDO;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraViolacao;
import static com.jparams.verifier.tostring.preset.Presets.APACHE_TO_STRING_BUILDER_JSON_STYLE;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import br.com.contmatic.model.restricoes.grupos.Post;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class ContaTest.
 */
public class ContaTest {

	@AfterClass
	public static void tearDownAfterClass() {
		cleanBuilder();
	}

	/**
     * Nao deve aceitar valor nulo no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_numero() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNuloNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_numero() {
        assertTrue(procuraQualquerViolacao(getInstance().buildVazioNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_numero() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaisQue12NumeraisNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_numero() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNaoApenasNumeralNumero(), Post.class));
    }
    
    /**
     * Deve aceitar numero valido.
     */
    @Test
    public void deve_aceitar_numero_valido() {
        assertFalse(procuraViolacao(getInstance().buildValid(), NUMERO_CONTA_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar valor nulo no agencia.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_agencia() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNuloAgencia(), Post.class));
    }
    
    /**
     * Nao deve aceitar agencia invalido.
     */
    @Test
    public void nao_deve_aceitar_agencia_invalido() {
        assertTrue(procuraQualquerViolacao(getInstance().buildAgenciaIvalido(), Post.class));
    }
    
    @Test
    public void deve_aceitar_agencia_valida() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), AGENCIA_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(getInstance().buildValid(), NUMERO_AGENCIA_INVALIDO, Post.class));
        assertFalse(procuraViolacao(getInstance().buildValid(), CODIGO_BANCO_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar valor nulo no tipo conta.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_tipoConta() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNuloTipoConta(), Post.class));
    }
    
    /**
     * Deve aceitar valor nao nulo no tipo conta.
     */
    @Test
    public void deve_aceitar_valor_nao_nulo_no_tipoConta() {
        assertFalse(procuraViolacao(getInstance().buildValid(), TIPO_CONTA_INVALIDO, Post.class));
    }    
        
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void deve_possuir_equals_hashcode_implementados_corretamente() {
        EqualsVerifier.forClass(Conta.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).withOnlyTheseFields("agencia", "numero").verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
    	ToStringVerifier.forClass(Conta.class).withPreset(APACHE_TO_STRING_BUILDER_JSON_STYLE).verify();
    }
    
}
