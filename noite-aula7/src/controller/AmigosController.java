package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Amigos;
import model.AmigosDAOImpl;
import model.Usuario;

@WebServlet("/AmigosController")
public class AmigosController extends HttpServlet {

	private static final long serialVersionUID = 5787171390071875019L;
	private AmigosDAOImpl amigosController;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mensagem = "";
		Amigos amigos = new Amigos();
		amigosController = new AmigosDAOImpl();
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		if (usuario != null) {
			try {

				amigos.setIdUsuario(usuario.getIdUsuario());
				amigos.setNome(request.getParameter("txtNome"));
				amigos.setEmail(request.getParameter("txtEmail"));				
				amigos.setTelefone(request.getParameter("txtDataDevolucao"));
				amigosController.adicionaAmigo(amigos);
				mensagem = "Amigo adicionado com sucesso.";
			} catch (Throwable e) {
				mensagem = "Não foi possível adicionar o amigos.";
			}
			session.setAttribute("MENSAGEM", mensagem);
			response.sendRedirect("./amigos.jsp");
		} else {
			mensagem = "Faça login para registrar um amigo.";
			response.sendRedirect("./index.jsp");
		}
	}

}
