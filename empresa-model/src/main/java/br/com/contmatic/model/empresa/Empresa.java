package br.com.contmatic.model.empresa;

import static br.com.contmatic.model.restricoes.RestricaoCampo.ESPACO;
import static br.com.contmatic.model.restricoes.RestricaoCampo.NOME;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.CNPJ_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.DATA_ABERTURA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CELULARES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_CONTAS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_EMAILS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_ENDERECOS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_RESPONSAVEIS_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.LISTA_TELEFONES_INVALIDA;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.RAZAO_SOCIAL_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_EMPRESA_INVALIDO;
import static br.com.contmatic.validacoes.utilidades.MensagensErro.TIPO_PORTE_EMPRESA_INVALIDO;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.LocalDate;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.pessoa.Pessoa;

import br.com.contmatic.validacoes.CNPJbr;
import br.com.contmatic.validacoes.NaoNulo;
import br.com.contmatic.validacoes.NaoNuloCollection;
import br.com.contmatic.validacoes.NotEmptyCollection;
import br.com.contmatic.validacoes.TextDividedBy;

import br.com.contmatic.model.restricoes.grupos.Delete;
import br.com.contmatic.model.restricoes.grupos.Get;
import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;

/**
 * The Class Empresa.
 */
public class Empresa {

	/** The cnpj. */
	@NaoNulo(message = CNPJ_INVALIDO, groups = {Post.class, Put.class, Delete.class})
	@CNPJbr(groups = {Post.class, Put.class, Delete.class, Get.class})
	private String cnpj;

	/** The razao social. */
	@NaoNulo(message = RAZAO_SOCIAL_INVALIDO, groups = {Post.class, Put.class})
	@TextDividedBy(separator = ESPACO, groups = {Post.class, Put.class}, message = RAZAO_SOCIAL_INVALIDO)
	@Pattern(regexp = NOME, groups = {Post.class, Put.class}, message = RAZAO_SOCIAL_INVALIDO)
	private String razaoSocial;

	/** The data abertura. */
	@NaoNulo(message = DATA_ABERTURA, groups = {Post.class, Put.class})
	@Past(message = DATA_ABERTURA, groups = {Post.class, Put.class})
	private LocalDate dataAbertura;

	/** The responsaveis. */
	@NotEmptyCollection(message = LISTA_RESPONSAVEIS_INVALIDA, groups = {Post.class})
	@Valid
	private Set<Pessoa> responsaveis;

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
	@NaoNulo(message = TIPO_EMPRESA_INVALIDO, groups = {Post.class, Put.class})
	private TipoEmpresa tipoEmpresa;

	/** The tipo porte empresa. */
	@NaoNulo(message = TIPO_PORTE_EMPRESA_INVALIDO, groups = {Post.class, Put.class})
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
		return new StringBuilder()
				.append("{")
				.append("cnpj:")
				.append(cnpj)
				.append(",")
				.append("razaoSocial:")
				.append(razaoSocial)
				.append(",")
				.append("dataAbertura:")
				.append(dataAbertura)
				.append(",")
				.append("responsaveis:")
				.append(responsaveis)
				.append(",")
				.append("enderecos:")
				.append(enderecos)
				.append(",")
				.append("telefonesFixo:")
				.append(telefonesFixo)
				.append(",")
				.append("emails:")
				.append(emails)
				.append(",")
				.append("celulares:")
				.append(celulares)
				.append(",")
				.append("contas:")
				.append(contas)
				.append(",")
				.append("tipoEmpresa:")
				.append(tipoEmpresa != null ? tipoEmpresa.name() : null)
				.append(",")
				.append("tipoPorteEmpresa:")
				.append(tipoPorteEmpresa != null ? tipoPorteEmpresa.name() : null)
				.append("}")
				.toString();
	}
	
}
