package br.com.contmatic.model.pessoa;

import static br.com.contmatic.model.restricoes.RestricaoCampo.ESPACO;
import static br.com.contmatic.model.restricoes.RestricaoCampo.NOME;

import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.CPF_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.NOME_RESPONSAVEL_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.DATA_NASCIMENTO_INVALIDA;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_SEXO_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_GRAU_INSTRUCAO_INVALIDO;
import static br.com.contmatic.model.restricoes.mensagens.MensagensErro.TIPO_ESTADO_CIVIL_INVALIDO;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.LocalDate;

import br.com.contmatic.validacoes.CPF;
import br.com.contmatic.validacoes.NotNull;
import br.com.contmatic.validacoes.TextDividedBy;

import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;

/**
 * The Class Pessoa.
 */
public class Pessoa {

	/** The cpf. */
	@NotNull(message = CPF_INVALIDO, groups = {Post.class, Put.class})
	@CPF(message = CPF_INVALIDO, groups = {Post.class, Put.class})
	private String cpf;

	/** The nome. */
	@NotNull(message = NOME_RESPONSAVEL_INVALIDO, groups = {Post.class, Put.class})
	@TextDividedBy(separator = ESPACO, groups = {Post.class, Put.class}, message = NOME_RESPONSAVEL_INVALIDO)
	@Pattern(regexp = NOME, groups = {Post.class, Put.class}, message = NOME_RESPONSAVEL_INVALIDO)
	private String nome;

	/** The data nascimento. */
	@NotNull(message = DATA_NASCIMENTO_INVALIDA, groups = {Post.class, Put.class})
	@Past(message = DATA_NASCIMENTO_INVALIDA, groups = {Post.class, Put.class})
	private LocalDate dataNascimento;

	/** The tipo grau instrucao. */
	@NotNull(message = TIPO_GRAU_INSTRUCAO_INVALIDO, groups = {Post.class, Put.class})
	private TipoGrauInstrucao tipoGrauInstrucao;

	/** The tipo estado civil. */
	@NotNull(message = TIPO_ESTADO_CIVIL_INVALIDO, groups = {Post.class, Put.class})
	private TipoEstadoCivil tipoEstadoCivil;

	/** The tipo sexo. */
	@NotNull(message = TIPO_SEXO_INVALIDO, groups = {Post.class, Put.class})
	private TipoSexo tipoSexo;
	
	/**
	 * Gets the cpf.
	 *
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the cpf.
	 *
	 * @param cpf the new cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	 * Gets the data nascimento.
	 *
	 * @return the data nascimento
	 */
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Sets the data nascimento.
	 *
	 * @param dataNascimento the new data nascimento
	 */
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Gets the tipo grau instrucao.
	 *
	 * @return the tipo grau instrucao
	 */
	public TipoGrauInstrucao getTipoGrauInstrucao() {
		return tipoGrauInstrucao;
	}

	/**
	 * Sets the tipo grau instrucao.
	 *
	 * @param tipoGrauInstrucao the new tipo grau instrucao
	 */
	public void setTipoGrauInstrucao(TipoGrauInstrucao tipoGrauInstrucao) {
		this.tipoGrauInstrucao = tipoGrauInstrucao;
	}

	/**
	 * Gets the tipo estado civil.
	 *
	 * @return the tipo estado civil
	 */
	public TipoEstadoCivil getTipoEstadoCivil() {
		return tipoEstadoCivil;
	}

	/**
	 * Sets the tipo estado civil.
	 *
	 * @param tipoEstadoCivil the new tipo estado civil
	 */
	public void setTipoEstadoCivil(TipoEstadoCivil tipoEstadoCivil) {
		this.tipoEstadoCivil = tipoEstadoCivil;
	}

	/**
	 * Gets the tipo sexo.
	 *
	 * @return the tipo sexo
	 */
	public TipoSexo getTipoSexo() {
		return tipoSexo;
	}

	/**
	 * Sets the tipo sexo.
	 *
	 * @param tipoSexo the new tipo sexo
	 */
	public void setTipoSexo(TipoSexo tipoSexo) {
		this.tipoSexo = tipoSexo;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public final int hashCode() {
		return new HashCodeBuilder()
				.append(cpf)
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
		if (!(objeto instanceof Pessoa)) {
			return false;
		}
		if (this == objeto) {
			return true;
		}
		final Pessoa outroPessoa = (Pessoa) objeto;
		return new EqualsBuilder()
				.append(cpf, outroPessoa.cpf)
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
				.append("cpf", this.cpf)
				.append("nome", this.nome)
				.append("dataNascimento", this.dataNascimento)
				.append("tipoGrauInstrucao", this.tipoGrauInstrucao)
				.append("tipoEstadoCivil", this.tipoEstadoCivil)
				.append("tipoSexo", this.tipoSexo)
				.toString();
	}

}
