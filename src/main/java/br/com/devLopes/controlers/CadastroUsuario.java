package br.com.devLopes.controlers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.devLopes.controlers.metodos.Criptografia;
import br.com.devLopes.controlers.metodos.Validacoes;
import br.com.devLopes.model.DAO.DAOUsuario;
import br.com.devLopes.model.entidades.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastroDeUsuario", urlPatterns = "/cadastroUsuario")
public class CadastroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOUsuario DAOUser = new DAOUsuario();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario user = new Usuario();

		if (Validacoes.CampoVazio(req, resp, "cadastro.jsp")) {
			return;
		}

		if (Validacoes.tamanhoParam(req, resp, "nome", "cadastro.jsp", 3, 80)) {
			return;
		}

		if (DAOUser.consultarPorEmail(req.getParameter("email")) != null) {
			Validacoes.validacaoGeral(req, resp, "Email já existente", "cadastro.jsp");
			return;
		} else if (!req.getParameter("email").contains("@")) {
			Validacoes.validacaoGeral(req, resp, "Email deve conter '@'", "cadastro.jsp");
			return;
		} else if (Validacoes.tamanhoParam(req, resp, "email", "cadastro.jsp", 10, 100)) {
			return;
		}
		String password = req.getParameter("senha");

		if (Validacoes.tamanhoParam(req, resp, "senha","cadastro.jsp", 6, 255)) {
			return;
		}
		try {
			String cripto = Criptografia.crip(password);

			user.setSenha(cripto);

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}

		user.setNome(req.getParameter("nome"));
		user.setEmail(req.getParameter("email"));

		DAOUser.cadastrarUsuario(user);

		resp.sendRedirect("login.jsp");
	}

}
