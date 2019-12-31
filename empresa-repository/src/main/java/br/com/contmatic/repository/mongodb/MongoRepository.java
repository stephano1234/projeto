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

import br.com.contmatic.repository.Repository;

public abstract class MongoRepository<E> implements Repository<E> {

	private MongoClient conexao;
	
	private MongoCollection<Document> collection;

	private String collectionName;

	private String databaseName;

	private String keyFieldName;

	public MongoRepository(String databaseName, String collectionName, String keyFieldName) {
		this.databaseName = databaseName;
		this.collectionName = collectionName;
		this.keyFieldName = keyFieldName;
	}

	protected abstract E toObject(Document bson);
	
	protected abstract Document toBson(E object);
	
	private final void open() {
		conexao = new MongoClient(HOST_NAME, PORTA);
		MongoDatabase database = conexao.getDatabase(databaseName);
		this.collection = database.getCollection(collectionName);
	}

	@Override
	public final void create(E creatableObject) {
		this.open();
		try {
			this.collection.insertOne(this.toBson(creatableObject));
		} finally {
			this.close();
		}
	}
	
	@Override
	public final void delete(Object keyFieldValue) {
		this.open();
		try {
			this.collection.deleteOne(Filters.eq(this.keyFieldName, keyFieldValue));
		} finally {
			this.close();
		}		
	}	
	
	@Override
	public final void update(Object keyFieldValue, E upToDateObject) {
		this.open();
		try {
			this.collection.replaceOne(Filters.eq(this.keyFieldName, keyFieldValue), this.toBson(upToDateObject));
		} finally {
			this.close();
		}
	}

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
		MongoCursor<Document> cursor = collection.find(Filters.eq(fieldName, value)).iterator();
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
		
	@Override
	public final long count() {
		this.open();
		long quantidadeBson = collection.countDocuments();
		this.close();
		return quantidadeBson;
	}
	
	private final void close() {
		conexao.close();
	}

}
