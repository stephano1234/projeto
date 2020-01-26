package br.com.contmatic.model.endereco;

import static br.com.contmatic.validacoes.utilidades.ConstantesString.ESPACO;
import static br.com.contmatic.validacoes.utilidades.ConstantesNumericas.TAMANHO_REGULAR;
import static br.com.contmatic.validacoes.utilidades.ConstantesString.CEP;
import static br.com.contmatic.validacoes.utilidades.ConstantesString.NUMERO_ENDERECO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CEP_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NUMERO_ENDERECO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.COMPLEMENTO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_TELEFONES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LOGRADOURO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_ENDERECO_INVALIDO;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.validacoes.NaoNuloCollection;
import br.com.contmatic.validacoes.TextDividedBy;
import br.com.contmatic.validacoes.groups.Post;
import br.com.contmatic.validacoes.groups.Put;

/**
 * The Class Endereco.
 */
public class Endereco {

    /** The cep. */
    @NotNull(message = CEP_INVALIDO, groups = {Post.class})
    @Pattern(regexp = CEP, groups = {Post.class, Put.class}, message = CEP_INVALIDO)
    private String cep;
    
    /** The numero. */
    @Pattern(regexp = NUMERO_ENDERECO, groups = {Post.class, Put.class}, message = NUMERO_ENDERECO_INVALIDO)
    private String numero;
    
    /** The complemento. */
    @Size(min = 1, max = TAMANHO_REGULAR, groups = {Post.class, Put.class}, message = COMPLEMENTO_INVALIDO)
    @TextDividedBy(separator = ESPACO, groups = {Post.class, Put.class}, message = COMPLEMENTO_INVALIDO)
    private String complemento;
    
    /** The logradouro. */
    @NotNull(message = LOGRADOURO_INVALIDO, groups = {Post.class})
    @Valid
    private Logradouro logradouro;
    
	/** The telefones fixo. */
    @NaoNuloCollection(groups = {Post.class}, message = LISTA_TELEFONES_INVALIDA)
    @Valid
    private Set<TelefoneFixo> telefonesFixo;
    
    /** The tipo endereco. */
    @NotNull(message = TIPO_ENDERECO_INVALIDO, groups = {Post.class})
    private TipoEndereco tipoEndereco;
    
    /**
     * Instantiates a new endereco.
     *
     * @param cep the cep
     * @param numero the numero
     * @param logradouro the logradouro
     * @param tipoEndereco the tipo endereco
     */
    public Endereco(String cep, String numero, Logradouro logradouro, TipoEndereco tipoEndereco) {
        this.cep = cep;
        this.numero = numero;
        this.logradouro = logradouro;
        this.tipoEndereco = tipoEndereco;
    }

    /**
     * Instantiates a new endereco.
     */
    public Endereco() {
    }
    
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
     * Gets the telefones fixo.
     *
     * @return the telefones fixo
     */
    public Set<TelefoneFixo> getTelefonesFixo() {
        return telefonesFixo;
    }

    /**
     * Sets the telefones fixo.
     *
     * @param telefonesFixo the new telefones fixo
     */
    public void setTelefonesFixo(Set<TelefoneFixo> telefonesFixo) {
        this.telefonesFixo = telefonesFixo;
    }

    /**
     * Gets the tipo endereco.
     *
     * @return the tipo endereco
     */
    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    /**
     * Sets the tipo endereco.
     *
     * @param tipoEndereco the new tipo endereco
     */
    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
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
        return new ToStringBuilder(this, JSON_STYLE)
                .append("cep", cep)
                .append("numero", numero)
                .append("complemento", complemento)
                .append("logradouro", logradouro)
                .append("telefonesFixo", (telefonesFixo != null) ? telefonesFixo : new HashSet<>())
                .append("tipoEndereco", tipoEndereco)
                .toString();
    }
    
}
