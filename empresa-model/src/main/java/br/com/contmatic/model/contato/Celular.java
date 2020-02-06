package br.com.contmatic.model.contato;

import static br.com.contmatic.validacoes.utilidades.ConstantesString.CELULAR;
import static br.com.contmatic.validacoes.utilidades.ConstantesString.DDD;

import static br.com.contmatic.validacoes.utilidades.MensagensErro.CELULAR_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.DDD_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_CONTATO_INVALIDO;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.validacoes.NaoNulo;
import br.com.contmatic.validacoes.groups.Post;
import br.com.contmatic.validacoes.groups.Put;

/**
 * The Class Celular.
 */
public class Celular {

    /** The ddd. */
    @NaoNulo(message = DDD_INVALIDO, groups = {Post.class})   
    @Pattern(regexp = DDD, groups = {Post.class, Put.class}, message = DDD_INVALIDO)
    private String ddd;
    
    /** The numero. */
    @NaoNulo(message = CELULAR_INVALIDO, groups = {Post.class})
    @Pattern(regexp = CELULAR, groups = {Post.class, Put.class}, message = CELULAR_INVALIDO)
    private String numero;
    
    /** The tipo contato celular. */
    @NaoNulo(message = TIPO_CONTATO_INVALIDO, groups = {Post.class})
    private TipoContatoCelular tipoContatoCelular;
    
    /**
     * Instantiates a new celular.
     *
     * @param ddd the ddd
     * @param numero the numero
     * @param tipoContatoCelular the tipo contato celular
     */
    public Celular(String ddd, String numero, TipoContatoCelular tipoContatoCelular) {
        this.ddd = ddd;
        this.numero = numero;
        this.tipoContatoCelular = tipoContatoCelular;
    }

    /**
     * Instantiates a new celular.
     */
    public Celular() {
    }
    
    /**
     * Gets the ddd.
     *
     * @return the ddd
     */
    public String getDdd() {
        return ddd;
    }

    /**
     * Sets the ddd.
     *
     * @param ddd the new ddd
     */
    public void setDdd(String ddd) {
        this.ddd = ddd;
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
     * Gets the tipo contato celular.
     *
     * @return the tipo contato celular
     */
    public TipoContatoCelular getTipoContatoCelular() {
        return tipoContatoCelular;
    }

    /**
     * Sets the tipo contato celular.
     *
     * @param tipoContatoCelular the new tipo contato celular
     */
    public void setTipoContatoCelular(TipoContatoCelular tipoContatoCelular) {
        this.tipoContatoCelular = tipoContatoCelular;
    }
    
    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(ddd)
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
        if (!(objeto instanceof Celular)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final Celular outroCelular = (Celular) objeto;
        return new EqualsBuilder()
                .append(ddd, outroCelular.ddd)
                .append(numero, outroCelular.numero)
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
                .append("ddd:")
                .append(ddd)
                .append(",")
                .append("numero:")
                .append(numero)
                .append(",")
                .append("tipoContatoCelular:")
                .append(tipoContatoCelular != null ? tipoContatoCelular.name() : null)
                .append("}")
                .toString();
    }
    
}
