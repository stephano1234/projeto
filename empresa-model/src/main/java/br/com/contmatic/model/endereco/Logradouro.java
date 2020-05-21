package br.com.contmatic.model.endereco;

import static br.com.contmatic.model.restricoes.RestricaoCampo.ESPACO;
import static br.com.contmatic.model.restricoes.RestricaoCampo.NOME;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.BAIRRO_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NOME_LOGRADOURO_INVALIDO;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;
import br.com.contmatic.validacoes.NotNull;
import br.com.contmatic.validacoes.TextDividedBy;

/**
 * The Class Logradouro.
 */
public class Logradouro {

    /** The nome. */
    @NotNull(message = NOME_LOGRADOURO_INVALIDO, groups = {Post.class, Put.class})
    @TextDividedBy(separator = ESPACO, groups = {Post.class, Put.class}, message = NOME_LOGRADOURO_INVALIDO)
    @Pattern(regexp = NOME, groups = {Post.class, Put.class}, message = NOME_LOGRADOURO_INVALIDO)
    private String nome;
    
    /** The bairro. */
    @NotNull(message = BAIRRO_INVALIDO, groups = {Post.class, Put.class})
    @Valid
    private Bairro bairro;
    
    /**
     * Gets the nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param nome the new nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the bairro.
     *
     * @return the bairro
     */
    public Bairro getBairro() {
        return bairro;
    }

    /**
     * Sets the bairro.
     *
     * @param bairro the new bairro
     */
    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    
    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(nome)
                .append(bairro)
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
        if (!(objeto instanceof Logradouro)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final Logradouro outroLogradouro = (Logradouro) objeto;
        return new EqualsBuilder()
                .append(nome, outroLogradouro.nome)
                .append(bairro, outroLogradouro.bairro)
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
        		.append("nome", this.nome)
        		.append("bairro", this.bairro)
                .toString();
    }
    
}
