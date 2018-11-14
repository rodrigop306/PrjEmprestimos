<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Categoria, java.util.List, java.util.ArrayList"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Categorias</title>
	<link rel="stylesheet" href="./css/bootstrap.min.css"/>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/jquery-3.3.1.min.js"></script>
		<script>
		function remover( id ) {
			if (confirm("Remove o sorvete com id " + id)) {
				$('#formCategoria').empty();
				$('#formCategoria').append('<input type="hidden" name="txtId" value="' + id + '"/>');
				$('#formCategoria').append('<input type="hidden" name="cmd" value="remover"/>');
				$('#formCategoria').submit();
			}
		}
		function editar( id ) {
			$('#formCategoria').empty();
			$('#formCategoria').append('<input type="hidden" name="txtId" value="' + id + '"/>');
			$('#formCategoria').append('<input type="hidden" name="cmd" value="editar"/>');
			$('#formCategoria').submit();
		}		
	</script>
</head>
<body>
	<h2 align='Center'>Categorias</h2>
	
	<%  String msg = (String)session.getAttribute("MENSAGEM");
		List<Categoria> lista = (List<Categoria>)session.getAttribute("LISTA");
	   if (lista == null) { 
	   	   lista = new ArrayList<Categoria>();
	   } else { 
		   session.setAttribute("LISTA", null);
	   }
	   
	   Categoria categoriaAtual = (Categoria)session.getAttribute("CATEGORIA_ATUAL");
	   if (categoriaAtual == null) { 
		   categoriaAtual = new Categoria();
	   } else { 
		   session.setAttribute("CATEGORIA_ATUAL", null);
	   }
	   
	   if (msg != null) {
		   session.setAttribute("MENSAGEM", null);
	%>
			<h3 class="alert alert-danger"><%=msg%></h3>
<% } %>
	
	
	<form id="formCategoria" action="./CategoriaController" method="post">
		<div class="container">
			<div class="form-group">
    			<label for="txtId">Id</label>
    			<input type="text" class="form-control" id="txtId" name="txtId" value="<%=categoriaAtual.getId()%>" readonly/>
  			</div>
  			<div class="form-group">
    			<label for="txtSabor">Tipo</label>
    			<input type="text" class="form-control" id="txtTipo" name="txtTipo" value="<%=categoriaAtual.getTipo()%>"/>
			</div>
  			
			
			<div class="form-group">
				<%if (categoriaAtual.getId() == 0) { %>
					<button type="submit" class="btn btn-dark" name="cmd" value="adicionar">Adicionar</button>
				<%} else { %>
					<button type="submit" class="btn btn-dark" name="cmd" value="salvar">Salvar</button>
				<%} %>
				<button type="submit" class="btn btn-dark" name="cmd" value="pesquisar">Pesquisar</button>
</div> 																	
		</div>
		<div class="container">
			<table class="table table-striped">
				<tbody>
					<% for (Categoria c : lista) { %>
					<tr>
						<td><%=c.getId()%></td>
						<td><%=c.getTipo()%></td>
						<td>
							<div class="form-group">
								<button type="button" class="btn btn-dark" onclick="remover(<%=c.getId()%>);">Remover</button>
								<button type="button" class="btn btn-dark" onclick="editar(<%=c.getId()%>);">Editar</button>
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