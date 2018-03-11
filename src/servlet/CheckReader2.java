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
import model.User;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CheckReader2
 */
@WebServlet("/CheckReader2")
public class CheckReader2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckReader2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean exist = false;
		String reader_id = request.getParameter("reader_id");
	    ArrayList<BorrowRecord> records = new ArrayList<>();
		DBManager manager = new DBManager();
		try {
			records = manager.getBorrowingRecords();
			for(BorrowRecord record: records){
				if(record.getReader_id().equals(reader_id) && record.getIsReturn() == 0){
					exist = true;
				}
			}
			User user = manager.getUserById(reader_id);
			if(user == null){
				response.getWriter().print("1");
			}else{
				if(!exist){
					response.getWriter().print("2");
				}else{
					response.getWriter().print("3");
				}
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
