package br.com.devLopes.controlers;

import java.io.IOException;

import br.com.devLopes.controlers.metodos.Validacoes;
import br.com.devLopes.model.DAO.DAOEndereco;
import br.com.devLopes.model.entidades.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ExcluirEndereco", urlPatterns = "/in/excluirEndereco")
public class ExcluirEndereco extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAOEndereco daoEndereco = new DAOEndereco();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = Validacoes.isLogado(req);
		
		if(usuario != null) {
			daoEndereco.excluirEndereco(usuario);
			req.getSession().removeAttribute("endereco");
			resp.sendRedirect(req.getContextPath()+"/in/perfil.jsp");
			return;
		} else {
			resp.sendRedirect(req.getContextPath()+"/publico/login.jsp");
			return;
		}
	}
}