package br.com.contmatic.model.pessoa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * The Class TipoEstadoCivilTest.
 */
public class TipoEstadoCivilTest {

    /**
     * Verifica valor do atributo descricao da constante SOLTEIRO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_SOLTEIRO() {
        assertThat(TipoEstadoCivil.SOLTEIRO.getDescricao(), is(equalTo("Solteiro(a)")));
    }

    /**
     * Verifica valor do atributo descricao da constante CASADO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_CASADO() {
        assertThat(TipoEstadoCivil.CASADO.getDescricao(), is(equalTo("Casado(a)")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante DIVORCIADO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_DIVORCIADO() {
        assertThat(TipoEstadoCivil.DIVORCIADO.getDescricao(), is(equalTo("Divorciado(a)")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante VIUVO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_VIUVO() {
        assertThat(TipoEstadoCivil.VIUVO.getDescricao(), is(equalTo("Viuvo(a)")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante OUTROS.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_OUTROS() {
        assertThat(TipoEstadoCivil.OUTROS.getDescricao(), is(equalTo("Outros")));
    }
    
}
