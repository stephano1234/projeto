package br.com.contmatic.model.contato;

import static br.com.contmatic.validacoes.utilidades.ConstantesString.DDD;
import static br.com.contmatic.validacoes.utilidades.ConstantesString.TELEFONE;

import static br.com.contmatic.validacoes.utilidades.MensagensErro.DDD_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TELEFONE_INVALIDO;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.contmatic.validacoes.groups.Post;
import br.com.contmatic.validacoes.groups.Put;

/**
 * The Class TelefoneFixo.
 */
public class TelefoneFixo {

    /** The ddd. */
    @NotNull(message = DDD_INVALIDO, groups = {Post.class})   
    @Pattern(regexp = DDD, groups = {Post.class, Put.class}, message = DDD_INVALIDO)
    private String ddd;
    
    /** The numero. */
    @NotNull(message = TELEFONE_INVALIDO, groups = {Post.class})
    @Pattern(regexp = TELEFONE, groups = {Post.class, Put.class}, message = TELEFONE_INVALIDO)
    private String numero;
    
    /**
     * Instantiates a new telefone fixo.
     *
     * @param ddd the ddd
     * @param numero the numero
     */
    public TelefoneFixo(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    /**
     * Instantiates a new telefone fixo.
     */
    public TelefoneFixo() {
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
        if (!(objeto instanceof TelefoneFixo)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final TelefoneFixo outroTelefoneFixo = (TelefoneFixo) objeto;
        return new EqualsBuilder()
                .append(ddd, outroTelefoneFixo.ddd)
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
        return new ToStringBuilder(this, JSON_STYLE)
                .append("ddd", ddd)
                .append("numero", numero)
                .toString();
    }
    
}
