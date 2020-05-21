package br.com.contmatic.model.conta;

import static br.com.contmatic.model.random.conta.AgenciaTestRandomBuilder.cleanBuilder;
import static br.com.contmatic.model.random.conta.AgenciaTestRandomBuilder.getInstance;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.CODIGO_BANCO_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NUMERO_AGENCIA_INVALIDO;
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
 * The Class AgenciaTest.
 */
public class AgenciaTest {
        
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
     * Nao deve aceitar mais que 5 numerais no numero.
     */
    @Test
    public void nao_deve_aceitar_mais_que_5_numerais_no_numero() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaisQue5NumeraisNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar caractere nao numeral no numero.
     */
    @Test
    public void nao_deve_aceitar_caractere_nao_numeral_no_numero() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNaoApenasNumeralNumero(), Post.class));
    }
    
    /**
     * Deve aceitar numero valido.
     */
    @Test
    public void deve_aceitar_numero_valido() {
        assertFalse(procuraViolacao(getInstance().buildValid(), NUMERO_AGENCIA_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar valor nulo no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_codigoBanco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNuloCodigoBanco(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_codigoBanco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildVazioCodigoBanco(), Post.class));
    }
    
    /**
     * Nao deve aceitar mais que 5 numerais no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_mais_que_5_numerais_no_codigoBanco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaisQue4NumeraisCodigoBanco(), Post.class));
    }
    
    /**
     * Nao deve aceitar caractere nao numeral no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_caractere_nao_numeral_no_codigoBanco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNaoApenasNumeralCodigoBanco(), Post.class));
    }
    
    /**
     * Deve aceitar codigo banco valido.
     */
    @Test
    public void deve_aceitar_codigoBanco_valido() {
        assertFalse(procuraViolacao(getInstance().buildValid(), CODIGO_BANCO_INVALIDO, Post.class));
    }
        
    /**
     * Deve possuir equals hashcode implementados corretamente que diferenciam pelo numero codigo banco.
     */
    @Test
    public void deve_possuir_equals_hashcode_implementados_corretamente() {
        EqualsVerifier.forClass(Agencia.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).withOnlyTheseFields("codigoBanco", "numero").verify();
    }
        
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
    	ToStringVerifier.forClass(Agencia.class).withPreset(APACHE_TO_STRING_BUILDER_JSON_STYLE).verify();
    }
    
}
