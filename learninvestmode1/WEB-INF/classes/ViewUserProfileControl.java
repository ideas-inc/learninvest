import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;    
import javax.servlet.http.*;  
import java.sql.*;

public class ViewUserProfileControl extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost:3306/vts";
		
		//  Database credentials
		final String USER = "root";
		final String PASS = "muthu";

		Statement stmt = null;
		Connection conn = null;
		
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();
       
        String username = request.getParameter("uname"); 

        out.println("<html><body>  <p> Welcome to stocks, "+ username + "</p></body></html>");		
		
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute SQL query
			stmt = conn.createStatement();
			String sql;
			sql = "select * from user";
			ResultSet rs =  stmt.executeQuery(sql);
			boolean found = false;
			String fname=null,lname=null,pwd=null,email=null,uname=null;
			float cash=0,pvalue=0;
			while(rs.next() && !found) {
				String un = rs.getString("uname");
				if(username.equals(un)) {
					found = true;
				uname=un;
				fname=rs.getString("first");  lname=rs.getString("last");
				pwd=rs.getString("password"); email=rs.getString("email");
                cash=rs.getFloat("cash"); pvalue=rs.getFloat("pvalue");                 				
				}
			}
		
	/*RequestDispatcher rd = request.getRequestDispatcher("view-profile.jsp");
	        request.setAttribute("uname", username);
	        request.setAttribute("first", fname);
	        request.setAttribute("last", lname);
	        request.setAttribute("password", pwd);
	        request.setAttribute("email", email);
	        request.setAttribute("cash", cash);
	        request.setAttribute("pvalue", pvalue);
			
			rd.forward(request, response);*/
			
			
			rs.close();
			stmt.close();
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
		 