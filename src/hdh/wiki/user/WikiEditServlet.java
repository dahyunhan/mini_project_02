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


@WebServlet("/wikiEdit")
public class WikiEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MySQLConnector mySQLConnector = new MySQLConnector();
    private BoardDAO board = new BoardDAO();

    public WikiEditServlet() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idx = Integer.parseInt(request.getParameter("idx"));
        System.out.println(idx);
        board = mySQLConnector.selectDetailPage(idx);
        request.setAttribute("board", board);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("board/WikiEdit.jsp");
        requestDispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String contentsTitle = request.getParameter("title");
        String contentsText = request.getParameter("text");
        String updateFlag = request.getParameter("flag");

        int contentsIdx = Integer.parseInt(request.getParameter("idx"));
        if (updateFlag == null) {

            request.setAttribute("Title", contentsTitle);
            request.setAttribute("Text", contentsText);
            request.setAttribute("Idx", contentsIdx);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("board/WikiEdit.jsp");
            requestDispatcher.forward(request, response);

        } else {

            mySQLConnector.updateContents(contentsTitle, contentsText, contentsIdx);

            request.setAttribute("Title", contentsTitle);
            request.setAttribute("Text", contentsText);
            request.setAttribute("Idx", contentsIdx);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("wikiDetail?num=" + contentsIdx);
            requestDispatcher.forward(request, response);
        }
    }
}