package br.com.contmatic.repository.mongo.conversor.contato;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.model.contato.Email;

public class EmailConversorMongo {

	private static final String FIELD_ENDERECO = "endereco";

	public Document emailToDocument(Email email) {
		if (email == null) {
			return null;
		}
		final Document docEmail = new Document();
		docEmail.append(FIELD_ENDERECO, null);
		if (email.getEndereco() != null) {
			docEmail.put(FIELD_ENDERECO, email.getEndereco());
		}
		return docEmail;
	}
	
	public Email documentToEmail(Document docEmail) {
		if (docEmail == null) {
			return null;
		}
		final Email email = new Email();
		if (docEmail.get(FIELD_ENDERECO, String.class) != null) {
			email.setEndereco(docEmail.get(FIELD_ENDERECO, String.class));			
		}
		return email;
	}
	
	public List<Document> emailsToDocuments(Set<Email> emails) {
		if (emails == null) {
			return new ArrayList<>();
		}
		final List<Document> docEmails = new ArrayList<>();
		for (Email email : emails) {
			docEmails.add(emailToDocument(email));
		}
		return docEmails;
	}
	
	public Set<Email> documentsToEmails(List<Document> docEmails) {
		if (docEmails == null) {
			return new HashSet<>();
		}
		final Set<Email> emails = new HashSet<>();
		for (Document docEmail : docEmails) {
			emails.add(documentToEmail(docEmail));
		}
		return emails;
	}

}
