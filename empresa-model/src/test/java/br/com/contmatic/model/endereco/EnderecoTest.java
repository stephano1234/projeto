package br.com.contmatic.model.endereco;

import static br.com.contmatic.utilidades.MensagensErro.CEP_INVALIDO;
import static br.com.contmatic.utilidades.MensagensErro.NOT_BLANK_INVALIDO;
import static br.com.contmatic.utilidades.MensagensErro.NUMERO_ENDERECO_INVALIDO;
import static br.com.contmatic.utilidades.Verificadores.procuraAlgumErro;
import static br.com.contmatic.utilidades.Verificadores.verificaEncapsulamentos;
import static br.com.contmatic.utilidades.Verificadores.verificaErro;
import static br.com.contmatic.utilidades.Verificadores.verificaToStringJSONSTYLE;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder;
import br.com.contmatic.templates.endereco.CidadeRandomBuilder;
import br.com.contmatic.templates.endereco.EnderecoRandomBuilder;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class EnderecoTest.
 */
public class EnderecoTest {

    /** The endereco. */
    private Endereco endereco;
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        endereco = EnderecoRandomBuilder.buildValido();
    }

    /**
     * Nao deve aceitar valor nulo no cep.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_cep() {
        endereco.setCep(null);
        assertTrue(procuraAlgumErro(endereco));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no cep.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_cep() {
        assertTrue(procuraAlgumErro(EnderecoRandomBuilder.buildMaiorTamanhoCep()));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no cep.
     */
    @Test
    public void nao_deve_aceitar_valor_menor_que_tamanho_no_cep() {
        assertTrue(procuraAlgumErro(EnderecoRandomBuilder.buildMenorTamanhoCep()));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no cep.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_cep() {
        assertTrue(procuraAlgumErro(EnderecoRandomBuilder.buildNaoApenasNumeralCep()));
    }
    
    /**
     * Deve aceitar cep valido.
     */
    @Test
    public void deve_aceitar_cep_valido() {
        assertFalse(verificaErro(EnderecoRandomBuilder.buildValido(), CEP_INVALIDO));
    }

    /**
     * Deve aceitar valor nulo no numero.
     */
    @Test
    public void deve_aceitar_valor_nulo_no_numero() {
    	endereco.setNumero(null);
    	assertFalse(procuraAlgumErro(endereco));
    }
    
    /**
     * Nao deve aceitar valor vazio no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_numero() {
        endereco.setNumero("");
        assertTrue(procuraAlgumErro(endereco));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_numero() {
        assertTrue(procuraAlgumErro(EnderecoRandomBuilder.buildMaiorTamanhoNumero()));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no numero.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_numero() {
        assertTrue(procuraAlgumErro(EnderecoRandomBuilder.buildNaoApenasNumeralNumero()));
    }
    
    /**
     * Deve aceitar numero valido.
     */
    @Test
    public void deve_aceitar_numero_valido() {
        assertFalse(verificaErro(EnderecoRandomBuilder.buildValido(), NUMERO_ENDERECO_INVALIDO));
    }
    
    /**
     * Deve aceitar valor nulo no complemento.
     */
    @Test
    public void deve_aceitar_valor_nulo_no_complemento() {
    	endereco.setComplemento(null);
    	assertFalse(procuraAlgumErro(endereco));
    }
    
    /**
     * Nao deve aceitar valor vazio no complemento.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_complemento() {
        endereco.setComplemento("");
        assertTrue(procuraAlgumErro(endereco));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no complemento.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_complemento() {
        assertTrue(procuraAlgumErro(EnderecoRandomBuilder.buildMaiorTamanhoComplemento()));
    }
    
    /**
     * Nao deve aceitar valor com apenas espaco no complemento.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_complemento() {
        assertTrue(procuraAlgumErro(EnderecoRandomBuilder.buildApenasEspacoComplemento()));
    }
    
    /**
     * Deve aceitar complemento valido.
     */
    @Test
    public void deve_aceitar_complemento_valido() {
        assertFalse(verificaErro(EnderecoRandomBuilder.buildValido(), NOT_BLANK_INVALIDO));
    }
    
    /**
     * Nao deve aceitar valor nulo no logradouro.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_logradouro() {
        endereco.setLogradouro(null);
        assertTrue(procuraAlgumErro(endereco));
    }
    
    /**
     * Nao deve aceitar logradouro invalido.
     */
    @Test
    public void nao_deve_aceitar_logradouro_invalido() {
    	endereco.getLogradouro().getBairro().setCidade(CidadeRandomBuilder.buildPrimeiroCaractereMinusculoNome());
        assertTrue(procuraAlgumErro(endereco));
    }
    
    /**
     * Deve aceitar logradouro nao nulo valido.
     */
    @Test
    public void deve_aceitar_logradouro_nao_nulo_valido() {
        assertFalse(procuraAlgumErro(EnderecoRandomBuilder.buildValido()));
    }
    
    /**
     * Deve aceitar valor nulo no telefones fixo.
     */
    @Test
    public void deve_aceitar_valor_nulo_no_telefonesFixo() {
    	endereco.setTelefonesFixo(null);
    	assertFalse(procuraAlgumErro(endereco));
    }

    /**
     * Nao deve aceitar telefones fixo vazio.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_vazio() {
        endereco.setTelefonesFixo(new HashSet<TelefoneFixo>());
        assertTrue(procuraAlgumErro(endereco));
    }

    /**
     * Nao deve aceitar telefones fixo com elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_elemento_nulo() {
        endereco.getTelefonesFixo().add(null);
    	assertTrue(procuraAlgumErro(endereco));
    }
    
    /**
     * Nao deve aceitar telefones fixo com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_elemento_invalido() {
    	endereco.getTelefonesFixo().add(TelefoneFixoRandomBuilder.buildNaoApenasNumeralNumero());
        assertTrue(procuraAlgumErro(endereco));
    }
    
    /**
     * Deve aceitar telefones fixo nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_telefonesFixo_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
        assertFalse(procuraAlgumErro(EnderecoRandomBuilder.buildValido()));
    }
    
    /**
     * Nao deve aceitar valor nulo no tipo endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_tipoEndereco() {
        endereco.setTipoEndereco(null);
        assertTrue(procuraAlgumErro(endereco));
    }
    
    /**
     * Deve aceitar valor nao nulo no tipo endereco.
     */
    @Test
    public void deve_aceitar_valor_nao_nulo_no_tipoEndereco() {
        endereco.setTipoEndereco(TipoEndereco.COMERCIAL);
        assertFalse(procuraAlgumErro(endereco));
    }
    
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(verificaEncapsulamentos(Endereco.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier.forClass(Endereco.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(endereco));
    }
    
}
