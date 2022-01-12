<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Erro no Sistema</title>
</head>
<body>

	<h1>Desculpe, ocorreu um erro no sistema, será corrigido o mais
		breve possível</h1>

	<h1>
		Erro:
		<%= exception.getMessage()%></h1>

</body>
</html>