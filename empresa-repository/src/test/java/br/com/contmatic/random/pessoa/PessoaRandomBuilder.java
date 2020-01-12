package br.com.contmatic.random.pessoa;

import static br.com.contmatic.utilidades.ConstantesTesteNumericas.ELEMENTOS_ARRAY_GERADA;
import static br.com.contmatic.utilidades.FuncoesRandomicas.cpfValido;
import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import br.com.contmatic.model.conta.Conta;
import br.com.contmatic.model.contato.Celular;
import br.com.contmatic.model.contato.Email;
import br.com.contmatic.model.contato.TelefoneFixo;
import br.com.contmatic.model.endereco.Endereco;
import br.com.contmatic.model.pessoa.Pessoa;
import br.com.contmatic.model.pessoa.TipoEstadoCivil;
import br.com.contmatic.model.pessoa.TipoGrauInstrucao;
import br.com.contmatic.model.pessoa.TipoSexo;
import br.com.contmatic.random.conta.ContaRandomBuilder;
import br.com.contmatic.random.contato.CelularRandomBuilder;
import br.com.contmatic.random.contato.EmailRandomBuilder;
import br.com.contmatic.random.contato.TelefoneFixoRandomBuilder;
import br.com.contmatic.random.endereco.EnderecoRandomBuilder;

public class PessoaRandomBuilder {

	public static Pessoa buildValido() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpfValido());
		pessoa.setNome(somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]") + " "
				+ somenteCaractere(1, "[A-Z]") + somenteCaractere(nextInt(1, 10), "[a-z]"));
		Set<Endereco> enderecos = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			enderecos.add(EnderecoRandomBuilder.buildValido());
		}
		pessoa.setEnderecos(enderecos);
		pessoa.setDataNascimento(LocalDate.now().minusYears(nextInt(1, 30)));
		Set<Celular> celulares = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			celulares.add(CelularRandomBuilder.buildValido());
		}
		pessoa.setCelulares(celulares);
		Set<TelefoneFixo> telefonesFixo = new HashSet<TelefoneFixo>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			telefonesFixo.add(TelefoneFixoRandomBuilder.buildValido());
		}
		pessoa.setTelefonesFixo(telefonesFixo);
		Set<Email> emails = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			emails.add(EmailRandomBuilder.buildValido());
		}
		pessoa.setEmails(emails);
		pessoa.setTipoGrauInstrucao(TipoGrauInstrucao.values()[nextInt(0, TipoGrauInstrucao.values().length)]);
		pessoa.setTipoEstadoCivil(TipoEstadoCivil.values()[nextInt(0, TipoEstadoCivil.values().length)]);
		pessoa.setTipoSexo(TipoSexo.values()[nextInt(0, TipoSexo.values().length)]);
		Set<Conta> contas = new HashSet<>();
		for (int i = 0; i < nextInt(1, ELEMENTOS_ARRAY_GERADA); i++) {
			contas.add(ContaRandomBuilder.buildValido());
		}
		pessoa.setContas(contas);
		return pessoa;
	}
	
}
