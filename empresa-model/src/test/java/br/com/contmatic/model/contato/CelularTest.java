package br.com.contmatic.model.contato;

import static br.com.contmatic.templates.contato.CelularRandomBuilder.buildMaisQue2NumeraisDdd;
import static br.com.contmatic.templates.contato.CelularRandomBuilder.buildMaisQue9NumeraisNumero;
import static br.com.contmatic.templates.contato.CelularRandomBuilder.buildMenosQue2NumeraisDdd;
import static br.com.contmatic.templates.contato.CelularRandomBuilder.buildMenosQue9NumeraisNumero;
import static br.com.contmatic.templates.contato.CelularRandomBuilder.buildNaoApenasNumeralDdd;
import static br.com.contmatic.templates.contato.CelularRandomBuilder.buildNaoApenasNumeralNumero;
import static br.com.contmatic.templates.contato.CelularRandomBuilder.buildValido;
import static br.com.contmatic.utilidades.MensagensErro.CELULAR_INVALIDO;
import static br.com.contmatic.utilidades.MensagensErro.DDD_INVALIDO;
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
 * The Class CelularTest.
 */
public class CelularTest {
    
    /** The celular. */
    private Celular celular;
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        celular = buildValido();
    }

    /**
     * Nao deve aceitar valor nulo no ddd.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_ddd() {
        celular.setDdd(null);
        assertTrue(procuraAlgumErro(celular));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no ddd.
     */
    @Test
    public void nao_deve_aceitar_mais_que_2_numerais_no_ddd() {
        assertTrue(procuraAlgumErro(buildMaisQue2NumeraisDdd()));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no ddd.
     */
    @Test
    public void nao_deve_aceitar_menos_que_2_numerais_no_ddd() {
        assertTrue(procuraAlgumErro(buildMenosQue2NumeraisDdd()));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no ddd.
     */
    @Test
    public void nao_deve_aceitar_caractere_nao_numeral_no_ddd() {
        assertTrue(procuraAlgumErro(buildNaoApenasNumeralDdd()));
    }
    
    /**
     * Deve aceitar ddd valido.
     */
    @Test
    public void deve_aceitar_ddd_valido() {
        assertFalse(verificaErro(buildValido(), DDD_INVALIDO));
    }
    
    /**
     * Nao deve aceitar valor nulo no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_numero() {
        celular.setNumero(null);
        assertTrue(procuraAlgumErro(celular));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no numero.
     */
    @Test
    public void nao_deve_aceitar_mais_que_9_numerais_no_numero() {
        assertTrue(procuraAlgumErro(buildMaisQue9NumeraisNumero()));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no numero.
     */
    @Test
    public void nao_deve_aceitar_menos_que_9_numerais_no_numero() {
        assertTrue(procuraAlgumErro(buildMenosQue9NumeraisNumero()));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no numero.
     */
    @Test
    public void nao_deve_aceitar_caractere_nao_numeral_no_numero() {
        assertTrue(procuraAlgumErro(buildNaoApenasNumeralNumero()));
    }
    
    /**
     * Deve aceitar numero valido.
     */
    public void deve_aceitar_numero_valido() {
        assertFalse(verificaErro(buildValido(), CELULAR_INVALIDO));
    }
    
    /**
     * Nao deve aceitar valor nulo no tipo contato celular.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_tipoContatoCelular() {
        celular.setTipoContatoCelular(null);
        assertTrue(procuraAlgumErro(celular));
    }
    
    /**
     * Deve aceitar valor nao nulo no tipo contato celular.
     */
    @Test
    public void deve_aceitar_valor_nao_nulo_no_tipoContatoCelular() {
        celular.setTipoContatoCelular(TipoContatoCelular.APENAS_LIGACAO);
        assertFalse(procuraAlgumErro(celular));
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
        EqualsVerifier.forClass(Celular.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(celular));
    }
    
}
