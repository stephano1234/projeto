package br.com.contmatic.model.conta;

import static br.com.contmatic.templates.conta.AgenciaRandomBuilder.buildMaisQue4NumeraisCodigoBanco;
import static br.com.contmatic.templates.conta.AgenciaRandomBuilder.buildMaisQue5NumeraisNumero;
import static br.com.contmatic.templates.conta.AgenciaRandomBuilder.buildNaoApenasNumeralCodigoBanco;
import static br.com.contmatic.templates.conta.AgenciaRandomBuilder.buildNaoApenasNumeralNumero;
import static br.com.contmatic.templates.conta.AgenciaRandomBuilder.buildValido;

import static br.com.contmatic.utilidades.MensagensErro.CODIGO_BANCO_INVALIDO;
import static br.com.contmatic.utilidades.MensagensErro.NUMERO_AGENCIA_INVALIDO;

import static br.com.contmatic.utilidades.Verificadores.procuraAlgumErro;
import static br.com.contmatic.utilidades.Verificadores.verificaEncapsulamentos;
import static br.com.contmatic.utilidades.Verificadores.verificaErro;
import static br.com.contmatic.utilidades.Verificadores.verificaToStringJSONSTYLE;

import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class AgenciaTest.
 */
public class AgenciaTest {
        
	/** The agencia. */
	private Agencia agencia;
	
    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
    	agencia = buildValido();
    }

    /**
     * Nao deve aceitar valor nulo no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_numero() {
    	agencia.setNumero(null);
        assertTrue(procuraAlgumErro(agencia));
    }
    
    /**
     * Nao deve aceitar valor vazio no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_numero() {
        agencia.setNumero("");
        assertTrue(procuraAlgumErro(agencia));
    }
    
    /**
     * Nao deve aceitar mais que 5 numerais no numero.
     */
    @Test
    public void nao_deve_aceitar_mais_que_5_numerais_no_numero() {
        assertTrue(procuraAlgumErro(buildMaisQue5NumeraisNumero()));
    }
    
    /**
     * Nao deve aceitar caractere nao numeral no numero.
     */
    @Test
    public void nao_deve_aceitar_caractere_nao_numeral_no_numero() {
        assertTrue(procuraAlgumErro(buildNaoApenasNumeralNumero()));
    }
    
    /**
     * Deve aceitar numero valido.
     */
    @Test
    public void deve_aceitar_numero_valido() {
        assertFalse(verificaErro(buildValido(), NUMERO_AGENCIA_INVALIDO));
    }

    /**
     * Nao deve aceitar valor nulo no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_codigoBanco() {
        agencia.setCodigoBanco(null);
        assertTrue(procuraAlgumErro(agencia));
    }
    
    /**
     * Nao deve aceitar valor vazio no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_codigoBanco() {
        agencia.setCodigoBanco("");
        assertTrue(procuraAlgumErro(agencia));
    }
    
    /**
     * Nao deve aceitar mais que 5 numerais no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_mais_que_5_numerais_no_codigoBanco() {
        assertTrue(procuraAlgumErro(buildMaisQue4NumeraisCodigoBanco()));
    }
    
    /**
     * Nao deve aceitar caractere nao numeral no codigo banco.
     */
    @Test
    public void nao_deve_aceitar_caractere_nao_numeral_no_codigoBanco() {
        assertTrue(procuraAlgumErro(buildNaoApenasNumeralCodigoBanco()));
    }
    
    /**
     * Deve aceitar codigo banco valido.
     */
    @Test
    public void deve_aceitar_codigoBanco_valido() {
        assertFalse(verificaErro(buildValido(), CODIGO_BANCO_INVALIDO));
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
        EqualsVerifier.forClass(Agencia.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
        
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(agencia));
    }
    
}
