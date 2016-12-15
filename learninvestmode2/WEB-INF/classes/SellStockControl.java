import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;    
import javax.servlet.http.*;  
import java.sql.*;

public class SellStockControl extends HttpServlet {  
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
		String tickerChosen = request.getParameter("ticker");   // chosen from drop down menu
        String qty = request.getParameter("quantity");  
		int quantity=Integer.parseInt(qty);  // quantity must be within a limit set type="range"
		float price=0,cash=0;
		
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute SQL query
			stmt = conn.createStatement();
			String sql;
            sql="select uname,ticker,quantity from userstock";
			ResultSet rs =  stmt.executeQuery(sql);
			PreparedStatement ps; 
			
			boolean found = false;  int initial_qty=0;
			while(rs.next() && !found)
			{
				String ticker = rs.getString("ticker");
				String uname = rs.getString("uname");
				if(tickerChosen.equals(ticker) && username.equals(uname)) 
				{  
						found=true;
						initial_qty=rs.getInt("quantity");
				}
			}
			
			if(found == true) {
				 
				if(quantity<=initial_qty)
				{  
	  sql="select uname,cash from user";
		rs = stmt.executeQuery(sql);
		    boolean found1=false,found2=false;
			while(rs.next() && !found1)
			{  String uname = rs.getString("uname");
		       if(username.equals(uname))
			   {     found1=true;
                 cash=rs.getFloat("cash");   }				   
			}
			
      sql="select ticker,price from stock";
		rs =  stmt.executeQuery(sql);
		       while(rs.next() && !found2)
			{  String ticker = rs.getString("ticker");
		       if(tickerChosen.equals(ticker))
			   {     found2=true;
                 price=rs.getFloat("price");   }				   
			}
			    
				if(found1 && found2)
			{      cash+=(quantity*price);
			       initial_qty-=quantity;
ps=conn.prepareStatement("update user set cash=? where uname=?");
				ps.setFloat(1, cash);
				ps.setString(2, username);
				ps.executeUpdate();
				ps.close();
				
				if(initial_qty!=0){
ps=conn.prepareStatement("update userstock set quantity=? where uname=? and ticker=?");
				
				ps.setInt(1, initial_qty);
				ps.setString(2, username);
				ps.setString(3, tickerChosen);
				ps.executeUpdate();
				ps.close();
				}
				else  // quantity = 0
				{
ps=conn.prepareStatement("delete from userstock where uname=? and ticker=?");
            
				ps.setString(1, username);
				ps.setString(2, tickerChosen);
				ps.executeUpdate();
				ps.close();
					
				}
				
			RequestDispatcher rd = request.getRequestDispatcher("sell-success.jsp");
	            request.setAttribute("uname", username);
				rd.forward(request, response);  
			}		
				}
				else
				{  RequestDispatcher rd = request.getRequestDispatcher("sell-failure.jsp");  // insufficient quantity
				   rd.forward(request, response);  
				}
				
			}
			else {
		         RequestDispatcher rd = request.getRequestDispatcher("sell-failure.jsp");   // user doesnt have that stock at all
				       rd.forward(request, response); 
				}
			
			rs.close();
			stmt.close();
			conn.close();
			// out.close(); ?
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