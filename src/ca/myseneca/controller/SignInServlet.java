package ca.myseneca.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.myseneca.model.User;
import ca.myseneca.model.UserDB;

@WebServlet("/SIS")
public class SignInServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
     	// get request parameters for email and password
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");

        // store data in User object
        User user = new User();
        user.setEmail(email);
        user.setPassword(pwd);

        // check the email and password from database
        boolean pass = UserDB.CheckAdmin(user);
        
        boolean found = UserDB.CheckUser(user);
        
        if(found){
        	HttpSession session = request.getSession();
            session.setAttribute("user", user.getEmail());
            System.out.println("loggin in userId: " + user.getEmail());
            
            response.sendRedirect("user/takeQuiz.jsp");
        } else {

        if (pass) {
        	HttpSession session = request.getSession();
            session.setAttribute("admin", user.getEmail());
            if (request.getParameter("from")!=null && !request.getParameter("from").isEmpty()) {
            	response.sendRedirect(request.getParameter("from"));
            }
            else {
            	response.sendRedirect("index.jsp");
            }
            
        } else {
        	//RequestDispatcher rd = getServletContext().getRequestDispatcher("login.jsp");
        	request.setAttribute("error", "Incorrect Email and/or Password");
        	request.getRequestDispatcher("login.jsp").forward(request, response);
            /*PrintWriter out= response.getWriter();
            out.println("<p style=\"color:red;\">Either email or password is wrong. "
            		+ "Please try again.</p>");*/
            //rd.include(request, response);
            //rd.forward(request, response);
        }
        }
	}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
    		throws ServletException, IOException {
        doPost(request, response);
    } 
}
