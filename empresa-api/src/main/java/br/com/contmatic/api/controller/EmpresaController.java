package br.com.contmatic.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.contmatic.assembler.empresa.EmpresaResourceAssembler;
import br.com.contmatic.dto.empresa.v1.EmpresaResource;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.service.empresa.EmpresaService;
import br.com.contmatic.service.mensagens.MensagemServidor;
import br.com.contmatic.service.parametros.FindParams;

public class EmpresaController extends HttpServlet {

	private static final String FIND_PARAMS = "FindParams";

	private static final String X_QUERY_QTD = "X-Query-Qtd";

	private static final String TEXT_HTML = "text/html";

	private static final String UTF_8 = "UTF-8";

	private static final String APPLICATION_JSON = "application/json";

	private static final long serialVersionUID = 1L;

	private static final ObjectMapper mapper = new ObjectMapper();

	private static final EmpresaResourceAssembler empresaResourceAssembler = EmpresaResourceAssembler.getInstance();

	private static final EmpresaService empresaService = EmpresaService.getInstance();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getMethod()) {
		case "GET":
			getEmpresa(req, resp);
			break;
		case "POST":
			createEmpresa(req, resp);
			break;
		case "PUT":
			updateEmpresa(req, resp);
			break;
		case "DELETE":
			deleteEmpresa(req, resp);
			break;
		default:
			break;
		}
	}

	private void getEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		resp.setContentType(APPLICATION_JSON);
		FindParams findParams = mapper.readValue(req.getParameter(FIND_PARAMS), FindParams.class);
		resp.addHeader(X_QUERY_QTD, empresaService.countByParams(findParams));
		PrintWriter out = resp.getWriter();
		List<Empresa> entities = empresaService.findByParams(findParams);
		List<EmpresaResource> resources = empresaResourceAssembler.toResources(entities);
		out.print(mapper.writeValueAsString(resources));
		out.flush();
	}

	private void updateEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		resp.setContentType(TEXT_HTML);
		EmpresaResource resource = mapper.readValue(req.getReader().readLine(), EmpresaResource.class);
		Empresa entity = empresaResourceAssembler.toEntity(resource);
		MensagemServidor mensagemServidor = empresaService.update(entity);
		PrintWriter out = resp.getWriter();
		resp.setStatus(mensagemServidor.getStatusCode());
		out.print(mapper.writeValueAsString(mensagemServidor.getMensagens()));
		out.flush();
	}

	private void deleteEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		resp.setContentType(TEXT_HTML);
		EmpresaResource resource = mapper.readValue(req.getReader().readLine(), EmpresaResource.class);
		Empresa entity = empresaResourceAssembler.toEntity(resource);
		MensagemServidor mensagemServidor = empresaService.delete(entity);
		PrintWriter out = resp.getWriter();
		resp.setStatus(mensagemServidor.getStatusCode());
		out.print(mapper.writeValueAsString(mensagemServidor.getMensagens()));
		out.flush();
	}

	private void createEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		resp.setContentType(TEXT_HTML);
		EmpresaResource resource = mapper.readValue(req.getReader().readLine(), EmpresaResource.class);
		Empresa entity = empresaResourceAssembler.toEntity(resource);
		MensagemServidor mensagemServidor = empresaService.create(entity);
		PrintWriter out = resp.getWriter();
		resp.setStatus(mensagemServidor.getStatusCode());
		out.print(mapper.writeValueAsString(mensagemServidor.getMensagens()));
		out.flush();
	}

}
