import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;    
import javax.servlet.http.*;  
import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AddStock extends HttpServlet { 

	
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
		
		
		String company = request.getParameter("company"); 
		String ticker = request.getParameter("ticker"); 
		ticker = ticker.toUpperCase();
		float price  = Float.parseFloat(request.getParameter("price")); 
		
        float pc = Float.parseFloat(request.getParameter("pc"));  
		
        float high = Float.parseFloat(request.getParameter("high"));  
		float low = Float.parseFloat(request.getParameter("low")); 
		
		int tradevol = Integer.parseInt(request.getParameter("tradevol"));
		
		String sector = request.getParameter("sector"); 
		
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute SQL query
			stmt = conn.createStatement();
			String sql;
			sql = "select ticker from stock";
			ResultSet rs =  stmt.executeQuery(sql);
			boolean found = false;
			while(rs.next() && !found) {
				
				if(ticker.equals(rs.getString("ticker"))) {
					found = true;
				}
			}
			if(found == true) {
				RequestDispatcher rd = request.getRequestDispatcher("addstock-failure.jsp");  
				rd.forward(request, response);  
				
			}
			else {
				
				PreparedStatement ps; 
ps=conn.prepareStatement("insert into stock values (?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, company);
				ps.setString(2, ticker);
				ps.setFloat(3, price);
				ps.setFloat(4, pc);
				ps.setFloat(5, high);
				ps.setFloat(6, low);
				ps.setInt(7, tradevol);
				ps.setString(8, sector);
				
				
				ps.executeUpdate();
				RequestDispatcher rd = request.getRequestDispatcher("addstock-success.jsp");				
				rd.forward(request, response);  
				ps.close();
				//out.println("<html><body><p> inserted... </p></body></html>");
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
 /*   //Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        doPost(req, resp);  
    }  */
}