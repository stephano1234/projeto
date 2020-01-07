package br.com.contmatic.repository.mongodb;

import java.util.Collection;
import java.util.HashSet;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.contmatic.repository.Repository;

public abstract class MongoRepository<E> implements Repository<E> {

	private static MongoClient mongoClient = null;

	private MongoCollection<Document> collection;

	private String collectionName;

	private String databaseName;

	private String keyFieldName;

	private String hostName;

	private int porta;

	protected MongoRepository(String databaseName, String collectionName, String keyFieldName, String hostName,
			int porta) {
		this.databaseName = databaseName;
		this.collectionName = collectionName;
		this.keyFieldName = keyFieldName;
		this.hostName = hostName;
		this.porta = porta;
	}

	private static MongoClient getMongoClient(String hostName, int porta) {
		if (mongoClient == null) {
			mongoClient = new MongoClient(hostName, porta);
		}
		return mongoClient;
	}

	private final void open() {
		MongoDatabase database = getMongoClient(this.hostName, this.porta).getDatabase(databaseName);
		this.collection = database.getCollection(collectionName);
	}

	protected abstract E toObject(Document bson);

	@Override
	public final void create(E creatableObject) {
		this.open();
		try {
			this.collection.insertOne(Document.parse(creatableObject.toString()));
		} finally {
			close();
		}
	}

	@Override
	public final void delete(Object keyFieldValue) {
		this.open();
		try {
			this.collection.deleteOne(Filters.eq(this.keyFieldName, keyFieldValue));
		} finally {
			close();
		}
	}

	@Override
	public final void update(Object keyFieldValue, E upToDateObject) {
		this.open();
		try {
			this.collection.replaceOne(Filters.eq(this.keyFieldName, keyFieldValue),
					Document.parse(upToDateObject.toString()));
		} finally {
			close();
		}
	}

	@Override
	public final E readByKeyField(Object keyFieldValue) {
		this.open();
		try {
			return this.toObject(collection.find(Filters.eq(this.keyFieldName, keyFieldValue)).first());
		} finally {
			close();
		}
	}

	@Override
	public final Collection<E> read(Object value, String fieldName) {
		Collection<E> readableObjects = new HashSet<>();
		this.open();
		try {
			MongoCursor<Document> cursor = collection.find(Filters.eq(fieldName, value)).iterator();
			try {
				while (cursor.hasNext()) {
					readableObjects.add(this.toObject(cursor.next()));
				}
			} finally {
				cursor.close();
			}
			return readableObjects;
		} finally {
			close();
		}
	}

	@Override
	public final Collection<E> readMany(Collection<?> values, String fieldName) {
		Collection<E> readableObjects = new HashSet<>();
		this.open();
		try {
			for (Object value : values) {
				MongoCursor<Document> cursor = collection.find(Filters.eq(fieldName, value)).iterator();
				try {
					while (cursor.hasNext()) {
						readableObjects.add(this.toObject(cursor.next()));
					}
				} finally {
					cursor.close();
				}
			}
			return readableObjects;
		} finally {
			close();
		}
	}

	@Override
	public final Collection<E> readAll() {
		Collection<E> readableObjects = new HashSet<>();
		this.open();
		try {
			MongoCursor<Document> cursor = collection.find().iterator();
			try {
				while (cursor.hasNext()) {
					readableObjects.add(this.toObject(cursor.next()));
				}
			} finally {
				cursor.close();

			}
			return readableObjects;
		} finally {
			close();
		}

	}

	@Override
	public final long count() {
		this.open();
		try {
			return collection.countDocuments();
		} finally {
			close();
		}
	}

	private static final void close() {
		if (mongoClient != null) {
			mongoClient.close();
			mongoClient = null;
		}

	}

}
