package br.com.contmatic.service.empresa;

import java.util.List;

import com.mongodb.MongoWriteException;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.repository.configuracao.mongodb.MongoConnection;
import br.com.contmatic.repository.mongo.empresa.EmpresaMongoRepository;
import br.com.contmatic.repository.mongo.empresa.EmpresaMongoRepositoryImpl;
import br.com.contmatic.service.mensagens.MensagemServidor;
import br.com.contmatic.service.parametros.FindParams;

public class EmpresaService {

	private static final String PUT_SUCESSO = "Empresa alterada com sucesso.";

	private static final String DELETE_SUCESSO = "Empresa apagada com sucesso.";

	private static final String POST_SUCESSO = "Empresa salva com sucesso.";

	private static final String CHAVE_PRIMARIA_DUPLICADA = "Já existe uma empresa armazenada com o mesmo C.N.P.J. no banco de dados.";

	private static final EmpresaValidador empresaValidador = EmpresaValidador.getInstance();

	private EmpresaMongoRepository empresaMongoRepository = EmpresaMongoRepositoryImpl
			.getInstance(MongoConnection.getInstance().getMongoDatabase());

	private static EmpresaService instance;

	private EmpresaService() {
	}

	public static EmpresaService getInstance() {
		if (instance == null) {
			instance = new EmpresaService();
		}
		return instance;
	}

	public List<Empresa> findByParams(FindParams params) {
		return empresaMongoRepository.findByParams(params.getFilter(), params.getSort(), params.getProjection(),
				params.getPageOffset(), params.getPageSize());
	}

	public String countByParams(FindParams params) {
		return params.getToCount() ? Long.toString(empresaMongoRepository.countByParams(params.getFilter())) : null;
	}

	public MensagemServidor update(Empresa empresa) {
		List<String> mensagensErro = empresaValidador.procuraViolacoesPut(empresa);
		if (mensagensErro.isEmpty()) {
			try {
				empresaMongoRepository.update(empresa.getCnpj(), empresa);
				return new MensagemServidor(200, PUT_SUCESSO);
			} catch (Exception e) {
				return new MensagemServidor(500, e.toString());
			}
		} else {
			return new MensagemServidor(422, mensagensErro);
		}
	}

	public MensagemServidor delete(Empresa empresa) {
		try {
			empresaMongoRepository.delete(empresa.getCnpj());
			return new MensagemServidor(200, DELETE_SUCESSO);
		} catch (Exception e) {
			return new MensagemServidor(500, e.toString());
		}
	}

	public MensagemServidor create(Empresa empresa) {
		List<String> mensagensErro = empresaValidador.procuraViolacoesPost(empresa);
		if (mensagensErro.isEmpty()) {
			try {
				empresaMongoRepository.create(empresa);
				return new MensagemServidor(200, POST_SUCESSO);
			} catch (MongoWriteException e) {
				if (e.getError().getCode() == 11000) {
					return new MensagemServidor(422, CHAVE_PRIMARIA_DUPLICADA);
				}
				return new MensagemServidor(500, e.toString());
			} catch (Exception e) {
				return new MensagemServidor(500, e.toString());
			}
		} else {
			return new MensagemServidor(422, mensagensErro);
		}
	}

}
