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

@WebServlet(name = "EditarEndereco", urlPatterns = "/editarEndereco")
public class EditarEndereco extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Instanciando novo Objeto de Acesso aos Dados (Data Access Object)
	DAOEndereco daoEnde = new DAOEndereco();

	// Configruando uma requisição GET para setar o endereço na sessão que deve ser
	// editado no post
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Conferindo se há um usuario logado pegando o atributo da session "usuario"
		Usuario usuario = Validacoes.isLogado(req);
		Endereco endereco = (Endereco) req.getSession().getAttribute("endereco");

		// Se o atributo for null o cliente não pode fazer essa requisição e é enviado
		// para fazer login
		if (usuario == null) {
			resp.sendRedirect("login.jsp");
			return;
		} else if(endereco == null) {			
			resp.sendRedirect("cadastroEndereco.jsp");
			return;
		} else {
			resp.sendRedirect("editarEndereco.jsp");
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Conferindo se há um usuario logado pegando o atributo da session "usuario"
		Usuario usuario = Validacoes.isLogado(req);

		// Se o atributo for null o cliente não pode fazer essa requisição e é enviado
		// para fazer login
		if (usuario == null) {
			resp.sendRedirect("login.jsp");
			return;
		} else {
			String rua = req.getParameter("rua");
			String cidade = req.getParameter("cidade");
			String estado = req.getParameter("estado");
			
			// Setando os novos valores no banco de dados;
			Endereco endereco = daoEnde.getEnderecoPorEmail(usuario.getEmail());

			if (Validacoes.CampoVazio(req, resp, "editarEndereco.jsp")) {
				return;
			}
			if (Validacoes.tamanhoParam(req, resp, "cidade", "editarEndereco.jsp", 3, 80)) {
				return;
			}
			if (Validacoes.tamanhoParam(req, resp, "rua", "editarEndereco.jsp", 5, 80)) {
				return;
			}
			if (Validacoes.tamanhoParam(req, resp, "estado", "editarEndereco.jsp", 5, 80)) {
				return;
			}

			endereco.setCidade(cidade);
			endereco.setEstado(estado);
			endereco.setRua(rua);

			daoEnde.alterarEndereco(endereco);
			resp.sendRedirect("perfil.jsp");
		}
	}
}
