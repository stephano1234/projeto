package br.com.contmatic.repository.mongo.empresa;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.repository.mongo.conversor.empresa.EmpresaConversorMongo;

public final class EmpresaMongoRepositoryImpl implements EmpresaMongoRepository {

	private static final String ID = "_id";

	private MongoCollection<Document> mongoCollection;

	private EmpresaConversorMongo empresaConversorMongo = EmpresaConversorMongo.getInstance();
	
	private static EmpresaMongoRepositoryImpl instance;
		
	public static EmpresaMongoRepositoryImpl getInstance(MongoDatabase mongoDatabase) {
		if (instance == null) {
			instance = new EmpresaMongoRepositoryImpl(mongoDatabase);
		}
		return instance;
	}
	
	private EmpresaMongoRepositoryImpl(MongoDatabase mongoDatabase) {
		this.mongoCollection = mongoDatabase.getCollection("empresa");
	}

	public static void closeRepository() {
		instance = null;
	}
	
	@Override
	public void create(Empresa empresa) {
		this.mongoCollection.insertOne(empresaConversorMongo.empresaToDocument(empresa));
	}

	@Override
	public void delete(String cnpj) {
		this.mongoCollection.deleteOne(Filters.eq(ID, cnpj));
	}

	@Override
	public void update(String cnpj, Empresa empresa) {
		this.mongoCollection.replaceOne(Filters.eq(ID, cnpj), empresaConversorMongo.empresaToDocument(empresa));
	}

	@Override
	public List<Empresa> findByParams(Document filter, Document sort, Document projection, int offset, int size) {
		List<Empresa> empresas = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find(filter)
				.projection(projection)
				.sort(sort)
				.skip(offset)
				.limit(size)
				.iterator();
		try {
			while (mongoCursor.hasNext()) {
				empresas.add(empresaConversorMongo.documentToEmpresa(mongoCursor.next()));
			}
		} finally {
			mongoCursor.close();
		}
		return empresas;
	}

	@Override
	public long countByParams(Document filter) {
		return this.mongoCollection.countDocuments(filter);
	}

}
