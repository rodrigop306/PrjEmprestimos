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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		emprestimosDAO = new UsuarioDAOImpl();
		String user = request.getParameter("txtLogin");
		String senha = request.getParameter("txtPassword");
		String mensagem = "";
		System.out.println(user + ", " + senha);
		HttpSession session = request.getSession();
//		try {
			if (emprestimosDAO.pesquisaUsuario(user, senha)) {
				mensagem = "Usu�rio logado!";
				response.sendRedirect("./emprestimos.jsp");
			} else {
				mensagem = "Usu�rio ou senha incorreto";
				response.sendRedirect("./login.jsp");
			}
		/**} catch (Throwable e) {
			mensagem = "Opa, deu erro!";
		}*/
		session.setAttribute("MENSAGEM", mensagem);
	}
}
