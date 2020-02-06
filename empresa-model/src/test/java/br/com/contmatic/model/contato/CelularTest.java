package br.com.contmatic.model.contato;

import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.verificaEncapsulamentos;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CELULAR_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.DDD_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_CONTATO_INVALIDO;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import br.com.contmatic.model.random.contato.CelularTestRandomBuilder;
import br.com.contmatic.validacoes.groups.Post;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class CelularTest.
 */
public class CelularTest {
    
	private static CelularTestRandomBuilder random;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		random = CelularTestRandomBuilder.getInstance();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		CelularTestRandomBuilder.cleanBuilder();
	}

    /**
     * Nao deve aceitar valor nulo no ddd.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_ddd() {
        assertTrue(procuraQualquerViolacao(random.buildNuloDdd(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no ddd.
     */
    @Test
    public void nao_deve_aceitar_mais_que_2_numerais_no_ddd() {
        assertTrue(procuraQualquerViolacao(random.buildMaisQue2NumeraisDdd(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no ddd.
     */
    @Test
    public void nao_deve_aceitar_menos_que_2_numerais_no_ddd() {
        assertTrue(procuraQualquerViolacao(random.buildMenosQue2NumeraisDdd(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no ddd.
     */
    @Test
    public void nao_deve_aceitar_caractere_nao_numeral_no_ddd() {
        assertTrue(procuraQualquerViolacao(random.buildNaoApenasNumeralDdd(), Post.class));
    }
    
    /**
     * Deve aceitar ddd valido.
     */
    @Test
    public void deve_aceitar_ddd_valido() {
        assertFalse(procuraViolacao(random.buildValid(), DDD_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar valor nulo no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_numero() {
        assertTrue(procuraQualquerViolacao(random.buildNuloNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no numero.
     */
    @Test
    public void nao_deve_aceitar_mais_que_9_numerais_no_numero() {
        assertTrue(procuraQualquerViolacao(random.buildMaisQue9NumeraisNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no numero.
     */
    @Test
    public void nao_deve_aceitar_menos_que_9_numerais_no_numero() {
        assertTrue(procuraQualquerViolacao(random.buildMenosQue9NumeraisNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no numero.
     */
    @Test
    public void nao_deve_aceitar_caractere_nao_numeral_no_numero() {
        assertTrue(procuraQualquerViolacao(random.buildNaoApenasNumeralNumero(), Post.class));
    }
    
    /**
     * Deve aceitar numero valido.
     */
    @Test
    public void deve_aceitar_numero_valido() {
        assertFalse(procuraViolacao(random.buildValid(), CELULAR_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar valor nulo no tipo contato celular.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_tipoContatoCelular() {
        assertTrue(procuraQualquerViolacao(random.buildNuloTipoContatoCelular(), Post.class));
    }
    
    /**
     * Deve aceitar valor nao nulo no tipo contato celular.
     */
    @Test
    public void deve_aceitar_valor_nao_nulo_no_tipoContatoCelular() {
        assertFalse(procuraViolacao(random.buildValid(), TIPO_CONTATO_INVALIDO, Post.class));
    }
        
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(verificaEncapsulamentos(Celular.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier
        .forClass(Celular.class)
        .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED)
        .withOnlyTheseFields("ddd", "numero")
        .verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
    	ToStringVerifier
    	.forClass(Celular.class)
    	.verify();
    }
    
}
