package br.com.contmatic.model.conta;

import static br.com.contmatic.validacoes.utilidades.ConstantesString.NUMERO_CONTA;

import static br.com.contmatic.validacoes.utilidades.MensagensErro.NUMERO_CONTA_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.AGENCIA_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_CONTA_INVALIDO;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.validacoes.NaoNulo;
import br.com.contmatic.validacoes.groups.Post;
import br.com.contmatic.validacoes.groups.Put;

/**
 * The Class Conta.
 */
public class Conta {

    /** The numero. */
    @NaoNulo(message = NUMERO_CONTA_INVALIDO, groups = {Post.class})
    @Pattern(regexp = NUMERO_CONTA, groups = {Post.class, Put.class}, message = NUMERO_CONTA_INVALIDO)
    private String numero;
    
    /** The agencia. */
    @NaoNulo(message = AGENCIA_INVALIDA, groups = {Post.class})
    @Valid
    private Agencia agencia;
    
    /** The tipo conta. */
    @NaoNulo(message = TIPO_CONTA_INVALIDO, groups = {Post.class})
    private TipoConta tipoConta;

    /**
     * Instantiates a new conta.
     *
     * @param numero the numero
     * @param agencia the agencia
     * @param tipoConta the tipo conta
     */
    public Conta(String numero, Agencia agencia, TipoConta tipoConta) {
        this.numero = numero;
        this.agencia = agencia;
        this.tipoConta = tipoConta;
    }

    /**
     * Instantiates a new conta.
     */
    public Conta() {
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
     * Gets the agencia.
     *
     * @return the agencia
     */
    public Agencia getAgencia() {
        return agencia;
    }

    /**
     * Sets the agencia.
     *
     * @param agencia the new agencia
     */
    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    /**
     * Gets the tipo conta.
     *
     * @return the tipo conta
     */
    public TipoConta getTipoConta() {
        return tipoConta;
    }

    /**
     * Sets the tipo conta.
     *
     * @param tipoConta the new tipo conta
     */
    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }
    
    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(numero)
                .append(agencia)
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
        if (!(objeto instanceof Conta)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final Conta outroConta = (Conta) objeto;
        return new EqualsBuilder()
                .append(numero, outroConta.numero)
                .append(agencia, outroConta.agencia)
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
                .append("numero:")
                .append(numero)
                .append(",")
                .append("agencia:")
                .append(agencia)
                .append(",")
                .append("tipoConta:")
                .append(tipoConta != null ? tipoConta.name() : null)
                .append("}")
                .toString();
    }
    
}
