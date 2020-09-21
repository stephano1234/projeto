package br.com.contmatic.service.empresa;

import java.util.List;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.repository.empresa.EmpresaRepository;
import br.com.contmatic.repository.empresa.EmpresaRepositoryImpl;
import br.com.contmatic.service.mensagens.MensagemServidor;
import br.com.contmatic.service.parametros.FindParams;

public class EmpresaService {

	private static final String PUT_SUCESSO = "Empresa alterada com sucesso.";

	private static final String DELETE_SUCESSO = "Empresa apagada com sucesso.";

	private static final String POST_SUCESSO = "Empresa salva com sucesso.";

	private static final String CHAVE_PRIMARIA_INEXISTENTE = "Não existe empresa cadastrada com esse C.N.P.J. no banco de dados.";
	
	private static final String CHAVE_PRIMARIA_DUPLICADA = "Já existe uma empresa armazenada com o mesmo C.N.P.J. no banco de dados.";

	private EmpresaRepository empresaMongoRepository;

	private static EmpresaService instance;

	private EmpresaService(EmpresaRepository empresaMongoRepository) {
		this.empresaMongoRepository = empresaMongoRepository;
	}

	public static EmpresaService getInstance() {
		if (instance == null) {
			instance = new EmpresaService(EmpresaRepositoryImpl.getInstance());
		}
		return instance;
	}

	public static EmpresaService getInstance(EmpresaRepository empresaMongoRepository) {
		if (instance == null) {
			instance = new EmpresaService(empresaMongoRepository);
		}
		return instance;
	}

	public Empresa findByCnpj(String cnpj) {
		return empresaMongoRepository.findByCnpj(cnpj);
	}
	
	public List<Empresa> findByParams(FindParams params) {
		return empresaMongoRepository.findByParams(params.getFilter(), params.getSort(), params.getProjection(),
				params.getPageOffset(), params.getPageSize());
	}

	public String countByParams(FindParams params) {
		return params.getToCount() ? Long.toString(empresaMongoRepository.countByParams(params.getFilter())) : null;
	}

	public MensagemServidor update(Empresa empresa) {
		List<String> mensagensErro = EmpresaValidador.getInstance().procuraViolacoesPut(empresa);
		if (mensagensErro.isEmpty()) {
			try {
				if (empresaMongoRepository.isExistent(empresa.getCnpj())) {
					empresaMongoRepository.update(empresa.getCnpj(), empresa);
					return new MensagemServidor(200, PUT_SUCESSO);					
				}
				return this.sendNotFoundResponse();
			} catch (Exception e) {
				return new MensagemServidor(500, e.toString());
			}
		} else {
			return new MensagemServidor(422, mensagensErro);
		}
	}

	public MensagemServidor delete(String cnpj) {
		try {
			if (empresaMongoRepository.isExistent(cnpj)) {
				empresaMongoRepository.delete(cnpj);
				return new MensagemServidor(200, DELETE_SUCESSO);				
			}
			return this.sendNotFoundResponse();
		} catch (Exception e) {
			return new MensagemServidor(500, e.toString());
		}
	}

	public MensagemServidor create(Empresa empresa) {
		List<String> mensagensErro = EmpresaValidador.getInstance().procuraViolacoesPost(empresa);
		if (mensagensErro.isEmpty()) {
			try {
				if (!empresaMongoRepository.isExistent(empresa.getCnpj())) {
					empresaMongoRepository.create(empresa);
					return new MensagemServidor(201, POST_SUCESSO);					
				}
				return new MensagemServidor(409, CHAVE_PRIMARIA_DUPLICADA);
			} catch (Exception e) {
				return new MensagemServidor(500, e.toString());
			}
		} else {
			return new MensagemServidor(422, mensagensErro);
		}
	}

	public MensagemServidor sendNotFoundResponse() {
		return new MensagemServidor(404, CHAVE_PRIMARIA_INEXISTENTE);
	}
	
}
