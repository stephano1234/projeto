package br.com.contmatic.model.pessoa;

import static br.com.contmatic.validacoes.utilidades.ConstantesString.NOME;
import static br.com.contmatic.validacoes.utilidades.ConstantesString.ESPACO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CPF_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.NOME_PESSOA_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.DATA_NASCIMENTO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CELULARES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CONTAS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_EMAILS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_ENDERECOS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_TELEFONES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_SEXO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_GRAU_INSTRUCAO_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_ESTADO_CIVIL_INVALIDO;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.validacoes.CPFbr;
import br.com.contmatic.validacoes.NaoNuloCollection;
import br.com.contmatic.validacoes.NotEmptyCollection;
import br.com.contmatic.validacoes.TextDividedBy;
import br.com.contmatic.validacoes.groups.Post;
import br.com.contmatic.validacoes.groups.Put;

/**
 * The Class Pessoa.
 */
public class Pessoa {

	/** The cpf. */
	@NotNull(message = CPF_INVALIDO, groups = {Post.class})
	@CPFbr(groups = {Post.class, Put.class})
	private String cpf;

	/** The nome. */
	@NotNull(message = NOME_PESSOA_INVALIDO, groups = {Post.class})
	@TextDividedBy(separator = ESPACO, groups = {Post.class, Put.class}, message = NOME_PESSOA_INVALIDO)
	@Pattern(regexp = NOME, groups = {Post.class, Put.class}, message = NOME_PESSOA_INVALIDO)
	private String nome;

	/** The enderecos. */
	@NotEmptyCollection(groups = {Post.class}, message = LISTA_ENDERECOS_INVALIDA)
	@Valid
	private Set<Endereco> enderecos;

	/** The data nascimento. */
	@NotNull(message = DATA_NASCIMENTO, groups = {Post.class})
	@Past(message = DATA_NASCIMENTO, groups = {Post.class, Put.class})
	private LocalDate dataNascimento;

	/** The celulares. */
	@NaoNuloCollection(groups = {Post.class}, message = LISTA_CELULARES_INVALIDA)
	@Valid
	private Set<Celular> celulares;
	
	/** The telefones fixo. */
	@NaoNuloCollection(groups = {Post.class}, message = LISTA_TELEFONES_INVALIDA)
	@Valid
	private Set<TelefoneFixo> telefonesFixo;

	/** The emails. */
	@NaoNuloCollection(groups = {Post.class}, message = LISTA_EMAILS_INVALIDA)
	@Valid
	private Set<Email> emails;

	/** The tipo grau instrucao. */
	@NotNull(message = TIPO_GRAU_INSTRUCAO_INVALIDO, groups = {Post.class})
	private TipoGrauInstrucao tipoGrauInstrucao;

	/** The tipo estado civil. */
	@NotNull(message = TIPO_ESTADO_CIVIL_INVALIDO, groups = {Post.class})
	private TipoEstadoCivil tipoEstadoCivil;

	/** The tipo sexo. */
	@NotNull(message = TIPO_SEXO_INVALIDO, groups = {Post.class})
	private TipoSexo tipoSexo;
	
	/** The contas. */
	@NaoNuloCollection(groups = {Post.class}, message = LISTA_CONTAS_INVALIDA)
	@Valid
	private Set<Conta> contas;

	/**
	 * Instantiates a new pessoa.
	 *
	 * @param cpf the cpf
	 * @param nome the nome
	 * @param enderecos the enderecos
	 * @param dataNascimento the data nascimento
	 * @param tipoGrauInstrucao the tipo grau instrucao
	 * @param tipoEstadoCivil the tipo estado civil
	 * @param tipoSexo the tipo sexo
	 */
	public Pessoa(String cpf, String nome, Set<Endereco> enderecos, LocalDate dataNascimento,
			TipoGrauInstrucao tipoGrauInstrucao, TipoEstadoCivil tipoEstadoCivil, TipoSexo tipoSexo) {
		this.cpf = cpf;
		this.nome = nome;
		this.enderecos = enderecos;
		this.dataNascimento = dataNascimento;
		this.tipoGrauInstrucao = tipoGrauInstrucao;
		this.tipoEstadoCivil = tipoEstadoCivil;
		this.tipoSexo = tipoSexo;
	}

	/**
	 * Instantiates a new pessoa.
	 */
	public Pessoa() {
	}
	
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
	 * Gets the enderecos.
	 *
	 * @return the enderecos
	 */
	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	/**
	 * Sets the enderecos.
	 *
	 * @param enderecos the new enderecos
	 */
	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
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
	 * Gets the celulares.
	 *
	 * @return the celulares
	 */
	public Set<Celular> getCelulares() {
		return celulares;
	}

	/**
	 * Sets the celulares.
	 *
	 * @param celulares the new celulares
	 */
	public void setCelulares(Set<Celular> celulares) {
		this.celulares = celulares;
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
	 * Gets the emails.
	 *
	 * @return the emails
	 */
	public Set<Email> getEmails() {
		return emails;
	}

	/**
	 * Sets the emails.
	 *
	 * @param emails the new emails
	 */
	public void setEmails(Set<Email> emails) {
		this.emails = emails;
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
	 * Gets the contas.
	 *
	 * @return the contas
	 */
	public Set<Conta> getContas() {
		return contas;
	}

	/**
	 * Sets the contas.
	 *
	 * @param contas the new contas
	 */
	public void setContas(Set<Conta> contas) {
		this.contas = contas;
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
		return new ToStringBuilder(this, JSON_STYLE)
				.append("cpf", cpf)
				.append("nome", nome)
				.append("enderecos", enderecos)
				.append("dataNascimento", dataNascimento)
				.append("celulares", (celulares != null) ? celulares : new HashSet<>())
				.append("telefonesFixo", (telefonesFixo != null) ? telefonesFixo : new HashSet<>())
				.append("emails", (emails != null) ? emails : new HashSet<>())
				.append("tipoGrauInstrucao", tipoGrauInstrucao)
				.append("tipoEstadoCivil", tipoEstadoCivil)
				.append("tipoSexo", tipoSexo)
				.append("contas", (contas != null) ? contas : new HashSet<>())
				.toString();
	}

}
