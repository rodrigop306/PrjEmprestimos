package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Amigos;
import model.AmigosDAOImpl;
import model.Categoria;
import model.CategoriaDAOImpl;
import model.Usuario;

@WebServlet("/PrincipalController")
public class PrincipalController extends HttpServlet {

	private static final long serialVersionUID = 5500435974113774805L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("cmd");
		String mensagem = "";
		HttpSession session = request.getSession();
		Usuario u = (Usuario) session.getAttribute("USUARIO");
		if (u != null) {
			try {
				if (acao.equals("Amigos")) {
					response.sendRedirect("./amigos.jsp");
				} 
				if (acao.equals("Objetos")) {
					AmigosDAOImpl amigo = new AmigosDAOImpl();
					List<Amigos> lista = amigo.pesquisarAmigo("", u.getIdUsuario());
					session.setAttribute("LISTAAMIGO", lista);
					response.sendRedirect("./emprestimos.jsp");
				} 
				if (acao.equals("Categorias")) {
					response.sendRedirect("./categoria.jsp");
				} 
				if (acao.equals("Sair")) {
					mensagem = "Até mais, "+u.getNome()+"!";
					session.setAttribute("USUARIO", null);
					response.sendRedirect("./index.jsp");
				}
			} catch (Throwable e) {
				mensagem = "Não foi possível realizar a ação.";
			}
		} else {
			mensagem = "Faça o login para acessar a página.";
			response.sendRedirect("./index.jsp");
		}
		session.setAttribute("MENSAGEM", mensagem);
	}
}