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
		Usuario usuario = new Usuario();
		usuario.setLogin(user);
		usuario.setSenha(senha);
		try {
			if (usuarioDAO.logar(usuario)) {
				mensagem = "Usu�rio logado!";
				usuario = usuarioDAO.getUsuario(usuario);
				session.setAttribute("USUARIO", usuario);
				response.sendRedirect("./emprestimos.jsp");
				session.setAttribute("LISTA", null);
			} else {
				mensagem = "Usu�rio ou senha incorreto";
				response.sendRedirect("./login.jsp");
			}
		} catch(Throwable e){
			mensagem = "N�o foi poss�vel logar.";
			response.sendRedirect("./login.jsp");
		}
		session.setAttribute("MENSAGEM", mensagem);
	}
}
