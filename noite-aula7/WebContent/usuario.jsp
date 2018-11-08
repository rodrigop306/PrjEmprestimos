<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Usuario, java.util.List, java.util.ArrayList"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Registrar-se</title>
	<link rel="stylesheet" href="./css/bootstrap.min.css"/>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<h2>Registrar-se</h2>
	
	<%  String msg = (String)session.getAttribute("MENSAGEM");
		List<Usuario> lista = (List<Usuario>)session.getAttribute("LISTA");
	   if (lista == null) { 
	   	   lista = new ArrayList<Usuario>();
	   } else { 
		   session.setAttribute("LISTA", null);
	   }
	   
	   if (msg != null) {
		   session.setAttribute("MENSAGEM", null);
	%>
			<h3 class="alert alert-danger"><%=msg%></h3>
	<% } %>
	
	
	<form id="formUsuario" action="./UsuarioController" method="post">
		<div class="container">
			<div class="form-group">
    			<label for="txtId">Id</label>
    			<input type="text" class="form-control" id="txtId" name="txtId" readonly/>
  			</div>
  			<div class="form-group">
    			<label for="txtTelefone">Nome</label>
    			<input type="text" class="form-control" id="txtSenha" name="txtNome"/>
  			</div>   
			<div class="form-group">
    			<label for="txtNome">Login</label>
    			<input type="text" class="form-control" id="txtLogin" name="txtLogin"/>
  			</div>  	
  			<div class="form-group">
    			<label for="txtEmail">Senha</label>
    			<input type="password" class="form-control" id="txtSenha" name="txtSenha"/>
  			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary" name="cmd" value="adicionar">Adicionar</button>
				<button type="submit" class="btn btn-primary" name="cmd" value="voltar">Voltar</button>
			</div>																		
		</div>
	</form>
</body>
</html>