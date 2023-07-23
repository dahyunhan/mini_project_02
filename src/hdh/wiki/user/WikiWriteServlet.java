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
	

@WebServlet("/wikiwrite")
public class WikiWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MySQLConnector mySQLConnector = new MySQLConnector();     
    BoardDAO board = new BoardDAO();
	
    public WikiWriteServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    String contentsTitle = request.getParameter("contentsTitle");
	    String contentsText = request.getParameter("contentstext");
	    String writerId = request.getParameter("writerId");
	        
	    mySQLConnector.insertContents(contentsTitle, contentsText, writerId);
	    // 데이터를 request에 속성으로 추가
	    request.setAttribute("contentsTitle", contentsTitle);
	    request.setAttribute("contentsText", contentsText);
	    request.setAttribute("writerId", writerId);
	    int idx = mySQLConnector.getMaxIdx();
	    
	    RequestDispatcher rd = request.getRequestDispatcher("wikiDetail?num="+idx); 
	    rd.forward(request, response);
		doGet(request, response);
	}

}