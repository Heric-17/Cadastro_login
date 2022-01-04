<%@page import="br.com.devLopes.model.entidades.Endereco"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Perfil</title>

</head>

<body>
	<%@ include file="auth.jsp"%>
	<h1>Perfil</h1>

	<h1>
		Welcome
		<%=user.getNome()%>
		!!!
	</h1>

	<%
	Endereco endereco = (Endereco) session.getAttribute("endereco");

	if (endereco != null) {
	%>
	<ul>
		<li><%=endereco.getEstado()%></li>
		<li><%=endereco.getCidade()%></li>
		<li><%=endereco.getRua()%></li>
	</ul>
	<a href="editarEndereco">Editar endereço</a>
	<br>
	<a href="deslogar">Deslogar do sistema</a>
	<br>
	
	
	<%
	} else {
	%>
	<p>
		Termine de preencher seus dados <a href="cadastroEndereco.jsp">aqui</a>
	</p>
	<%
	}
	%>
	<a href="excluirUsuario">Excluir perfil</a>

</body>
</html>