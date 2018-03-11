package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("GBK");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		if(id!= null && password!=null){
			DBManager dbManager = new DBManager();
			try {
				String error = dbManager.checkAccount(id, password);
				Cookie myCookie=new Cookie("id",id);  
	            myCookie.setMaxAge(60*60*24*7);  
	            response.addCookie(myCookie);
				if(error.equals("correct")){
					if(dbManager.getSomeUser(id) == 4){
						response.getWriter().print("http://localhost:8088/NWPU/manager2Pages/book-information.html");
					}else if(dbManager.getSomeUser(id) == 3){
						response.getWriter().print("http://localhost:8088/NWPU/manager2Pages/reader-book-information.html");
					}else if(dbManager.getSomeUser(id) == 2){
						response.getWriter().print("http://localhost:8088/NWPU/manager2Pages/employee-book-information.html");
					}else {
						response.getWriter().print("http://www.baidu.com");
					}
				}else{
					response.getWriter().print("incorrect");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
