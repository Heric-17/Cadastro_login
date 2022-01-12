<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="br.com.devLopes.model.entidades.Endereco"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alterar Endereço</title>
</head>
<body>

	<%
	Endereco endereco = (Endereco) session.getAttribute("endereco");

	if (endereco == null) {
		response.sendRedirect("/in/cadastroEndereco.jsp");
		return;
	}
	%>

	<h1>Editar Endereço</h1>

	<form action="<%=application.getContextPath()%>/in/editarEndereco"
		method="post">
		<input type="text" name="rua" placeholder="Rua"
			value="<%=endereco.getRua()%>"> <input type="text"
			name="cidade" placeholder="Cidade" value="<%=endereco.getCidade()%>">
		<input type="text" name="estado" placeholder="Estado"
			value="<%=endereco.getEstado()%>"> <input type="submit"
			placeholder="Enviar">
	</form>
	<%@ include file="../MensagensErro.jsp"%>

	<a href="<%=application.getContextPath()%>/in/excluirEndereco">Excluir
		este endereço</a>

</body>
</html>