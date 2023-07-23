package hdh.wiki.login;

import hdh.wiki.dao.UserDAO;
import hdh.wiki.db.MySQLConnector;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/wikijoin")
public class WikiJoinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO user = new UserDAO();
    private MySQLConnector mySQLConnector = new MySQLConnector();

    public WikiJoinServlet() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        user.setUserId(request.getParameter("USERID"));
        //패스워드, 패스워드확인을 비교하기 위해 변수에 저장.
        String userpw1 = request.getParameter("USERPW");
        String userpw2 = request.getParameter("USERPW2");
        user.setUserName(request.getParameter("USERNAME"));
        user.setUserPw(userpw2);

        if (userpw1.equals(userpw2)) { // 패스워드, 패스워드 확인이 일치할 경우
            int result = mySQLConnector.UserJoin(user);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();

            if (result == 1) {
                writer.println("<script>alert('회원 가입 성공입니다'); location.href='WikiLogin.jsp';</script>");
            } else {
                writer.println("<script>alert('아이디가 중복입니다'); history.back();</script>");
            }
            writer.close();
            response.sendRedirect("/board/WikiLogin.jsp");
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('비밀번호가 일치하지 않습니다'); history.back();</script>");
            writer.close();
            response.sendRedirect("/board/WikiLogin.jsp");
        }
    }

}