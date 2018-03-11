package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.prism.impl.Disposer.Record;

import db.DBManager;
import model.Book;
import model.BorrowRecord;

/**
 * Servlet implementation class BorrowBookByIsbn
 */
@WebServlet("/BorrowBookByIsbn")
public class BorrowBookByIsbn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowBookByIsbn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager manager = new DBManager();
		String isbn = request.getParameter("isbn");
		String reader_id = request.getParameter("reader_id");
		String book_id ="";
		String formerIsbn = "";
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BorrowRecord> records = new ArrayList<>();
		try {
			books = manager.getBook();
			records = manager.getBorrowingRecords();
			for(BorrowRecord record: records){
				if(record.getReader_id().equals(reader_id) && record.getIsReturn() == 0){
					formerIsbn = record.getIsbn();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!formerIsbn.equals(isbn)){
			for(Book book: books){
				if(book.getIsbn().equals(isbn)){
					if(book.getIsAvailable() == 1){
						book_id = book.getId();
					}
				}
			}
			BorrowRecord record = new BorrowRecord();
			if (book_id!=null && (!book_id.equals(""))){
				Book book = new Book();
				try {
					book = manager.getBookById(book_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				try {
					
					if(manager.borrowBook(record)&&manager.updateBookState(book_id)&&manager.updateBookLentout(book_id)&&manager.updateReaderBorrowBooks(reader_id)){
						response.getWriter().print("Borrow successfully");
					}else{
						response.getWriter().print("there was an error!");
					}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		
		
		
		
}else{
	response.getWriter().print("Sorry,you can not borrow two books of the same kind!");
}
	}
		// TODO Auto-generated method stub
//		DBManager manager = new DBManager();
//		String isbn = request.getParameter("isbn");
//		String reader_id = request.getParameter("reader_id");
//		String book_id ="";
//		
//		ArrayList<Book> books = new ArrayList<>();
//		
//		try {
//			books = manager.getBook();
//			for(Book book: books){
//				if(book.getIsbn().equals(isbn)){
//					if(book.getIsAvailable() == 1){
//						book_id = book.getId();
//					}
//				}
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		
//		
//		BorrowRecord record = new BorrowRecord();
//		try {
//			if (book_id!=null ){
//				Book book = manager.getBookById(book_id);
//				long borrow_time = System.currentTimeMillis();
//				//long record_id  = System.currentTimeMillis();
//				record.setRecord_id(String.valueOf(borrow_time+1));
//				record.setReader_id(reader_id);
//				record.setBook_id(book_id);
//				record.setIsbn(book.getIsbn());
//				record.setBorrow_time(String.valueOf(borrow_time));
//				
//				long days = (long)1000*(long)60*60*(long)24*30;
//				borrow_time += days;
//				
//				record.setReturn_time(String.valueOf(borrow_time).substring(0, 10));
//				record.setRequired_time(String.valueOf(borrow_time).substring(0, 10));
//				record.setOverdue(0);
//				record.setIsReturn(0);
//				if(manager.borrowBook(record)&&manager.updateBookState(book_id)&&manager.updateBookLentout(book_id)&&manager.updateReaderBorrowBooks(reader_id)){
//					response.getWriter().print("Borrow successfully");
//				}else{
//					response.getWriter().print("there was an error!");
//				}
//			}else{
//				response.getWriter().print("there was an error!");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
