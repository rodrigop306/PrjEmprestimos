package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import model.UsuarioDAOImpl;
import sun.nio.cs.HistoricallyNamedCharset;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UsuarioDAOImpl usuarioDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("USUARIO", null);
		usuarioDAO = new UsuarioDAOImpl();
		String user = request.getParameter("txtLogin");
		String senha = request.getParameter("txtPassword");
		String mensagem = "";
		String acao = request.getParameter("cmd");
		Usuario usuario = new Usuario();
		usuario.setLogin(user);
		usuario.setSenha(senha);
		if(acao.equals("Logar")){
			try {
				if (usuarioDAO.logar(usuario)) {
					mensagem = "Usuário logado!";
					usuario = usuarioDAO.getUsuario(usuario);
					session.setAttribute("USUARIO", usuario);
					response.sendRedirect("./principal.jsp");
					session.setAttribute("LISTA", null);
				} else {
					mensagem = "Usuário ou senha incorreto";
					response.sendRedirect("./index.jsp");
					
				}
			} catch(Throwable e){
				mensagem = "Não foi possível logar.";
				response.sendRedirect("./index.jsp");
				
			}
			session.setAttribute("MENSAGEM", mensagem);
		}else if(acao.equals("Cadastro")){
			session.setAttribute("LISTA", null);
			response.sendRedirect("./usuario.jsp");
			session.setAttribute("LISTA", null);
			
		}
		
	}
}
