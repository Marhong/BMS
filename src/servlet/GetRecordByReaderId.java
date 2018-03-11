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
import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetRecordByReaderId
 */
@WebServlet("/GetRecordByReaderId")
public class GetRecordByReaderId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRecordByReaderId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reader_id = request.getParameter("reader_id");
		DBManager manager = new DBManager();
		ArrayList<BorrowRecord> records = new ArrayList<>();
		ArrayList<BorrowRecord> specialRecords = new ArrayList<>();
		try {
			records=manager.getBorrowingRecords();
			
			if(records!=null){
				for(BorrowRecord record: records){
					if(record.getReader_id().equals(reader_id) && record.getIsReturn() == 0){
						specialRecords.add(record);
					}
				}
				
				JSONArray usersJson = JSONArray.fromObject(specialRecords);
				
				response.getWriter().print(usersJson);

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
