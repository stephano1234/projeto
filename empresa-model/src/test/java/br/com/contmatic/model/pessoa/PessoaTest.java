package br.com.contmatic.model.pessoa;

import static br.com.contmatic.model.random.pessoa.PessoaTestRandomBuilder.cleanBuilder;
import static br.com.contmatic.model.random.pessoa.PessoaTestRandomBuilder.getInstance;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.CPF_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.DATA_NASCIMENTO_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NOME_RESPONSAVEL_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_ESTADO_CIVIL_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_GRAU_INSTRUCAO_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_SEXO_INVALIDO;
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
 * The Class PessoaTest.
 */
public class PessoaTest {
    
	@AfterClass
	public static void tearDownAfterClass() {
		cleanBuilder();
	}

    /**
     * Nao deve aceitar valor nulo no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_cpf() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNuloCpf(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_cpf() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaiorTamanhoCpf(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_menor_que_tamanho_no_cpf() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMenorTamanhoCpf(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_cpf() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNaoApenasNumeralCpf(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com apenas numeros repetidos no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_numeros_repetidos_no_cpf() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNumerosRepetidosCpf(), Post.class));
    }
    
    /**
     * Nao deve aceitar cpf com primeiro digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cpf_com_primeiro_digito_verificador_invalido() {
        assertTrue(procuraQualquerViolacao(getInstance().buildPrimeiroDigitoVerificadorInvalidoCpf(), Post.class));
    }    

    /**
     * Nao deve aceitar cpf com segundo digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cpf_com_segundo_digito_verificador_invalido() {
        assertTrue(procuraQualquerViolacao(getInstance().buildSegundoDigitoVerificadorInvalidoCpf(), Post.class));
    }    
    
    /**
     * Deve aceitar cpf valido.
     */
    @Test
    public void deve_aceitar_cpf_valido() {
        assertFalse(procuraViolacao(getInstance().buildValid(), CPF_INVALIDO, Post.class));
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
        assertFalse(procuraViolacao(getInstance().buildValid(), NOME_RESPONSAVEL_INVALIDO, Post.class));
    }
        
    /**
     * Nao deve aceitar data nascimento nulo.
     */
    @Test
    public void nao_deve_aceitar_dataNascimento_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloDataNascimento(), Post.class));
    }
    
    /**
     * Nao deve aceitar data nascimento futura.
     */
    @Test
    public void nao_deve_aceitar_dataNascimento_futura() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildDataFuturaDataNascimento(), Post.class));
    }

    /**
     * Deve aceitar data nascimento passada.
     */
    @Test
    public void deve_aceitar_dataNascimento_valida() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), DATA_NASCIMENTO_INVALIDA, Post.class));
    }

    /**
     * Nao deve aceitar tipo grau instrucao nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoGrauInstrucao_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloTipoGrauInstrucao(), Post.class));
    }

    /**
     * Deve aceitar tipo grau instrucao nao nulo.
     */
    @Test
    public void deve_aceitar_tipoGrauInstrucao_nao_nulo() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), TIPO_GRAU_INSTRUCAO_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar tipo estado civil nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoEstadoCivil_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloTipoEstadoCivil(), Post.class));
    }

    /**
     * Deve aceitar tipo estado civil nao nulo.
     */
    @Test
    public void deve_aceitar_tipoEstadoCivil_nao_nulo() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), TIPO_ESTADO_CIVIL_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar tipo sexo nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoSexo_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloTipoSexo(), Post.class));
    }

    /**
     * Deve aceitar tipo sexo nao nulo.
     */
    @Test
    public void deve_aceitar_tipoSexo_nao_nulo() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), TIPO_SEXO_INVALIDO, Post.class));
    }

    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier.forClass(Pessoa.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).withOnlyTheseFields("cpf").verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        ToStringVerifier.forClass(Pessoa.class).withPreset(APACHE_TO_STRING_BUILDER_JSON_STYLE).verify();
    }
    
}
