package br.com.contmatic.api.controller.empresa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.contmatic.assembler.empresa.EmpresaResourceAssembler;
import br.com.contmatic.assembler.exception.AssemblerException;
import br.com.contmatic.dto.empresa.v1.EmpresaResource;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.service.empresa.EmpresaService;
import br.com.contmatic.service.mensagens.MensagemServidor;
import br.com.contmatic.service.parametros.FindParams;

public class EmpresasController extends HttpServlet {

	private static final String APPLICATION_XML = "application/xml";

	private static final String ACCEPT = "Accept";
	
	private static final String ALLOW = "Allow";

	private static final String ALLOWED_METHODS = "GET, PUT, POST";
	
	private static final String FIND_PARAMS = "FindParams";

	private static final String LOCATION = "Location";
	
	private static final String X_QUERY_QTD = "X-Query-Qtd";

	private static final String UTF_8 = "UTF-8";

	private static final String APPLICATION_JSON = "application/json";

	private static final long serialVersionUID = 1L;

	private static final ObjectMapper mapperJson = new ObjectMapper();

	private static final XmlMapper mapperXml = new XmlMapper();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getMethod()) {
		case "GET":
			getEmpresa(req, resp);
			break;
		case "PUT":
			updateEmpresa(req, resp);
			break;
		case "POST":
			createEmpresa(req, resp);
			break;
		default:
			resp.setCharacterEncoding(UTF_8);
			resp.setStatus(405);
			resp.addHeader(ALLOW, ALLOWED_METHODS);
			break;
		}
	}

	private void getEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		if (req.getHeader(ACCEPT).equals(APPLICATION_XML)) {
			resp.setContentType(APPLICATION_XML);
		} else {		
			resp.setContentType(APPLICATION_JSON);
		}
		FindParams findParams = mapperJson.readValue(req.getParameter(FIND_PARAMS), FindParams.class);
		resp.addHeader(X_QUERY_QTD, EmpresaService.getInstance().countByParams(findParams));
		PrintWriter out = resp.getWriter();
		List<Empresa> entities = EmpresaService.getInstance().findByParams(findParams);
		List<EmpresaResource> resources = null;
		try {
			resources = EmpresaResourceAssembler.getInstance().toResources(entities);
		} catch (AssemblerException e) {
			resp.setStatus(500);
			out.print(e.toString());
		}
		if (resources != null) {
			if (req.getHeader(ACCEPT).equals(APPLICATION_XML)) {
				out.print(mapperXml.writeValueAsString(resources));
			} else {		
				out.print(mapperJson.writeValueAsString(resources));
			}
		}
		out.flush();
	}

	private void updateEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		if (req.getHeader(ACCEPT).equals(APPLICATION_XML)) {
			resp.setContentType(APPLICATION_XML);
		} else {		
			resp.setContentType(APPLICATION_JSON);
		}
		EmpresaResource resource = mapperJson.readValue(req.getReader().readLine(), EmpresaResource.class);
		PrintWriter out = resp.getWriter();
		Empresa entity = null;
		try {
			entity = EmpresaResourceAssembler.getInstance().toEntity(resource);
		} catch (AssemblerException e) {
			resp.setStatus(500);
			out.print(e.toString());
		}
		if (entity != null) {
			MensagemServidor mensagemServidor = EmpresaService.getInstance().update(entity);
			resp.setStatus(mensagemServidor.getStatusCode());
			if (req.getHeader(ACCEPT).equals(APPLICATION_XML)) {
				out.print(mapperXml.writeValueAsString(mensagemServidor));
			} else {		
				out.print(mapperJson.writeValueAsString(mensagemServidor));
			}
		}
		out.flush();
	}

	private void createEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		if (req.getHeader(ACCEPT).equals(APPLICATION_XML)) {
			resp.setContentType(APPLICATION_XML);
		} else {		
			resp.setContentType(APPLICATION_JSON);
		}
		EmpresaResource resource = mapperJson.readValue(req.getReader().readLine(), EmpresaResource.class);
		PrintWriter out = resp.getWriter();
		Empresa entity = null;
		try {
			entity = EmpresaResourceAssembler.getInstance().toEntity(resource);
		} catch (AssemblerException e) {
			resp.setStatus(500);
			out.print(e.toString());
		}
		if (entity != null) {
			MensagemServidor mensagemServidor = EmpresaService.getInstance().create(entity);
			resp.setStatus(mensagemServidor.getStatusCode());
			if (req.getHeader(ACCEPT).equals(APPLICATION_XML)) {
				out.print(mapperXml.writeValueAsString(mensagemServidor));
			} else {		
				out.print(mapperJson.writeValueAsString(mensagemServidor));
			}
			if (mensagemServidor.getStatusCode() == 201) {
				resp.addHeader(LOCATION, "/empresas/" + entity.getCnpj());
			}
		}
		out.flush();
	}

}
