package br.com.contmatic.model.random.contato;

import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegex;
import static br.com.contmatic.testes.utilidades.FuncoesRandomicas.generateStringBySizeAndRegexWithOneCharByRegex;
import static java.util.regex.Matcher.quoteReplacement;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.contato.Email;

public class EmailTestRandomBuilder {

	private static final String LETRA_MINUSCULA = "[a-z]";

	private static final String LETRA_MINUSCULA_NUMERAL = "[a-z0-9]";

	private static final String SEM_LETRA_MINUSCULA_NUMERAL = "[^a-z0-9]";

	private static final String INVALIDO_ANTES_ARROBA = "[^a-z0-9._-]";

	private static final String INVALIDO_ENTRE_ARROBA_E_PONTO_OBRIGATORIO = "[^a-z0-9.-]";

	private static final String INVALIDO_DEPOIS_PONTO_OBRIGATORIO = "[^a-z.]";

	private final Email emailValido = new EmailRandomBuilder().build();

	private static EmailTestRandomBuilder instance;

	public static EmailTestRandomBuilder getInstance() {
		if (instance == null) {
			instance = new EmailTestRandomBuilder();
		}
		return instance;
	}

	public void cleanBuilder() {
		instance = null;
	}

	public Email buildValid() {
		return emailValido;
	}

	public Email buildNuloEndereco() {
		Email email = new Email();
		email.setEndereco(null);
		return email;
	}

	public Email buildVazioEndereco() {
		Email email = new Email();
		email.setEndereco("");
		return email;
	}

	public Email buildSemArroba() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("@", ""));
		return email;
	}

	public Email buildArrobaPrecedidoPorCaractereInvalido() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("@", generateStringBySizeAndRegex(1, "[._-]") + "@"));
		return email;
	}

	public Email buildMaisDeUmArroba() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco() + "@");
		return email;
	}

	public Email buildSemPonto() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("\\.", ""));
		return email;
	}

	public Email buildVazioAntesArroba() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll(".*@", "@"));
		return email;
	}

	public Email buildMaiorTamanhoAntesArroba() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll(".*@",
				quoteReplacement(generateStringBySizeAndRegex(30 + 1, LETRA_MINUSCULA_NUMERAL) + "@")));
		return email;
	}

	public Email buildPrimeiroCaractereInvalidoAntesArroba() {
		Email email = new Email();
		email.setEndereco(generateStringBySizeAndRegex(1, SEM_LETRA_MINUSCULA_NUMERAL) + emailValido.getEndereco());
		return email;
	}

	public Email buildCaractereInvalidoAntesArroba() {
		Email email = new Email();
		email.setEndereco(
				emailValido.getEndereco()
						.replaceAll(".*@",
								generateStringBySizeAndRegex(1, LETRA_MINUSCULA_NUMERAL)
										+ quoteReplacement(generateStringBySizeAndRegexWithOneCharByRegex(
												nextInt(1, 29 + 1), INVALIDO_ANTES_ARROBA, LETRA_MINUSCULA_NUMERAL))
										+ "@"));
		return email;
	}

	public Email buildVazioDepoisArrobaAtePontoObrigatorio() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("@.*\\.(?!\\.)", "@" + "."));
		return email;
	}

	public Email buildMaiorTamanhoDepoisArrobaAtePontoObrigatorio() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("@.*\\.(?!\\.)",
				"@" + generateStringBySizeAndRegex(20 + 1, LETRA_MINUSCULA_NUMERAL) + "."));
		return email;
	}

	public Email buildPrimeiroCaractereInvalidoDepoisArrobaAtePontoObrigatorio() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("(?<=@).",
				quoteReplacement(generateStringBySizeAndRegex(1, SEM_LETRA_MINUSCULA_NUMERAL))));
		return email;
	}

	public Email buildCaractereInvalidoDepoisArrobaAtePontoObrigatorio() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("@.*\\.(?!\\.)",
				"@" + generateStringBySizeAndRegex(1, LETRA_MINUSCULA_NUMERAL)
						+ quoteReplacement(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(1, 19 + 1),
								INVALIDO_ENTRE_ARROBA_E_PONTO_OBRIGATORIO, LETRA_MINUSCULA_NUMERAL))
						+ "."));
		return email;
	}

	public Email buildSemPontoObrigatorioDepoisArroba() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("@.*\\.(?!\\.)",
				"@" + generateStringBySizeAndRegex(nextInt(1, 19 + 1), LETRA_MINUSCULA_NUMERAL)));
		return email;
	}

	public Email buildPontoObrigatorioPrecedidoPorCaractereInvalido() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("@.*\\.(?!\\.)",
				"@" + generateStringBySizeAndRegex(nextInt(1, 19 + 1), LETRA_MINUSCULA_NUMERAL)
						+ generateStringBySizeAndRegex(1, "[.-]") + "."));
		return email;
	}

	public Email buildMaiorTamanhoDepoisPontoObrigatorio() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("\\.(?!.*\\.).*",
				"." + generateStringBySizeAndRegex(6 + 1, LETRA_MINUSCULA)));
		return email;
	}

	public Email buildMenorTamanhoDepoisPontoObrigatorio() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("\\.(?!.*\\.).*",
				"." + generateStringBySizeAndRegex(2 - 1, LETRA_MINUSCULA)));
		return email;
	}

	public Email buildCaractereInvalidoDepoisPontoObrigatorio() {
		Email email = new Email();
		email.setEndereco(emailValido.getEndereco().replaceAll("\\.(?!.*\\.).*",
				"." + quoteReplacement(generateStringBySizeAndRegexWithOneCharByRegex(nextInt(2, 6 + 1),
						INVALIDO_DEPOIS_PONTO_OBRIGATORIO, LETRA_MINUSCULA))));
		return email;
	}

}
