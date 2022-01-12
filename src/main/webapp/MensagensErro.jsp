
<% String msg = (String) request.getAttribute("mensagemErro"); 
if(msg != null){ %>

<p><%= msg %></p>

<% } %>