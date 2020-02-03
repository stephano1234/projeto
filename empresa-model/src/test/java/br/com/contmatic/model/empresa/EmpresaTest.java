package br.com.contmatic.model.empresa;

import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraViolacao;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CELULAR_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CEP_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CNPJ_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CPF_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.DATA_ABERTURA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.EMAIL_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CELULARES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_RESPONSAVEIS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.PESSOA_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CONTRATOS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CONTAS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_EMAILS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_ENDERECOS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_TELEFONES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.RAZAO_SOCIAL_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NUMERO_CONTA_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TELEFONE_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_EMPRESA_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_PORTE_EMPRESA_INVALIDO;
import static com.jparams.verifier.tostring.preset.Presets.APACHE_TO_STRING_BUILDER_JSON_STYLE;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jparams.verifier.tostring.ToStringVerifier;

import br.com.contmatic.model.random.empresa.EmpresaTestRandomBuilder;
import br.com.contmatic.testes.utilidades.Verificadores;
import br.com.contmatic.validacoes.groups.Post;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class EmpresaTest.
 */
public class EmpresaTest {
    
	private static EmpresaTestRandomBuilder random;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		random = EmpresaTestRandomBuilder.getInstance();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		random.cleanBuilder();
	}

    /**
     * Nao deve aceitar valor nulo no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_cnpj() {
        assertTrue(procuraQualquerViolacao(random.buildNuloCnpj(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_cnpj() {
        assertTrue(procuraQualquerViolacao(random.buildMaiorTamanhoCnpj(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_menor_que_tamanho_no_cnpj() {
        assertTrue(procuraQualquerViolacao(random.buildMenorTamanhoCnpj(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_cnpj() {
        assertTrue(procuraQualquerViolacao(random.buildNaoApenasNumeralCnpj(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com apenas numeros repetidos no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_numeros_repetidos_no_cnpj() {
        assertTrue(procuraQualquerViolacao(random.buildNumerosRepetidosCnpj(), Post.class));
    }
    
    /**
     * Nao deve aceitar cnpj com primeiro digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_primeiro_digito_verificador_invalido() {
        assertTrue(procuraQualquerViolacao(random.buildPrimeiroDigitoVerificadorInvalidoCnpj(), Post.class));
    }    

    /**
     * Nao deve aceitar cnpj com segundo digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_segundo_digito_verificador_invalido() {
        assertTrue(procuraQualquerViolacao(random.buildSegundoDigitoVerificadorInvalidoCnpj(), Post.class));
    }    
    
    /**
     * Deve aceitar cnpj valido.
     */
    @Test
    public void deve_aceitar_cnpj_valido() {
        assertFalse(procuraViolacao(random.buildValid(), CNPJ_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar valor nulo no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(random.buildNuloRazaoSocial(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(random.buildVazioRazaoSocial(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(random.buildMaiorTamanhoRazaoSocial(), Post.class));
    }

    /**
     * Nao deve aceitar valor com apenas espaco no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(random.buildApenasEspacoRazaoSocial(), Post.class));
    }
    
    @Test
    public void nao_deve_aceitar_valor_com_espaco_no_inicio_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(random.buildInicioEspacoRazaoSocial(), Post.class));
    }
    
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_fim_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(random.buildFimEspacoRazaoSocial(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_nao_letra_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(random.buildNaoApenasLetraEspacoRazaoSocial(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com dois espacos juntos no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_com_dois_espacos_juntos_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(random.buildEspacoSeguidoDeEspacoRazaoSocial(), Post.class));
    }
    
    /**
     * Deve aceitar razaoSocial valido.
     */
    @Test
    public void deve_aceitar_razaoSocial_valido() {
        assertFalse(procuraViolacao(random.buildValid(), RAZAO_SOCIAL_INVALIDO, Post.class));
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
     * Nao deve aceitar responsaveis nulo.
     */
    @Test
    public void nao_deve_aceitar_responsaveis_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloResponsaveis(), Post.class));
    }

    @Test
    public void nao_deve_aceitar_responsaveis_vazio() {
    	assertTrue(procuraQualquerViolacao(random.buildVazioResponsaveis(), Post.class));
    }

    /**
     * Nao deve aceitar responsaveis com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_responsaveis_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildResponsaveisComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar responsaveis com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_responsaveis_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(random.buildResponsaveisComElementoInvalido(), Post.class));
    }

    /**
     * Deve aceitar responsaveis nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_responsaveis_valido() {
    	assertFalse(procuraViolacao(random.buildValid(), LISTA_RESPONSAVEIS_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(random.buildValid(), CPF_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar contratosTrabalho nulo.
     */
    @Test
    public void nao_deve_aceitar_contratosTrabalho_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloContratosTrabalho(), Post.class));
    }
        
    /**
     * Nao deve aceitar contratosTrabalho com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_contratosTrabalho_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildContratosTrabalhoComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar contratosTrabalho com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_contratosTrabalho_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(random.buildContratosTrabalhoComElementoInvalido(), Post.class));
    }

    /**
     * Deve aceitar contratosTrabalho nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_contratosTrabalho_valido() {
    	assertFalse(procuraViolacao(random.buildValid(), LISTA_CONTRATOS_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(random.buildValid(), PESSOA_INVALIDA, Post.class));
    }

    /**
     * Nao deve aceitar data nascimento nulo.
     */
    @Test
    public void nao_deve_aceitar_dataAbertura_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloDataAbertura(), Post.class));
    }
    
    /**
     * Nao deve aceitar data nascimento futura.
     */
    @Test
    public void nao_deve_aceitar_dataAbertura_futura() {
    	assertTrue(procuraQualquerViolacao(random.buildDataFuturaDataAbertura(), Post.class));
    }

    /**
     * Deve aceitar data nascimento passada.
     */
    @Test
    public void deve_aceitar_dataNascimento_valida() {
    	assertFalse(procuraViolacao(random.buildValid(), DATA_ABERTURA, Post.class));
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
     * Nao deve aceitar tipo grau instrucao nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoEmpresa_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloTipoEmpresa(), Post.class));
    }

    /**
     * Deve aceitar tipo grau instrucao nao nulo.
     */
    @Test
    public void deve_aceitar_tipoEmpresa_nao_nulo() {
    	assertFalse(procuraViolacao(random.buildValid(), TIPO_EMPRESA_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar tipo estado civil nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoPorteEmpresa_nulo() {
    	assertTrue(procuraQualquerViolacao(random.buildNuloTipoPorteEmpresa(), Post.class));
    }

    /**
     * Deve aceitar tipo estado civil nao nulo.
     */
    @Test
    public void deve_aceitar_tipoPorteEmpresa_nao_nulo() {
    	assertFalse(procuraViolacao(random.buildValid(), TIPO_PORTE_EMPRESA_INVALIDO, Post.class));
    }

    
    /**
     * Deve possuir getters e setters implmentados corretamente.
     */
    @Test
    public void deve_possuir_getters_e_setters_implmentados_corretamente() {
    	assertTrue(Verificadores.verificaEncapsulamentos(Empresa.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier
        .forClass(Empresa.class)
        .suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED)
        .withOnlyTheseFields("cnpj")
        .verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
    	ToStringVerifier
    	.forClass(Empresa.class)
    	.withPreset(APACHE_TO_STRING_BUILDER_JSON_STYLE)
    	.verify();
    }
    
}
