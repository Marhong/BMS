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

/**
 * Servlet implementation class ReturnBookByIsbn
 */
@WebServlet("/ReturnBookByIsbn")
public class ReturnBookByIsbn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnBookByIsbn() {
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
		try {
			records = manager.getBorrowingRecords();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String reader_id = request.getParameter("reader_id");
		String isbn = request.getParameter("isbn");
		String record_id = "";
		String book_id = "";
		for(BorrowRecord record: records){
			if(record.getReader_id().equals(reader_id) && record.getIsbn().equals(isbn) && record.getIsReturn() == 0){
				record_id =record.getRecord_id();
				book_id = record.getBook_id();
			}
		}
	
		if(record_id!=null && !record_id.equals("")){
		
			
			try {
				if(manager.updateBorrowingRecord(record_id)&&manager.updateReturnBookLentout(book_id)&&manager.updateReturnBookState(book_id)){
					response.getWriter().print("Successfully returned the book!");
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
