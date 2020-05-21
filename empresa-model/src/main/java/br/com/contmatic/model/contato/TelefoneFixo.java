package br.com.contmatic.model.contato;

import static br.com.contmatic.model.restricoes.RestricaoCampo.TELEFONE;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NUMERO_TELEFONE_INVALIDO;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;
import br.com.contmatic.validacoes.NotNull;

/**
 * The Class TelefoneFixo.
 */
public class TelefoneFixo {

    /** The numero. */
    @NotNull(message = NUMERO_TELEFONE_INVALIDO, groups = {Post.class, Put.class})
    @Pattern(regexp = TELEFONE, groups = {Post.class, Put.class}, message = NUMERO_TELEFONE_INVALIDO)
    private String numero;
    
    /**
     * Gets the numero.
     *
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the numero.
     *
     * @param numero the new numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(numero)
                .toHashCode();
    }
    
    /**
     * Equals.
     *
     * @param objeto the objeto
     * @return true, if successful
     */
    @Override
    public final boolean equals(Object objeto) {
        if (!(objeto instanceof TelefoneFixo)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final TelefoneFixo outroTelefoneFixo = (TelefoneFixo) objeto;
        return new EqualsBuilder()
                .append(numero, outroTelefoneFixo.numero)
                .isEquals();
    }
    
    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        		.append("numero", this.numero)
                .toString();
    }
    
}
