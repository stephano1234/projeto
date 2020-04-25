package br.com.contmatic.model.contato;

import static br.com.contmatic.model.restricoes.RestricaoCampo.EMAIL;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.ENDERECO_EMAIL_INVALIDO;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.validacoes.NotNull;

import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;

/**
 * The Class Email.
 */
public class Email {

    /** The endereco. */
    @NotNull(message = ENDERECO_EMAIL_INVALIDO, groups = {Post.class, Put.class})
    @Pattern(regexp = EMAIL, groups = {Post.class, Put.class}, message = ENDERECO_EMAIL_INVALIDO)
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
        return new StringBuilder()
        		.append("{")
                .append("endereco:")
                .append(endereco)
                .append("}")
                .toString();
    }
    
}
