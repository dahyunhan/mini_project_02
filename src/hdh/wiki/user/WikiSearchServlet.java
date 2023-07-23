package hdh.wiki.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wiki.dao.BoardDAO;
import com.wiki.db.MySQLConnector;


@WebServlet("/wikisearch")
public class WikiSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<BoardDAO> boards = new ArrayList<BoardDAO>();
	private MySQLConnector mySQLConnector = new MySQLConnector();
    
	public WikiSearchServlet() {
        super();
     
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String contents = request.getParameter("keyword");;
		if (contents == null) {
			
			boards = mySQLConnector.selectBest3();
			request.setAttribute("boards", boards);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("board/WikiSearch.jsp");
			requestDispatcher.forward(request, response);
			
		}else {
			String src = "contentsText";
			boards = mySQLConnector.searchKeyword(src,contents);
			request.setAttribute("boards", boards);
			request.setAttribute("search", src);
			request.setAttribute("keyword", contents);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("./wikiresult?search="+ src +"&?keyword="+ contents);
			requestDispatcher.forward(request, response);
		}
	}   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}


}