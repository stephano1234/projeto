package br.com.contmatic.model.pessoa;

import static br.com.contmatic.utilidades.Verificadores.procuraAlgumErro;
import static br.com.contmatic.utilidades.Verificadores.verificaToStringJSONSTYLE;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.templates.pessoa.ContratoTrabalhoRandomBuilder;
import br.com.contmatic.templates.pessoa.PessoaRandomBuilder;
import br.com.contmatic.utilidades.Verificadores;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class ContratoTrabalhoTest.
 */
public class ContratoTrabalhoTest {
    
    /** The contrato trabalho. */
    private ContratoTrabalho contratoTrabalho;
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        contratoTrabalho = ContratoTrabalhoRandomBuilder.buildValido();
    }

    /**
     * Nao deve aceitar pessoa nulo.
     */
    @Test
    public void nao_deve_aceitar_pessoa_nulo() {
    	contratoTrabalho.setPessoa(null);
    	assertTrue(procuraAlgumErro(contratoTrabalho));
    }
    
    /**
     * Nao deve aceitar pessoa invalido.
     */
    @Test
    public void nao_deve_aceitar_pessoa_invalido() {
    	contratoTrabalho.setPessoa(PessoaRandomBuilder.buildApenasEspacoNome());
    	assertTrue(procuraAlgumErro(contratoTrabalho));
    }

    /**
     * Deve aceitar pessoa nao nulo valido.
     */
    @Test
    public void deve_aceitar_pessoa_nao_nulo_valido() {
    	assertFalse(procuraAlgumErro(contratoTrabalho));
    }
    
    /**
     * Nao deve aceitar tipo contrato trabalho nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoContratoTrabalho_nulo() {
    	contratoTrabalho.setTipoContratoTrabalho(null);
    	assertTrue(procuraAlgumErro(contratoTrabalho));
    }

    /**
     * Deve aceitar tipo contrato trabalho nao nulo.
     */
    @Test
    public void deve_aceitar_tipoContratoTrabalho_nao_nulo() {
    	contratoTrabalho.setTipoContratoTrabalho(TipoContratoTrabalho.AUTONOMO);
    	assertFalse(procuraAlgumErro(contratoTrabalho));
    }
    
    /**
     * Nao deve aceitar data inicio contrato nulo.
     */
    @Test
    public void nao_deve_aceitar_dataInicioContrato_nulo() {
    	contratoTrabalho.setDataInicioContrato(null);
    	assertTrue(procuraAlgumErro(contratoTrabalho));
    }
    
    /**
     * Nao deve aceitar data inicio contrato futura.
     */
    @Test
    public void nao_deve_aceitar_dataInicioContrato_futura() {
    	assertTrue(procuraAlgumErro(ContratoTrabalhoRandomBuilder.buildDataFuturaDataInicioContrato()));
    }

    /**
     * Deve aceitar data inicio contrato passada.
     */
    @Test
    public void deve_aceitar_dataInicioContrato_passada() {
    	assertFalse(procuraAlgumErro(contratoTrabalho));
    }
    
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(Verificadores.verificaEncapsulamentos(ContratoTrabalho.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier.forClass(ContratoTrabalho.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(contratoTrabalho));
    }    
}
