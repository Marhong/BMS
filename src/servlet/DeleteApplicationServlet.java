package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;
import model.User;

/**
 * Servlet implementation class DeleteApplicationServlet
 */
@WebServlet("/DeleteApplicationServlet")
public class DeleteApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String password = request.getParameter("password");
	    String email = request.getParameter("email");
	    String userName = request.getParameter("userName");
	    String classNumber = request.getParameter("classNumber");
		if((id!=null) && (password!=null) && (email!=null)&& (userName!=null) && (classNumber!=null)){
			User user = new User();
			user.setId(id);
			user.setPassword(password);
			user.setEmail(email);
			user.setUserName(userName);
			user.setClassNumber(classNumber);
			DBManager manager = new DBManager();
			try {
				if(manager.deleteUserApplicationRecord(user)){
					response.getWriter().print("success");
				}else{
					response.getWriter().print("failed");
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
