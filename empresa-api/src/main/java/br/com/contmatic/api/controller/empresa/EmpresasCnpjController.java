package br.com.contmatic.api.controller.empresa;

import java.io.IOException;
import java.io.PrintWriter;

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

public class EmpresasCnpjController extends HttpServlet {

	private static final String APPLICATION_XML = "application/xml";

	private static final String ACCEPT = "Accept";

	private static final String ALLOW = "Allow";

	private static final String ALLOWED_METHODS = "GET, DELETE";

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
		case "DELETE":
			deleteEmpresa(req, resp);
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
		PrintWriter out = resp.getWriter();
		String cnpj = req.getPathInfo().substring(1);
		Empresa entity = EmpresaService.getInstance().findByCnpj(cnpj);
		if (entity != null) {
			EmpresaResource resource = null;
			try {
				resource = EmpresaResourceAssembler.getInstance().toResource(entity);
			} catch (AssemblerException e) {
				resp.setStatus(500);
				out.print(e.toString());
			}
			if (resource != null) {
				if (req.getHeader(ACCEPT).equals(APPLICATION_XML)) {
					out.print(mapperXml.writeValueAsString(resource));
				} else {		
					out.print(mapperJson.writeValueAsString(resource));
				}
			}			
		} else {
			MensagemServidor mensagemServidor = EmpresaService.getInstance().sendNotFoundResponse();
			resp.setStatus(mensagemServidor.getStatusCode());
			if (req.getHeader(ACCEPT).equals(APPLICATION_XML)) {
				out.print(mapperXml.writeValueAsString(mensagemServidor));
			} else {		
				out.print(mapperJson.writeValueAsString(mensagemServidor));
			}
		}
		out.flush();
	}

	private void deleteEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		if (req.getHeader(ACCEPT).equals(APPLICATION_XML)) {
			resp.setContentType(APPLICATION_XML);
		} else {		
			resp.setContentType(APPLICATION_JSON);
		}
		PrintWriter out = resp.getWriter();
		String cnpj = req.getPathInfo().substring(1);
		MensagemServidor mensagemServidor = EmpresaService.getInstance().delete(cnpj);
		resp.setStatus(mensagemServidor.getStatusCode());
		if (req.getHeader(ACCEPT).equals(APPLICATION_XML)) {
			out.print(mapperXml.writeValueAsString(mensagemServidor));
		} else {		
			out.print(mapperJson.writeValueAsString(mensagemServidor));
		}
		out.flush();
	}

}
