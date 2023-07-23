package hdh.wiki.admin;

import hdh.wiki.dao.BoardDAO;
import hdh.wiki.db.MySQLConnector;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/conlist")
public class ConListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConListServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLConnector dbConnect = new MySQLConnector();
		request.setCharacterEncoding("UTF-8");
		dbConnect.connectMySQL();
		
		// 글 정보 삭제
		if(request.getParameter("conInfo") != null) {
			String num1 = request.getParameter("conInfo");
			dbConnect.deleteCon(num1);
		}
		ArrayList<BoardDAO> boardlist = new ArrayList<BoardDAO>();
		boardlist=dbConnect.boardAll();
		request.setAttribute("boardlist", boardlist);
		RequestDispatcher rd = request.getRequestDispatcher("./admin/ConList.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
