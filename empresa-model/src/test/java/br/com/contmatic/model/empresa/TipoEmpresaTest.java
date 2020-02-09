package br.com.contmatic.model.empresa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * The Class TipoEmpresaTest.
 */
public class TipoEmpresaTest {

    /**
     * Verifica valor do atributo descricao da constante INDIVIDUAL.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_INDIVIDUAL() {
        assertThat(TipoEmpresa.INDIVIDUAL.getDescricao(), is(equalTo("Empresário Individual")));
    }

    /**
     * Verifica valor do atributo descricao da constante MEI.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_MEI() {
        assertThat(TipoEmpresa.MEI.getDescricao(), is(equalTo("Microempreendedor Individual")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante EIRELI.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_EIRELI() {
        assertThat(TipoEmpresa.EIRELI.getDescricao(), is(equalTo("Empresa Individual de Responsabilidade Limitada")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante SOCIEDADE.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_SOCIEDADE() {
        assertThat(TipoEmpresa.SOCIEDADE.getDescricao(), is(equalTo("Sociedade Emppresária")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante SOCIEDAD E SIMPLES.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_SOCIEDADE_SIMPLES() {
        assertThat(TipoEmpresa.SOCIEDADE_SIMPLES.getDescricao(), is(equalTo("Sociedade Simples")));
    }
    
}
