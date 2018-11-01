<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidade.Objeto, java.util.List, java.util.ArrayList"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Amigos</title>
	<link rel="stylesheet" href="./css/bootstrap.min.css"/>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/jquery-3.3.1.min.js"></script>
	<script>
		function remover( id ) {
			if (confirm("Remove o sorvete com id " + id)) {
				$('#formObjetos').empty();
				$('#formObjetos').append('<input type="hidden" name="txtId" value="' + id + '"/>');
				$('#formObjetos').append('<input type="hidden" name="cmd" value="remover"/>');
				$('#formObjetos').submit();
			}
		}
	</script>
</head>
<body>
	<h2>Amigos</h2>
	
	<%  String msg = (String)session.getAttribute("MENSAGEM");
		List<Objeto> lista = (List<Objeto>)session.getAttribute("LISTA");
	   if (lista == null) { 
	   	   lista = new ArrayList<Objeto>();
	   } else { 
		   session.setAttribute("LISTA", null);
	   }
	   
	   if (msg != null) {
		   session.setAttribute("MENSAGEM", null);
	%>
			<h3 class="alert alert-danger"><%=msg%></h3>
	<% } %>
	
	
	<form id="formObjetos" action="./SorveteController" method="post">
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
    			<label for="txtEmail">Descrição</label>
    			<input type="text" class="form-control" id="txtDescricao" name="txtDescricao"/>
  			</div>
  			<div class="form-group">
    			<label for="txtCategoria">Categoria</label>
    			<select class="form-control" id="txtTipo" name="txtTipo">
      				<option value="massa2k">Massa 2Kg</option>
      				<option value="massa1k">Massa 1Kg</option>
      				<option value="massa250">Massa 250g</option>
				    <option value="picole">Picole</option>
				</select>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary" name="cmd" value="adicionar">Adicionar</button>
				<button type="submit" class="btn btn-primary" name="cmd" value="pesquisar">Pesquisar</button>
			</div>
																						
		</div>
		<div class="container">
			<table class="table table-striped">
				<tbody>
					<% for (Objeto a : lista) { %>
					<tr>
						<td><%=a.getIdObjeto()%></td>
						<td><%=a.getNome()%></td>
						<td><%=a.getDescricao()%></td>
						<td><%=a.getIdCategoria()%></td>
						<td>
							<div class="form-group">
								<button type="button" class="btn btn-primary" onclick="remover(<%=a.getIdObjeto()%>);">Remover</button>
								<button type="button" class="btn btn-primary" onclick="editar(<%=a.getIdObjeto()%>);">Editar</button>
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