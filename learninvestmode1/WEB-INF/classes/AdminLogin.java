import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;

public class AdminLogin extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
		
		String username = request.getParameter("uname");  
        String password = request.getParameter("password");  
		
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  

		
				if(username.equals("admin") && password.equals("docs2016")) 
			  { RequestDispatcher rd = request.getRequestDispatcher("admin-dash.jsp");
                HttpSession session=request.getSession();				
				session.setAttribute("admin-uname", username);
				session.setAttribute("admin-password",password);
				rd.forward(request, response);
				
				}
			    else 
			  {
				RequestDispatcher rd = request.getRequestDispatcher("admin-login-failure.jsp");  
				rd.forward(request, response);  
			     }
			
			
    }  
 /*
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        doPost(req, resp);  
    }    */
}