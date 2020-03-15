package Register;

import java.io.File;
import java.io.IOException;

import model.User;
import model.User_Utils;

import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.Connection_Dist;
import java.math.*;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     
    public Register() {
        super();
    }
*/	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rdip;
		Connection_Dist conn_d = new Connection_Dist();
		
		User newuser = new User();
		User_Utils u_u = new User_Utils();
		
		newuser.setEmail(request.getParameter("email"));
		newuser.setName(request.getParameter("name"));
		newuser.setSurname(request.getParameter("surname"));
		newuser.setPassword(request.getParameter("password"));
		newuser.setTelnumber(new BigInteger(request.getParameter("number")));
		newuser.setType("user");
		if(request.getParameter("image") == null)
		{
			newuser.setImage("Green Space Invader.png");
		}
		else
		{
			String filepath = "/home/vassilis/eclipse-workspace/LinkedIn/WebContent/images"+ File.separator + request.getAttribute("image");
			newuser.setImage(filepath);				
		}
		
		if(Objects.equals(request.getParameter("password"),request.getParameter("rpassword")))
		{
			if(!(u_u.Check_Name_Surname(conn_d,newuser.getName(),newuser.getSurname())) && !(u_u.Check_Email(conn_d,newuser.getEmail())))
			{	
				u_u.NewUser_Insert(conn_d,newuser);

				newuser.setIdusers(u_u.Get_User_Id(conn_d,newuser.getEmail()));
				
				conn_d.Close_Connection();
			
				HttpSession session = request.getSession();
				session.setAttribute("user",newuser);
				
				rdip = request.getRequestDispatcher("UserPage.jsp");
				rdip.forward(request, response);
			}

		}
		conn_d.Close_Connection();
		
		rdip = request.getRequestDispatcher("SignUpPage.html");
		rdip.forward(request, response);
		
	}

}
