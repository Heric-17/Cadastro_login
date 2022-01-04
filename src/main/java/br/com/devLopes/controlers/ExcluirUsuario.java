package br.com.devLopes.controlers;

import java.io.IOException;

import br.com.devLopes.controlers.metodos.Validacoes;
import br.com.devLopes.model.DAO.DAOUsuario;
import br.com.devLopes.model.entidades.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ExcluirUsuario", urlPatterns = "/excluirUsuario")
public class ExcluirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOUsuario daoUsuario = new DAOUsuario();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = Validacoes.isLogado(req);

		if (usuario != null) {
			daoUsuario.excluirUsuario(usuario);
			resp.sendRedirect("deslogar");
		} else {
			resp.sendRedirect("login.jsp");
			return;
		}
	}

}
