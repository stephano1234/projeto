package br.com.contmatic.repository.mongo.conversor.empresa;

import org.bson.Document;
import org.joda.time.LocalDate;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;
import br.com.contmatic.repository.mongo.conversor.conta.ContaConversorMongo;
import br.com.contmatic.repository.mongo.conversor.contato.CelularConversorMongo;
import br.com.contmatic.repository.mongo.conversor.contato.EmailConversorMongo;
import br.com.contmatic.repository.mongo.conversor.contato.TelefoneFixoConversorMongo;
import br.com.contmatic.repository.mongo.conversor.endereco.EnderecoConversorMongo;
import br.com.contmatic.repository.mongo.conversor.pessoa.ContratoTrabalhoConversorMongo;
import br.com.contmatic.repository.mongo.conversor.pessoa.PessoaConversorMongo;

public class EmpresaConversorMongo {

	private static final String FIELD_CNPJ = "cnpj";
	private static final String FIELD_TIPO_PORTE_EMPRESA = "tipoPorteEmpresa";
	private static final String FIELD_TIPO_EMPRESA = "tipoEmpresa";
	private static final String FIELD_CONTAS = "contas";
	private static final String FIELD_CELULARES = "celulares";
	private static final String FIELD_EMAILS = "emails";
	private static final String FIELD_TELEFONES_FIXO = "telefonesFixo";
	private static final String FIELD_ENDERECOS = "enderecos";
	private static final String FIELD_CONTRATOS_TRABALHO = "contratosTrabalho";
	private static final String FIELD_RESPONSAVEIS = "responsaveis";
	private static final String FIELD_DATA_ABERTURA = "dataAbertura";
	private static final String FIELD_RAZAO_SOCIAL = "razaoSocial";

	private PessoaConversorMongo pessoaConversorMongo = new PessoaConversorMongo();

	private ContratoTrabalhoConversorMongo contratoTrabalhoConversorMongo = new ContratoTrabalhoConversorMongo();
	
	private EnderecoConversorMongo enderecoConversorMongo = new EnderecoConversorMongo();
	
	private CelularConversorMongo celularConversorMongo = new CelularConversorMongo();
	
	private EmailConversorMongo emailConversorMongo = new EmailConversorMongo();
	
	private TelefoneFixoConversorMongo telefoneFixoConversorMongo = new TelefoneFixoConversorMongo();
	
	private ContaConversorMongo contaConversorMongo = new ContaConversorMongo();

	private static EmpresaConversorMongo instance;
	
	private EmpresaConversorMongo() {
		
	}
	
	public static EmpresaConversorMongo getInstance() {
		if (instance == null) {
			instance = new EmpresaConversorMongo();
		}
		return instance;
	}
	
	public static void closeConversor() {
		instance = null;
	}
	
