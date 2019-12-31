package br.com.contmatic.repository.mongodb;

import static br.com.contmatic.repository.mongodb.ConstantesMongo.HOST_NAME;
import static br.com.contmatic.repository.mongodb.ConstantesMongo.PORTA;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import br.com.contmatic.repository.ReadOnlyRepository;

public abstract class MongoReadOnlyRepository<E> implements ReadOnlyRepository<E> {

	private MongoClient conexao;
	
	private MongoCollection<Document> collection;

	private String collectionName;

	private String databaseName;

	private String keyFieldName;
	
	private String projectedFieldName;

	public MongoReadOnlyRepository(String databaseName, String collectionName, String keyFieldName, String projectedFieldName) {
		this.databaseName = databaseName;
		this.collectionName = collectionName;
		this.keyFieldName = keyFieldName;
		this.projectedFieldName = projectedFieldName;
	}
		
	private final void open() {
		conexao = new MongoClient(HOST_NAME, PORTA);
		MongoDatabase database = conexao.getDatabase(databaseName);
		this.collection = database.getCollection(collectionName);
	}

	protected abstract E toObject(Document bson);

	protected abstract Document toBson(E object);

	@Override
	public final E readByKeyField(Object keyFieldValue) {
		this.open();
		try {
			return this.toObject(collection.find(Filters.eq(this.keyFieldName, keyFieldValue)).first());
		} finally {
			this.close();
		}
	}
		
	@Override
	public final Collection<E> read(Object value, String fieldName) {
		Collection<E> readableObject = new ArrayList<>();
		this.open();
		MongoCursor<Document> cursor = collection.find(Filters.eq(fieldName, value)).projection(Projections.include(this.projectedFieldName)).iterator();
		try {
			while (cursor.hasNext()) {
				readableObject.add(this.toObject(cursor.next()));
			}
		} finally {
			cursor.close();
			this.close();
		}
		return readableObject;
	}

	private final void close() {
		conexao.close();
	}

}
