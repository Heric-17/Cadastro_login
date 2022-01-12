package br.com.devLopes.controlers.metodos;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import br.com.devLopes.model.entidades.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Validacoes {

	static public void validacaoGeral(HttpServletRequest req, HttpServletResponse res, String mensagemErro,
			String direcionamento) throws ServletException, IOException {

		req.setAttribute("mensagemErro", mensagemErro);
		req.getRequestDispatcher("/"+direcionamento).forward(req, res);
//		res.sendRedirect(req.getContextPath()+"/"+direcionamento);
	}

	static public Usuario isLogado(HttpServletRequest req) {

		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		return usuario;
	}

	static public boolean CampoVazio(HttpServletRequest req, HttpServletResponse resp, String localDeEnvio)
			throws ServletException, IOException {

		Map<String, String[]> parametros = req.getParameterMap();

		Collection<String[]> valoresParametros = parametros.values();

		for (String[] valores : valoresParametros) {
			if (valores[0].equalsIgnoreCase("") || valores[0] == null) {
				Validacoes.validacaoGeral(req, resp, "Nenhum dos campos pode ser vazio", localDeEnvio);
				return true;
			}
		}
		return false;
	}

	static public boolean tamanhoParam(HttpServletRequest req, HttpServletResponse resp,  String reqParametro, String localDestino, int menorQ, int maiorQ)
			throws ServletException, IOException {
		String parametro = req.getParameter(reqParametro);
		String menorQue = Integer.toString(menorQ);
		String maiorQue = Integer.toString(maiorQ);

		if (parametro == null || parametro.trim().length() < menorQ) {
			Validacoes.validacaoGeral(req, resp, reqParametro + " não pode conter menos que " + menorQue + " caracteres", localDestino);
			return true;
		}
		if (parametro.trim().length() > 80) {
			Validacoes.validacaoGeral(req, resp, reqParametro + " não pode conter mais que " + maiorQue + " caracteres", localDestino);
			return true;
		}

		return false;
	}
}
