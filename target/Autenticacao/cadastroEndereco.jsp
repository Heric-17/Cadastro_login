<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dados pessoais</title>
</head>
<body>

	<%@ include file="auth.jsp"%>

	<h1>Cadastro de Endereço</h1>

	<form action="endereco" method="post">
		<input type="text" name="rua" placeholder="Rua"> <input
			type="text" name="cidade" placeholder="Cidade"> <input
			type="text" name="estado" placeholder="Estado"> <input
			type="submit" placeholder="Enviar">
	</form>

	<%@ include file="MensagensErro.jsp"%>

</body>
</html>