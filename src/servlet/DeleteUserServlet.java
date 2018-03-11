package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;
import model.BorrowRecord;
import model.User;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBManager manager = new DBManager();
		ArrayList<BorrowRecord> records = new ArrayList<>();
		String id = request.getParameter("id");
		boolean exist = false;
		try {
			records = manager.getBorrowingRecords();
			for(BorrowRecord record: records){
				if(record.getReader_id().equals(id) && record.getIsReturn() == 0){
					exist = true;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!exist){
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
				
				try {
					if(manager.deleteUser(user)){
						response.getWriter().print("success");
					}else{
						response.getWriter().print("failed");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			response.getWriter().print("failed");
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
