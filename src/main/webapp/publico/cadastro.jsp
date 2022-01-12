<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro Usuario</title>
</head>
<body>

	<h1>Cadastro de Usuarios</h1>

	<form action="<%=application.getContextPath()%>/cadastroUsuario" method="post">
		<input type="text" name="nome" placeholder="Nome"> <input
			type="text" name="email" placeholder="email"> <input
			type="text" name="senha" placeholder="senha"> <input
			type="submit" placeholder="Enviar">
	</form>

	<%@ include file="../MensagensErro.jsp"%>


</body>
</html>