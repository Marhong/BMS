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
import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetRecordByType
 */
@WebServlet("/GetRecordByType")
public class GetRecordByType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRecordByType() {
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
		ArrayList<BorrowRecord> records = new ArrayList<>();
	
		ArrayList<BorrowRecord> specialRecords = new ArrayList<>();
	
 		try {
			records=manager.getBorrowingRecords();
	
			switch (type) {
	
			case "1":
				for(BorrowRecord record: records){
					if((manager.getBookById(record.getBook_id())).getName().contains(keyword) ){
						specialRecords.add(record);
					}
				}
			
				break;
			case "2":
				for(BorrowRecord book: records){
					if(book.getReader_id().contains(keyword)){
						specialRecords.add(book);
					}
				}
				break;
			case "3":
				for(BorrowRecord record: records){
					if((manager.getBookById(record.getBook_id())).getName().contains(keyword) ){
						specialRecords.add(record);
					}
				}
			
				break;
			case "4":
				for(BorrowRecord book: records){
					if(book.getReader_id().contains(keyword)){
						specialRecords.add(book);
					}
				}
				break;
			}
			if(specialRecords!=null){
				JSONArray usersJson = JSONArray.fromObject(specialRecords);
				
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
