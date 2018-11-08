<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Amigos, java.util.List, java.util.ArrayList"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Amigos</title>
	<link rel="stylesheet" href="./css/bootstrap.min.css"/>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<h2>Amigos</h2>
	
	<%  String msg = (String)session.getAttribute("MENSAGEM");
		List<Amigos> lista = (List<Amigos>)session.getAttribute("LISTA");
	   if (lista == null) { 
	   	   lista = new ArrayList<Amigos>();
	   } else { 
		   session.setAttribute("LISTA", null);
	   }
	   
	   if (msg != null) {
		   session.setAttribute("MENSAGEM", null);
	%>
			<h3 class="alert alert-danger"><%=msg%></h3>
	<% } %>
	
	
	<form id="formAmigos" action="./AmigosController" method="post">
		<div class="container">
			<div class="form-group">
    			<label for="txtId">Id</label>
    			<input type="text" class="form-control" id="txtId" name="txtId" readonly/>
  			</div>
			<div class="form-group">
    			<label for="txtNome">Nome</label>
    			<input type="text" class="form-control" id="txtNome" name="txtNome"/>
  			</div>  	
			<div class="form-group">
    			<label for="txtEmail">Email</label>
    			<input type="text" class="form-control" id="txtEmail" name="txtEmail"/>
  			</div>
  			<div class="form-group">
    			<label for="txtTelefone">Telefone</label>
    			<input type="text" class="form-control" id="txtTelefone" name="txtTelefone"/>
  			</div>   
			<div class="form-group">
				<button type="submit" class="btn btn-primary" name="cmd" value="adicionar">Adicionar</button>
				<button type="submit" class="btn btn-primary" name="cmd" value="pesquisar">Pesquisar</button>
			</div>																		
		</div>
		<div class="container">
			<table class="table table-striped">
				<tbody>
					<% for (Amigos a : lista) { %>
					<tr>
						<td><%=a.getIdAmigo()%></td>
						<td><%=a.getNome()%></td>
						<td><%=a.getEmail()%></td>
						<td><%=a.getTelefone()%></td>
						<td>
							<div class="form-group">
								<button type="button" class="btn btn-primary" onclick="remover(<%=a.getIdAmigo()%>);">Remover</button>
								<button type="button" class="btn btn-primary" onclick="editar(<%=a.getIdAmigo()%>);">Editar</button>
							</div>																		
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>