package hdh.wiki.user;

import hdh.wiki.dao.BoardDAO;
import hdh.wiki.db.MySQLConnector;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/wikiDetail")
public class WikiDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MySQLConnector mysql = new MySQLConnector();
    private BoardDAO board = new BoardDAO();


    public WikiDetailServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num = Integer.parseInt(request.getParameter("num"));
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        board = mysql.selectDetailPage(num);
        mysql.updateHitCount(num);
        request.setAttribute("board", board);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("board/WikiDetail.jsp");
        requestDispatcher.forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}