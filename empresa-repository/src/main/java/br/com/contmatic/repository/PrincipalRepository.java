package br.com.contmatic.repository;

import java.util.Collection;

public interface PrincipalRepository<T> extends ConsultaRepository<T> {

	public void create(T objeto);
	
	public default void create(Collection<T> objetos) {
		objetos.forEach(this::create);
	}

	public void delete(T objeto);
	
	public default void delete(Collection<T> objetos) {
		objetos.forEach(this::delete);
	}
	
	public Collection<T> query(RepositoryFilter<T> filter);
	
	public long count();
		
}
