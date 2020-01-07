package br.com.contmatic.model.empresa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * The Class TipoPorteEmpresaTest.
 */
public class TipoPorteEmpresaTest {

    /**
     * Verifica valor do atributo descricao da constante ME.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_ME() {
        assertThat(TipoPorteEmpresa.ME.getDescricao(), is(equalTo("Microempresa")));
    }

    /**
     * Verifica valor do atributo descricao da constante EPP.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_EPP() {
        assertThat(TipoPorteEmpresa.EPP.getDescricao(), is(equalTo("Empresa de pequeno porte")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante MEDIO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_MEDIO() {
        assertThat(TipoPorteEmpresa.MEDIO.getDescricao(), is(equalTo("Empresa de médio porte")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante GRANDE.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_GRANDE() {
        assertThat(TipoPorteEmpresa.GRANDE.getDescricao(), is(equalTo("Empresa de grande porte")));
    }
    
}