	public Document empresaToDocument(Empresa empresa) {
		if (empresa == null) {
			return null;
		}
		final Document docEmpresa = new Document();
		docEmpresa.append(FIELD_CNPJ, null);
		docEmpresa.append(FIELD_RAZAO_SOCIAL, null);
		docEmpresa.append(FIELD_DATA_ABERTURA, null);
		docEmpresa.append(FIELD_RESPONSAVEIS, null);
		docEmpresa.append(FIELD_CONTRATOS_TRABALHO, null);
		docEmpresa.append(FIELD_ENDERECOS, null);
		docEmpresa.append(FIELD_TELEFONES_FIXO, null);
		docEmpresa.append(FIELD_EMAILS, null);
		docEmpresa.append(FIELD_CELULARES, null);
		docEmpresa.append(FIELD_CONTAS, null);
		docEmpresa.append(FIELD_TIPO_EMPRESA, null);
		docEmpresa.append(FIELD_TIPO_PORTE_EMPRESA, null);
		if (empresa.getCnpj() != null) {
			docEmpresa.put(FIELD_CNPJ, empresa.getCnpj());
		}
		if (empresa.getRazaoSocial() != null) {
			docEmpresa.put(FIELD_RAZAO_SOCIAL, empresa.getRazaoSocial());
		}
		if (empresa.getDataAbertura() != null) {
			docEmpresa.put(FIELD_DATA_ABERTURA, empresa.getDataAbertura().toString());
		}
		docEmpresa.put(FIELD_RESPONSAVEIS, pessoaConversorMongo.pessoasToDocuments(empresa.getResponsaveis()));
		docEmpresa.put(FIELD_CONTRATOS_TRABALHO, contratoTrabalhoConversorMongo.contratoTrabalhosToDocuments(empresa.getContratosTrabalho()));
		docEmpresa.put(FIELD_ENDERECOS, enderecoConversorMongo.enderecosToDocuments(empresa.getEnderecos()));
		docEmpresa.put(FIELD_TELEFONES_FIXO, telefoneFixoConversorMongo.telefonesFixoToDocuments(empresa.getTelefonesFixo()));
		docEmpresa.put(FIELD_EMAILS, emailConversorMongo.emailsToDocuments(empresa.getEmails()));
		docEmpresa.put(FIELD_CELULARES, celularConversorMongo.celularsToDocuments(empresa.getCelulares()));
		docEmpresa.put(FIELD_CONTAS, contaConversorMongo.contasToDocuments(empresa.getContas()));
		if (empresa.getTipoEmpresa() != null) {
			docEmpresa.put(FIELD_TIPO_EMPRESA, empresa.getTipoEmpresa().name());
		}
		if (empresa.getTipoPorteEmpresa() != null) {
			docEmpresa.put(FIELD_TIPO_PORTE_EMPRESA, empresa.getTipoPorteEmpresa().name());
		}
		return docEmpresa;
	}
	
	public Empresa documentToEmpresa(Document docEmpresa) {
		if (docEmpresa == null) {
			return null;
		}
		Empresa empresa = new Empresa();
		if (docEmpresa.get(FIELD_CNPJ, String.class) != null) {
			empresa.setCnpj(docEmpresa.get(FIELD_CNPJ, String.class));			
		}
		if (docEmpresa.get(FIELD_RAZAO_SOCIAL, String.class) != null) {
			empresa.setRazaoSocial(docEmpresa.get(FIELD_RAZAO_SOCIAL, String.class));			
		}
		if (docEmpresa.get(FIELD_DATA_ABERTURA, String.class) != null) {
			empresa.setDataAbertura(LocalDate.parse(docEmpresa.get(FIELD_DATA_ABERTURA, String.class)));
		}
		empresa.setResponsaveis(pessoaConversorMongo.documentsToPessoas(docEmpresa.getList(FIELD_RESPONSAVEIS, Document.class)));
		empresa.setContratosTrabalho(contratoTrabalhoConversorMongo.documentsToContratoTrabalhos(docEmpresa.getList(FIELD_CONTRATOS_TRABALHO, Document.class)));
		empresa.setEnderecos(enderecoConversorMongo.documentsToEnderecos(docEmpresa.getList(FIELD_ENDERECOS, Document.class)));
		empresa.setTelefonesFixo(telefoneFixoConversorMongo.documentsToTelefonesFixo(docEmpresa.getList(FIELD_TELEFONES_FIXO, Document.class)));
		empresa.setEmails(emailConversorMongo.documentsToEmails(docEmpresa.getList(FIELD_EMAILS, Document.class)));
		empresa.setCelulares(celularConversorMongo.documentsToCelulars(docEmpresa.getList(FIELD_CELULARES, Document.class)));
		empresa.setContas(contaConversorMongo.documentsToContas(docEmpresa.getList(FIELD_CONTAS, Document.class)));
		if (docEmpresa.get(FIELD_TIPO_EMPRESA, String.class) != null) {
			empresa.setTipoEmpresa(TipoEmpresa.valueOf(docEmpresa.get(FIELD_TIPO_EMPRESA, String.class)));			
		}
		if (docEmpresa.get(FIELD_TIPO_PORTE_EMPRESA, String.class) != null) {
			empresa.setTipoPorteEmpresa(TipoPorteEmpresa.valueOf(docEmpresa.get(FIELD_TIPO_PORTE_EMPRESA, String.class)));			
		}
		return empresa;
	}
	
}
