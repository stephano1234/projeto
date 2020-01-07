package br.com.contmatic.repository;

import java.util.Collection;

public interface ReadOnlyRepository<E> {
	
	public E readByKeyField(Object keyFieldValue);
	
	public Collection<E> read(Object value, String fieldName);
	
	public Collection<E> readMany(Collection<?> values, String fieldName);
	
	public Collection<E> readAll();
		
}
