package br.com.contmatic.model.contato;

import static br.com.contmatic.validacoes.utilidades.ConstantesString.EMAIL;

import static br.com.contmatic.validacoes.utilidades.MensagensErro.EMAIL_INVALIDO;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.contmatic.validacoes.groups.Post;
import br.com.contmatic.validacoes.groups.Put;

/**
 * The Class Email.
 */
public class Email {

    /** The endereco. */
    @NotNull(message = EMAIL_INVALIDO, groups = {Post.class})
    @Pattern(regexp = EMAIL, groups = {Post.class, Put.class}, message = EMAIL_INVALIDO)
    private String endereco;
    
    /**
     * Instantiates a new email.
     *
     * @param endereco the endereco
     */
    public Email(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Instantiates a new email.
     */
    public Email() {
    }
    
    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Sets the endereco.
     *
     * @param endereco the new endereco
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(endereco)
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
        if (!(objeto instanceof Email)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final Email outroEmail = (Email) objeto;
        return new EqualsBuilder()
                .append(endereco, outroEmail.endereco)
                .isEquals();
    }
    
    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("endereco", endereco)
                .toString();
    }
    
}
