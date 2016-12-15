import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;    
import javax.servlet.http.*;  
import java.sql.*;

public class SendMessage extends HttpServlet {  
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
		
		String info = request.getParameter("info"); 
		if(info.equals(""))
		{
		RequestDispatcher rd = request.getRequestDispatcher("empty-message-error.jsp");  
				rd.forward(request, response);  	
		}
		else
		{
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			String sql="select count(*) totalCount from adminmessage";
			ResultSet rs =  stmt.executeQuery(sql);
			int count=0;
			
			while(rs.next()) {
			 count = rs.getInt("totalCount");
			}
	            count++;
				PreparedStatement ps; 
ps=conn.prepareStatement("insert into adminmessage values (?,?)");
                ps.setInt(1, count);
				ps.setString(2, info);
				
				ps.executeUpdate();
				RequestDispatcher rd = request.getRequestDispatcher("message-sent.jsp");
             			
				rd.forward(request, response);  
				ps.close();
				//out.println("<html><body><p> inserted... </p></body></html>");
			
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
		
    }  
 /*   //Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        doPost(req, resp);  
    }  */
}