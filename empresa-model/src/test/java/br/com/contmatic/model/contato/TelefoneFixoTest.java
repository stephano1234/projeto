package br.com.contmatic.model.contato;

import static br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder.buildMaisQue2NumeraisDdd;
import static br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder.buildMaisQue8NumeraisNumero;
import static br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder.buildMenosQue2NumeraisDdd;
import static br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder.buildMenosQue8NumeraisNumero;
import static br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder.buildNaoApenasNumeralDdd;
import static br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder.buildNaoApenasNumeralNumero;
import static br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder.buildValido;
import static br.com.contmatic.utilidades.MensagensErro.TELEFONE_INVALIDO;
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
 * The Class TelefoneFixoTest.
 */
public class TelefoneFixoTest {
    
    /** The telefoneFixo. */
    private TelefoneFixo telefoneFixo;
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        telefoneFixo = buildValido();
    }

    /**
     * Nao deve aceitar valor nulo no ddd.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_ddd() {
        telefoneFixo.setDdd(null);
        assertTrue(procuraAlgumErro(telefoneFixo));
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
        telefoneFixo.setNumero(null);
        assertTrue(procuraAlgumErro(telefoneFixo));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no numero.
     */
    @Test
    public void nao_deve_aceitar_mais_que_8_numerais_no_numero() {
        assertTrue(procuraAlgumErro(buildMaisQue8NumeraisNumero()));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no numero.
     */
    @Test
    public void nao_deve_aceitar_menos_que_8_numerais_no_numero() {
        assertTrue(procuraAlgumErro(buildMenosQue8NumeraisNumero()));
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
        assertFalse(verificaErro(buildValido(), TELEFONE_INVALIDO));
    }
    
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(verificaEncapsulamentos(TelefoneFixo.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier.forClass(TelefoneFixo.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(telefoneFixo));
    }
    
}
