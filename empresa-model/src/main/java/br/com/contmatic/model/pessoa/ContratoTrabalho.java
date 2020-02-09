package br.com.contmatic.model.pessoa;

import static br.com.contmatic.validacoes.utilidades.MensagensErro.DATA_INICIO_CONTRATO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.PESSOA_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_CONTATO_INVALIDO;

import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.joda.time.LocalDate;

import br.com.contmatic.validacoes.NaoNulo;
import br.com.contmatic.validacoes.groups.Post;
import br.com.contmatic.validacoes.groups.Put;

/**
 * The Class ContratoTrabalho.
 */
public class ContratoTrabalho {

    /** The pessoa. */
    @NaoNulo(message = PESSOA_INVALIDA, groups = {Post.class})
    @Valid
    private Pessoa pessoa;
      
    /** The tipo contrato trabalho. */
    @NaoNulo(message = TIPO_CONTATO_INVALIDO, groups = {Post.class})
    private TipoContratoTrabalho tipoContratoTrabalho;

    /** The data inicio contrato. */
    @NaoNulo(message = DATA_INICIO_CONTRATO, groups = {Post.class})
    @Past(message = DATA_INICIO_CONTRATO, groups = {Post.class, Put.class})
    private LocalDate dataInicioContrato;
    
    /**
     * Instantiates a new contrato trabalho.
     *
     * @param pessoa the pessoa
     * @param tipoContratoTrabalho the tipo contrato trabalho
     * @param dataInicioContrato the data inicio contrato
     */
    public ContratoTrabalho(Pessoa pessoa, TipoContratoTrabalho tipoContratoTrabalho, LocalDate dataInicioContrato) {
        this.pessoa = pessoa;
        this.tipoContratoTrabalho = tipoContratoTrabalho;
        this.dataInicioContrato = dataInicioContrato;
    }
   
    /**
     * Instantiates a new contrato trabalho.
     */
    public ContratoTrabalho() {
    }
    
    /**
     * Gets the pessoa.
     *
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * Sets the pessoa.
     *
     * @param pessoa the new pessoa
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * Gets the tipo contrato trabalho.
     *
     * @return the tipo contrato trabalho
     */
    public TipoContratoTrabalho getTipoContratoTrabalho() {
		return tipoContratoTrabalho;
	}

	/**
	 * Sets the tipo contrato trabalho.
	 *
	 * @param tipoContratoTrabalho the new tipo contrato trabalho
	 */
	public void setTipoContratoTrabalho(TipoContratoTrabalho tipoContratoTrabalho) {
		this.tipoContratoTrabalho = tipoContratoTrabalho;
	}

	/**
	 * Gets the data inicio contrato.
	 *
	 * @return the data inicio contrato
	 */
	public LocalDate getDataInicioContrato() {
		return dataInicioContrato;
	}

	/**
	 * Sets the data inicio contrato.
	 *
	 * @param dataInicioContrato the new data inicio contrato
	 */
	public void setDataInicioContrato(LocalDate dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(pessoa)
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
        if (!(objeto instanceof ContratoTrabalho)) {
            return false;
        }
        if (this == objeto) {
            return true;
        }
        final ContratoTrabalho outroContratoTrabalho = (ContratoTrabalho) objeto;
        return new EqualsBuilder()
                .append(pessoa, outroContratoTrabalho.pessoa)
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
                .append("pessoa:")
                .append(pessoa)
                .append(",")
                .append("tipoContratoTrabalho:")
                .append(tipoContratoTrabalho != null ? tipoContratoTrabalho.name() : null)
                .append(",")
                .append("dataInicioContrato:")
                .append(dataInicioContrato)
                .append("}")
                .toString();
    }
    
}
