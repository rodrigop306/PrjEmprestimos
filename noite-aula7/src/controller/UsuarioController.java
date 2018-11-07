package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UsuarioDAOImpl;
import model.Usuario;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 5787171390071875019L;
	private UsuarioDAOImpl usuarioController;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mensagem = "";
		Usuario user = new Usuario();
		usuarioController = new UsuarioDAOImpl();
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		if (usuario != null) {
			try {				
				user.setNome(request.getParameter("txtNome"));
				user.setLogin(request.getParameter("txtLogin"));				
				user.setSenha(request.getParameter("txtSenha"));
				usuarioController.adicionaUsuario(usuario);
				mensagem = "Amigo adicionado com sucesso.";
			} catch (Throwable e) {
				mensagem = "Não foi possível adicionar o amigos.";
			}
			session.setAttribute("MENSAGEM", mensagem);
			response.sendRedirect("./usuario.jsp");
		} else

		{
			mensagem = "Faça login para registrar o empréstimo.";
			response.sendRedirect("./index.jsp");
		}
	}

}
