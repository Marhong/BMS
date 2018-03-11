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
import model.User;

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String isbn = request.getParameter("isbn");
		String bookName = request.getParameter("bookName");
	    String writerName = request.getParameter("writerName");
	    String type = request.getParameter("type");
	    String location = request.getParameter("location");
	    int copy = Integer.parseInt(request.getParameter("copy"));
	    int lentout = Integer.parseInt(request.getParameter("lentout"));
	    String description = request.getParameter("description");
	    String date = request.getParameter("date");
		if((id!=null) && (isbn!=null) && (bookName!=null) && (writerName!=null)&& (type!=null) && (location!=null)&& (copy!=-1)&& (lentout!=-1) && (description!=null)){
			Book book = new Book();
			
			book.setId(id);
			book.setIsbn(isbn);
			book.setName(bookName);
			book.setWriter(writerName);
			book.setType(type);
			book.setLocation(location);
			book.setCopy(copy);
			book.setLentout(lentout);
			book.setDescription(description);
			book.setDate(date);
			DBManager manager = new DBManager();
			try {
				if(manager.updateBook(book)){
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
