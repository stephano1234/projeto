package br.com.contmatic.repository.mongodb;

import java.util.Collection;
import java.util.HashSet;

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
	
	private String hostName;
	
	private int porta;

	protected MongoReadOnlyRepository(String databaseName, String collectionName, String keyFieldName, String projectedFieldName, String hostName, int porta) {
		this.databaseName = databaseName;
		this.collectionName = collectionName;
		this.keyFieldName = keyFieldName;
		this.projectedFieldName = projectedFieldName;
		this.hostName = hostName;
		this.porta = porta;
	}
		
	private final void open() {
		conexao = new MongoClient(this.hostName, this.porta);
		MongoDatabase database = conexao.getDatabase(databaseName);
		this.collection = database.getCollection(collectionName);
	}

	protected abstract E toObject(Document bson);

	@Override
	public final E readByKeyField(Object keyFieldValue) {
		this.open();
		try {
			return this.toObject(collection.find(Filters.eq(this.keyFieldName, keyFieldValue)).projection(Projections.include(this.projectedFieldName)).first());
		} finally {
			this.close();
		}
	}

	@Override
	public final Collection<E> read(Object value, String fieldName) {
		Collection<E> readableObjects = new HashSet<>();
		this.open();
		MongoCursor<Document> cursor = collection.find(Filters.eq(fieldName, value)).projection(Projections.include(this.projectedFieldName)).iterator();
		try {
			while (cursor.hasNext()) {
				readableObjects.add(this.toObject(cursor.next()));
			}
		} finally {
			cursor.close();
			this.close();
		}
		return readableObjects;
	}

	@Override
	public final Collection<E> readMany(Collection<?> values, String fieldName) {
		Collection<E> readableObjects = new HashSet<>();
		this.open();
		try {
			for (Object value : values) {
				MongoCursor<Document> cursor = collection.find(Filters.eq(fieldName, value)).projection(Projections.include(this.projectedFieldName)).iterator();
				try {
					while (cursor.hasNext()) {
						readableObjects.add(this.toObject(cursor.next()));
					}
				} finally {
					cursor.close();
				}
			}
		} finally {
			this.close();
		}
		return readableObjects;
	}

	@Override
	public final Collection<E> readAll() {
		Collection<E> readableObjects = new HashSet<>();
		this.open();
		MongoCursor<Document> cursor = collection.find().projection(Projections.include(this.projectedFieldName)).iterator();
		try {
			while (cursor.hasNext()) {
				readableObjects.add(this.toObject(cursor.next()));
			}
		} finally {
			cursor.close();
			this.close();
		}
		return readableObjects;		
	}

	private final void close() {
		conexao.close();
	}

}
