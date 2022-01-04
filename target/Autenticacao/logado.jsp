<%@page import="br.com.devLopes.model.entidades.Usuario"%>
<% 

Usuario user = (Usuario) session.getAttribute("usuario");
Object obj = request.getAttribute("mensagemErro");

if(user != null && obj == null){
	response.sendRedirect("perfil.jsp");
}
%>