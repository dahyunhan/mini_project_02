package hdh.wiki.login;

import hdh.wiki.dao.UserDAO;
import hdh.wiki.db.MySQLConnector;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/wikilogin")
public class WikiLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public WikiLoginServlet() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MySQLConnector dbConnect = new MySQLConnector();
        request.setCharacterEncoding("UTF-8");
        dbConnect.connectMySQL();
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        UserDAO user = new UserDAO();
        user.setUserId(userId);
        user.setUserPw(userPw);
        HttpSession session = request.getSession();
        ArrayList<UserDAO> userlist = new ArrayList<UserDAO>();
        userlist = dbConnect.userAll();
        if (userId.equals("admin")) {
            session.setAttribute("user", user.getUserId());
            response.sendRedirect("userdata");
        } else {

            for (int i = 0; i < userlist.size(); i++) {

                if (userlist.get(i).getUserId().equals(userId)) {
                    if (userlist.get(i).isUserPass() == false) {
                        response.setContentType("text/html; charset=UTF-8");
                        PrintWriter writer = response.getWriter();
                        writer.println("<script>alert('승인되지 않았습니다'); document.location = 'WikiLogin.jsp';</script>");
                        writer.close();
                    } else {
                        session.setAttribute("user", user.getUserId());
                        response.sendRedirect("wikisearch");
                    }
                }
            }
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('존재하지 않은 아이디 입니다'); document.location = 'WikiLogin.jsp';</script>");
            writer.close();

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}