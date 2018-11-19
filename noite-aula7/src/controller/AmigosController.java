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
		String acao = request.getParameter("cmd");
		amigosController = new AmigosDAOImpl();
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		if (usuario != null) {
			try {
				if(acao.equals("emprestimos")){
					response.sendRedirect("./emprestimos.jsp");
				}
				if(acao.equals("categorias")){
					response.sendRedirect("./categoria.jsp");
				}
				if(acao.equals("adicionar")){
					Amigos amigos = new Amigos();
					amigos.setIdUsuario(usuario.getIdUsuario());
					amigos.setNome(request.getParameter("txtNome"));
					amigos.setEmail(request.getParameter("txtEmail"));				
					amigos.setTelefone(request.getParameter("txtTelefone"));
					amigosController.adicionaAmigo(amigos);
					mensagem = "Amigo adicionado com sucesso.";
				}
				if(acao.equals("pesquisar")) {
					String nomeAmigo = request.getParameter("txtNome");
					List<Amigos> lista = amigosController.pesquisarAmigo(nomeAmigo, usuario.getIdUsuario());
					session.setAttribute("LISTA", lista);
					mensagem = "Foram encontrados "+lista.size()+" amigos.";
				}
				if ("editar".equals(acao)) {
					Amigos amigos = new Amigos();
					String id = request.getParameter("txtId");
					amigos = amigosController.getAmigo(Integer.parseInt(id));
					session.setAttribute("AMIGO_ATUAL", amigos);
					mensagem = "Detalhes do amigo com o Id " + id;
				}
				if(acao.equals("remover")){
					String id = request.getParameter("txtId");
					amigosController.removeAmigo(Integer.parseInt(id));
					mensagem = "Categoria com o Id " + id + " foi removido";
					List<Amigos> lista = amigosController.pesquisarAmigo("", usuario.getIdUsuario());
					session.setAttribute("LISTA", lista);
				}
				if ("salvar".equals(acao)) {
					Amigos amigos = new Amigos();
					amigos.setIdAmigo(Integer.parseInt(request.getParameter("txtId")));
					amigos.setNome(request.getParameter("txtNome"));
					amigos.setEmail(request.getParameter("txtEmail"));				
					amigos.setTelefone(request.getParameter("txtTelefone"));
					amigosController.editaAmigo(amigos);				
					List<Amigos> lista = amigosController.pesquisarAmigo("", usuario.getIdUsuario());
					session.setAttribute("LISTA", lista);
					mensagem = "Amigo atualizada com sucesso";
				}else if(acao.equals("voltar")){
					//response.sendRedirect("./principal.jsp");
					session.setAttribute("LISTA", null);
				} 
			}catch(Throwable e){
				mensagem = "Não foi possível realizar a ação.";
				//response.sendRedirect("./amigos.jsp");
			}
			session.setAttribute("MENSAGEM", mensagem);
			response.sendRedirect("./amigos.jsp");
		} else {
			mensagem = "Faça login para pesquisar ou registrar um amigo.";
			session.setAttribute("MENSAGEM", mensagem);
			response.sendRedirect("./index.jsp");
		}
	}

}
