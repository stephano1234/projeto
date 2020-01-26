package br.com.contmatic.model.endereco;

import static br.com.contmatic.validacoes.utilidades.ConstantesString.NOME;
import static br.com.contmatic.validacoes.utilidades.ConstantesString.ESPACO;

import static br.com.contmatic.validacoes.utilidades.MensagensErro.NOME_BAIRRO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CIDADE_INVALIDA;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.contmatic.validacoes.TextDividedBy;
import br.com.contmatic.validacoes.groups.Post;
import br.com.contmatic.validacoes.groups.Put;

/**
 * The Class Bairro.
 */
public class Bairro {

    /** The nome. */
    @NotNull(message = NOME_BAIRRO_INVALIDO, groups = {Post.class})
    @TextDividedBy(separator = ESPACO, groups = {Post.class, Put.class}, message = NOME_BAIRRO_INVALIDO)
    @Pattern(regexp = NOME, groups = {Post.class, Put.class}, message = NOME_BAIRRO_INVALIDO)
    private String nome;
    
    /** The cidade. */
    @NotNull(message = CIDADE_INVALIDA, groups = {Post.class})
    @Valid
    private Cidade cidade;
    
    /**
     * Instantiates a new bairro.
     *
     * @param nome the nome
     * @param cidade the cidade
     */
    public Bairro(String nome, Cidade cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    /**
     * Instantiates a new bairro.
     */
    public Bairro() {
    }
    
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
     * Gets the cidade.
     *
     * @return the cidade
     */
    public Cidade getCidade() {
        return cidade;
    }

    /**
     * Sets the cidade.
     *
     * @param cidade the new cidade
     */
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
                .append(cidade)
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
        if (!(objeto instanceof Bairro)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final Bairro outroBairro = (Bairro) objeto;
        return new EqualsBuilder()
                .append(nome, outroBairro.nome)
                .append(cidade, outroBairro.cidade)
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
                .append("nome", nome)
                .append("cidade", cidade)
                .toString();
    }
    
}
