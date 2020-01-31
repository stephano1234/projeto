package br.com.contmatic.model.endereco;

import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.verificaEncapsulamentos;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.BAIRRO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CEP_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.COMPLEMENTO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.DDD_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_TELEFONES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LOGRADOURO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NOME_LOGRADOURO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NUMERO_ENDERECO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TELEFONE_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_ENDERECO_INVALIDO;
import static com.jparams.verifier.tostring.preset.Presets.APACHE_TO_STRING_BUILDER_JSON_STYLE;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import br.com.contmatic.model.random.endereco.EnderecoTestRandomBuilder;
import br.com.contmatic.validacoes.groups.Post;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class EnderecoTest.
 */
public class EnderecoTest {

	private static EnderecoTestRandomBuilder random;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		random = EnderecoTestRandomBuilder.getInstance();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		random.cleanBuilder();
	}

    /**
     * Nao deve aceitar valor nulo no cep.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_cep() {
        assertTrue(procuraQualquerViolacao(random.buildNuloCep(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no cep.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_cep() {
        assertTrue(procuraQualquerViolacao(random.buildMaiorTamanhoCep(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no cep.
     */
    @Test
    public void nao_deve_aceitar_valor_menor_que_tamanho_no_cep() {
        assertTrue(procuraQualquerViolacao(random.buildMenorTamanhoCep(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no cep.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_cep() {
        assertTrue(procuraQualquerViolacao(random.buildNaoApenasNumeralCep(), Post.class));
    }
    
    /**
     * Deve aceitar cep valido.
     */
    @Test
    public void deve_aceitar_cep_valido() {
        assertFalse(procuraViolacao(random.buildValid(), CEP_INVALIDO, Post.class));
    }

    /**
     * Deve aceitar valor nulo no numero.
     */
    @Test
    public void deve_aceitar_valor_nulo_no_numero() {
    	assertFalse(procuraQualquerViolacao(random.buildNuloNumero(), Post.class));
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
        assertTrue(procuraQualquerViolacao(random.buildMaiorTamanhoNumero(), Post.class));
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
        assertFalse(procuraViolacao(random.buildValid(), NUMERO_ENDERECO_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio no complemento.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_complemento() {
        assertTrue(procuraQualquerViolacao(random.buildVazioComplemento(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no complemento.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_complemento() {
        assertTrue(procuraQualquerViolacao(random.buildMaiorTamanhoComplemento(), Post.class));
    }
    
    /**
     * Deve aceitar complemento valido.
     */
    @Test
    public void deve_aceitar_complemento_valido() {
        assertFalse(procuraViolacao(random.buildValid(), COMPLEMENTO_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar valor nulo no logradouro.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_logradouro() {
        assertTrue(procuraQualquerViolacao(random.buildNuloLogradouro(), Post.class));
    }
    
    /**
     * Nao deve aceitar logradouro invalido.
     */
    @Test
    public void nao_deve_aceitar_logradouro_invalido() {
        assertTrue(procuraQualquerViolacao(random.buildLogradouroInvalido(), Post.class));
    }
    
    /**
     * Deve aceitar logradouro nao nulo valido.
     */
    @Test
    public void deve_aceitar_logradouro_valido() {
        assertFalse(procuraViolacao(random.buildValid(), LOGRADOURO_INVALIDO, Post.class));
        assertFalse(procuraViolacao(random.buildValid(), NOME_LOGRADOURO_INVALIDO, Post.class));
        assertFalse(procuraViolacao(random.buildValid(), BAIRRO_INVALIDO, Post.class));
    }
    
    /**
     * Deve aceitar valor nulo no telefones fixo.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_telefonesFixo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloTelefonesFixo(), Post.class));
    }

    /**
     * Nao deve aceitar telefones fixo com elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildTelefonesFixoComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar telefones fixo com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_elemento_invalido() {
        assertTrue(procuraQualquerViolacao(random.buildTelefonesFixoComElementoInvalido(), Post.class));
    }
    
    /**
     * Deve aceitar telefones fixo nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_telefonesFixo_valido() {
        assertFalse(procuraViolacao(random.buildValid(), LISTA_TELEFONES_INVALIDA, Post.class));
        assertFalse(procuraViolacao(random.buildValid(), DDD_INVALIDO, Post.class));
        assertFalse(procuraViolacao(random.buildValid(), TELEFONE_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar valor nulo no tipo endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_tipoEndereco() {
        assertTrue(procuraQualquerViolacao(random.buildNuloTipoEndereco(), Post.class));
    }
    
    /**
     * Deve aceitar valor nao nulo no tipo endereco.
     */
    @Test
    public void deve_aceitar_valor_nao_nulo_no_tipoEndereco() {
        assertFalse(procuraViolacao(random.buildValid(), TIPO_ENDERECO_INVALIDO, Post.class));
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
        EqualsVerifier
        .forClass(Endereco.class)
        .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED)
        .withOnlyTheseFields("cep")
        .verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        ToStringVerifier
        .forClass(Endereco.class)
        .withPreset(APACHE_TO_STRING_BUILDER_JSON_STYLE)
        .verify();
    }
    
}
