package br.com.contmatic.model.endereco;

import static br.com.contmatic.model.restricoes.RestricaoCampo.ESPACO;
import static br.com.contmatic.model.restricoes.RestricaoCampo.NOME;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NOME_CIDADE_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_UF_INVALIDO;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.validacoes.NotNull;
import br.com.contmatic.validacoes.TextDividedBy;

import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;

/**
 * The Class Cidade.
 */
public class Cidade {

    /** The nome. */
    @NotNull(message = NOME_CIDADE_INVALIDO, groups = {Post.class, Put.class})
    @TextDividedBy(separator = ESPACO, groups = {Post.class, Put.class}, message = NOME_CIDADE_INVALIDO)
    @Pattern(regexp = NOME, groups = {Post.class, Put.class}, message = NOME_CIDADE_INVALIDO)
    private String nome;
    
    /** The tipo uf. */
    @NotNull(message = TIPO_UF_INVALIDO, groups = {Post.class, Put.class})
    private TipoUf tipoUf;
    
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
     * Gets the tipo uf.
     *
     * @return the tipo uf
     */
    public TipoUf getTipoUf() {
        return tipoUf;
    }

    /**
     * Sets the tipo uf.
     *
     * @param tipoUf the new tipo uf
     */
    public void setTipoUf(TipoUf tipoUf) {
        this.tipoUf = tipoUf;
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
                .append(tipoUf)
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
        if (!(objeto instanceof Cidade)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final Cidade outroCidade = (Cidade) objeto;
        return new EqualsBuilder()
                .append(nome, outroCidade.nome)
                .append(tipoUf, outroCidade.tipoUf)
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
        		.append("tipoUf", this.tipoUf)
                .toString();
    }
    
}
