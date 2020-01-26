package br.com.contmatic.model.empresa;

import static br.com.contmatic.validacoes.utilidades.ConstantesString.ESPACO;
import static br.com.contmatic.validacoes.utilidades.ConstantesString.RAZAO_SOCIAL;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CNPJ_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.DATA_ABERTURA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CELULARES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CONTAS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CONTRATOS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_EMAILS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_ENDERECOS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_RESPONSAVEIS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_TELEFONES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.RAZAO_SOCIAL_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_EMPRESA_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_PORTE_EMPRESA_INVALIDO;
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
import br.com.contmatic.model.pessoa.ContratoTrabalho;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.validacoes.CNPJbr;
import br.com.contmatic.validacoes.NaoNuloCollection;
import br.com.contmatic.validacoes.NotEmptyCollection;
import br.com.contmatic.validacoes.TextDividedBy;
import br.com.contmatic.validacoes.groups.Delete;
import br.com.contmatic.validacoes.groups.Post;
import br.com.contmatic.validacoes.groups.Put;

/**
 * The Class Empresa.
 */
public class Empresa {

	/** The cnpj. */
	@NotNull(message = CNPJ_INVALIDO, groups = {Post.class, Put.class, Delete.class})
	@CNPJbr(groups = {Post.class, Put.class, Delete.class})
	private String cnpj;

	/** The razao social. */
	@NotNull(message = RAZAO_SOCIAL_INVALIDO, groups = {Post.class})
	@TextDividedBy(separator = ESPACO, groups = {Post.class, Put.class}, message = RAZAO_SOCIAL_INVALIDO)
	@Pattern(regexp = RAZAO_SOCIAL, groups = {Post.class, Put.class}, message = RAZAO_SOCIAL_INVALIDO)
	private String razaoSocial;

	/** The data abertura. */
	@NotNull(message = DATA_ABERTURA, groups = {Post.class})
	@Past(message = DATA_ABERTURA, groups = {Post.class, Put.class})
	private LocalDate dataAbertura;

	/** The responsaveis. */
	@NotEmptyCollection(message = LISTA_RESPONSAVEIS_INVALIDA, groups = {Post.class})
	@Valid
	private Set<Pessoa> responsaveis;

	/** The contratos trabalho. */
	@NaoNuloCollection(message = LISTA_CONTRATOS_INVALIDA, groups = {Post.class})
	@Valid
	private Set<ContratoTrabalho> contratosTrabalho;

	/** The enderecos. */
	@NotEmptyCollection(message = LISTA_ENDERECOS_INVALIDA, groups = {Post.class})
	@Valid
	private Set<Endereco> enderecos;

	/** The telefones fixo. */
	@NaoNuloCollection(message = LISTA_TELEFONES_INVALIDA, groups = {Post.class})
	@Valid
	private Set<TelefoneFixo> telefonesFixo;

	/** The emails. */
	@NaoNuloCollection(message = LISTA_EMAILS_INVALIDA, groups = {Post.class})
	@Valid
	private Set<Email> emails;

	/** The celulares. */
	@NaoNuloCollection(message = LISTA_CELULARES_INVALIDA, groups = {Post.class})
	@Valid
	private Set<Celular> celulares;

	/** The contas. */
	@NaoNuloCollection(message = LISTA_CONTAS_INVALIDA, groups = {Post.class})
	@Valid
	private Set<Conta> contas;

	/** The tipo empresa. */
	@NotNull(message = TIPO_EMPRESA_INVALIDO, groups = {Post.class})
	private TipoEmpresa tipoEmpresa;

	/** The tipo porte empresa. */
	@NotNull(message = TIPO_PORTE_EMPRESA_INVALIDO, groups = {Post.class})
	private TipoPorteEmpresa tipoPorteEmpresa;

