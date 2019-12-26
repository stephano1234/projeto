package br.com.contmatic.repository;

import java.util.Collection;

public interface ConsultaRepository<T> {
		
	public Collection<T> findAll();
	
}
