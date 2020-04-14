package br.com.contmatic.model.endereco;

import static br.com.contmatic.validacoes.utilidades.MensagensErro.NOME_BAIRRO_INVALIDO;
import static br.com.contmatic.model.restricoes.RestricaoCampo.ESPACO;
import static br.com.contmatic.model.restricoes.RestricaoCampo.NOME;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CIDADE_INVALIDA;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.validacoes.NaoNulo;
import br.com.contmatic.validacoes.TextDividedBy;

import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;

/**
 * The Class Bairro.
 */
public class Bairro {

    /** The nome. */
    @NaoNulo(message = NOME_BAIRRO_INVALIDO, groups = {Post.class, Put.class})
    @TextDividedBy(separator = ESPACO, groups = {Post.class, Put.class}, message = NOME_BAIRRO_INVALIDO)
    @Pattern(regexp = NOME, groups = {Post.class, Put.class}, message = NOME_BAIRRO_INVALIDO)
    private String nome;
    
    /** The cidade. */
    @NaoNulo(message = CIDADE_INVALIDA, groups = {Post.class, Put.class})
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
        return new StringBuilder()
        		.append("{")
                .append("nome:")
                .append(nome)
                .append(",")
                .append("cidade:")
                .append(cidade)
                .append("}")
                .toString();
    }
    
}
