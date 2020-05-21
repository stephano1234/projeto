package br.com.contmatic.repository.configuracao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public final class MongoConnection {

	private static final String HOST_NAME = "localhost";
	
	private static final int PORTA = 27017;
	
	private static final String DATABASE_NAME = "projeto";
	
	private static MongoConnection instance;
	
	private MongoClient mongoClient;
	
	private MongoDatabase mongoDatabase;
	
	private MongoConnection() {
	}
	
	public static synchronized MongoConnection getInstance() {
		if (instance == null) {
			instance = new MongoConnection();
		}
		return instance;
	}
	
	public MongoClient getMongoClient() {
		if (this.mongoClient == null) {
			this.mongoClient = new MongoClient(HOST_NAME, PORTA);
		}
		return this.mongoClient;
	}
	
	public MongoDatabase getMongoDatabase() {
		if (this.mongoDatabase == null) {
			this.mongoDatabase = this.getMongoClient().getDatabase(DATABASE_NAME);
		}
		return this.mongoDatabase;
	}
	
	public void close() {
		if (this.mongoClient != null) {
			this.mongoClient.close();
			this.mongoClient = null;
			this.mongoDatabase = null;
		}
	}
	
}
