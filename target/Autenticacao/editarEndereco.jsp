<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="br.com.devLopes.model.entidades.Endereco"%>
<%@page import="br.com.devLopes.model.entidades.Usuario"%>
<%@page import="br.com.devLopes.model.DAO.DAOEndereco"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alterar Endereço</title>
</head>
<body>

	<%@ include file="auth.jsp"%>
	<%
	Endereco endereco = (Endereco) session.getAttribute("endereco");
	
	if (endereco == null) {
		response.sendRedirect("cadastroEndereco.jsp");
		return;
	}
	%>

	<h1>Editar Endereço</h1>

	<form action="editarEndereco" method="post">
		<input type="text" name="rua" placeholder="Rua"
			value="<%=endereco.getRua()%>"> <input type="text"
			name="cidade" placeholder="Cidade" value="<%=endereco.getCidade()%>">
		<input type="text" name="estado" placeholder="Estado"
			value="<%=endereco.getEstado()%>"> <input type="submit"
			placeholder="Enviar">
	</form>
	
	<a href="excluirEndereco">Excluir este endereço</a>

</body>
</html>