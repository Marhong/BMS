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
import model.User;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetBookByType
 */
@WebServlet("/GetBookByType")
public class GetBookByType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookByType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String type = request.getParameter("type");
		String keyword = request.getParameter("keywords");
	
		DBManager manager = new DBManager();
		ArrayList<Book> users = new ArrayList<>();
		ArrayList<Book> specialBook = new ArrayList<>();
		try {
			users=manager.getBook();
			switch (type) {
			case "1":
				for(Book book: users){
					if(book.getName().contains(keyword)){
						specialBook.add(book);
					}
				}
				break;

			case "2":
				for(Book book: users){
					if(book.getWriter().contains(keyword)){
						specialBook.add(book);
					}
				}
				break;
			case "3":
				for(Book book: users){
					if(book.getIsbn().contains(keyword)){
						specialBook.add(book);
					}
				}
				break;
			}
			if(specialBook!=null){
				JSONArray usersJson = JSONArray.fromObject(specialBook);
				
				response.getWriter().print(usersJson);
//				for(User user: users){
//					response.getWriter().print(user.toString()+"\n");
//				}
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
