<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Emprestimos, model.Amigos, model.AmigosDAOImpl, model.CategoriaDAOImpl, model.Categoria, java.util.List, java.util.ArrayList"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Emprestimos</title>
	<link rel="stylesheet" href="./css/bootstrap.min.css"/>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"></script>
	<script>
	function remover( id ) {
		if (confirm("Remove o empréstimo com ID " + id+"?")) {
			$('#formEmprestimo').empty();
			$('#formEmprestimo').append('<input type="hidden" name="txtId" value="' + id + '"/>');
			$('#formEmprestimo').append('<input type="hidden" name="cmd" value="remover"/>');
			$('#formEmprestimo').submit();
		}
	}
	function editar( id ) {
		$('#formEmprestimo').empty();
		$('#formEmprestimo').append('<input type="hidden" name="txtId" value="' + id + '"/>');
		$('#formEmprestimo').append('<input type="hidden" name="cmd" value="editar"/>');
		$('#formEmprestimo').submit();
	}	
	</script>

</head>
<body>
	<h2 align='Center'>Emprestimos</h2>
	
	<%  String msg = (String)session.getAttribute("MENSAGEM");
		int idamigo=0;
		List<Emprestimos> lista = (List<Emprestimos>)session.getAttribute("LISTA");
		List<Categoria> listaCat = (List<Categoria>)session.getAttribute("LISTACAT");
		List<Amigos> listaAmig = (List<Amigos>)session.getAttribute("LISTAAMIGO");
	   if (lista == null) { 
	   	   lista = new ArrayList<Emprestimos>();
	   } else { 
		   session.setAttribute("LISTA", null);
	   }
	   if (listaAmig == null) { 
		   listaAmig = new ArrayList<Amigos>();
	   } else { 
		   session.setAttribute("LISTAAMIGO", null);
	   }
	   if (listaCat == null) { 
		   listaCat = new ArrayList<Categoria>();
	   } else { 
		   session.setAttribute("LISTACAT", null);
	   }
	   
	   
	   Emprestimos emprestimoAtual = (Emprestimos)session.getAttribute("EMP_ATUAL");
	   if (emprestimoAtual == null) { 
		   emprestimoAtual  = new Emprestimos();
	   } else { 
		   session.setAttribute("EMP_ATUAL", null);
	   }
	   
	   
	   if (msg != null) {
		   session.setAttribute("MENSAGEM", null);
	%>
			<h3 class="alert alert-danger"><%=msg%></h3>
	<% } %>
	
	
	<form id="formEmprestimo" action="./EmprestimoController" method="post">
		<div class="container" align='Center'>	
				<div class="form-group">
					<button type="submit" class="btn btn-dark btn-lg" name="cmd" value="amigos">Amigos</button>
					<button type="submit" class="btn btn-dark btn-lg" name="cmd" value="categorias">Categorias</button>
				</div>
		</div>
		<div class="container">
			<div class="form-group">
			<%if(emprestimoAtual.getIdEmprestimos()==0){ %>
    			<label for="txtAcao">Ação</label>
    			<select class="form-control" id="txtAcao" name="txtAcao">
      				<option value="emprestar">Emprestar</option>
      				<option value="pegarEmprestado">Pegar emprestado</option>
				</select>
			<%}else if(emprestimoAtual.getIdAmigoDono()!=0){ %>
				<label for="txtAcao">Ação</label>
    			<select class="form-control" id="txtAcao" name="txtAcao" readonly>
      				<option value="pegarEmprestado">Pegar emprestado</option>
				</select>
			<%}else{ %>
				<label for="txtAcao">Ação</label>
    			<select class="form-control" id="txtAcao" name="txtAcao" readonly>
      				<option value="emprestar">Emprestar</option>
				</select>
			<%} %>
			</div>	
			<div class="form-group">
    			<label for="txtId">Id</label>
    			<input type="text" class="form-control" id="txtId" name="txtId" value="<%=emprestimoAtual.getIdEmprestimos()%>" readonly/>
  			</div>
			<div class="form-group">
    			<label for="txtNomeObjeto">Nome do Objeto</label>
    			<input type="text" class="form-control" id="txtNomeObjeto" name="txtNomeObjeto" value="<%=emprestimoAtual.getNomeObjeto()%>"/>
  			</div>
  			<div class="form-group" id="Emprestei">
    			<label for="txtEmprestar" id = "Emprestimo">Emprestei para</label>
    			
    			<select class="form-control" id="amigo" name="amigo">
      				<%for(Amigos a : listaAmig){ %>
      					<option value="<%=a.getIdAmigo()%>" ><%= a.getNome() %></option>
      				<% } %>
				</select>
  			</div> 
  			  	
			<div class="form-group">
    			<label for="txtCategoria">Categoria</label>
    			<select class="form-control" id="txtCategoria" name="txtCategoria">
      				<%for(Categoria c : listaCat){ %>
      					<option value="<%=c.getId()%>"><%= c.getTipo() %></option>
      				<% } %>
				</select>
			</div>
			<div class="form-group">
    			<label for="txtDataEmprestimo">Data de emprestimo</label>
    			<input type="text" class="form-control" id="txtDataEmprestimo" name="txtDataEmprestimo" value="<%=emprestimoAtual.getDataEmprestimo() %>"/>
  			</div>  
  			<div class="form-group">
    			<label for="txtDataDevolucao">Data de devolução</label>
    			<input type="text" class="form-control" id="txtDataDevolucao" name="txtDataDevolucao" value="<%=emprestimoAtual.getDataDevolucao() %>"/>
  			</div>  
  			<div class="form-group">
    			<label for="txtStatus">Status</label>
    			<select class="form-control" id="txtStatus" name="txtStatus">
      				<option value="Em dia">Em dia</option>
      				<option value="Concluido">Concluído</option>
      				<option value="Atrasado">Atrasado</option>
				</select>
			</div>
			<div class="form-group">
    			<label for="txtDetalhes">Detalhes</label>
    			<input type="text" class="form-control" id="txtDetalhes" name="txtDetalhes" value="<%=emprestimoAtual.getDetalhesEmprestimo() %>"/>
			</div>
			<div class="form-group">
				<%if (emprestimoAtual.getIdEmprestimos() == 0) { %>
					<button type="submit" class="btn btn-dark" name="cmd" value="adicionar">Adicionar</button>
				<%} else { %>
					<button type="submit" class="btn btn-dark" name="cmd" value="salvar">Salvar</button>
				<%} %>
				<button type="submit" class="btn btn-dark" name="cmd" value="pesquisar">Pesquisar</button>
				<button type="submit" class="btn btn-dark" name="cmd" value="voltar">Voltar</button>
		</div> 																							
		</div>
		<script language="javascript">
			var acao = document.getElementById('txtAcao');
			document.getElementById( 'txtAcao' ).addEventListener( 'change', function(){
    			if(this.value === 'emprestar') {
    				$('#Emprestimo').html('Emprestei para');
  				}else if(this.value === null) {
  					$('#Emprestimo').html('Peguei de');    				
  				}    			
    			else {
    				$('#Emprestimo').html('Peguei de');
    			}
			});
		</script>
		<div class="container">
			<table class="table table-striped">
				<tbody>
					<% for (Emprestimos e : lista) { %>
					<tr>
						<td><%=e.getIdEmprestimos()%></td>
						<td><%=e.getNomeObjeto()%></td>
						<td><%=e.getNomeAmigo()%></td>
						<td><%=e.getNomeCategoria()%></td>
						<td><%=e.getDataEmprestimo()%></td>
						<td><%=e.getDataDevolucao()%></td>						
						<td><%=e.getStatus()%></td>
						<td><%=e.getDetalhesEmprestimo()%> </td>
						<td>
							<div class="form-group">
								<button type="button" class="btn btn-dark" onclick="remover(<%=e.getIdEmprestimos()%>);">Remover</button>
								<button type="button" class="btn btn-dark" onclick="editar(<%=e.getIdEmprestimos()%>);">Editar</button>
							</div>																		
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</div>

	</form>
	<%
	if(emprestimoAtual.getIdAmigoDono()!=0){
		idamigo = emprestimoAtual.getIdAmigoDono();
	}else if(emprestimoAtual.getIdAmigoEmprestimo()!=0){
		idamigo = emprestimoAtual.getIdAmigoEmprestimo();
	}
	%>
	<script>
		$('#txtStatus').val("<%=emprestimoAtual.getStatus()%>");
		$('#txtCategoria').val("<%=emprestimoAtual.getIdCategoria()%>");
		$('#amigo').val("<%=idamigo%>");
		
	</script>
</body>
</html>
