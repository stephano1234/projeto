package br.com.contmatic.repository;

import java.util.Collection;

public interface Repository<E> {

	public void create(E creatableObject);
		
	public void delete(Object keyFieldValue);
		
	public void update(Object keyFieldValue, E upToDateObject);
	
	public E readByKeyField(Object keyFieldValue);
	
	public Collection<E> read(Object value, String fieldName);
	
	public long count();
	
}
