package br.com.contmatic.model.empresa;

import static br.com.contmatic.testes.utilidades.Verificadores.verificaToStringJSONSTYLE;
import static br.com.contmatic.testes.utilidades.Verificadores.procuraQualquerViolacao;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.pessoa.ContratoTrabalho;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.random.conta.ContaTestRandomBuilder;
import br.com.contmatic.model.random.contato.CelularTestRandomBuilder;
import br.com.contmatic.model.random.contato.EmailTestRandomBuilder;
import br.com.contmatic.model.random.contato.TelefoneTestFixoRandomBuilder;
import br.com.contmatic.model.random.empresa.EmpresaTestRandomBuilder;
import br.com.contmatic.model.random.endereco.EnderecoTestRandomBuilder;
import br.com.contmatic.model.random.pessoa.ContratoTrabalhoTestRandomBuilder;
import br.com.contmatic.model.random.pessoa.PessoaTestRandomBuilder;
import br.com.contmatic.testes.utilidades.Verificadores;
import br.com.contmatic.validacoes.groups.Post;
import br.com.contmatic.validacoes.groups.Put;
import br.com.contmatic.validacoes.utilidades.MensagensErro;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class EmpresaTest.
 */
public class EmpresaTest {
    
    /** The empresa. */
    private Empresa empresa;
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        empresa = new Empresa();
    }

    /**
     * Nao deve aceitar valor nulo no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_cnpj() {
        empresa.setCnpj(null);
        assertTrue(procuraQualquerViolacao(empresa, Put.class));
    }
    
//	public static boolean procuraAlgumErro(Object objetoTestado) {
//		factory = Validation.buildDefaultValidatorFactory();
//		validator = factory.getValidator();
//		Set<ConstraintViolation<Object>> violacoes = validator.validate(objetoTestado, Post.class);
//		return !violacoes.isEmpty();
//	}

	/** The validator. */
	private static Validator validator;

	/** The factory. */
	private static ValidatorFactory factory;

    /**
     * Nao deve aceitar valor maior que tamanho no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_cnpj() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildMaiorTamanhoCnpj()));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_menor_que_tamanho_no_cnpj() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildMenorTamanhoCnpj()));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_cnpj() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildNaoApenasNumeralCnpj()));
    }
    
    /**
     * Nao deve aceitar valor com apenas numeros repetidos no cnpj.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_numeros_repetidos_no_cnpj() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildNumerosRepetidosCnpj()));
    }
    
    /**
     * Nao deve aceitar cnpj com primeiro digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_primeiro_digito_verificador_invalido() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildPrimeiroDigitoVerificadorInvalidoCnpj()));
    }    

    /**
     * Nao deve aceitar cnpj com segundo digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cnpj_com_segundo_digito_verificador_invalido() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildSegundoDigitoVerificadorInvalidoCnpj()));
    }    

    /**
     * Deve aceitar cnpj valido.
     */
    @Test
    public void deve_aceitar_cnpj_valido() {
        assertFalse(Verificadores.verificaErro(empresa, MensagensErro.CNPJ_INVALIDO));
    }
    
    /**
     * Nao deve aceitar valor nulo no razao social.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_razaoSocial() {
        empresa.setRazaoSocial(null);
        assertTrue(procuraAlgumErro(empresa));
    }

    /**
     * Nao deve aceitar valor vazio no razao social.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_razaoSocial() {
    	empresa.setRazaoSocial("");
        assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no razao social.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_razaoSocial() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildMaiorTamanhoRazaoSocial()));
    }
    
    /**
     * Nao deve aceitar valor com apenas um caractere razao social.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_um_caractere_razaoSocial() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildApenasUmCaractereRazaoSocial()));
    }
    
    /**
     * Nao deve aceitar valor com primeiro caractere invalido no razao social.
     */
    @Test
    public void nao_deve_aceitar_valor_com_primeiro_caractere_invalido_no_razaoSocial() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildPrimeiroCaractereInvalido()));
    }

    /**
     * Nao deve aceitar valor com ultimo caractere invalido no razao social.
     */
    @Test
    public void nao_deve_aceitar_valor_com_ultimo_caractere_invalido_no_razaoSocial() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildUltimoCaractereInvalido()));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no razao social.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_razaoSocial() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildCaractereInvalidoRazaoSocial()));
    }
    
    /**
     * Nao deve aceitar valor com dois espacos juntos no razao social.
     */
    @Test
    public void nao_deve_aceitar_valor_com_dois_espacos_juntos_no_razaoSocial() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildEspacoDuploRazaoSocial()));
    }

    /**
     * Nao deve aceitar valor com dois pontos juntos no meio do razao social.
     */
    @Test
    public void nao_deve_aceitar_valor_com_dois_pontos_juntos_no_meio_do_razaoSocial() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildPontoDuploRazaoSocial()));
    }

    /**
     * Nao deve aceitar valor com dois pontos juntos no final do razao social.
     */
    @Test
    public void nao_deve_aceitar_valor_com_dois_pontos_juntos_no_final_do_razaoSocial() {
        assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildPontoDuploNoFinalRazaoSocial()));
    }
    
    /**
     * Deve aceitar razao social valido.
     */
    @Test
    public void deve_aceitar_razaoSocial_valido() {
        assertFalse(Verificadores.verificaErro(empresa, MensagensErro.RAZAO_SOCIAL_INVALIDO));
    }

    /**
     * Nao deve aceitar data abertura nulo.
     */
    @Test
    public void nao_deve_aceitar_dataAbertura_nulo() {
    	empresa.setDataAbertura(null);
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar data abertura futura.
     */
    @Test
    public void nao_deve_aceitar_dataAbertura_futura() {
    	assertTrue(procuraAlgumErro(EmpresaTestRandomBuilder.buildDataAberturaDataFutura()));
    }

    /**
     * Deve aceitar data abertura passada.
     */
    @Test
    public void deve_aceitar_dataAbertura_passada() {
    	assertFalse(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar responsaveis nulo.
     */
    @Test
    public void nao_deve_aceitar_responsaveis_nulo() {
    	empresa.setResponsaveis(null);
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar responsaveis vazio.
     */
    @Test
    public void nao_deve_aceitar_responsaveis_vazio() {
    	empresa.setResponsaveis(new HashSet<Pessoa>());
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar responsaveis com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_responsaveis_com_pelo_menos_um_elemento_nulo() {
    	empresa.getResponsaveis().add(null);
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar responsaveis com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_responsaveis_com_elemento_invalido() {
    	empresa.getResponsaveis().add(PessoaTestRandomBuilder.buildApenasEspacoNome());
    	assertTrue(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar responsaveis nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_responsaveis_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(empresa));
    }
    
    /**
     * Deve aceitar contratos trabalho nulo.
     */
    @Test
    public void deve_aceitar_contratosTrabalho_nulo() {
    	empresa.setContratosTrabalho(null);
    	assertFalse(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar contratos trabalho vazio.
     */
    @Test
    public void nao_deve_aceitar_contratosTrabalho_vazio() {
    	empresa.setContratosTrabalho(new HashSet<ContratoTrabalho>());
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar contratos trabalho com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_contratosTrabalho_com_pelo_menos_um_elemento_nulo() {
    	empresa.getContratosTrabalho().add(null);
    	assertTrue(procuraAlgumErro(empresa));
    }
       
    /**
     * Nao deve aceitar contratos trabalho com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_contratosTrabalho_com_elemento_invalido() {
    	empresa.getContratosTrabalho().add(ContratoTrabalhoTestRandomBuilder.buildDataFuturaDataInicioContrato());
    	assertTrue(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar contratos trabalho nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_contratosTrabalho_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar enderecos nulo.
     */
    @Test
    public void nao_deve_aceitar_enderecos_nulo() {
    	empresa.setEnderecos(null);
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar enderecos vazio.
     */
    @Test
    public void nao_deve_aceitar_enderecos_vazio() {
    	empresa.setEnderecos(new HashSet<Endereco>());
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar enderecos com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_enderecos_com_pelo_menos_um_elemento_nulo() {
    	empresa.getEnderecos().add(null);
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar enderecos com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_enderecos_com_elemento_invalido() {
    	empresa.getEnderecos().add(EnderecoTestRandomBuilder.buildApenasEspacoComplemento());
    	assertTrue(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar enderecos nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_enderecos_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar telefones fixo nulo.
     */
    @Test
    public void deve_aceitar_telefonesFixo_nulo() {
    	empresa.setTelefonesFixo(null);
    	assertFalse(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar telefones fixo vazio.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_vazio() {
    	empresa.setTelefonesFixo(new HashSet<TelefoneFixo>());
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar telefones fixo com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_pelo_menos_um_elemento_nulo() {
    	empresa.getTelefonesFixo().add(null);
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar telefones fixo com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_elemento_invalido() {
    	empresa.getTelefonesFixo().add(TelefoneTestFixoRandomBuilder.buildMaisQue2NumeraisDdd());
    	assertTrue(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar telefones fixo nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_telefonesFixo_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar emails nulo.
     */
    @Test
    public void deve_aceitar_emails_nulo() {
    	empresa.setEmails(null);
    	assertFalse(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar emails vazio.
     */
    @Test
    public void nao_deve_aceitar_emails_vazio() {
    	empresa.setEmails(new HashSet<Email>());
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar emails com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_emails_com_pelo_menos_um_elemento_nulo() {
    	empresa.getEmails().add(null);
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar emails com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_emails_com_elemento_invalido() {
    	empresa.getEmails().add(EmailTestRandomBuilder.buildArrobaPrecedidoPorCaractereInvalido());
    	assertTrue(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar emails nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_emails_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar celulares nulo.
     */
    @Test
    public void deve_aceitar_celulares_nulo() {
    	empresa.setCelulares(null);
    	assertFalse(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar celulares vazio.
     */
    @Test
    public void nao_deve_aceitar_celulares_vazio() {
    	empresa.setCelulares(new HashSet<Celular>());
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar celulares com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_celulares_com_pelo_menos_um_elemento_nulo() {
    	empresa.getCelulares().add(null);
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar celulares com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_celulares_com_elemento_invalido() {
    	empresa.getCelulares().add(CelularTestRandomBuilder.buildMaisQue2NumeraisDdd());
    	assertTrue(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar celulares nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_celulares_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(empresa));
    }
    
    /**
     * Deve aceitar contas nulo.
     */
    @Test
    public void deve_aceitar_contas_nulo() {
    	empresa.setContas(null);
    	assertFalse(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar contas vazio.
     */
    @Test
    public void nao_deve_aceitar_contas_vazio() {
    	empresa.setContas(new HashSet<Conta>());
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar contas com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_contas_com_pelo_menos_um_elemento_nulo() {
    	empresa.getContas().add(null);
    	assertTrue(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar contas com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_contas_com_elemento_invalido() {
    	empresa.getContas().add(ContaTestRandomBuilder.buildMaisQue12NumeraisNumero());
    	assertTrue(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar contas nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_contas_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(empresa));
    }
    
    /**
     * Nao deve aceitar tipo empresa nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoEmpresa_nulo() {
    	empresa.setTipoEmpresa(null);
    	assertTrue(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar tipo empresa nao nulo.
     */
    @Test
    public void deve_aceitar_tipoEmpresa_nao_nulo() {
    	empresa.setTipoEmpresa(TipoEmpresa.SOCIEDADE);
    	assertFalse(procuraAlgumErro(empresa));
    }

    /**
     * Nao deve aceitar tipo porte empresa nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoPorteEmpresa_nulo() {
    	empresa.setTipoPorteEmpresa(null);
    	assertTrue(procuraAlgumErro(empresa));
    }

    /**
     * Deve aceitar tipo porte empresa nao nulo.
     */
    @Test
    public void deve_aceitar_tipoPorteEmpresa_nao_nulo() {
    	empresa.setTipoPorteEmpresa(TipoPorteEmpresa.EPP);
    	assertFalse(procuraAlgumErro(empresa));
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
        EqualsVerifier.forClass(Empresa.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
    	assertTrue(verificaToStringJSONSTYLE(empresa));
    }
    
}
