package br.com.contmatic.model.conta;

import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.verificaEncapsulamentos;
import static br.com.contmatic.testes.utilidades.Verificadores.verificaToStringJSONSTYLE;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CODIGO_BANCO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NUMERO_AGENCIA_INVALIDO;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.model.random.conta.AgenciaTestRandomBuilder;
import br.com.contmatic.validacoes.groups.Post;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class AgenciaTest.
 */
public class AgenciaTest {
        
	private static AgenciaTestRandomBuilder random;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		random = AgenciaTestRandomBuilder.getInstance();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		random.cleanBuilder();
	}
	
    /**
     * Nao deve aceitar valor nulo no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_numero() {
        assertTrue(procuraQualquerViolacao(random.buildNuloNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_numero() {
        assertTrue(procuraQualquerViolacao(random.buildVazioNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar mais que 5 numerais no numero.
     */
    @Test
    public void nao_deve_aceitar_mais_que_5_numerais_no_numero() {
        assertTrue(procuraQualquerViolacao(random.buildMaisQue5NumeraisNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar caractere nao numeral no numero.
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
        assertFalse(procuraViolacao(random.buildValid(), NUMERO_AGENCIA_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar valor nulo no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_codigoBanco() {
        assertTrue(procuraQualquerViolacao(random.buildNuloCodigoBanco(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_codigoBanco() {
        assertTrue(procuraQualquerViolacao(random.buildVazioCodigoBanco(), Post.class));
    }
    
    /**
     * Nao deve aceitar mais que 5 numerais no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_mais_que_5_numerais_no_codigoBanco() {
        assertTrue(procuraQualquerViolacao(random.buildMaisQue4NumeraisCodigoBanco(), Post.class));
    }
    
    /**
     * Nao deve aceitar caractere nao numeral no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_caractere_nao_numeral_no_codigoBanco() {
        assertTrue(procuraQualquerViolacao(random.buildNaoApenasNumeralCodigoBanco(), Post.class));
    }
    
    /**
     * Deve aceitar codigo banco valido.
     */
    @Test
    public void deve_aceitar_codigoBanco_valido() {
        assertFalse(procuraViolacao(random.buildValid(), CODIGO_BANCO_INVALIDO, Post.class));
    }
    
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(verificaEncapsulamentos(Agencia.class));
    }
    
    /**
     * Deve possuir equals hashcode implementados corretamente que diferenciam pelo numero codigo banco.
     */
    @Test
    public void deve_possuir_equals_hashcode_implementados_corretamente() {
        EqualsVerifier
        .forClass(Agencia.class)
        .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED)
        .withOnlyTheseFields("codigoBanco", "numero")
        .verify();
    }
        
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(random.buildValid()));
    }
    
}
