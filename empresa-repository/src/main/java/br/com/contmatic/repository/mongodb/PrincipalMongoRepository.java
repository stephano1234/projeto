package br.com.contmatic.repository.mongodb;

import java.util.Collection;
import java.util.HashSet;

import org.bson.Document;

import br.com.contmatic.repository.PrincipalRepository;
import br.com.contmatic.repository.RepositoryFilter;

public abstract class PrincipalMongoRepository<T> extends ConsultaMongoRepository<T> implements PrincipalRepository<T> {

	public PrincipalMongoRepository(String database, String collection) {
		super(database, collection);		
	}

	@Override
	public final void create(T objeto) {
		super.collection.insertOne(Document.parse(objeto.toString()));
	}
	
	@Override
	public final void delete(T objeto) {
		super.collection.deleteOne(Document.parse(objeto.toString()));
	}
	
	@Override
	public final Collection<T> query(RepositoryFilter<T> filter) {
		Collection<T> objetosFiltrados = new HashSet<>();
		for (T objeto : super.findAll()) {
			if (filter.filtered(objeto)) {
				objetosFiltrados.add(objeto);
			}
		}
		return objetosFiltrados;
	}

	@Override
	public final long count() {
		return super.collection.countDocuments();
	}

}
