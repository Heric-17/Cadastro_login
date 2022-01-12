package br.com.devLopes.controlers;

import java.io.IOException;

import br.com.devLopes.controlers.metodos.Validacoes;
import br.com.devLopes.model.DAO.DAOEndereco;
import br.com.devLopes.model.entidades.Endereco;
import br.com.devLopes.model.entidades.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarEndereco", urlPatterns = "/in/editarEndereco")
public class EditarEndereco extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOEndereco daoEnde = new DAOEndereco();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Usuario usuario = Validacoes.isLogado(req);
		Endereco endereco = (Endereco) req.getSession().getAttribute("endereco");

		if (usuario == null) {
			resp.sendRedirect(req.getContextPath() + "/publico/login.jsp");
			return;
		} else if (endereco == null) {
			resp.sendRedirect(req.getContextPath() + "/in/cadastroEndereco.jsp");
			return;
		} else {
			resp.sendRedirect(req.getContextPath() + "/in/editarEndereco.jsp");
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Usuario usuario = Validacoes.isLogado(req);

		Endereco endereco = (Endereco) req.getSession().getAttribute("endereco");

		if (usuario == null) {
			resp.sendRedirect(req.getContextPath() + "/publico/login.jsp");
			return;
		} else if (endereco == null) {
			resp.sendRedirect(req.getContextPath() + "/in/cadastroEndereco.jsp");
		} else {
			String rua = req.getParameter("rua");
			String cidade = req.getParameter("cidade");
			String estado = req.getParameter("estado");

			if (Validacoes.CampoVazio(req, resp, "in/editarEndereco.jsp")) {
				return;
			}
			if (Validacoes.tamanhoParam(req, resp, "cidade", "in/editarEndereco.jsp", 3, 80)) {
				return;
			}
			if (Validacoes.tamanhoParam(req, resp, "rua", "in/editarEndereco.jsp", 5, 80)) {
				return;
			}
			if (Validacoes.tamanhoParam(req, resp, "estado", "in/editarEndereco.jsp", 5, 80)) {
				return;
			}

			endereco.setUsuario_id(usuario.getId());
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
			endereco.setRua(rua);

			daoEnde.alterarEndereco(endereco, usuario.getEmail());
			Endereco enderecoAtt = daoEnde.getEnderecoPorEmail(usuario.getEmail());

			req.getSession().setAttribute("endereco", enderecoAtt);
			resp.sendRedirect(req.getContextPath() + "/in/perfil.jsp");
		}
	}
}
