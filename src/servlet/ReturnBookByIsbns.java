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
 * Servlet implementation class ReturnBookByIsbns
 */
@WebServlet("/ReturnBookByIsbns")
public class ReturnBookByIsbns extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnBookByIsbns() {
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
		String isbn1 = request.getParameter("isbn1");
		String isbn2 = request.getParameter("isbn2");
		String record_id1 = "";
		String record_id2 = "";
		String book_id1 = "";
		String book_id2 = "";
		for(BorrowRecord record: records){
			if(record.getReader_id().equals(reader_id) && record.getIsbn().equals(isbn1) && record.getIsReturn() == 0){
				record_id1 =record.getRecord_id();
				book_id1 = record.getBook_id();
			}
			if(record.getReader_id().equals(reader_id) && record.getIsbn().equals(isbn2) && record.getIsReturn() == 0){
				record_id2 =record.getRecord_id();
				book_id2 = record.getBook_id();
			}
		}
	
		if(record_id1!=null && !record_id1.equals("")){
		
			
			try {
				if(manager.updateBorrowingRecord(record_id1)&&manager.updateReturnBookLentout(book_id1)&&manager.updateReturnBookState(book_id1)){
					response.getWriter().print("Successfully returned the book!");
				}else{
					response.getWriter().print("failed");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	if(record_id2!=null && !record_id2.equals("")){
		
			
			try {
				if(manager.updateBorrowingRecord(record_id2)&&manager.updateReturnBookLentout(book_id2)&&manager.updateReturnBookState(book_id2)){
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
