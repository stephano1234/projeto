package br.com.contmatic.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.contmatic.api.controller.adapters.LocalDateTypeAdapter;
import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.service.empresa.EmpresaService;
import br.com.contmatic.service.empresa.EmpresaValidador;
import br.com.contmatic.service.parametros.FindParams;

public class EmpresaController extends HttpServlet {

	private static final String X_FIND_PARAMS = "X-Find-Params";

	private static final String X_COUNT_QUERY = "X-Count-Query";

	private static final String TEXT_HTML = "text/html";

	private static final String UTF_8 = "UTF-8";

	private static final String APPLICATION_JSON = "application/json";

	private static final String X_SERVER_MSG = "X-Server-Msg";

	private static final String PUT_SUCESSO = "Empresa alterada com sucesso.";

	private static final String DELETE_SUCESSO = "Empresa apagada com sucesso.";
	
	private static final String POST_SUCESSO = "Empresa salva com sucesso.";
	
	private static final long serialVersionUID = 1L;

	private static final EmpresaValidador empresaValidador = EmpresaValidador.getInstance();

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
		Gson gson = configGson();
		FindParams findParams = gson.fromJson(req.getHeader(X_FIND_PARAMS), FindParams.class);
		if (empresaValidador.validaGet(findParams)) {
			resp.setContentType(APPLICATION_JSON);
			if (findParams.getToCount()) {
				resp.addHeader(X_COUNT_QUERY, Long.toString(empresaService.countByParams(findParams)));
			}
			PrintWriter out = resp.getWriter();
			List<Empresa> empresas = empresaService.findByParams(findParams);
			out.print(gson.toJson(empresas));
			out.flush();
		} else {
			resp.setContentType(TEXT_HTML);
			resp.setHeader(X_SERVER_MSG, gson.toJson(empresaValidador.procuraViolacoesGet(findParams)));
			resp.sendError(422);
		}
	}

	private void updateEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		resp.setContentType(TEXT_HTML);
		Gson gson = configGson();
		Empresa empresa = gson.fromJson(req.getReader().readLine(), Empresa.class);
		if (empresaValidador.validaPut(empresa)) {
			empresaService.update(empresa);
			resp.setHeader(X_SERVER_MSG, PUT_SUCESSO);
		} else {
			resp.setHeader(X_SERVER_MSG, gson.toJson(empresaValidador.procuraViolacoesPut(empresa)));
			resp.sendError(422);
		}
	}

	private void deleteEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		resp.setContentType(TEXT_HTML);
		Gson gson = configGson();
		Empresa empresa = gson.fromJson(req.getReader().readLine(), Empresa.class);
		if (empresaValidador.validaDelete(empresa)) {
			empresaService.delete(empresa);
			resp.setHeader(X_SERVER_MSG, DELETE_SUCESSO);
		} else {
			resp.setHeader(X_SERVER_MSG, gson.toJson(empresaValidador.procuraViolacoesDelete(empresa)));
			resp.sendError(422);
		}
	}

	private void createEmpresa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding(UTF_8);
		resp.setContentType(TEXT_HTML);
		Gson gson = configGson();
		Empresa empresa = gson.fromJson(req.getReader().readLine(), Empresa.class);
		if (empresaValidador.validaPost(empresa)) {
			empresaService.create(empresa);
			resp.setHeader(X_SERVER_MSG, POST_SUCESSO);
		} else {
			resp.setHeader(X_SERVER_MSG, gson.toJson(empresaValidador.procuraViolacoesPost(empresa)));
			resp.sendError(422);
		}
	}

	private Gson configGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter().nullSafe());
		return gsonBuilder.setPrettyPrinting().create();
	}

}
