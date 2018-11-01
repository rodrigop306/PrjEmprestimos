<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidade.Emprestimos, java.util.List, java.util.ArrayList"%>   
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
			if (confirm("Remove o sorvete com id " + id)) {
				$('#formEmprestimo').empty();
				$('#formEmprestimo').append('<input type="hidden" name="txtId" value="' + id + '"/>');
				$('#formEmprestimo').append('<input type="hidden" name="cmd" value="remover"/>');
				$('#formEmprestimo').submit();
			}
		}
	</script>
</head>
<body>
	<h2>Emprestimos</h2>
	
	<%  String msg = (String)session.getAttribute("MENSAGEM");
		List<Emprestimos> lista = (List<Emprestimos>)session.getAttribute("LISTA");
	   if (lista == null) { 
	   	   lista = new ArrayList<Emprestimos>();
	   } else { 
		   session.setAttribute("LISTA", null);
	   }
	   
	   if (msg != null) {
		   session.setAttribute("MENSAGEM", null);
	%>
			<h3 class="alert alert-danger"><%=msg%></h3>
	<% } %>
	
	
	<form id="formEmprestimo" action="./SorveteController" method="post">
		<div class="container">
			<div class="form-group">
    			<label for="txtAcao">Ação</label>
    			<select class="form-control" id="txtAcao" name="txtAcao">
      				<option value="emprestar">Emprestar</option>
      				<option value="pegarEmprestado">Pegar emprestado</option>
				</select>
			</div>	
			<div class="form-group">
    			<label for="txtId">Id</label>
    			<input type="text" class="form-control" id="txtId" name="txtId" readonly/>
  			</div>
			<div class="form-group">
    			<label for="txtNomeObjeto">Nome do Objeto</label>
    			<input type="text" class="form-control" id="txtNomeObjeto" name="txtNomeObjeto"/>
  			</div>
  			<div class="form-group" id="Emprestei">
    			<label for="txtEmprestar" id = "Emprestimo">Emprestei para</label>
    			<input type="text" class="form-control" id="txtEmprestar" name="txtEmprestar"/>
  			</div> 
  			  	
			<div class="form-group">
    			<label for="txtCategoria">Categoria</label>
    			<select class="form-control" id="txtCategoria" name="txtCategoria">
      				<option value="massa2k">Massa 2Kg</option>
      				<option value="massa1k">Massa 1Kg</option>
      				<option value="massa250">Massa 250g</option>
				    <option value="picole">Picole</option>
				</select>
			</div>
			<div class="form-group">
    			<label for="txtDataEmprestimo">Data de emprestimo</label>
    			<input type="text" class="form-control" id="txtDataEmprestimo" name="txtDataEmprestimo"/>
  			</div>  
  			<div class="form-group">
    			<label for="txtDataDevolucao">Data de devolução</label>
    			<input type="text" class="form-control" id="txtDataDevolucao" name="txtDataDevolucao"/>
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
    			<input type="text" class="form-control" id="txtDetalhes" name="txtDetalhes"/>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary" name="cmd" value="adicionar">Adicionar</button>
				<button type="submit" class="btn btn-primary" name="cmd" value="pesquisar">Pesquisar</button>
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
						<td><%=e.getIdAmigoDono()%></td>
						<td><%=e.getIdAmigoEmprestimo()%></td>
						<td><%=e.getDataEmprestimo()%></td>
						<td><%=e.getDataDevolucao()%></td>						
						<td><%=e.getStatus()%></td>
						<td><%=e.getDetalhesEmprestimo()%> </td>
						<td>
							<div class="form-group">
								<button type="button" class="btn btn-primary" onclick="remover(<%=e.getIdEmprestimos()%>);">Remover</button>
								<button type="button" class="btn btn-primary" onclick="editar(<%=e.getIdEmprestimos()%>);">Editar</button>
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