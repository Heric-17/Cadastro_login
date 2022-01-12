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

	<jsp:useBean id="usuario"
		class="br.com.devLopes.model.entidades.Usuario" scope="session"></jsp:useBean>

	<h1>Perfil</h1>

	<h1>
		Bem vindo:
		<jsp:getProperty property="nome" name="usuario" />
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
	<a href="<%=application.getContextPath()%>/in/editarEndereco">Editar
		endereço</a>


	<%
	} else {
	%>
	<p>
		Termine de preencher seus dados <a
			href="<%=application.getContextPath()%>/in/cadastroEndereco.jsp">aqui</a>
	</p>
	<%
	}
	%>
	<a href="<%=application.getContextPath()%>/in/deslogar">Deslogar do
		sistema</a>
	<br>
	<a href="<%=application.getContextPath()%>/in/excluirUsuario">Excluir
		perfil</a>

</body>
</html>