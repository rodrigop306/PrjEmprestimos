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
		Amigos amigos = new Amigos();
		amigosController = new AmigosDAOImpl();
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		if (usuario != null) {
			try {
				if(acao.equals("adicionar")){
					amigos.setIdUsuario(usuario.getIdUsuario());
					amigos.setNome(request.getParameter("txtNome"));
					amigos.setEmail(request.getParameter("txtEmail"));				
					amigos.setTelefone(request.getParameter("txtTelefone"));
					amigosController.adicionaAmigo(amigos);
					mensagem = "Amigo adicionado com sucesso.";
				}
				if(acao.equals("pesquisar")) {
					String nomeAmigo = request.getParameter("txtNome");
					if(!nomeAmigo.trim().equals("")){
						List<Amigos> lista = amigosController.pesquisarAmigo(nomeAmigo, usuario.getIdUsuario());
						if(lista != null){
							mensagem = "Foram encontrados "+lista.size()+" amigos.";
						} else {
							mensagem = "A busca não retornou resultados.";
						}
						session.setAttribute("LISTA", lista);
					} else {
						mensagem = "Digite um nome para realizar a pesquisa.";
					}
				}
				if(acao.equals("remover")){
					String id = request.getParameter("txtId");
					amigosController.removeAmigo(Integer.parseInt(id));
					mensagem = "Amigo removido com sucesso.";
				}
				if(acao.equals("editar")){
					amigos.setIdAmigo(Integer.parseInt(request.getParameter("txtId")));
					amigos.setIdUsuario(usuario.getIdUsuario());
					amigos.setNome(request.getParameter("txtNome"));
					amigos.setEmail(request.getParameter("txtEmail"));				
					amigos.setTelefone(request.getParameter("txtTelefone"));
					amigosController.editaAmigo(amigos);
					mensagem = "Informações de amigo editada com sucesso.";
				}
			} catch(Throwable e){
				mensagem = "Não foi possível realizar a ação.";
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
