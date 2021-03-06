

//package realtimestock;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;    
import javax.servlet.http.*;  
import java.sql.*;

public class RealTimeStock {

	private final String USER_AGENT = "Chrome/41.0.2228.0";
        
        

	public static void main(String[] args) throws Exception {
            
            
            // JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost:3306/realtimevts";

		//  Database credentials
		final String USER = "root";
		final String PASS = "muthu";

		Statement stmt = null;
		Connection conn = null;   

         while(true)
        {
		 RealTimeStock http = new RealTimeStock();
      
		//System.out.println("Testing - Send Http GET request");
		http.sendGet();
                
                float[] price = new float[100];
                String[] ticker= new String[100];
         try {

	File fXmlFile = new File("quotes.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);

	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("quote");

	//System.out.println("----------------------------");

        int i=0;
	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);

		//System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			
	/*		System.out.println("Name company: " + eElement.getElementsByTagName("Name").item(0).getTextContent());
			System.out.println("Ticker : " + eElement.getElementsByTagName("Symbol").item(0).getTextContent());
			System.out.println("Price :$ " + eElement.getElementsByTagName("LastTradePriceOnly").item(0).getTextContent());
			
	*/
    
     	String x= eElement.getElementsByTagName("LastTradePriceOnly").item(0).getTextContent();
                price[i]= Float.parseFloat(x);
                ticker[i]=eElement.getElementsByTagName("Symbol").item(0).getTextContent();
                // System.out.println("contents of array= "+ price[i] + "  " + ticker[i]);
                i++;
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
       
         float[] newPrice=new float[100];
         newPrice=price;
         
	 //  http.refreshDatabase(price,ticker,15);
         
         int n=15;
         
    try {    
             // Register JDBC driver
	  Class.forName("com.mysql.jdbc.Driver");

	     // Open a connection
	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	       stmt = conn.createStatement();
		        String sql;
			
		        PreparedStatement ps;
                        ResultSet rs;	 
        
        
        int i=0;
			 
			while(i<n)
	{       ps=conn.prepareStatement("update stock set price=? where ticker=?");
				ps.setFloat(1,newPrice[i]);
				ps.setString(2,ticker[i]);
				ps.executeUpdate();
				ps.close();
				i++;
			}
		
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
		                while(i<user_count && !found)
				{
				if(user[i].equals(rs.getString("uname")))
				{  int quantity=rs.getInt("quantity");
			       float p= rs.getFloat("price");
			       newPvalue[i]+=(quantity*p);
				   found=true;
				}
				i++; 
				}
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
         
        
         Thread.sleep(10000);
         
         
	
   }

         
	 
                        
	}
		
		
        
	// HTTP GET request
	private void sendGet() throws Exception {

    //String url="http://finance.google.com/finance/info?client=ig&q=NASDAQ:GOOG";
    //String url = "http://finance.yahoo.com/d/quotes.csv?s=AMZN+FB+NFLX+GOOG+MSFT+NVDA+BBRY+ADBE+AAPL+SYMC+CSCO+INTC+ZNGA+FOXA+AMAT&f=h&e=.csv";
        
    String url = "https://query.yahooapis.com/v1/public/yql?q=select%20Name%2CSymbol%2CLastTradePriceOnly%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22AMZN%22%2C%22FB%22%2C%22NFLX%22%2C%22GOOG%22%2C%22MSFT%22%2C%22NVDA%22%2C%22BBRY%22%2C%22ADBE%22%2C%22AAPL%22%2C%22SYMC%22%2C%22CSCO%22%2C%22INTC%22%2C%22ZNGA%22%2C%22FOXA%22%2C%22AMAT%22)&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		//in.close();

		//print result
                String result=response.toString();
                        
                        try{
            PrintWriter writer = new PrintWriter("quotes.xml", "UTF-8");
             writer.println(result);
             writer.close();
             } catch (Exception e) {
                   e.printStackTrace();
             }
		//System.out.println("success!");

	}
        
 
}

