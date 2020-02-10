package br.com.contmatic.repository.mongo.empresa;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;

import br.com.contmatic.repository.mongo.conversor.empresa.EmpresaConversorMongo;

public final class EmpresaMongoRepositoryImpl implements EmpresaMongoRepository {

	private static final String FIELD_CNPJ = "cnpj";
	private static final String FIELD_RAZAO_SOCIAL = "razaoSocial";
	private static final String FIELD_RESPONSAVEIS = "responsaveis";
	private static final String FIELD_TIPO_EMPRESA = "tipoEmpresa";
	private static final String FIELD_TIPO_PORTE_EMPRESA = "tipoPorteEmpresa";

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
		this.mongoCollection.deleteOne(Filters.eq(FIELD_CNPJ, cnpj));
	}

	@Override
	public void update(String cnpj, Empresa empresa) {
		this.mongoCollection.replaceOne(Filters.eq(FIELD_CNPJ, cnpj), empresaConversorMongo.empresaToDocument(empresa));
	}

	@Override
	public Empresa readByCnpj(String cnpj) {
		return empresaConversorMongo.documentToEmpresa(this.mongoCollection
				.find(Filters.eq(FIELD_CNPJ, cnpj))
				.projection(Projections.fields(Projections.excludeId()))
				.first());
	}

	@Override
	public List<Empresa> readCnpjAndRazaoSocialByRazaoSocial(String razaoSocial) {
		List<Empresa> empresas = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find(Filters.eq(FIELD_RAZAO_SOCIAL, razaoSocial))
				.projection(Projections.fields(Projections.include(FIELD_CNPJ, FIELD_RAZAO_SOCIAL), Projections.excludeId()))
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
	public List<Empresa> readCnpjAndRazaoSocialByResponsavelCpf(String cpf) {
		List<Empresa> empresas = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find(Filters.eq(FIELD_RESPONSAVEIS + ".cpf", cpf))
				.projection(Projections.fields(Projections.include(FIELD_CNPJ, FIELD_RAZAO_SOCIAL), Projections.excludeId()))
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
	public List<Empresa> readCnpjAndRazaoSocialByTipoEmpresa(TipoEmpresa tipoEmpresa) {
		List<Empresa> empresas = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find(Filters.eq(FIELD_TIPO_EMPRESA, tipoEmpresa.name()))
				.projection(Projections.fields(Projections.include(FIELD_CNPJ, FIELD_RAZAO_SOCIAL), Projections.excludeId()))
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
	public List<Empresa> readCnpjAndRazaoSocialByTipoPorteEmpresa(TipoPorteEmpresa tipoPorteEmpresa) {
		List<Empresa> empresas = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find(Filters.eq(FIELD_TIPO_PORTE_EMPRESA, tipoPorteEmpresa.name()))
				.projection(Projections.fields(Projections.include(FIELD_CNPJ, FIELD_RAZAO_SOCIAL), Projections.excludeId()))
				.iterator();
		try {
			while (mongoCursor.hasNext()) {
				empresas.add(empresaConversorMongo.documentToEmpresa(mongoCursor.next()));			}
		} finally {
			mongoCursor.close();
		}
		return empresas;
	}

	@Override
	public List<Empresa> readAllCnpjAndRazaoSocial(int pageSize) {
		List<Empresa> empresas = new ArrayList<>();
		MongoCursor<Document> mongoCursor = this.mongoCollection
				.find()
				.projection(Projections.fields(Projections.include(FIELD_CNPJ, FIELD_RAZAO_SOCIAL), Projections.excludeId()))
				.limit(pageSize)
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
	public long countAll() {
		return this.mongoCollection.countDocuments();
	}

}
