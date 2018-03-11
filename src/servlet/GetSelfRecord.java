package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBManager;
import model.Book;
import model.BorrowRecord;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetBorrowingRecord
 */
@WebServlet("/GetSelfRecord")
public class GetSelfRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSelfRecord() {
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
		String id = "";
		 try {  
            
	            response.setContentType("text/html;charset=utf-8");  
	          
	            //�ӿͻ��˵õ�����cookie��Ϣ  
	            Cookie [] allCookies=request.getCookies();  
	          
	            int i=0;  
	            //���allCookies��Ϊ��...  
	            if(allCookies!=null){  
	              
	                //����ȡ��cookie  
	                for(i=0;i<allCookies.length;i++){  
	                  
	                    //����ȡ��  
	                    Cookie temp=allCookies[i];  
	                  
	                    if(temp.getName().equals("id")){  
	                          
	                        //�õ�cookie��ֵ  
	                        id=temp.getValue();  
	                        System.out.println(id); 
	                        break;  
	                          
	                    }  
	                }  
	                          
	            }    
	          
	        }  
	        catch (Exception ex) {  
	              
	            ex.printStackTrace();  
	        }
		try {
			records=manager.getBorrowingRecordsByUser(id);
			if(records!=null){
				JSONArray usersJson = JSONArray.fromObject(records);
				
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
