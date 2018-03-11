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
import model.Book;
import model.BorrowRecord;

/**
 * Servlet implementation class BorrowBookByIsbns
 */
@WebServlet("/BorrowBookByIsbns")
public class BorrowBookByIsbns extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowBookByIsbns() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBManager manager = new DBManager();
		String isbn1 = request.getParameter("isbn1");
		String isbn2 = request.getParameter("isbn2");
		String reader_id = request.getParameter("reader_id");
		String book_id1 ="";
		String book_id2="";
		ArrayList<Book> books = new ArrayList<>();
		try {
			books = manager.getBook();
			for(Book book: books){
				if(book.getIsbn().equals(isbn1)){
					if(book.getIsAvailable() == 1){
						book_id1 = book.getId();
					}
				}
				if(book.getIsbn().equals(isbn2)){
					if(book.getIsAvailable() == 1){
						book_id2 = book.getId();
					}
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	
		BorrowRecord record = new BorrowRecord();
		try {
			if (book_id1!=null ){
				
				Book book = manager.getBookById(book_id1);
				long borrow_time = System.currentTimeMillis();
				//long record_id  = System.currentTimeMillis();
				record.setRecord_id(String.valueOf(borrow_time+1));
				record.setReader_id(reader_id);
				record.setBook_id(book_id1);
				record.setIsbn(book.getIsbn());
				record.setBorrow_time(String.valueOf(borrow_time));
				
				long days = (long)1000*(long)60*60*(long)24*30;
				borrow_time += days;
				
				record.setReturn_time(String.valueOf(borrow_time).substring(0, 10));
				record.setRequired_time(String.valueOf(borrow_time).substring(0, 10));
				record.setOverdue(0);
				record.setIsReturn(0);
				if(manager.borrowBook(record)&&manager.updateBookState(book_id1)&&manager.updateBookLentout(book_id1)&&manager.updateReaderBorrowBooks(reader_id)){
					response.getWriter().print("Borrow successfully");
				}else{
					response.getWriter().print("there was an error!");
				}
			}else{
				response.getWriter().print("there was an error!");
			}
if (book_id2!=null ){
				
				Book book = manager.getBookById(book_id2);
				long borrow_time = System.currentTimeMillis();
				//long record_id  = System.currentTimeMillis();
				record.setRecord_id(String.valueOf(borrow_time+1));
				record.setReader_id(reader_id);
				record.setBook_id(book_id2);
				record.setIsbn(book.getIsbn());
				record.setBorrow_time(String.valueOf(borrow_time));
				
				long days = (long)1000*(long)60*60*(long)24*30;
				borrow_time += days;
				
				record.setReturn_time(String.valueOf(borrow_time).substring(0, 10));
				record.setRequired_time(String.valueOf(borrow_time).substring(0, 10));
				record.setOverdue(0);
				record.setIsReturn(0);
				if(manager.borrowBook(record)&&manager.updateBookState(book_id2)&&manager.updateBookLentout(book_id2)&&manager.updateReaderBorrowBooks(reader_id)){
					response.getWriter().print("Borrow successfully");
				}else{
					response.getWriter().print("there was an error!");
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
