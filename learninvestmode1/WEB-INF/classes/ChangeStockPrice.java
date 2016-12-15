import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;    
import javax.servlet.http.*;  
import java.sql.*;
import java.util.Random;

public class ChangeStockPrice extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
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

        String tickerChosen = request.getParameter("ticker");  // chosen from drop down menu
        String nP = request.getParameter("newPrice"); 
        float chosenPrice=Float.parseFloat(nP);  		
		
		
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute SQL query
			stmt = conn.createStatement();
			String sql;
			
	       sql="select * from stock";		
			ResultSet rs =  stmt.executeQuery(sql);
			PreparedStatement ps; 
			
		     int i=0;
			 float[] newPrice=new float[100];
			 String[] ticker=new String[100];
			 
			while(rs.next())
			{
				float price= rs.getFloat("price");
				String tkr=rs.getString("ticker");
				if(tkr.equals(tickerChosen))
				{ 
					newPrice[i]=chosenPrice;
				}
				else{ // no changes
				newPrice[i]=price; 
				}
				ticker[i]=rs.getString("ticker");
	             i++;
			}
			
			int n=i;  i=0;
			while(i<n)
			{  ps=conn.prepareStatement("update stock set price=? where ticker=?");
				ps.setFloat(1,newPrice[i]);
				ps.setString(2,ticker[i]);
				ps.executeUpdate();
				ps.close();
				i++;
			}
			
			rs.close();
			//stmt.close();
			//stmt = conn.createStatement();
			
			String[] user=new String[200];
			float[] newPvalue=new float[200];
			sql="select * from user";
			rs =  stmt.executeQuery(sql);
			i=0;
			while(rs.next())
			{
				user[i]= rs.getString("uname");
				newPvalue[i]=rs.getFloat("cash");
				i++;
			}
			 int user_count=i;
			 rs.close();
			 
			 sql="select uname,stock.ticker,price,quantity from userstock,stock where stock.ticker=userstock.ticker";
			 rs =  stmt.executeQuery(sql);
			 i=0; boolean found=false;
			
			while(rs.next())
			{   i=0;  found=false;
		        while(i<user_count && !found){
				if(user[i].equals(rs.getString("uname")))
				{  int quantity=rs.getInt("quantity");
			       float price= rs.getFloat("price");
			       newPvalue[i]+=(quantity*price);
				   found=true;
				}
				i++; }
			}
			
			i=0;
			while(i<user_count)
			{  ps=conn.prepareStatement("update user set pvalue=? where uname=?");
				ps.setFloat(1,newPvalue[i]);
				ps.setString(2,user[i]);
				ps.executeUpdate();
				ps.close();
				i++;
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("changestockprice-success.jsp");
	        rd.forward(request, response); 
			
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
