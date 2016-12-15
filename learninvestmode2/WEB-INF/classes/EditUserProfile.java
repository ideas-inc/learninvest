import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class EditUserProfile extends HttpServlet { 

   public static String md5(String input) 
   {
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
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{  
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
		
		
		String fname = request.getParameter("first"); 
		String lname = request.getParameter("last"); 
		//String newUname = request.getParameter("uname");  
        String password = request.getParameter("password"); 
        String repassword = request.getParameter("repassword");  	
		String email = request.getParameter("email");  
		String mobile=request.getParameter("mobile");  
		
	if(password.equals(repassword) && mobile.length()==10 && password.length()>=8)
	{
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute SQL query
			
			//stmt = conn.createStatement();
			//String sql;
		PreparedStatement ps; 
	password=password + "#random+!digital-ocean-cs16#";
ps=conn.prepareStatement("update user set first=?,last=?,email=?,mobile=?,password=? where uname=?");
				
				 //ps.setString(1, newUname);
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, email);
				ps.setString(4, mobile);
				ps.setString(5, md5(password));
				ps.setString(6, username);
				ps.executeUpdate();
				ps.close();

/*				
ps=conn.prepareStatement("update userstock set uname=? where uname=?");
				
				ps.setString(1, newUname);
				ps.setString(2, username);
				ps.executeUpdate();
				ps.close();
				*/
		
		RequestDispatcher rd = request.getRequestDispatcher("edit-success.jsp");
               				
				session.removeAttribute("password");
				  //session.setAttribute("uname", newUname);
				session.setAttribute("password",password);				
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
	else
	{  RequestDispatcher rd = request.getRequestDispatcher("edit-invalid.jsp");  
	   rd.forward(request, response);  
	}
	}
  /*  //Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        doPost(req, resp);  
    }  */
}