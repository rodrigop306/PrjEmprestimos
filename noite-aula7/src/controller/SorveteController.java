package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GenericDAOException;
import dao.SorveteDAO;
import dao.SorveteDAOImpl;
import entidade.Sorvete;
@WebServlet("/SorveteController")
public class SorveteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SorveteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter()
		.append("Para acessar utilize a pagina de <a href=\"./sorvete.jsp\">sorvete</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();
		try {
			SorveteDAO sDao = new SorveteDAOImpl();
			if ("adicionar".equals(cmd)) {
				Sorvete s = new Sorvete();
				s.setSabor(request.getParameter("txtSabor"));
				s.setPreco(Float.parseFloat(request.getParameter("txtPreco")));
				s.setTipo(request.getParameter("txtTipo"));
				s.setCobertura(request.getParameter("txtCobertura"));
				sDao.adicionar( s );
				msg = "Sorvete foi adicionado com sucesso";
			} else if ("pesquisar".equals(cmd)) {
				List<Sorvete> lista = sDao.presquisarPorSabor(
						request.getParameter("txtSabor"));
				session.setAttribute("LISTA", lista);
				msg = "Foram encontrados " + lista.size() + " sorvetes";
			} else if ("remover".equals(cmd)) {
				String id = request.getParameter("txtId");
				sDao.remover(Long.parseLong(id));
//				List<Sorvete> lista = sDao.presquisarPorSabor(
//						request.getParameter("txtSabor"));
//				session.setAttribute("LISTA", lista);
				msg = "Sorvete com o Id " + id + " foi removido";
			}
		} catch (GenericDAOException | NumberFormatException e) {
			e.printStackTrace();
			msg = "Erro ao adicionar este sorvete";
		}

		session.setAttribute("MENSAGEM", msg);
		response.sendRedirect("./sorvete.jsp");

		//		List<Sorvete> lista = (List<Sorvete>) getServletContext().getAttribute("LISTA");
		//		if (lista == null) { 
		//			lista = new Vector<Sorvete>();
		//			getServletContext().setAttribute("LISTA", lista);
		//		}
		//		lista.add( s );
		//		System.out.println( lista.size() );
	}

}
