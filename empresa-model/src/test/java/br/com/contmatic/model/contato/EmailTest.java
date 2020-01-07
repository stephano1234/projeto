package br.com.contmatic.model.contato;

import static br.com.contmatic.utilidades.Verificadores.procuraAlgumErro;
import static br.com.contmatic.utilidades.Verificadores.verificaEncapsulamentos;
import static br.com.contmatic.utilidades.Verificadores.verificaToStringJSONSTYLE;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.templates.contato.EmailRandomBuilder;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class EmailTest.
 */
public class EmailTest {
    
    /** The email. */
    private Email email;
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        email = EmailRandomBuilder.buildValido();
    }

    /**
     * Nao deve aceitar valor nulo no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_endereco() {
        email.setEndereco(null);
        assertTrue(procuraAlgumErro(email));
    }
    
    /**
     * Nao deve aceitar valor vazio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_endereco() {
        email.setEndereco("");
        assertTrue(procuraAlgumErro(email));
    }
        
    /**
     * Nao deve aceitar valor sem arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_sem_arroba_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildSemArroba()));
    }
    
    /**
     * Nao deve aceitar valor com arroba precedido por caractere invalido no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_arroba_precedido_por_caractere_invalido_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildArrobaPrecedidoPorCaractereInvalido()));
    }

    /**
     * Nao deve aceitar valor com mais de um arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_mais_de_um_arroba_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildMaisDeUmArroba()));
    }
    
    /**
     * Nao deve aceitar valor sem ponto no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_sem_ponto_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildSemPonto()));
    }
    
    /**
     * Deve aceitar valor valido no endereco.
     */
    @Test
    public void deve_aceitar_valor_valido_no_endereco() {
        assertFalse(procuraAlgumErro(EmailRandomBuilder.buildValido()));
    }
    
    /**
     * Nao deve aceitar valor vazio antes do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_antes_do_arroba_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildVazioAntesArroba()));
    }

    /**
     * Nao deve aceitar valor maior que tamanho antes do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_antes_do_arroba_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildMaiorTamanhoAntesArroba()));
    }

    /**
     * Nao deve aceitar primeiro valor invalido antes do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_primeiro_valor_invalido_antes_do_arroba_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildPrimeiroCaractereInvalidoAntesArroba()));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido antes do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_antes_do_arroba_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildCaractereInvalidoAntesArroba()));
    }
    
    /**
     * Nao deve aceitar valor vazio depois do arroba antes do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_depois_do_arroba_antes_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildVazioDepoisArrobaAtePontoObrigatorio()));
    }

    /**
     * Nao deve aceitar valor maior que tamanho depois do arroba antes do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_depois_do_arroba_antes_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildMaiorTamanhoDepoisArrobaAtePontoObrigatorio()));
    }

    /**
     * Nao deve aceitar primeiro valor invalido depois do arroba antes do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_primeiro_valor_invalido_depois_do_arroba_antes_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildPrimeiroCaractereInvalidoDepoisArrobaAtePontoObrigatorio()));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido depois do arroba antes do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_depois_do_arroba_antes_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildCaractereInvalidoDepoisArrobaAtePontoObrigatorio()));
    }
    
    /**
     * Nao deve aceitar valor sem ponto obrigatorio depois do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_sem_ponto_obrigatorio_depois_do_arroba_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildSemPontoObrigatorioDepoisArroba()));
    }
    
    /**
     * Nao deve aceitar valor com ponto precedido por caractere invalido depois do arroba no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_ponto_precedido_por_caractere_invalido_depois_do_arroba_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildPontoObrigatorioPrecedidoPorCaractereInvalido()));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho depois do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_menor_que_tamanho_depois_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildMenorTamanhoDepoisPontoObrigatorio()));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho depois do ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_depois_do_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildMaiorTamanhoDepoisPontoObrigatorio()));
    }
        
    /**
     * Nao deve aceitar valor com um caractere invalido depois ponto obrigatorio no endereco.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_depois_ponto_obrigatorio_no_endereco() {
        assertTrue(procuraAlgumErro(EmailRandomBuilder.buildCaractereInvalidoDepoisPontoObrigatorio()));
    }
        
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(verificaEncapsulamentos(Email.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier.forClass(Email.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(email));
    }
    
}
