<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.LoginController, java.util.List, java.util.ArrayList"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina de Login</title>
<link rel="stylesheet" href="./css/bootstrap.min.css"/>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<h2 align='Center'>Login do sistema</h2>
	<%
		String msg = (String)session.getAttribute("MENSAGEM");
		if (msg != null) {
			session.setAttribute("MENSAGEM", null);
	%>
			<h3><script>alert("<%=msg%>");</script></h3>
	<%  } %>
	
	<form action="./LoginController" method="post">
		
			<div class="container">
				<div class="form-group">
    				<label for="txtLogin">Login:</label>
    				<input type="text" class="form-control" id="txtLogin" name="txtLogin"/>
  				</div>  			 
				<div class="form-group">
    				<label for="txtLogin">Senha:</label>
    				<input type="password" class="form-control" id="txtPassword" name="txtPassword"/>
  				</div> 		
				<div class="form-group">
					<button type="submit" class="btn btn-dark" name="cmd" value="Logar">Logar</button>
					<button type="submit" class="btn btn-dark" name="cmd" value="Cadastro">Registrar-se</button>
				</div>
			</div>
	</form>
</body>
</html>