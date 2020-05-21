package br.com.contmatic.model.endereco;

import static br.com.contmatic.model.random.endereco.BairroTestRandomBuilder.cleanBuilder;
import static br.com.contmatic.model.random.endereco.BairroTestRandomBuilder.getInstance;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.CIDADE_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NOME_BAIRRO_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NOME_CIDADE_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_UF_INVALIDO;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraViolacao;
import static com.jparams.verifier.tostring.preset.Presets.APACHE_TO_STRING_BUILDER_JSON_STYLE;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import br.com.contmatic.model.restricoes.grupos.Post;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class BairroTest.
 */
public class BairroTest {
    
	@AfterClass
	public static void tearDownAfterClass() {
		cleanBuilder();
	}

    /**
     * Nao deve aceitar valor nulo no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_nome() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNuloNome(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_nome() {
        assertTrue(procuraQualquerViolacao(getInstance().buildVazioNome(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_nome() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaiorTamanhoNome(), Post.class));
    }

    /**
     * Nao deve aceitar valor com apenas espaco no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_nome() {
        assertTrue(procuraQualquerViolacao(getInstance().buildApenasEspacoNome(), Post.class));
    }
    
    @Test
    public void nao_deve_aceitar_valor_com_espaco_no_inicio_no_nome() {
        assertTrue(procuraQualquerViolacao(getInstance().buildInicioEspacoNome(), Post.class));
    }
    
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_fim_no_nome() {
        assertTrue(procuraQualquerViolacao(getInstance().buildFimEspacoNome(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_nao_letra_no_nome() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNaoApenasLetraEspacoNome(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com dois espacos juntos no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_dois_espacos_juntos_no_nome() {
        assertTrue(procuraQualquerViolacao(getInstance().buildEspacoSeguidoDeEspacoNome(), Post.class));
    }
    
    /**
     * Deve aceitar nome valido.
     */
    @Test
    public void deve_aceitar_nome_valido() {
        assertFalse(procuraViolacao(getInstance().buildValid(), NOME_BAIRRO_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar valor nulo no tipo uf.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_na_cidade() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNuloCidade(), Post.class));
    }
    
    @Test
    public void nao_deve_aceitar_valor_invalido_na_cidade() {
        assertTrue(procuraQualquerViolacao(getInstance().buildCidadeInvalido(), Post.class));
    }
    
    /**
     * Deve aceitar valor nao nulo no tipo uf.
     */
    @Test
    public void deve_aceitar_cidade_valida() {
        assertFalse(procuraViolacao(getInstance().buildValid(), CIDADE_INVALIDA, Post.class));
        assertFalse(procuraViolacao(getInstance().buildValid(), NOME_CIDADE_INVALIDO, Post.class));
        assertFalse(procuraViolacao(getInstance().buildValid(), TIPO_UF_INVALIDO, Post.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier.forClass(Bairro.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).withOnlyTheseFields("nome", "cidade").verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
    	ToStringVerifier.forClass(Bairro.class).withPreset(APACHE_TO_STRING_BUILDER_JSON_STYLE).verify();
    }
    
}
