package br.com.contmatic.model.endereco;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.contmatic.model.endereco.TipoEndereco;

/**
 * The Class TipoEnderecoTest.
 */
public class TipoEnderecoTest {

    /**
     * Verifica valor do atributo descricao da constante RESIDENCIAL.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_RESIDENCIAL() {
        assertThat(TipoEndereco.RESIDENCIAL.getDescricao(), is(equalTo("Endereço residencial")));
    }

    /**
     * Verifica valor do atributo descricao da constante COMERCIAL.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_COMERCIAL() {
        assertThat(TipoEndereco.COMERCIAL.getDescricao(), is(equalTo("Endereço comercial")));
    }
    
}
