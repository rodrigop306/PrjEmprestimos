package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UsuarioDAOImpl;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UsuarioDAOImpl emprestimosDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		emprestimosDAO = new UsuarioDAOImpl();
		String user = request.getParameter("txtLogin");
		String senha = request.getParameter("txtPassword");
		String mensagem = "";
		HttpSession session = request.getSession();
		if(emprestimosDAO.pesquisaUsuario(user, senha)){
			mensagem = "Usuário logado!";
		} else {
			mensagem = "Usuário ou senha incorreto";
		}
		session.setAttribute("MENSAGEM", mensagem);
		response.sendRedirect("./login.jsp");
	}
}
