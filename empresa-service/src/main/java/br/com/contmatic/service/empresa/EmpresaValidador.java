package br.com.contmatic.service.empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;

public class EmpresaValidador {

	private static final Logger logger = Logger.getLogger(EmpresaValidador.class.getName());
	
	private static EmpresaValidador instance;

	private EmpresaValidador() {
	}
	
	public static EmpresaValidador getInstance() {
		if (instance == null) {
			instance = new EmpresaValidador();
		}
		return instance;
	}
	
	public List<String> procuraViolacoesPost(Empresa empresa) {
		return procuraViolacoes(empresa, Post.class);		
	}	
	
	public List<String> procuraViolacoesPut(Empresa empresa) {
		return procuraViolacoes(empresa, Put.class);		
	}	
	
	private List<String> procuraViolacoes(Empresa empresa, Class<?> groupClass) {
		List<String> mensagensErro = new ArrayList<>();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Empresa>> violacoes = validator.validate(empresa, groupClass);
		for (ConstraintViolation<Empresa> violacao : violacoes) {
			logger.log(Level.INFO, violacao.getMessage());
			mensagensErro.add(violacao.getMessage());
		}
		return mensagensErro;
	}

}
