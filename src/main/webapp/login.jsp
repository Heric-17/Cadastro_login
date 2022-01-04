<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

	<%@ include file="logado.jsp"%>
	
	<h1>Login</h1>


	<form action="login" method="post">
		<input type="text" name="email" placeholder="Email"> <input
			type="password" name="senha" placeholder="Senha"> <input
			type="submit" placeholder="Entrar">
	</form>

	<a href="cadastro.jsp">Cadastre-se</a>


	<%@ include file="MensagensErro.jsp"%>


</body>
</html>