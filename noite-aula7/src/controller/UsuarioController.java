package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import model.UsuarioDAOImpl;

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
		String cmd = request.getParameter("cmd");
		String txtLogin = (request.getParameter("txtLogin"));
		String txtNome = (request.getParameter("txtNome"));
		String txtSenha = (request.getParameter("txtSenha"));
		HttpSession session = request.getSession();
		if (cmd.equals("adicionar")) {
			if((!txtLogin.trim().equals("") && txtLogin != null)
					&& (!txtNome.trim().equals("") && txtNome != null)
					&& (!txtSenha.trim().equals("") && txtSenha != null)){
				try {
					usuarioController = new UsuarioDAOImpl();
					if(!usuarioController.existeUsuario(txtLogin)){
						Usuario usuario = new Usuario();
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						Date date = new Date();
						usuario.setLogin(request.getParameter("txtLogin"));
						usuario.setNome(request.getParameter("txtNome"));
						usuario.setSenha(request.getParameter("txtSenha"));
						usuario.setDataCadastro(dateFormat.format(date));
						usuarioController.adicionaUsuario(usuario);
						mensagem = "Seu cadastro foi realizado com sucesso!";
						response.sendRedirect("./emprestimos.jsp");
					} else {
						mensagem = "Login já cadastrado no sistema. Por favor, digite outro";
						response.sendRedirect("./usuario.jsp");
					}
				} catch(Throwable e){
					mensagem = "Erro ao se registrar.";
				}
				session.setAttribute("MENSAGEM", mensagem);
			} else {
				mensagem = "Preencha todos os campos para efetuar o cadastro.";
				session.setAttribute("MENSAGEM", mensagem);
				response.sendRedirect("./usuario.jsp");
			}
		} else {
			response.sendRedirect("./index.jsp");
		}
	}
}
