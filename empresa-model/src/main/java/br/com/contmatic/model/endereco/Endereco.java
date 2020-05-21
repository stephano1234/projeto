package br.com.contmatic.model.endereco;

import static br.com.contmatic.model.restricoes.RestricaoCampo.CEP;
import static br.com.contmatic.model.restricoes.RestricaoCampo.COMPLEMENTO;
import static br.com.contmatic.model.restricoes.RestricaoCampo.NUMERO_ENDERECO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.CEP_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.COMPLEMENTO_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.LOGRADOURO_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NUMERO_ENDERECO_INVALIDO;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;
import br.com.contmatic.validacoes.NotNull;

/**
 * The Class Endereco.
 */
public class Endereco {

    /** The cep. */
    @NotNull(message = CEP_INVALIDO, groups = {Post.class, Put.class})
    @Pattern(regexp = CEP, groups = {Post.class, Put.class}, message = CEP_INVALIDO)
    private String cep;
    
    /** The numero. */
    @Pattern(regexp = NUMERO_ENDERECO, groups = {Post.class, Put.class}, message = NUMERO_ENDERECO_INVALIDO)
    private String numero;
    
    /** The complemento. */
    @Pattern(regexp = COMPLEMENTO, groups = {Post.class, Put.class}, message = COMPLEMENTO_INVALIDO)
    private String complemento;
    
    /** The logradouro. */
    @NotNull(message = LOGRADOURO_INVALIDO, groups = {Post.class, Put.class})
    @Valid
    private Logradouro logradouro;
    
    /**
     * Gets the cep.
     *
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * Sets the cep.
     *
     * @param cep the new cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

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
     * Gets the complemento.
     *
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Sets the complemento.
     *
     * @param complemento the new complemento
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * Gets the logradouro.
     *
     * @return the logradouro
     */
    public Logradouro getLogradouro() {
        return logradouro;
    }

    /**
     * Sets the logradouro.
     *
     * @param logradouro the new logradouro
     */
    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(cep)
                .append(numero)
                .append(logradouro)
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
        if (!(objeto instanceof Endereco)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final Endereco outroEndereco = (Endereco) objeto;
        return new EqualsBuilder()
                .append(cep, outroEndereco.cep)
                .append(numero, outroEndereco.numero)
                .append(logradouro, outroEndereco.logradouro)
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
        		.append("cep", this.cep)
        		.append("numero", this.numero)
        		.append("complemento", this.complemento)
        		.append("logradouro", this.logradouro)
                .toString();
    }
    
}
