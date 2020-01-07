package br.com.contmatic.templates.contato;

import static br.com.contmatic.utilidades.ConstantesTesteString.INVALIDOS_EMAIL;
import static br.com.contmatic.utilidades.ConstantesTesteString.LETRA_MINUSCULA_NUMERAL;
import static br.com.contmatic.utilidades.FuncoesRandomicas.apenasUmCaractere;
import static br.com.contmatic.utilidades.FuncoesRandomicas.emailAleatorio;
import static br.com.contmatic.utilidades.FuncoesRandomicas.somenteCaractere;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import br.com.contmatic.model.contato.Email;

public class EmailRandomBuilder {

	public static Email buildValido() {
		Email email = new Email();
		email.setEndereco(emailAleatorio());
		return email;
	}
	
	public static Email buildSemArroba() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("@", ""));
		return email;
	}

	public static Email buildArrobaPrecedidoPorCaractereInvalido() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("@", somenteCaractere(1, "[._-]") + "@"));
		return email;
	}

	public static Email buildMaisDeUmArroba() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio() + "@");
		return email;
	}

	public static Email buildSemPonto() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("\\.", ""));
		return email;
	}

	public static Email buildVazioAntesArroba() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll(".*@", "@"));
		return email;
	}

	public static Email buildMaiorTamanhoAntesArroba() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll(".*@", somenteCaractere(30 + 1, LETRA_MINUSCULA_NUMERAL) + "@"));
		return email;
	}

	public static Email buildPrimeiroCaractereInvalidoAntesArroba() {
		Email email = buildValido();
		email.setEndereco(somenteCaractere(1, "[._-]|" + INVALIDOS_EMAIL) + emailAleatorio());
		return email;
	}

	public static Email buildCaractereInvalidoAntesArroba() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll(".*@", somenteCaractere(1, LETRA_MINUSCULA_NUMERAL) + apenasUmCaractere(nextInt(1, 29 + 1), INVALIDOS_EMAIL, LETRA_MINUSCULA_NUMERAL) + "@"));
		return email;
	}

	public static Email buildVazioDepoisArrobaAtePontoObrigatorio() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("@.*\\.(?!\\.)", "@" + "."));
		return email;
	}

	public static Email buildMaiorTamanhoDepoisArrobaAtePontoObrigatorio() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("@.*\\.(?!\\.)", "@" + somenteCaractere(20 + 1, "[a-z]") + "."));
		return email;
	}

	public static Email buildPrimeiroCaractereInvalidoDepoisArrobaAtePontoObrigatorio() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("(?<=@).", somenteCaractere(1, "[._-]|" + INVALIDOS_EMAIL)));
		return email;
	}

	public static Email buildCaractereInvalidoDepoisArrobaAtePontoObrigatorio() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("@.*\\.(?!\\.)", "@" + somenteCaractere(1, LETRA_MINUSCULA_NUMERAL) + apenasUmCaractere(nextInt(1, 19 + 1), "[_]|" + INVALIDOS_EMAIL, LETRA_MINUSCULA_NUMERAL) + "."));
		return email;
	}

	public static Email buildSemPontoObrigatorioDepoisArroba() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("@.*\\.(?!\\.)", "@" + somenteCaractere(1, LETRA_MINUSCULA_NUMERAL) + somenteCaractere(nextInt(1, 19 + 1), LETRA_MINUSCULA_NUMERAL)));
		return email;
	}

	public static Email buildPontoObrigatorioPrecedidoPorCaractereInvalido() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("@.*\\.(?!\\.)", "@" + somenteCaractere(1, LETRA_MINUSCULA_NUMERAL) + somenteCaractere(nextInt(1, 19 - 1 + 1), LETRA_MINUSCULA_NUMERAL) + somenteCaractere(1, "[._-]|" + INVALIDOS_EMAIL) + "."));
		return email;
	}

	public static Email buildMaiorTamanhoDepoisPontoObrigatorio() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("\\.(?!.*\\.).*", "." + somenteCaractere(6 + 1, "[a-z]")));
		return email;
	}

	public static Email buildMenorTamanhoDepoisPontoObrigatorio() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("\\.(?!.*\\.).*", "." + somenteCaractere(2 - 1, "[a-z]")));
		return email;
	}

	public static Email buildCaractereInvalidoDepoisPontoObrigatorio() {
		Email email = buildValido();
		email.setEndereco(emailAleatorio().replaceAll("\\.(?!.*\\.).*", "." + apenasUmCaractere(nextInt(2, 6 + 1), "[_-]|[0-9]|" + INVALIDOS_EMAIL, "[a-z]")));
		return email;
	}

}
