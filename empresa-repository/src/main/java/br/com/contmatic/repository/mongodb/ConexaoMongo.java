package br.com.contmatic.repository.mongodb;

import static br.com.contmatic.repository.mongodb.ConstantesMongo.HOST_NAME;
import static br.com.contmatic.repository.mongodb.ConstantesMongo.PORTA;

import com.mongodb.MongoClient;

public abstract class ConexaoMongo {
	
	private static ConexaoMongo conexao;
	
	private static MongoClient mongoClient;
		
	public ConexaoMongo() {
		conexao = null;
	}
	
	protected static final synchronized ConexaoMongo open() {
		if (conexao == null) {
			mongoClient = new MongoClient(HOST_NAME, PORTA);
		}
		return conexao;
	}
	
	protected final MongoClient getMongoClient() {
		return mongoClient;
	}
	
	protected static final synchronized void close() {
		mongoClient.close();
		conexao = null;
	}
}
