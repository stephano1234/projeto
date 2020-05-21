package br.com.contmatic.model.contato;

import static br.com.contmatic.model.random.contato.EmailTestRandomBuilder.cleanBuilder;
import static br.com.contmatic.model.random.contato.EmailTestRandomBuilder.getInstance;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
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
 * The Class EmailTest.
 */
public class EmailTest {
    
	@AfterClass
	public static void tearDownAfterClass() {
		cleanBuilder();
	}

    /**
     * Nao deve aceitar valor nulo no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNuloEndereco(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildVazioEndereco(), Post.class));
    }
        
    /**
     * Nao deve aceitar valor sem arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_sem_arroba_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildSemArroba(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com arroba precedido por caractere invalido no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_arroba_precedido_por_caractere_invalido_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildArrobaPrecedidoPorCaractereInvalido(), Post.class));
    }

    /**
     * Nao deve aceitar valor com mais de um arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_mais_de_um_arroba_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaisDeUmArroba(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor sem ponto no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_sem_ponto_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildSemPonto(), Post.class));
    }
        
    /**
     * Nao deve aceitar valor vazio antes do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_antes_do_arroba_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildVazioAntesArroba(), Post.class));
    }

    /**
     * Nao deve aceitar valor maior que tamanho antes do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_antes_do_arroba_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaiorTamanhoAntesArroba(), Post.class));
    }

    /**
     * Nao deve aceitar primeiro valor invalido antes do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_primeiro_valor_invalido_antes_do_arroba_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildPrimeiroCaractereInvalidoAntesArroba(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido antes do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_antes_do_arroba_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildCaractereInvalidoAntesArroba(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio depois do arroba antes do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_depois_do_arroba_antes_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildVazioDepoisArrobaAtePontoObrigatorio(), Post.class));
    }

    /**
     * Nao deve aceitar valor maior que tamanho depois do arroba antes do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_depois_do_arroba_antes_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaiorTamanhoDepoisArrobaAtePontoObrigatorio(), Post.class));
    }

    /**
     * Nao deve aceitar primeiro valor invalido depois do arroba antes do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_primeiro_valor_invalido_depois_do_arroba_antes_do_ponto_obrigatorio_no_endereco() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildPrimeiroCaractereInvalidoDepoisArrobaAtePontoObrigatorio(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido depois do arroba antes do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_depois_do_arroba_antes_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildCaractereInvalidoDepoisArrobaAtePontoObrigatorio(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor sem ponto obrigatorio depois do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_sem_ponto_obrigatorio_depois_do_arroba_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildSemPontoObrigatorioDepoisArroba(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com ponto precedido por caractere invalido depois do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_ponto_precedido_por_caractere_invalido_depois_do_arroba_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildPontoObrigatorioPrecedidoPorCaractereInvalido(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho depois do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_menor_que_tamanho_depois_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMenorTamanhoDepoisPontoObrigatorio(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho depois do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_depois_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaiorTamanhoDepoisPontoObrigatorio(), Post.class));
    }
        
    /**
     * Nao deve aceitar valor com um caractere invalido depois ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_depois_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraQualquerViolacao(getInstance().buildCaractereInvalidoDepoisPontoObrigatorio(), Post.class));
    }
    
    /**
     * Deve aceitar valor valido no endereco.
     */
    @Test
    public void deve_aceitar_valor_valido_no_endereco() {
        assertFalse(procuraQualquerViolacao(getInstance().buildValid(), Post.class));
    }

    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier.forClass(Email.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).withOnlyTheseFields("endereco").verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
    	ToStringVerifier.forClass(Email.class).withPreset(APACHE_TO_STRING_BUILDER_JSON_STYLE).verify();
    }
    
}
