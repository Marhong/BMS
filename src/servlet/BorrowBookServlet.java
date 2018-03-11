package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;
import model.Book;
import model.BorrowRecord;

/**
 * Servlet implementation class BorrowBookServlet
 */
@WebServlet("/BorrowBookServlet")
public class BorrowBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			DBManager manager = new DBManager();
			String reader_id = request.getParameter("reader_id");
			String book_id = request.getParameter("book_id");
			
			
			BorrowRecord record = new BorrowRecord();
			try {
				if((manager.checkReaderId(reader_id)==true) && book_id!=null ){
					Book book = manager.getBookById(book_id);
					long borrow_time = System.currentTimeMillis();
					//long record_id  = System.currentTimeMillis();
					record.setRecord_id(String.valueOf(borrow_time+1));
					record.setReader_id(reader_id);
					record.setBook_id(book_id);
					record.setIsbn(book.getIsbn());
					record.setBorrow_time(String.valueOf(borrow_time));
					
					long days = (long)1000*(long)60*60*(long)24*30;
					borrow_time += days;
					
					record.setReturn_time(String.valueOf(borrow_time).substring(0, 10));
					record.setRequired_time(String.valueOf(borrow_time).substring(0, 10));
					record.setOverdue(0);
					record.setIsReturn(0);
					if(manager.getUserById(reader_id).getFine()>30) {
						response.getWriter().print("Please pay off the fine first!");
					}else {
						if(manager.borrowBook(record)&&manager.updateBookState(book_id)&&manager.updateBookLentout(book_id)&&manager.updateReaderBorrowBooks(reader_id)){
							response.getWriter().print("Borrow successfully");
						}else{
							response.getWriter().print("there was an error!");
						}
					}
				}else{
					response.getWriter().print("there was an error!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
