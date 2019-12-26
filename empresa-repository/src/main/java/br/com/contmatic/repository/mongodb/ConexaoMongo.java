package br.com.contmatic.repository.mongodb;

import static br.com.contmatic.repository.mongodb.ConstantesMongo.HOST_NAME;
import static br.com.contmatic.repository.mongodb.ConstantesMongo.PORTA;

import com.mongodb.MongoClient;

public final class ConexaoMongo {
	
	private static ConexaoMongo instance;
	
	private MongoClient mongoClient;
	
	private ConexaoMongo() {	
		this.mongoClient = new MongoClient(HOST_NAME, PORTA);
	}
	
	public static synchronized ConexaoMongo getInstance() {
		if (instance == null) {
			instance = new ConexaoMongo();
		}
		return instance;
	}
	
	public MongoClient getMongoClient() {
		return this.mongoClient;
	}
	
}
