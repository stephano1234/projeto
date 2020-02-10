package br.com.contmatic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.contmatic.model.empresa.Empresa;
import br.com.contmatic.service.empresa.EmpresaService;

public class EmpresaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final EmpresaService empresaService = EmpresaService.getInstance();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Empresa> empresas = empresaService.findAll(10);
		PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>");
        out.println("Gerenciador de Empresas");
        out.println("</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>");
        out.println("Listagem de Empresas");
        out.println("</h1>");
        out.println("<table>");
        for (int i = 0; i < 10; i++) {
        	out.println("<tr>");
        	out.println("<th>");
        	out.println(empresas.get(i).getCnpj());
        	out.println("</th>");
        	out.println("<th>");
        	out.println(empresas.get(i).getRazaoSocial());
        	out.println("</th>");
        	out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
	
}