	/**
	 * Instantiates a new empresa.
	 *
	 * @param cnpj the cnpj
	 * @param razaoSocial the razao social
	 * @param dataAbertura the data abertura
	 * @param responsaveis the responsaveis
	 * @param enderecos the enderecos
	 * @param tipoEmpresa the tipo empresa
	 * @param tipoPorteEmpresa the tipo porte empresa
	 */
	public Empresa(String cnpj, String razaoSocial, LocalDate dataAbertura, Set<Pessoa> responsaveis,
			Set<Endereco> enderecos, TipoEmpresa tipoEmpresa, TipoPorteEmpresa tipoPorteEmpresa) {
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.dataAbertura = dataAbertura;
		this.responsaveis = responsaveis;
		this.enderecos = enderecos;
		this.tipoEmpresa = tipoEmpresa;
		this.tipoPorteEmpresa = tipoPorteEmpresa;
	}

	/**
	 * Instantiates a new empresa.
	 */
	public Empresa() {
	}
	
	/**
	 * Gets the cnpj.
	 *
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * Sets the cnpj.
	 *
	 * @param cnpj the new cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Gets the razao social.
	 *
	 * @return the razao social
	 */
	public String getRazaoSocial() {
		return razaoSocial;
	}

	/**
	 * Sets the razao social.
	 *
	 * @param razaoSocial the new razao social
	 */
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	/**
	 * Gets the data abertura.
	 *
	 * @return the data abertura
	 */
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	/**
	 * Sets the data abertura.
	 *
	 * @param dataAbertura the new data abertura
	 */
	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	/**
	 * Gets the responsaveis.
	 *
	 * @return the responsaveis
	 */
	public Set<Pessoa> getResponsaveis() {
		return responsaveis;
	}

	/**
	 * Sets the responsaveis.
	 *
	 * @param responsaveis the new responsaveis
	 */
	public void setResponsaveis(Set<Pessoa> responsaveis) {
		this.responsaveis = responsaveis;
	}

	/**
	 * Gets the contratos trabalho.
	 *
	 * @return the contratos trabalho
	 */
	public Set<ContratoTrabalho> getContratosTrabalho() {
		return contratosTrabalho;
	}

	/**
	 * Sets the contratos trabalho.
	 *
	 * @param contratosTrabalho the new contratos trabalho
	 */
	public void setContratosTrabalho(Set<ContratoTrabalho> contratosTrabalho) {
		this.contratosTrabalho = contratosTrabalho;
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
	 * Gets the tipo empresa.
	 *
	 * @return the tipo empresa
	 */
	public TipoEmpresa getTipoEmpresa() {
		return tipoEmpresa;
	}

	/**
	 * Sets the tipo empresa.
	 *
	 * @param tipoEmpresa the new tipo empresa
	 */
	public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	/**
	 * Gets the tipo porte empresa.
	 *
	 * @return the tipo porte empresa
	 */
	public TipoPorteEmpresa getTipoPorteEmpresa() {
		return tipoPorteEmpresa;
	}

	/**
	 * Sets the tipo porte empresa.
	 *
	 * @param tipoPorteEmpresa the new tipo porte empresa
	 */
	public void setTipoPorteEmpresa(TipoPorteEmpresa tipoPorteEmpresa) {
		this.tipoPorteEmpresa = tipoPorteEmpresa;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public final int hashCode() {
		return new HashCodeBuilder()
				.append(cnpj)
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
		if (!(objeto instanceof Empresa)) {
			return false;
		}
		if (this == objeto) {
			return true;
		}
		final Empresa outroEmpresa = (Empresa) objeto;
		return new EqualsBuilder()
				.append(cnpj, outroEmpresa.cnpj)
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
				.append("cnpj", cnpj)
				.append("razaoSocial", razaoSocial)
				.append("dataAbertura", dataAbertura)
				.append("responsaveis", responsaveis)
				.append("contratosTrabalho", (contratosTrabalho != null) ? contratosTrabalho : new HashSet<>())	
				.append("enderecos", enderecos)		
				.append("telefonesFixo", (telefonesFixo != null) ? telefonesFixo : new HashSet<>())
				.append("emails", (emails != null) ? emails : new HashSet<>())
				.append("celulares", (celulares != null) ? celulares : new HashSet<>())
				.append("contas", (contas != null) ? contas : new HashSet<>())
				.append("tipoEmpresa", tipoEmpresa)
				.append("tipoPorteEmpresa", tipoPorteEmpresa)
				.toString();
	}
	
}
