
import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginControl extends HttpServlet {  

  public static String md5(String input) {
	// MD5 HASHING ALGORITHM USED
	
		String md5 = null;
		
		if(null == input) return null;
		
		try {
			
		//Create MessageDigest object for MD5
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		//Update input string in message digest
		digest.update(input.getBytes(), 0, input.length());

		//Converts message digest value in base 16 (hex) 
		md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return md5;
	}
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost:3306/vts";

		//  Database credentials
		final String USER = "root";
		final String PASS = "muthu";

		Statement stmt = null;
		Connection conn = null;
		
		String username = request.getParameter("uname");  
        String password = md5(request.getParameter("password")+"#random+!digital-ocean-cs16#");  
		 
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  

		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute SQL query
			stmt = conn.createStatement();
			String sql;
			sql = "select uname,password from user";
			ResultSet rs = stmt.executeQuery(sql);
			boolean found = false;
			while(rs.next() && !found) {
				String uname = rs.getString("uname");
				String pwd = rs.getString("password");
				if(username.equals(uname) && password.equals(pwd)) {
					found = true;
				}
			}
			if(found == true) {
				RequestDispatcher rd = request.getRequestDispatcher("login-success.jsp");
                HttpSession session=request.getSession();				
				session.setAttribute("uname", username);
				session.setAttribute("password",password);
				rd.forward(request, response);  
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp");  
				rd.forward(request, response);  
			}
			
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
 /*
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        doPost(req, resp);  
    }    */
}