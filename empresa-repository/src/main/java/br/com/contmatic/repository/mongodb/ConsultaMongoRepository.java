package br.com.contmatic.repository.mongodb;

import java.lang.reflect.ParameterizedType;

import java.util.Collection;
import java.util.HashSet;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.repository.ConsultaRepository;

public abstract class ConsultaMongoRepository<T> implements ConsultaRepository<T> {

	private Class<T> type;

	private MongoLeitorDados<T> leitor;
	
	protected MongoDatabase database;

	protected MongoCollection<Document> collection;

	public ConsultaMongoRepository(String database, String collection) {
		getType();
		leitor = new MongoLeitorDados<>();
		ConexaoMongo.getInstance();
		this.database = ConexaoMongo.getInstance().getMongoClient().getDatabase(database);
		this.collection = this.database.getCollection(collection);
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public final Collection<T> findAll() {
		Collection<T> todosObjetos = new HashSet<>();
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				todosObjetos.add((T) leitor.instanciador(cursor.next(), this.type));
			}
		} finally {
			cursor.close();
		}
		return todosObjetos;
	}

	public final void close() {
		ConexaoMongo.getInstance().getMongoClient().close();
	}

	@SuppressWarnings("unchecked")
	private void getType() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
