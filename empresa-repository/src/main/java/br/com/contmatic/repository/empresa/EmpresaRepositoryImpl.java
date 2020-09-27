package br.com.contmatic.repository.empresa;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.repository.configuracao.MongoConnection;
import br.com.contmatic.repository.empresa.conversores.EmpresaConversor;

public final class EmpresaRepositoryImpl implements EmpresaRepository {

	private static final String COLLECTION_EMPRESA = "empresa";

	private static final String ID = "_id";

	private MongoCollection<Document> mongoCollection;

	private static EmpresaRepositoryImpl instance;

	public static EmpresaRepositoryImpl getInstance() {
		if (instance == null) {
			instance = new EmpresaRepositoryImpl(MongoConnection.getInstance().getMongoDatabase());
		}
		return instance;
	}

	public static EmpresaRepositoryImpl getInstance(MongoDatabase mongoDatabase) {
		if (instance == null) {
			instance = new EmpresaRepositoryImpl(mongoDatabase);
		}
		return instance;
	}
	
	private EmpresaRepositoryImpl(MongoDatabase mongoDatabase) {
		this.mongoCollection = mongoDatabase.getCollection(COLLECTION_EMPRESA);
	}

	public static void closeRepository() {
		instance = null;
	}
	
	@Override
	public void create(Empresa empresa) {
		this.mongoCollection.insertOne(EmpresaConversor.getInstance().empresaToDocument(empresa));
	}

	@Override
	public void delete(String cnpj) {
		this.mongoCollection.deleteOne(Filters.eq(ID, cnpj));
	}

	@Override
	public void update(String cnpj, Empresa empresa) {
		this.mongoCollection.replaceOne(Filters.eq(ID, cnpj), EmpresaConversor.getInstance().empresaToDocument(empresa));
	}

	@Override
	public Empresa findByCnpj(String cnpj) {
		return EmpresaConversor.getInstance().documentToEmpresa(this.mongoCollection.find(Filters.eq(ID, cnpj)).first());
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
				empresas.add(EmpresaConversor.getInstance().documentToEmpresa(mongoCursor.next()));
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

	@Override
	public boolean isExistent(String cnpj) {
		return this.mongoCollection.find(Filters.eq(ID, cnpj)).first() != null;
	}
	
}
