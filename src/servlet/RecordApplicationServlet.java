package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class RecordApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecordApplicationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String classNumber = request.getParameter("classNumber");
		if ((id != null) && (password != null) && (email != null) && (userName != null) && (classNumber != null)) {
			DBManager manager = new DBManager();
			try {
				
				if (manager.getApplicationById(id) == null && !manager.checkEmail(email)) {
					if (manager.addUserApplicationRecord(id, password, email, userName, classNumber)) {
						response.getWriter().print("success");
					} else {
						response.getWriter().print("fail");
					}
				}else if(manager.getApplicationById(id)!= null){
					response.getWriter().print("error1");
				}else if(manager.checkEmail(email)){
					response.getWriter().print("error2");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// response.getWriter().print(id+" "+password+" "+email+"
			// "+userName+" "+classNumber);
		} else {
			response.getWriter().print("there is an error!");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
