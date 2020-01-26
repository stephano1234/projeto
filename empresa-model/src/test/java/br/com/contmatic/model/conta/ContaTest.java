package br.com.contmatic.model.conta;

import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.verificaEncapsulamentos;
import static br.com.contmatic.testes.utilidades.Verificadores.verificaToStringJSONSTYLE;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.AGENCIA_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CODIGO_BANCO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NUMERO_AGENCIA_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NUMERO_CONTA_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_CONTA_INVALIDO;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.model.random.conta.ContaTestRandomBuilder;
import br.com.contmatic.validacoes.groups.Post;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class ContaTest.
 */
public class ContaTest {

	private static ContaTestRandomBuilder random;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		random = ContaTestRandomBuilder.getInstance();
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
     * Nao deve aceitar valor maior que tamanho no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_numero() {
        assertTrue(procuraQualquerViolacao(random.buildMaisQue12NumeraisNumero(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_numero() {
        assertTrue(procuraQualquerViolacao(random.buildNaoApenasNumeralNumero(), Post.class));
    }
    
    /**
     * Deve aceitar numero valido.
     */
    @Test
    public void deve_aceitar_numero_valido() {
        assertFalse(procuraViolacao(random.buildValid(), NUMERO_CONTA_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar valor nulo no agencia.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_agencia() {
        assertTrue(procuraQualquerViolacao(random.buildNuloAgencia(), Post.class));
    }
    
    /**
     * Nao deve aceitar agencia invalido.
     */
    @Test
    public void nao_deve_aceitar_agencia_invalido() {
        assertTrue(procuraQualquerViolacao(random.buildAgenciaIvalido(), Post.class));
    }
    
    @Test
    public void deve_aceitar_agencia_valida() {
    	assertFalse(procuraViolacao(random.buildValid(), AGENCIA_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(random.buildValid(), NUMERO_AGENCIA_INVALIDO, Post.class));
        assertFalse(procuraViolacao(random.buildValid(), CODIGO_BANCO_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar valor nulo no tipo conta.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_tipoConta() {
        assertTrue(procuraQualquerViolacao(random.buildNuloTipoConta(), Post.class));
    }
    
    /**
     * Deve aceitar valor nao nulo no tipo conta.
     */
    @Test
    public void deve_aceitar_valor_nao_nulo_no_tipoConta() {
        assertFalse(procuraViolacao(random.buildValid(), TIPO_CONTA_INVALIDO, Post.class));
    }    
    
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(verificaEncapsulamentos(Conta.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void deve_possuir_equals_hashcode_implementados_corretamente() {
        EqualsVerifier
        .forClass(Conta.class)
        .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED)
        .withOnlyTheseFields("agencia", "numero")
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
