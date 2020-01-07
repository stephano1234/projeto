package br.com.contmatic.model.conta;

import static br.com.contmatic.templates.conta.ContaRandomBuilder.buildAgenciaIvalido;
import static br.com.contmatic.templates.conta.ContaRandomBuilder.buildMaisQue12NumeraisNumero;
import static br.com.contmatic.templates.conta.ContaRandomBuilder.buildNaoApenasNumeralNumero;
import static br.com.contmatic.templates.conta.ContaRandomBuilder.buildValido;
import static br.com.contmatic.utilidades.MensagensErro.NUMERO_CONTA_INVALIDO;
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
 * The Class ContaTest.
 */
public class ContaTest {

    /** The conta. */
    private Conta conta;
            
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        conta = buildValido();
    }

    /**
     * Nao deve aceitar valor nulo no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_numero() {
        conta.setNumero(null);
        assertTrue(procuraAlgumErro(conta));
    }
    
    /**
     * Nao deve aceitar valor vazio no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_numero() {
        conta.setNumero("");
        assertTrue(procuraAlgumErro(conta));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_numero() {
        assertTrue(procuraAlgumErro(buildMaisQue12NumeraisNumero()));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_numero() {
        assertTrue(procuraAlgumErro(buildNaoApenasNumeralNumero()));
    }
    
    /**
     * Deve aceitar numero valido.
     */
    @Test
    public void deve_aceitar_numero_valido() {
        assertFalse(verificaErro(buildValido(), NUMERO_CONTA_INVALIDO));
    }

    /**
     * Nao deve aceitar valor nulo no agencia.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_agencia() {
        conta.setAgencia(null);
        assertTrue(procuraAlgumErro(conta));
    }
    
    /**
     * Nao deve aceitar agencia invalido.
     */
    @Test
    public void nao_deve_aceitar_agencia_invalido() {
        assertTrue(procuraAlgumErro(buildAgenciaIvalido()));
    }
    
    /**
     * Deve aceitar agencia nao nulo valido.
     */
    @Test
    public void deve_aceitar_agencia_nao_nulo_valido() {
        assertFalse(procuraAlgumErro(buildValido()));
    }
    
    /**
     * Nao deve aceitar valor nulo no tipo conta.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_tipoConta() {
        conta.setTipoConta(null);
        assertTrue(procuraAlgumErro(conta));
    }
    
    /**
     * Deve aceitar valor nao nulo no tipo conta.
     */
    @Test
    public void deve_aceitar_valor_nao_nulo_no_tipoConta() {
        conta.setTipoConta(TipoConta.CONTA_CORRENTE);
        assertFalse(procuraAlgumErro(conta));
    }    

    @Test
    public void deve_aceitar_conta_valido() {
        assertFalse(procuraAlgumErro(buildValido()));    	
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
        EqualsVerifier.forClass(Conta.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(conta));
    }
    
}
