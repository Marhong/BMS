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
import model.User;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetMyselfServlet
 */
@WebServlet("/GetMyselfServlet")
public class GetMyselfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMyselfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBManager manager = new DBManager();
		ArrayList<User> users = new ArrayList<>();
		String id = "";
		 try {  
             
	            response.setContentType("text/html;charset=utf-8");  
	          
	            //从客户端得到所有cookie信息  
	            Cookie [] allCookies=request.getCookies();  
	          
	            int i=0;  
	            //如果allCookies不为空...  
	            if(allCookies!=null){  
	              
	                //从中取出cookie  
	                for(i=0;i<allCookies.length;i++){  
	                  
	                    //依次取出  
	                    Cookie temp=allCookies[i];  
	                  
	                    if(temp.getName().equals("id")){  
	                          
	                        //得到cookie的值  
	                        id=temp.getValue();  
	                        break;  
	                          
	                    }  
	                }  
	                          
	            }    
	          
	        }  
	        catch (Exception ex) {  
	              
	            ex.printStackTrace();  
	        }  
		try {
			User user = manager.getUserById(id);
			users.add(user);
			if(users!=null){
				JSONArray usersJson = JSONArray.fromObject(users);
				
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
