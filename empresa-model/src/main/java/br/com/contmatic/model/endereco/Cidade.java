package br.com.contmatic.model.endereco;

import static br.com.contmatic.model.restricoes.RestricaoCampo.ESPACO;
import static br.com.contmatic.model.restricoes.RestricaoCampo.NOME;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NOME_CIDADE_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_UF_INVALIDO;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.validacoes.NaoNulo;
import br.com.contmatic.validacoes.TextDividedBy;

import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;

/**
 * The Class Cidade.
 */
public class Cidade {

    /** The nome. */
    @NaoNulo(message = NOME_CIDADE_INVALIDO, groups = {Post.class, Put.class})
    @TextDividedBy(separator = ESPACO, groups = {Post.class, Put.class}, message = NOME_CIDADE_INVALIDO)
    @Pattern(regexp = NOME, groups = {Post.class, Put.class}, message = NOME_CIDADE_INVALIDO)
    private String nome;
    
    /** The tipo uf. */
    @NaoNulo(message = TIPO_UF_INVALIDO, groups = {Post.class, Put.class})
    private TipoUf tipoUf;
    
    /**
     * Instantiates a new cidade.
     *
     * @param nome the nome
     * @param tipoUf the tipo uf
     */
    public Cidade(String nome, TipoUf tipoUf) {
        this.nome = nome;
        this.tipoUf = tipoUf;
    }

    /**
     * Instantiates a new cidade.
     */
    public Cidade() {
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
        return new StringBuilder()
        		.append("{")
                .append("nome:")
                .append(nome)
                .append(",")
                .append("tipoUf:")
                .append(tipoUf != null ? tipoUf.name() : null)
                .append("}")
                .toString();
    }
    
}
