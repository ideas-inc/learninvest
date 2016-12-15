import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;    
import javax.servlet.http.*;  
import java.sql.*;

public class DeleteUserAccount extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost:3306/realtimevts";

		//  Database credentials
		final String USER = "root";
		final String PASS = "muthu";

		Statement stmt = null;
		Connection conn = null;
		
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
		
		HttpSession session=request.getSession();
		String username= (String)session.getAttribute("uname");
	
	
	try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			PreparedStatement ps;
			
           ps=conn.prepareStatement("delete from userstock where uname=?");
           ps.setString(1, username);
		   ps.executeUpdate();
		   ps.close();
		   
		   ps=conn.prepareStatement("delete from user where uname=?");
           ps.setString(1, username);
		   ps.executeUpdate();
		   ps.close();
				
		  RequestDispatcher rd = request.getRequestDispatcher("deleted.jsp");
	           
		   rd.forward(request, response); 
				
			conn.close();
		}
        catch(SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		} 
		catch(Exception e) {
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally {
			//finally block used to close resources
			try {
				if(stmt!=null) {
				   stmt.close();
				}
			}
			catch(SQLException se2) {
				//nothing we can do 
			}
			try {
				if(conn!=null) {
					conn.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			//end finally try
		} 
    }  
    //Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        doPost(req, resp);  
    }  
}