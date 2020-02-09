package br.com.contmatic.model.conta;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * The Class TipoContaTest.
 */
public class TipoContaTest {

    /**
     * Verifica valor do atributo descricao da constante CONT A CORRENTE.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_CONTA_CORRENTE() {
        assertThat(TipoConta.CONTA_CORRENTE.getDescricao(), is(equalTo("Conta corrente")));
    }

    /**
     * Verifica valor do atributo descricao da constante CONT A POUPANCA.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_CONTA_POUPANCA() {
        assertThat(TipoConta.CONTA_POUPANCA.getDescricao(), is(equalTo("Conta poupança")));
    }
        
}
