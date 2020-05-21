package br.com.contmatic.model.empresa;

import static br.com.contmatic.model.random.empresa.EmpresaTestRandomBuilder.cleanBuilder;
import static br.com.contmatic.model.random.empresa.EmpresaTestRandomBuilder.getInstance;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.CEP_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.CNPJ_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.CPF_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.DATA_ABERTURA_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.ENDERECO_EMAIL_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.LISTA_CELULARES_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.LISTA_CONTAS_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.LISTA_EMAILS_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.LISTA_ENDERECOS_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.LISTA_RESPONSAVEIS_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.LISTA_TELEFONES_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NUMERO_CELULAR_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NUMERO_CONTA_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NUMERO_TELEFONE_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.RAZAO_SOCIAL_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_EMPRESA_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_PORTE_EMPRESA_INVALIDO;
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
 * The Class EmpresaTest.
 */
public class EmpresaTest {
    	
	@AfterClass
	public static void tearDownAfterClass() {
		cleanBuilder();
	}

    /**
     * Nao deve aceitar valor nulo no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_cnpj() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNuloCnpj(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_cnpj() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaiorTamanhoCnpj(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_menor_que_tamanho_no_cnpj() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMenorTamanhoCnpj(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_cnpj() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNaoApenasNumeralCnpj(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com apenas numeros repetidos no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_numeros_repetidos_no_cnpj() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNumerosRepetidosCnpj(), Post.class));
    }
    
    /**
     * Nao deve aceitar cnpj com primeiro digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_primeiro_digito_verificador_invalido() {
        assertTrue(procuraQualquerViolacao(getInstance().buildPrimeiroDigitoVerificadorInvalidoCnpj(), Post.class));
    }    

    /**
     * Nao deve aceitar cnpj com segundo digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_segundo_digito_verificador_invalido() {
        assertTrue(procuraQualquerViolacao(getInstance().buildSegundoDigitoVerificadorInvalidoCnpj(), Post.class));
    }    
    
    /**
     * Deve aceitar cnpj valido.
     */
    @Test
    public void deve_aceitar_cnpj_valido() {
        assertFalse(procuraViolacao(getInstance().buildValid(), CNPJ_INVALIDO, Post.class));
    }
    
    /**
     * Nao deve aceitar valor nulo no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNuloRazaoSocial(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor vazio no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(getInstance().buildVazioRazaoSocial(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(getInstance().buildMaiorTamanhoRazaoSocial(), Post.class));
    }

    /**
     * Nao deve aceitar valor com apenas espaco no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(getInstance().buildApenasEspacoRazaoSocial(), Post.class));
    }
    
    @Test
    public void nao_deve_aceitar_valor_com_espaco_no_inicio_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(getInstance().buildInicioEspacoRazaoSocial(), Post.class));
    }
    
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_fim_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(getInstance().buildFimEspacoRazaoSocial(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_nao_letra_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(getInstance().buildNaoApenasLetraEspacoRazaoSocial(), Post.class));
    }
    
    /**
     * Nao deve aceitar valor com dois espacos juntos no razaoSocial.
     */
    @Test
    public void nao_deve_aceitar_valor_com_dois_espacos_juntos_no_razaoSocial() {
        assertTrue(procuraQualquerViolacao(getInstance().buildEspacoSeguidoDeEspacoRazaoSocial(), Post.class));
    }
    
    /**
     * Deve aceitar razaoSocial valido.
     */
    @Test
    public void deve_aceitar_razaoSocial_valido() {
        assertFalse(procuraViolacao(getInstance().buildValid(), RAZAO_SOCIAL_INVALIDO, Post.class));
    }
        
