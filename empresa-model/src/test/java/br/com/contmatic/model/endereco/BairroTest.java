package br.com.contmatic.model.endereco;

import static br.com.contmatic.utilidades.MensagensErro.NOME_INVALIDO;
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

import br.com.contmatic.templates.endereco.BairroRandomBuilder;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class BairroTest.
 */
public class BairroTest {
    
    /** The bairro. */
    private Bairro bairro;
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bairro = BairroRandomBuilder.buildValido();
    }

    /**
     * Nao deve aceitar valor nulo no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_nome() {
        bairro.setNome(null);
        assertTrue(procuraAlgumErro(bairro));
    }
    
    /**
     * Nao deve aceitar valor vazio no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_nome() {
    	bairro.setNome("");
        assertTrue(procuraAlgumErro(bairro));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_nome() {
        assertTrue(procuraAlgumErro(BairroRandomBuilder.buildMaiorTamanhoNome()));
    }

    /**
     * Nao deve aceitar valor com apenas um caractere no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_um_caractere_no_nome() {
        assertTrue(procuraAlgumErro(BairroRandomBuilder.buildApenasUmCaractereNome()));
    }

    /**
     * Nao deve aceitar valor com apenas espaco no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_nome() {
        assertTrue(procuraAlgumErro(BairroRandomBuilder.buildApenasEspacoNome()));
    }
    
    /**
     * Nao deve aceitar valor com primeiro caractere invalido no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_primeiro_caractere_minusculo_no_nome() {
        assertTrue(procuraAlgumErro(BairroRandomBuilder.buildPrimeiroCaractereMinusculoNome()));
    }

    /**
     * Nao deve aceitar valor com um caractere invalido no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_nao_letra_no_nome() {
        assertTrue(procuraAlgumErro(BairroRandomBuilder.buildNaoApenasLetraNome()));
    }
    
    /**
     * Nao deve aceitar valor com dois espacos juntos no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_dois_espacos_juntos_no_nome() {
        assertTrue(procuraAlgumErro(BairroRandomBuilder.buildEspacoDuploNome()));
    }
    
    /**
     * Deve aceitar nome valido.
     */
    @Test
    public void deve_aceitar_nome_valido() {
        assertFalse(verificaErro(BairroRandomBuilder.buildValido(), NOME_INVALIDO));
    }
    
    /**
     * Nao deve aceitar valor nulo no tipo uf.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_cidade() {
        bairro.setCidade(null);
        assertTrue(procuraAlgumErro(bairro));
    }
    
    /**
     * Deve aceitar valor nao nulo no tipo uf.
     */
    @Test
    public void deve_aceitar_valor_valido_nao_nulo_no_cidade() {
        assertFalse(procuraAlgumErro(BairroRandomBuilder.buildValido()));
    }
    
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(verificaEncapsulamentos(Bairro.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier.forClass(Bairro.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(bairro));
    }
    
}
