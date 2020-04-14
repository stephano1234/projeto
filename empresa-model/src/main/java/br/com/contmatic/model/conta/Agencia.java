package br.com.contmatic.model.conta;

import static br.com.contmatic.model.restricoes.RestricaoCampo.CODIGO_BANCO;
import static br.com.contmatic.model.restricoes.RestricaoCampo.NUMERO_AGENCIA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.CODIGO_BANCO_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NUMERO_AGENCIA_INVALIDO;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.validacoes.NotNull;

import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;

/**
 * The Class Agencia.
 */
public class Agencia {
    
    /** The numero. */
    @NotNull(message = NUMERO_AGENCIA_INVALIDO, groups = {Post.class, Put.class})
    @Pattern(regexp = NUMERO_AGENCIA, groups = {Post.class, Put.class}, message = NUMERO_AGENCIA_INVALIDO)
    private String numero;
    
    /** The codigo banco. */
    @NotNull(message = CODIGO_BANCO_INVALIDO, groups = {Post.class, Put.class})
    @Pattern(regexp = CODIGO_BANCO, groups = {Post.class, Put.class}, message = CODIGO_BANCO_INVALIDO)
    private String codigoBanco;

    /**
     * Instantiates a new agencia.
     *
     * @param numero the numero
     * @param codigoBanco the codigo banco
     */
    public Agencia(String numero, String codigoBanco) {
        this.numero = numero;     
        this.codigoBanco = codigoBanco;
    }

    /**
     * Instantiates a new agencia.
     */
    public Agencia() {
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
     * Gets the codigo banco.
     *
     * @return the codigo banco
     */
    public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * Sets the codigo banco.
	 *
	 * @param codigoBanco the new codigo banco
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
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
                .append(codigoBanco)
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
        if (!(objeto instanceof Agencia)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final Agencia outroAgencia = (Agencia) objeto;
        return new EqualsBuilder()
                .append(numero, outroAgencia.numero)
                .append(codigoBanco, outroAgencia.codigoBanco)
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
                .append("codigoBanco:")
                .append(codigoBanco)
                .append("}")
                .toString();
    }
    
}
