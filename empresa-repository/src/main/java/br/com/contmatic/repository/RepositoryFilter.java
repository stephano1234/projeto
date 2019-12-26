package br.com.contmatic.repository;

public interface RepositoryFilter<T> {

	boolean filtered(T objeto);
	
}
