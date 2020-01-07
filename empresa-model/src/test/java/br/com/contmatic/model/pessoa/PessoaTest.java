package br.com.contmatic.model.pessoa;

import static br.com.contmatic.utilidades.Verificadores.procuraAlgumErro;
import static br.com.contmatic.utilidades.Verificadores.verificaToStringJSONSTYLE;
import static nl.jqno.equalsverifier.Warning.ALL_FIELDS_SHOULD_BE_USED;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.templates.conta.ContaRandomBuilder;
import br.com.contmatic.templates.contato.CelularRandomBuilder;
import br.com.contmatic.templates.contato.EmailRandomBuilder;
import br.com.contmatic.templates.contato.TelefoneFixoRandomBuilder;
import br.com.contmatic.templates.endereco.EnderecoRandomBuilder;
import br.com.contmatic.templates.pessoa.PessoaRandomBuilder;
import br.com.contmatic.utilidades.MensagensErro;
import br.com.contmatic.utilidades.Verificadores;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The Class PessoaTest.
 */
public class PessoaTest {
    
    /** The pessoa. */
    private Pessoa pessoa;
    
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        pessoa = PessoaRandomBuilder.buildValido();
    }

    /**
     * Nao deve aceitar valor nulo no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_cpf() {
        pessoa.setCpf(null);
        assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_cpf() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildMaiorTamanhoCpf()));
    }
    
    /**
     * Nao deve aceitar valor menor que tamanho no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_menor_que_tamanho_no_cpf() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildMenorTamanhoCpf()));
    }
    
    /**
     * Nao deve aceitar valor com um caractere invalido no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_cpf() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildNaoApenasNumeralCpf()));
    }
    
    /**
     * Nao deve aceitar valor com apenas numeros repetidos no cpf.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_numeros_repetidos_no_cpf() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildNumerosRepetidosCpf()));
    }
    
    /**
     * Nao deve aceitar cpf com primeiro digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cpf_com_primeiro_digito_verificador_invalido() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildPrimeiroDigitoVerificadorInvalidoCpf()));
    }    

    /**
     * Nao deve aceitar cpf com segundo digito verificador invalido.
     */
    @Test
    public void nao_deve_aceitar_cpf_com_segundo_digito_verificador_invalido() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildSegundoDigitoVerificadorInvalidoCpf()));
    }    
    
    /**
     * Deve aceitar cpf valido.
     */
    @Test
    public void deve_aceitar_cpf_valido() {
        assertFalse(Verificadores.verificaErro(pessoa, MensagensErro.CPF_INVALIDO));
    }
    
    /**
     * Nao deve aceitar valor nulo no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_nulo_no_nome() {
        pessoa.setNome(null);
        assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar valor vazio no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_vazio_no_nome() {
    	pessoa.setNome("");
        assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar valor maior que tamanho no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_maior_que_tamanho_no_nome() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildMaiorTamanhoNome()));
    }

    /**
     * Nao deve aceitar valor com apenas um caractere no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_um_caractere_no_nome() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildApenasUmCaractereNome()));
    }

    /**
     * Nao deve aceitar valor com apenas espaco no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_apenas_espaco_no_nome() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildApenasEspacoNome()));
    }
    
    /**
     * Nao deve aceitar valor com primeiro caractere invalido no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_primeiro_caractere_invalido_no_nome() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildPrimeiroCaractereMinusculoNome()));
    }

    /**
     * Nao deve aceitar valor com um caractere invalido no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_um_caractere_invalido_no_nome() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildNaoApenasLetraNome()));
    }
    
    /**
     * Nao deve aceitar valor com dois espacos juntos no nome.
     */
    @Test
    public void nao_deve_aceitar_valor_com_dois_espacos_juntos_no_nome() {
        assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildEspacoDuploNome()));
    }
    
    /**
     * Deve aceitar nome valido.
     */
    @Test
    public void deve_aceitar_nome_valido() {
        assertFalse(Verificadores.verificaErro(pessoa, MensagensErro.NOME_INVALIDO));
    }
        
    /**
     * Nao deve aceitar enderecos nulo.
     */
    @Test
    public void nao_deve_aceitar_enderecos_nulo() {
    	pessoa.setEnderecos(null);
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar enderecos vazio.
     */
    @Test
    public void nao_deve_aceitar_enderecos_vazio() {
    	pessoa.setEnderecos(new HashSet<Endereco>());
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar enderecos com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_enderecos_com_pelo_menos_um_elemento_nulo() {
    	pessoa.getEnderecos().add(null);
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar enderecos com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_enderecos_com_elemento_invalido() {
    	pessoa.getEnderecos().add(EnderecoRandomBuilder.buildNaoApenasNumeralCep());
    	assertTrue(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar enderecos nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_enderecos_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(pessoa));
    }

    /**
     * Nao deve aceitar data nascimento nulo.
     */
    @Test
    public void nao_deve_aceitar_dataNascimento_nulo() {
    	pessoa.setDataNascimento(null);
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar data nascimento futura.
     */
    @Test
    public void nao_deve_aceitar_dataNascimento_futura() {
    	assertTrue(procuraAlgumErro(PessoaRandomBuilder.buildDataFuturaDataNascimento()));
    }

    /**
     * Deve aceitar data nascimento passada.
     */
    @Test
    public void deve_aceitar_dataNascimento_passada() {
    	assertFalse(Verificadores.verificaErro(pessoa, MensagensErro.DATA_PASSADO));
    }

    /**
     * Deve aceitar celulares nulo.
     */
    @Test
    public void deve_aceitar_celulares_nulo() {
    	pessoa.setCelulares(null);
    	assertFalse(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar celulares vazio.
     */
    @Test
    public void nao_deve_aceitar_celulares_vazio() {
    	pessoa.setCelulares(new HashSet<Celular>());
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar celulares com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_celulares_com_pelo_menos_um_elemento_nulo() {
    	pessoa.getCelulares().add(null);
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar celulares com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_celulares_com_elemento_invalido() {
    	pessoa.getCelulares().add(CelularRandomBuilder.buildNaoApenasNumeralDdd());
    	assertTrue(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar celulares nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_celulares_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar telefones fixo nulo.
     */
    @Test
    public void deve_aceitar_telefonesFixo_nulo() {
    	pessoa.setTelefonesFixo(null);
    	assertFalse(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar telefones fixo vazio.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_vazio() {
    	pessoa.setTelefonesFixo(new HashSet<TelefoneFixo>());
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar telefones fixo com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_pelo_menos_um_elemento_nulo() {
    	pessoa.getTelefonesFixo().add(null);
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar telefones fixo com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_telefonesFixo_com_elemento_invalido() {
    	pessoa.getTelefonesFixo().add(TelefoneFixoRandomBuilder.buildNaoApenasNumeralDdd());
    	assertTrue(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar telefones fixo nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_telefonesFixo_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar emails nulo.
     */
    @Test
    public void deve_aceitar_emails_nulo() {
    	pessoa.setEmails(null);
    	assertFalse(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar emails vazio.
     */
    @Test
    public void nao_deve_aceitar_emails_vazio() {
    	pessoa.setEmails(new HashSet<Email>());
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar emails com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_emails_com_pelo_menos_um_elemento_nulo() {
    	pessoa.getEmails().add(null);
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar emails com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_emails_com_elemento_invalido() {
    	pessoa.getEmails().add(EmailRandomBuilder.buildMaisDeUmArroba());
    	assertTrue(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar emails nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_emails_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(pessoa));
    }

    /**
     * Nao deve aceitar tipo grau instrucao nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoGrauInstrucao_nulo() {
    	pessoa.setTipoGrauInstrucao(null);
    	assertTrue(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar tipo grau instrucao nao nulo.
     */
    @Test
    public void deve_aceitar_tipoGrauInstrucao_nao_nulo() {
    	pessoa.setTipoGrauInstrucao(TipoGrauInstrucao.ANALFABETO);
    	assertFalse(procuraAlgumErro(pessoa));
    }

    /**
     * Nao deve aceitar tipo estado civil nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoEstadoCivil_nulo() {
    	pessoa.setTipoEstadoCivil(null);
    	assertTrue(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar tipo estado civil nao nulo.
     */
    @Test
    public void deve_aceitar_tipoEstadoCivil_nao_nulo() {
    	pessoa.setTipoEstadoCivil(TipoEstadoCivil.CASADO);
    	assertFalse(procuraAlgumErro(pessoa));
    }

    /**
     * Nao deve aceitar tipo sexo nulo.
     */
    @Test
    public void nao_deve_aceitar_tipoSexo_nulo() {
    	pessoa.setTipoSexo(null);
    	assertTrue(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar tipo sexo nao nulo.
     */
    @Test
    public void deve_aceitar_tipoSexo_nao_nulo() {
    	pessoa.setTipoSexo(TipoSexo.FEMININO);
    	assertFalse(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar contas nulo.
     */
    @Test
    public void deve_aceitar_contas_nulo() {
    	pessoa.setContas(null);
    	assertFalse(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar contas vazio.
     */
    @Test
    public void nao_deve_aceitar_contas_vazio() {
    	pessoa.setContas(new HashSet<Conta>());
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar contas com pelo menos um elemento nulo.
     */
    @Test
    public void nao_deve_aceitar_contas_com_pelo_menos_um_elemento_nulo() {
    	pessoa.getContas().add(null);
    	assertTrue(procuraAlgumErro(pessoa));
    }
    
    /**
     * Nao deve aceitar contas com elemento invalido.
     */
    @Test
    public void nao_deve_aceitar_contas_com_elemento_invalido() {
    	pessoa.getContas().add(ContaRandomBuilder.buildNaoApenasNumeralNumero());
    	assertTrue(procuraAlgumErro(pessoa));
    }

    /**
     * Deve aceitar contas nao vazio sem elemento nulo apenas elemento valido.
     */
    @Test
    public void deve_aceitar_contas_nao_vazio_sem_elemento_nulo_apenas_elemento_valido() {
    	assertFalse(procuraAlgumErro(pessoa));
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
        EqualsVerifier.forClass(Pessoa.class).suppress(NONFINAL_FIELDS, ALL_FIELDS_SHOULD_BE_USED).verify();
    }
    
    /**
     * Metodo to string deve gerar representacao do objeto em json com todos os atributos da classe.
     */
    @Test
    public void metodo_toString_deve_gerar_representacao_do_objeto_em_json_com_todos_os_atributos_da_classe() {
        assertTrue(verificaToStringJSONSTYLE(pessoa));
    }
    
}
