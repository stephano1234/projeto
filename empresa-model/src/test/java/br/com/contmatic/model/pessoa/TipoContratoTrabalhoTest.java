package br.com.contmatic.model.pessoa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * The Class TipoContratoTrabalhoTest.
 */
public class TipoContratoTrabalhoTest {

    /**
     * Verifica valor do atributo descricao da constante TEMP O DETERMINADO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_TEMPO_DETERMINADO() {
        assertThat(TipoContratoTrabalho.TEMPO_DETERMINADO.getDescricao(), is(equalTo("Contrato de trabalho por tempo determinado")));
    }

    /**
     * Verifica valor do atributo descricao da constante TEMP O INDETERMINADO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_TEMPO_INDETERMINADO() {
        assertThat(TipoContratoTrabalho.TEMPO_INDETERMINADO.getDescricao(), is(equalTo("Contrato de trabalho por tempo indeterminado")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante TEMPORAREO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_TEMPORAREO() {
        assertThat(TipoContratoTrabalho.TEMPORAREO.getDescricao(), is(equalTo("Contrato de trabalho temporáreo")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante EVENTUAL.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_EVENTUAL() {
        assertThat(TipoContratoTrabalho.EVENTUAL.getDescricao(), is(equalTo("Contrato de trabalho eventual")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante AUTONOMO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_AUTONOMO() {
        assertThat(TipoContratoTrabalho.AUTONOMO.getDescricao(), is(equalTo("Contrato de trabalho autônomo")));
    }
    
    /**
     * Verifica valor do atributo descricao da constante ESTAGIO.
     */
    @Test
    public void verifica_valor_do_atributo_descricao_da_constante_ESTAGIO() {
        assertThat(TipoContratoTrabalho.ESTAGIO.getDescricao(), is(equalTo("Contrato de estágio")));
    }
    
}
