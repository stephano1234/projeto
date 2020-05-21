package br.com.contmatic.repository.empresa.conversores;

import java.util.ArrayList;

import org.bson.Document;
import org.joda.time.LocalDate;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.model.empresa.TipoEmpresa;
import br.com.contmatic.model.empresa.TipoPorteEmpresa;

public class EmpresaConversor {

	private static final String ID = "_id";
	private static final String FIELD_TIPO_PORTE_EMPRESA = "tipoPorteEmpresa";
	private static final String FIELD_TIPO_EMPRESA = "tipoEmpresa";
	private static final String FIELD_CONTAS = "contas";
	private static final String FIELD_CELULARES = "celulares";
	private static final String FIELD_EMAILS = "emails";
	private static final String FIELD_TELEFONES_FIXO = "telefonesFixo";
	private static final String FIELD_ENDERECOS = "enderecos";
	private static final String FIELD_RESPONSAVEIS = "responsaveis";
	private static final String FIELD_DATA_ABERTURA = "dataAbertura";
	private static final String FIELD_RAZAO_SOCIAL = "razaoSocial";

	private PessoaConversor pessoaConversorMongo = new PessoaConversor();

	private EnderecoConversor enderecoConversorMongo = new EnderecoConversor();
	
	private CelularConversor celularConversorMongo = new CelularConversor();
	
	private EmailConversor emailConversorMongo = new EmailConversor();
	
	private TelefoneFixoConversor telefoneFixoConversorMongo = new TelefoneFixoConversor();
	
	private ContaConversor contaConversorMongo = new ContaConversor();

	private static EmpresaConversor instance;
	
	private EmpresaConversor() {	
	}
	
	public static EmpresaConversor getInstance() {
		if (instance == null) {
			instance = new EmpresaConversor();
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
		docEmpresa.append(ID, null);
		docEmpresa.append(FIELD_RAZAO_SOCIAL, null);
		docEmpresa.append(FIELD_DATA_ABERTURA, null);
		docEmpresa.append(FIELD_RESPONSAVEIS, null);
		docEmpresa.append(FIELD_ENDERECOS, null);
		docEmpresa.append(FIELD_TELEFONES_FIXO, null);
		docEmpresa.append(FIELD_EMAILS, null);
		docEmpresa.append(FIELD_CELULARES, null);
		docEmpresa.append(FIELD_CONTAS, null);
		docEmpresa.append(FIELD_TIPO_EMPRESA, null);
		docEmpresa.append(FIELD_TIPO_PORTE_EMPRESA, null);
		if (empresa.getCnpj() != null) {
			docEmpresa.put(ID, empresa.getCnpj());
		}
		if (empresa.getRazaoSocial() != null) {
			docEmpresa.put(FIELD_RAZAO_SOCIAL, empresa.getRazaoSocial());
		}
		if (empresa.getDataAbertura() != null) {
			docEmpresa.put(FIELD_DATA_ABERTURA, empresa.getDataAbertura().toString());
		}
		if (empresa.getResponsaveis() != null) {
			Iterables.removeIf(empresa.getResponsaveis(), Predicates.isNull());
			docEmpresa.put(FIELD_RESPONSAVEIS, pessoaConversorMongo.pessoasToDocuments(empresa.getResponsaveis()));
		}
		if (empresa.getEnderecos() != null) {
			Iterables.removeIf(empresa.getEnderecos(), Predicates.isNull());
			docEmpresa.put(FIELD_ENDERECOS, enderecoConversorMongo.enderecosToDocuments(empresa.getEnderecos()));
		}
		if (empresa.getTelefonesFixo() != null) {
			Iterables.removeIf(empresa.getTelefonesFixo(), Predicates.isNull());
			docEmpresa.put(FIELD_TELEFONES_FIXO, telefoneFixoConversorMongo.telefonesFixoToDocuments(empresa.getTelefonesFixo()));
		}
		if (empresa.getEmails() != null) {
			Iterables.removeIf(empresa.getEmails(), Predicates.isNull());
			docEmpresa.put(FIELD_EMAILS, emailConversorMongo.emailsToDocuments(empresa.getEmails()));
		}
		if (empresa.getCelulares() != null) {
			Iterables.removeIf(empresa.getCelulares(), Predicates.isNull());
			docEmpresa.put(FIELD_CELULARES, celularConversorMongo.celularesToDocuments(empresa.getCelulares()));
		}
		if (empresa.getContas() != null) {
			Iterables.removeIf(empresa.getContas(), Predicates.isNull());
			docEmpresa.put(FIELD_CONTAS, contaConversorMongo.contasToDocuments(empresa.getContas()));
		}
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
		if (docEmpresa.get(ID, String.class) != null) {
			empresa.setCnpj(docEmpresa.get(ID, String.class));			
		}
		if (docEmpresa.get(FIELD_RAZAO_SOCIAL, String.class) != null) {
			empresa.setRazaoSocial(docEmpresa.get(FIELD_RAZAO_SOCIAL, String.class));			
		}
		if (docEmpresa.get(FIELD_DATA_ABERTURA, String.class) != null) {
			empresa.setDataAbertura(LocalDate.parse(docEmpresa.get(FIELD_DATA_ABERTURA, String.class)));
		}
		empresa.setResponsaveis(pessoaConversorMongo.documentsToPessoas(docEmpresa.getList(FIELD_RESPONSAVEIS, Document.class, new ArrayList<>())));
		empresa.setEnderecos(enderecoConversorMongo.documentsToEnderecos(docEmpresa.getList(FIELD_ENDERECOS, Document.class, new ArrayList<>())));
		empresa.setTelefonesFixo(telefoneFixoConversorMongo.documentsToTelefonesFixo(docEmpresa.getList(FIELD_TELEFONES_FIXO, Document.class, new ArrayList<>())));
		empresa.setEmails(emailConversorMongo.documentsToEmails(docEmpresa.getList(FIELD_EMAILS, Document.class, new ArrayList<>())));
		empresa.setCelulares(celularConversorMongo.documentsToCelulares(docEmpresa.getList(FIELD_CELULARES, Document.class, new ArrayList<>())));
		empresa.setContas(contaConversorMongo.documentsToContas(docEmpresa.getList(FIELD_CONTAS, Document.class, new ArrayList<>())));
		if (docEmpresa.get(FIELD_TIPO_EMPRESA, String.class) != null) {
			empresa.setTipoEmpresa(TipoEmpresa.valueOf(docEmpresa.get(FIELD_TIPO_EMPRESA, String.class)));			
		}
		if (docEmpresa.get(FIELD_TIPO_PORTE_EMPRESA, String.class) != null) {
			empresa.setTipoPorteEmpresa(TipoPorteEmpresa.valueOf(docEmpresa.get(FIELD_TIPO_PORTE_EMPRESA, String.class)));			
		}
		return empresa;
	}
	
}
