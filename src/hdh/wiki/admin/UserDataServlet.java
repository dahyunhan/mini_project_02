package hdh.wiki.admin;

import hdh.wiki.dao.UserDAO;
import hdh.wiki.db.MySQLConnector;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userdata")
public class UserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLConnector dbConnect = new MySQLConnector();
		request.setCharacterEncoding("UTF-8");
		dbConnect.connectMySQL();
		
		// 승인 과정 불린값 false를 true로
		if(request.getParameter("userIdx") != null) {
			String num1 = request.getParameter("userIdx");
			dbConnect.updateID(num1);
		}
		// 유저정보 삭제
		if(request.getParameter("userInfo") != null) {
			String num2 = request.getParameter("userInfo");
			dbConnect.deleteUser(num2);
		}
	
		ArrayList<UserDAO> userlist = new ArrayList<UserDAO>();
		userlist=dbConnect.userAll();
		request.setAttribute("userlist", userlist);
		RequestDispatcher rd = request.getRequestDispatcher("./admin/UserData.jsp");
		rd.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
