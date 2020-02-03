package br.com.contmatic.model.pessoa;

import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraViolacao;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CELULAR_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CEP_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CPF_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.DATA_NASCIMENTO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.EMAIL_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CELULARES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CONTAS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_EMAILS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_ENDERECOS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_TELEFONES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NOME_PESSOA_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NUMERO_CONTA_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TELEFONE_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_ESTADO_CIVIL_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_GRAU_INSTRUCAO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_SEXO_INVALIDO;
import static com.jparams.verifier.tostring.preset.Presets.APACHE_TO_STRING_BUILDER_JSON_STYLE;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import br.com.contmatic.model.random.pessoa.PessoaTestRandomBuilder;
import br.com.contmatic.testes.utilidades.Verificadores;
import br.com.contmatic.validacoes.groups.Post;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class PessoaTest.
 */
public class PessoaTest {
    
	private static PessoaTestRandomBuilder random;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		random = PessoaTestRandomBuilder.getInstance();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		random.cleanBuilder();
	}

    /**
     * Nao deve aceitar valor nulo no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_cpf() {
        assertTrue(procuraQualquerViolacao(random.buildNuloCpf(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_cpf() {
        assertTrue(procuraQualquerViolacao(random.buildMaiorTamanhoCpf(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_menor_que_tamanho_no_cpf() {
        assertTrue(procuraQualquerViolacao(random.buildMenorTamanhoCpf(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_cpf() {
        assertTrue(procuraQualquerViolacao(random.buildNaoApenasNumeralCpf(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com apenas numeros repetidos no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_numeros_repetidos_no_cpf() {
        assertTrue(procuraQualquerViolacao(random.buildNumerosRepetidosCpf(), Post.class));
    }
    
    /**
     * Nao deve aceitar cpf com primeiro digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cpf_com_primeiro_digito_verificador_invalido() {
        assertTrue(procuraQualquerViolacao(random.buildPrimeiroDigitoVerificadorInvalidoCpf(), Post.class));
    }    

    /**
     * Nao deve aceitar cpf com segundo digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cpf_com_segundo_digito_verificador_invalido() {
        assertTrue(procuraQualquerViolacao(random.buildSegundoDigitoVerificadorInvalidoCpf(), Post.class));
    }    
    
    /**
     * Deve aceitar cpf valido.
     */
    @Test
    public void deve_aceitar_cpf_valido() {
        assertFalse(procuraViolacao(random.buildValid(), CPF_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar valor nulo no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_nome() {
        assertTrue(procuraQualquerViolacao(random.buildNuloNome(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_nome() {
        assertTrue(procuraQualquerViolacao(random.buildVazioNome(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_nome() {
        assertTrue(procuraQualquerViolacao(random.buildMaiorTamanhoNome(), Post.class));
    }

    /**
     * Nao deve aceitar valor com apenas espaco no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_nome() {
        assertTrue(procuraQualquerViolacao(random.buildApenasEspacoNome(), Post.class));
    }
    
    @Test
    public void nao_deve_aceitar_valor_com_espaco_no_inicio_no_nome() {
        assertTrue(procuraQualquerViolacao(random.buildInicioEspacoNome(), Post.class));
    }
    
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_fim_no_nome() {
        assertTrue(procuraQualquerViolacao(random.buildFimEspacoNome(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_nao_letra_no_nome() {
        assertTrue(procuraQualquerViolacao(random.buildNaoApenasLetraEspacoNome(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com dois espacos juntos no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_dois_espacos_juntos_no_nome() {
        assertTrue(procuraQualquerViolacao(random.buildEspacoSeguidoDeEspacoNome(), Post.class));
    }
    
    /**
     * Deve aceitar nome valido.
     */
    @Test
    public void deve_aceitar_nome_valido() {
        assertFalse(procuraViolacao(random.buildValid(), NOME_PESSOA_INVALIDO, Post.class));
    }
        
    /**
     * Nao deve aceitar enderecos nulo.
     */
    @Test
    public void nao_deve_aceitar_enderecos_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloEnderecos(), Post.class));
    }

    @Test
    public void nao_deve_aceitar_enderecos_vazio() {
    	assertTrue(procuraQualquerViolacao(random.buildVazioEnderecos(), Post.class));
    }

    /**
     * Nao deve aceitar enderecos com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_enderecos_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildEnderecosComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar enderecos com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_enderecos_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(random.buildEnderecosComElementoInvalido(), Post.class));
    }

    /**
     * Deve aceitar enderecos nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_enderecos_valido() {
    	assertFalse(procuraViolacao(random.buildValid(), LISTA_ENDERECOS_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(random.buildValid(), CEP_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar data nascimento nulo.
     */
    @Test
    public void nao_deve_aceitar_dataNascimento_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloDataNascimento(), Post.class));
    }
    
    /**
     * Nao deve aceitar data nascimento futura.
     */
    @Test
    public void nao_deve_aceitar_dataNascimento_futura() {
    	assertTrue(procuraQualquerViolacao(random.buildDataFuturaDataNascimento(), Post.class));
    }

    /**
     * Deve aceitar data nascimento passada.
     */
    @Test
    public void deve_aceitar_dataNascimento_valida() {
    	assertFalse(procuraViolacao(random.buildValid(), DATA_NASCIMENTO, Post.class));
    }

    /**
     * Deve aceitar celulares nulo.
     */
    @Test
    public void nao_deve_aceitar_celulares_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloCelulares(), Post.class));
    }
    
    /**
     * Nao deve aceitar celulares com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_celulares_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildCelularesComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar celulares com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_celulares_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(random.buildCelularesComElementoInvalido(), Post.class));
    }

    /**
     * Deve aceitar celulares nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_celulares_valido() {
    	assertFalse(procuraViolacao(random.buildValid(), LISTA_CELULARES_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(random.buildValid(), CELULAR_INVALIDO, Post.class));
    }

    /**
     * Deve aceitar telefones fixo nulo.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloTelefonesFixo(), Post.class));
    }
    
    /**
     * Nao deve aceitar telefones fixo com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_pelo_menos_um_elemento_nulo() {
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
    	assertFalse(procuraViolacao(random.buildValid(), TELEFONE_INVALIDO, Post.class));
    }

    /**
     * Deve aceitar emails nulo.
     */
    @Test
    public void nao_deve_aceitar_emails_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloEmails(), Post.class));
    }
    
    /**
     * Nao deve aceitar emails com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_emails_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildEmailsComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar emails com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_emails_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(random.buildEmailsComElementoInvalido(), Post.class));
    }

    /**
     * Deve aceitar emails nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_emails_valido() {
    	assertFalse(procuraViolacao(random.buildValid(), LISTA_EMAILS_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(random.buildValid(), EMAIL_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar tipo grau instrucao nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoGrauInstrucao_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloTipoGrauInstrucao(), Post.class));
    }

    /**
     * Deve aceitar tipo grau instrucao nao nulo.
     */
    @Test
    public void deve_aceitar_tipoGrauInstrucao_nao_nulo() {
    	assertFalse(procuraViolacao(random.buildValid(), TIPO_GRAU_INSTRUCAO_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar tipo estado civil nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoEstadoCivil_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloTipoEstadoCivil(), Post.class));
    }

    /**
     * Deve aceitar tipo estado civil nao nulo.
     */
    @Test
    public void deve_aceitar_tipoEstadoCivil_nao_nulo() {
    	assertFalse(procuraViolacao(random.buildValid(), TIPO_ESTADO_CIVIL_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar tipo sexo nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoSexo_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloTipoSexo(), Post.class));
    }

    /**
     * Deve aceitar tipo sexo nao nulo.
     */
    @Test
    public void deve_aceitar_tipoSexo_nao_nulo() {
    	assertFalse(procuraViolacao(random.buildValid(), TIPO_SEXO_INVALIDO, Post.class));
    }

    /**
     * Deve aceitar contas nulo.
     */
    @Test
    public void nao_deve_aceitar_contas_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloContas(), Post.class));
    }
    
    /**
     * Nao deve aceitar contas com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_contas_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildContasComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar contas com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_contas_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(random.buildContasComElementoInvalido(), Post.class));
    }

    /**
     * Deve aceitar contas nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_contas_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraViolacao(random.buildValid(), LISTA_CONTAS_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(random.buildValid(), NUMERO_CONTA_INVALIDO, Post.class));
    }
    
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(Verificadores.verificaEncapsulamentos(Pessoa.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier
        .forClass(Pessoa.class)
        .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED)
        .withOnlyTheseFields("cpf")
        .verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        ToStringVerifier
        .forClass(Pessoa.class)
        .withPreset(APACHE_TO_STRING_BUILDER_JSON_STYLE)
        .verify();
    }
    
}
