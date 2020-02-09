package br.com.contmatic.model.pessoa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * The Class TipoSexoTest.
 */
public class TipoSexoTest {

    /**
     * Verifica valor do atributo descricao da constante FEMININO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_FEMININO() {
        assertThat(TipoSexo.FEMININO.getDescricao(), is(equalTo("Feminino")));
    }

    /**
     * Verifica valor do atributo descricao da constante MASCULINO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_MASCULINO() {
        assertThat(TipoSexo.MASCULINO.getDescricao(), is(equalTo("Masculino")));
    }
    
}
