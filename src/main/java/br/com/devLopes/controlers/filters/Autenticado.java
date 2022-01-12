package br.com.devLopes.controlers.filters;

import java.io.IOException;

import br.com.devLopes.model.entidades.Usuario;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/in/*")
public class Autenticado implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		Usuario user = (Usuario) session.getAttribute("usuario");

		if (user == null) {
			resp.sendRedirect(req.getContextPath()+"/publico/login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}
}
