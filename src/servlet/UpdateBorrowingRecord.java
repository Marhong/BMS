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
 * Servlet implementation class UpdateBorrowingRecord
 */
@WebServlet("/UpdateBorrowingRecord")
public class UpdateBorrowingRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBorrowingRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String record_id = request.getParameter("record_id");
		String book_id = request.getParameter("book_id");
		if(record_id!=null){
		
			DBManager manager = new DBManager();
			try {
				if(manager.updateBorrowingRecord(record_id)&&manager.updateReturnBookLentout(book_id)&&manager.updateReturnBookState(book_id)){
					response.getWriter().print(record_id);
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
