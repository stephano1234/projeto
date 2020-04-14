package br.com.contmatic.service.empresa;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.restricoes.grupos.Delete;
import br.com.contmatic.model.restricoes.grupos.Get;
import br.com.contmatic.model.restricoes.grupos.Post;
import br.com.contmatic.model.restricoes.grupos.Put;
import br.com.contmatic.repository.mongo.conversor.empresa.EmpresaConversorMongo;
import br.com.contmatic.service.parametros.FindParams;

public class EmpresaValidador {

	private static final Logger logger = Logger.getLogger(EmpresaValidador.class.getName());
	
	private EmpresaConversorMongo empresaConversorMongo = EmpresaConversorMongo.getInstance();
	
	private static EmpresaValidador instance;

	private EmpresaValidador() {
	}
	
	public static EmpresaValidador getInstance() {
		if (instance == null) {
			instance = new EmpresaValidador();
		}
		return instance;
	}
	
	public boolean validaPost(Empresa empresa) {
		return valida(empresa, Put.class);
	}

	public boolean validaPut(Empresa empresa) {
		return valida(empresa, Put.class);
	}

	public boolean validaDelete(Empresa empresa) {
		return valida(empresa, Delete.class);		
	}

	public boolean validaGet(FindParams findParams) {
		return valida(empresaConversorMongo.documentToEmpresa(findParams.getFilter()), Get.class);
	}

	public boolean valida(Empresa empresa, Class<?> groupClass) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Empresa>> violacoes = validator.validate(empresa, groupClass);
		return violacoes.isEmpty();		
	}

	public Set<String> procuraViolacoesGet(FindParams findParams) {
		return procuraViolacoes(empresaConversorMongo.documentToEmpresa(findParams.getFilter()), Get.class);		
	}	
	
	public Set<String> procuraViolacoesPost(Empresa empresa) {
		return procuraViolacoes(empresa, Post.class);		
	}	
	
	public Set<String> procuraViolacoesPut(Empresa empresa) {
		return procuraViolacoes(empresa, Put.class);		
	}	
	
	public Set<String> procuraViolacoesDelete(Empresa empresa) {
		return procuraViolacoes(empresa, Delete.class);		
	}	

	private Set<String> procuraViolacoes(Empresa empresa, Class<?> groupClass) {
		Set<String> mensagensErro = new HashSet<>();
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