    /**
     * Nao deve aceitar enderecos nulo.
     */
    @Test
    public void nao_deve_aceitar_enderecos_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloEnderecos(), Post.class));
    }
        
    @Test
    public void nao_deve_aceitar_enderecos_vazio() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildVazioEnderecos(), Post.class));
    }

    /**
     * Nao deve aceitar enderecos com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_enderecos_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildEnderecosComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar enderecos com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_enderecos_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildEnderecosComElementoInvalido(), Post.class));
    	assertTrue(procuraQualquerViolacao(getInstance().buildEnderecosComLogradouroInvalido(), Post.class));
    	assertTrue(procuraQualquerViolacao(getInstance().buildEnderecosComBairroInvalido(), Post.class));
    	assertTrue(procuraQualquerViolacao(getInstance().buildEnderecosComCidadeInvalido(), Post.class));
    }

    /**
     * Deve aceitar enderecos nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_enderecos_valido() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), LISTA_ENDERECOS_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(getInstance().buildValid(), CEP_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar responsaveis nulo.
     */
    @Test
    public void nao_deve_aceitar_responsaveis_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloResponsaveis(), Post.class));
    }

    @Test
    public void nao_deve_aceitar_responsaveis_vazio() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildVazioResponsaveis(), Post.class));
    }

    /**
     * Nao deve aceitar responsaveis com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_responsaveis_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildResponsaveisComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar responsaveis com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_responsaveis_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildResponsaveisComElementoInvalido(), Post.class));
    }

    /**
     * Deve aceitar responsaveis nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_responsaveis_valido() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), LISTA_RESPONSAVEIS_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(getInstance().buildValid(), CPF_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar data nascimento nulo.
     */
    @Test
    public void nao_deve_aceitar_dataAbertura_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloDataAbertura(), Post.class));
    }
    
    /**
     * Nao deve aceitar data nascimento futura.
     */
    @Test
    public void nao_deve_aceitar_dataAbertura_futura() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildDataFuturaDataAbertura(), Post.class));
    }

    /**
     * Deve aceitar data nascimento passada.
     */
    @Test
    public void deve_aceitar_dataNascimento_valida() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), DATA_ABERTURA_INVALIDA, Post.class));
    }

    /**
     * Deve aceitar celulares nulo.
     */
    @Test
    public void nao_deve_aceitar_celulares_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloCelulares(), Post.class));
    }
    
    /**
     * Nao deve aceitar celulares com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_celulares_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildCelularesComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar celulares com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_celulares_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildCelularesComElementoInvalido(), Post.class));
    }

    /**
     * Deve aceitar celulares nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_celulares_valido() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), LISTA_CELULARES_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(getInstance().buildValid(), NUMERO_CELULAR_INVALIDO, Post.class));
    }

    /**
     * Deve aceitar telefones fixo nulo.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloTelefonesFixo(), Post.class));
    }
    
    /**
     * Nao deve aceitar telefones fixo com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildTelefonesFixoComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar telefones fixo com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildTelefonesFixoComElementoInvalido(), Post.class));
    }

    /**
     * Deve aceitar telefones fixo nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_telefonesFixo_valido() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), LISTA_TELEFONES_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(getInstance().buildValid(), NUMERO_TELEFONE_INVALIDO, Post.class));
    }

    /**
     * Deve aceitar emails nulo.
     */
    @Test
    public void nao_deve_aceitar_emails_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloEmails(), Post.class));
    }
    
    /**
     * Nao deve aceitar emails com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_emails_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildEmailsComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar emails com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_emails_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildEmailsComElementoInvalido(), Post.class));
    }

    /**
     * Deve aceitar emails nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_emails_valido() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), LISTA_EMAILS_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(getInstance().buildValid(), ENDERECO_EMAIL_INVALIDO, Post.class));
    }

    /**
     * Deve aceitar contas nulo.
     */
    @Test
    public void nao_deve_aceitar_contas_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloContas(), Post.class));
    }
    
    /**
     * Nao deve aceitar contas com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_contas_com_pelo_menos_um_elemento_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildContasComElementoNulo(), Post.class));
    }
    
    /**
     * Nao deve aceitar contas com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_contas_com_elemento_invalido() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildContasComElementoInvalido(), Post.class));
    	assertTrue(procuraQualquerViolacao(getInstance().buildContasComAgenciaInvalido(), Post.class));
    }

    /**
     * Deve aceitar contas nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_contas_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), LISTA_CONTAS_INVALIDA, Post.class));
    	assertFalse(procuraViolacao(getInstance().buildValid(), NUMERO_CONTA_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar tipo grau instrucao nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoEmpresa_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloTipoEmpresa(), Post.class));
    }

    /**
     * Deve aceitar tipo grau instrucao nao nulo.
     */
    @Test
    public void deve_aceitar_tipoEmpresa_nao_nulo() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), TIPO_EMPRESA_INVALIDO, Post.class));
    }

    /**
     * Nao deve aceitar tipo estado civil nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoPorteEmpresa_nulo() {
    	assertTrue(procuraQualquerViolacao(getInstance().buildNuloTipoPorteEmpresa(), Post.class));
    }

    /**
     * Deve aceitar tipo estado civil nao nulo.
     */
    @Test
    public void deve_aceitar_tipoPorteEmpresa_nao_nulo() {
    	assertFalse(procuraViolacao(getInstance().buildValid(), TIPO_PORTE_EMPRESA_INVALIDO, Post.class));
    }
    
    /**
     * Verifica consistencia da implementacao do metodo equals de acordo com a regra estabelecida de comparacao.
     */
    @Test
    public void verifica_consistencia_da_implementacao_do_metodo_equals_de_acordo_com_a_regra_estabelecida_de_comparacao() {
        EqualsVerifier.forClass(Empresa.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).withOnlyTheseFields("cnpj").verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_com_todos_os_atributos_preenchidos() {
    	ToStringVerifier.forClass(Empresa.class).withPreset(APACHE_TO_STRING_BUILDER_JSON_STYLE).verify();
    }    
    
}
