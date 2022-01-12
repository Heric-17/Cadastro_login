package br.com.devLopes.controlers;

import java.io.IOException;

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

@WebServlet(name = "CadastroUsuario", urlPatterns = "/in/newEndereco")
public class CadastroEndereco extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOEndereco DAOendereco = new DAOEndereco();
	DAOUsuario DAOusuario = new DAOUsuario();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Usuario usuario = Validacoes.isLogado(req);
		Endereco endereco = new Endereco();

		if (usuario != null) {
			
			if (Validacoes.CampoVazio(req, resp, "in/cadastroEndereco.jsp")) {
				return;
			}

			if (Validacoes.tamanhoParam(req, resp, "cidade", "in/cadastroEndereco.jsp", 3, 80)) {
				return;
			}
			if (Validacoes.tamanhoParam(req, resp, "rua", "in/cadastroEndereco.jsp", 5, 80)) {
				return;
			}
			if (Validacoes.tamanhoParam(req, resp, "estado", "in/cadastroEndreco.jsp", 5, 80)) {
				return;
			}

			String rua = req.getParameter("rua");
			String cidade = req.getParameter("cidade");
			String estado = req.getParameter("estado");
			
			endereco.setRua(rua);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);

			DAOendereco.addEndereco(usuario, endereco);
			req.getSession().setAttribute("endereco", endereco);
			resp.sendRedirect(req.getContextPath()+"/in/perfil.jsp");
		} else {
			resp.sendRedirect(req.getContextPath()+"/publico/login.jsp");
		}
	}
}
