package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;
import model.Book;
import model.User;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String isbn = request.getParameter("isbn");
		String bookName = request.getParameter("bookName");
	    String writerName = request.getParameter("writerName");
	    String type = request.getParameter("type");
	    String location = request.getParameter("location");
	    String copy = request.getParameter("copy");
	    String description = request.getParameter("description");
	    String date = request.getParameter("date");
	    String image = request.getParameter("image");
		if((isbn!=null) && (bookName!=null) && (writerName!=null)&& (type!=null) && (location!=null)&& (copy!=null) && (description!=null)){
			DBManager manager = new DBManager();
			for(int i=1;i<=Integer.parseInt(copy);i++){
				Book book = new Book();
				long id = System.currentTimeMillis();
				book.setId(String.valueOf(id)+i);
				book.setIsbn(isbn);
				book.setName(bookName);
				book.setWriter(writerName);
				book.setType(type);
				book.setLocation(location);
				book.setCopy(Integer.parseInt(copy));
				book.setDescription(description);
				book.setLentout(0);
				book.setDate(date);
				book.setTimes(0);
				book.setImage(image);
				try {
					if(manager.addBook(book)){
						response.getWriter().print("success");
					}else{
						response.getWriter().print("fail");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
