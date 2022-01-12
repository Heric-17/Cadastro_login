package br.com.devLopes.controlers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.devLopes.controlers.metodos.Criptografia;
import br.com.devLopes.controlers.metodos.Validacoes;
import br.com.devLopes.model.DAO.DAOEndereco;
import br.com.devLopes.model.DAO.DAOUsuario;
import br.com.devLopes.model.entidades.Endereco;
import br.com.devLopes.model.entidades.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginUsuario", urlPatterns = "/login")
public class LoginUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOUsuario daoUser = new DAOUsuario();
	DAOEndereco daoEnde = new DAOEndereco();


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Usuario user = daoUser.consultarPorEmail(req.getParameter("email"));

		String senha = req.getParameter("senha");

		if (user != null) {
			try {
				if (Criptografia.crip(senha).equals(user.getSenha())) {
					HttpSession session = req.getSession();

					session.setAttribute("usuario", user);
					Endereco endereco = daoEnde.getEnderecoPorEmail(user.getEmail());
					if (endereco != null) {
						session.setAttribute("endereco", endereco);
					}
					res.sendRedirect(req.getContextPath() + "/in/perfil.jsp");
					return;
				} else {
					Validacoes.validacaoGeral(req, res, "Email ou senha incorreto", "publico/login.jsp");
				}
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		} else {
			Validacoes.validacaoGeral(req, res, "Email ou senha incorreto", "publico/login.jsp");
		}
	}
}
