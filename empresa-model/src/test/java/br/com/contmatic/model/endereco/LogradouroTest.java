package br.com.contmatic.model.endereco;

import static br.com.contmatic.validacoes.utilidades.MensagensErro.NOME_INVALIDO;
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

import br.com.contmatic.model.random.endereco.LogradouroRandomBuilder;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class LogradouroTest.
 */
public class LogradouroTest {
    
    /** The logradouro. */
    private Logradouro logradouro;
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        logradouro = LogradouroRandomBuilder.buildValido();
    }

    /**
     * Nao deve aceitar valor nulo no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_nome() {
        logradouro.setNome(null);
        assertTrue(procuraAlgumErro(logradouro));
    }
    
    /**
     * Nao deve aceitar valor vazio no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_nome() {
    	logradouro.setNome("");
        assertTrue(procuraAlgumErro(logradouro));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_nome() {
        assertTrue(procuraAlgumErro(LogradouroRandomBuilder.buildMaiorTamanhoNome()));
    }

    /**
     * Nao deve aceitar valor com apenas um caractere no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_um_caractere_no_nome() {
        assertTrue(procuraAlgumErro(LogradouroRandomBuilder.buildApenasUmCaractereNome()));
    }

    /**
     * Nao deve aceitar valor com apenas espaco no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_nome() {
        assertTrue(procuraAlgumErro(LogradouroRandomBuilder.buildApenasEspacoNome()));
    }
    
    /**
     * Nao deve aceitar valor com primeiro caractere invalido no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_primeiro_caractere_minusculo_no_nome() {
        assertTrue(procuraAlgumErro(LogradouroRandomBuilder.buildPrimeiroCaractereMinusculoNome()));
    }

    /**
     * Nao deve aceitar valor com um caractere invalido no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_nao_letra_no_nome() {
        assertTrue(procuraAlgumErro(LogradouroRandomBuilder.buildNaoApenasLetraNome()));
    }
    
    /**
     * Nao deve aceitar valor com dois espacos juntos no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_dois_espacos_juntos_no_nome() {
        assertTrue(procuraAlgumErro(LogradouroRandomBuilder.buildEspacoDuploNome()));
    }
    
    /**
     * Deve aceitar nome valido.
     */
    @Test
    public void deve_aceitar_nome_valido() {
        assertFalse(verificaErro(LogradouroRandomBuilder.buildValido(), NOME_INVALIDO));
    }
    
    /**
     * Nao deve aceitar valor nulo no tipo uf.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_bairro() {
        logradouro.setBairro(null);
        assertTrue(procuraAlgumErro(logradouro));
    }
    
    /**
     * Deve aceitar valor nao nulo no tipo uf.
     */
    @Test
    public void deve_aceitar_valor_valido_nao_nulo_no_bairro() {
        assertFalse(procuraAlgumErro(LogradouroRandomBuilder.buildValido()));
    }
    
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(verificaEncapsulamentos(Logradouro.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier.forClass(Logradouro.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(logradouro));
    }
    
}
