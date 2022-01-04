<%@page import="br.com.devLopes.model.entidades.Usuario"%>
<% 

Usuario user = (Usuario) session.getAttribute("usuario");

if(user == null){
	response.sendRedirect("login.jsp");
	return;
}
%>