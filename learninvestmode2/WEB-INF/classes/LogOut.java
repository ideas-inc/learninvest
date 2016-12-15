import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.*;    
import javax.servlet.http.*;  


public class LogOut extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
              
            //request.getRequestDispatcher("link.html").include(request, response);  
              
            HttpSession session=request.getSession(); 
            session.removeAttribute("uname");			
			session.removeAttribute("password");
            session.invalidate();  
			
			RequestDispatcher rd=request.getRequestDispatcher("logoutsuccess.jsp"); 
			rd.forward(request, response);  
			
			}
    //Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        doPost(req, resp);  
    }  			
}  