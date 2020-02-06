package br.com.contmatic.model.random.pessoa;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.cpfValido;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithSeparator;
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
import br.com.contmatic.model.random.conta.ContaRandomBuilder;
import br.com.contmatic.model.random.contato.CelularRandomBuilder;
import br.com.contmatic.model.random.contato.EmailRandomBuilder;
import br.com.contmatic.model.random.contato.TelefoneFixoRandomBuilder;
import br.com.contmatic.model.random.endereco.EnderecoRandomBuilder;

public class PessoaRandomBuilder {
	
	private static final int ELEMENTOS_ARRAY_GERADA = 10;
	
    private static final int TAMANHO_REGULAR = 100;
    
    private static final String VALIDO_NOME = "[A-ZÁÉÍÓÚÃÕÀÂÊÔÇ&\\-ªº\\.']";
    
    private static final String ESPACO = " ";
    
	private EnderecoRandomBuilder enderecoRandomBuilder = new EnderecoRandomBuilder();
	
	private CelularRandomBuilder celularRandomBuilder = new CelularRandomBuilder();
	
	private TelefoneFixoRandomBuilder telefoneFixoRandomBuilder = new TelefoneFixoRandomBuilder();
	
	private EmailRandomBuilder emailRandomBuilder = new EmailRandomBuilder();
	
	private ContaRandomBuilder contaRandomBuilder = new ContaRandomBuilder();
	
	public Pessoa build() {
		final Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpfValido());
		pessoa.setNome(generateStringBySizeAndRegexWithSeparator(nextInt(1, TAMANHO_REGULAR + 1), VALIDO_NOME, ESPACO));
		Set<Endereco> enderecos = new HashSet<>();
		int quantidadeCollection = nextInt(1, ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			enderecos.add(enderecoRandomBuilder.build());
		}
		pessoa.setEnderecos(enderecos);
		pessoa.setDataNascimento(LocalDate.now().minusYears(nextInt(1, 30)));
		Set<Celular> celulares = new HashSet<>();
		quantidadeCollection = nextInt(0, ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			celulares.add(celularRandomBuilder.build());
		}
		pessoa.setCelulares(celulares);
		Set<TelefoneFixo> telefonesFixo = new HashSet<>();
		quantidadeCollection = nextInt(0, ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			telefonesFixo.add(telefoneFixoRandomBuilder.build());
		}
		pessoa.setTelefonesFixo(telefonesFixo);
		Set<Email> emails = new HashSet<>();
		quantidadeCollection = nextInt(0, ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			emails.add(emailRandomBuilder.build());
		}
		pessoa.setEmails(emails);
		pessoa.setTipoGrauInstrucao(TipoGrauInstrucao.values()[nextInt(0, TipoGrauInstrucao.values().length)]);
		pessoa.setTipoEstadoCivil(TipoEstadoCivil.values()[nextInt(0, TipoEstadoCivil.values().length)]);
		pessoa.setTipoSexo(TipoSexo.values()[nextInt(0, TipoSexo.values().length)]);
		Set<Conta> contas = new HashSet<>();
		quantidadeCollection = nextInt(0, ELEMENTOS_ARRAY_GERADA);
		for (int i = 0; i < quantidadeCollection; i++) {
			contas.add(contaRandomBuilder.build());
		}
		pessoa.setContas(contas);
		return pessoa;
	}
	
}
