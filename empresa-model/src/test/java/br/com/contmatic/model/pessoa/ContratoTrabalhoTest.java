package br.com.contmatic.model.pessoa;

import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.verificaEncapsulamentos;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CPF_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.DATA_INICIO_CONTRATO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.PESSOA_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_CONTRATO_INVALIDO;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import br.com.contmatic.model.random.pessoa.ContratoTrabalhoTestRandomBuilder;
import br.com.contmatic.validacoes.groups.Post;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class ContratoTrabalhoTest.
 */
public class ContratoTrabalhoTest {
    
	private static ContratoTrabalhoTestRandomBuilder random;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		random = ContratoTrabalhoTestRandomBuilder.getInstance();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		random.cleanBuilder();
	}

    /**
     * Nao deve aceitar pessoa nulo.
     */
    @Test
    public void nao_deve_aceitar_pessoa_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloPessoa(), Post.class));
    }
    
    /**
     * Nao deve aceitar pessoa invalido.
     */
    @Test
    public void nao_deve_aceitar_pessoa_invalido() {
    	assertTrue(procuraQualquerViolacao(random.buildInvalidaPessoa(), Post.class));
    }

    /**
     * Deve aceitar pessoa nao nulo valido.
     */
    @Test
    public void deve_aceitar_pessoa_nao_nulo_valido() {
    	assertFalse(procuraViolacao(random.buildValid(), PESSOA_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(random.buildValid(), CPF_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar tipo contrato trabalho nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoContratoTrabalho_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloTipoContratoTrabalho(), Post.class));
    }

    /**
     * Deve aceitar tipo contrato trabalho nao nulo.
     */
    @Test
    public void deve_aceitar_tipoContratoTrabalho_nao_nulo() {
    	assertFalse(procuraViolacao(random.buildValid(), TIPO_CONTRATO_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar data inicio contrato nulo.
     */
    @Test
    public void nao_deve_aceitar_dataInicioContrato_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloDataInicioContrato(), Post.class));
    }
    
    /**
     * Nao deve aceitar data inicio contrato futura.
     */
    @Test
    public void nao_deve_aceitar_dataInicioContrato_futura() {
    	assertTrue(procuraQualquerViolacao(random.buildDataFuturaDataInicioContrato(), Post.class));
    }

    /**
     * Deve aceitar data inicio contrato passada.
     */
    @Test
    public void deve_aceitar_dataInicioContrato_passada() {
    	assertFalse(procuraViolacao(random.buildValid(), DATA_INICIO_CONTRATO, Post.class));
    }
    
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(verificaEncapsulamentos(ContratoTrabalho.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier
        .forClass(ContratoTrabalho.class)
        .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED)
        .withOnlyTheseFields("pessoa")
        .verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        ToStringVerifier
        .forClass(ContratoTrabalho.class)
        .verify();
    } 
    
}
